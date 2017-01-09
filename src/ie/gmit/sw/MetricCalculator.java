package ie.gmit.sw;

import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import java.util.*;
import java.util.jar.*;

/**
 * Created by Ross Byrne on 06/01/17.
 * Calculates the stability metric. Also stores the classes in
 * an adjacency list.
 */
public class MetricCalculator {

    private HashMap<Class, Metric> classMetrics = new HashMap<>();
    private String jarPathName;

    /**
     * Constructor for the class. Calculates stability for selected jar
     *
     * @param pathname
     * The file path to a .jar file as a String.
     */
    public MetricCalculator(String pathname){

        // save the jar pathname
        this.jarPathName = pathname;

        // add the classes in jar to map
        addClassNamesToMap();

        // calculate the metrics for classes in map
        calculateBasicMetric();

        // print out outdegree for classes
//        for(BasicMetric m : classMetrics.values()){
//
//            // print out outdegrees of classes
//            System.out.printf("\nOutdegree: %d. Indegree: %d. Stability: %.2f. Class: %s", m.getOutDegree(), m.getInDegree(), m.getStability(), m.getClassName());
//        }

    } // constructor

    /**
     * Gets the metrics in the format of a 2 dimensional array.
     * This is for updating the model of the summary dialog table.
     *
     * @return
     * Returns the metrics in a 2 dimensional array, for the format required for the table of a swing component.
     */
    public Object[][] getMetricData(){

        int i = 0;
        Object[][] data = new Object[classMetrics.size()][4];

        // order of data for cols
        // cols = {"Class", "Stability", "Out Degree", "In Degree"};

        // for each metric object in the map
        for(Metric m : classMetrics.values()){

            // add data to the 2d array
            data[i][0] = m.getClassName();  // set class name
            data[i][1] = m.getStability();  // set stability
            data[i][2] = m.getOutDegree();  // set outDegree
            data[i][3] = m.getInDegree();   // set inDegree

            // increment counter
            i++;
        } // for

        return data;

    } // getMetricData()

    /**
     * Addeds the class to the map with the metrics
     */
    private void addClassNamesToMap(){

        int i = 0;

        try {

            // get handle on jar file
            File file  = new File(jarPathName);

            // create inputStream for jar
            JarInputStream in = new JarInputStream(new FileInputStream(file));

            // get jar entry
            JarEntry next = in.getNextJarEntry();

            URL url = file.toURI().toURL();
            URL[] urls = new URL[]{url};

            ClassLoader cl = new URLClassLoader(urls);

            // loop through all jar entries
            while (next != null) {

                // if the file in jar is a class
                if (next.getName().endsWith(".class")) {

                    // format the class name
                    String name = next.getName().replaceAll("/", "\\.");
                    name = name.replaceAll(".class", "");
                    if (!name.contains("$")) name.substring(0, name.length() - ".class".length());
                    //System.out.println("Class Name: " + name); // print out name

                    // get handle on class, using the class loader, not initialising the class
                    Class cls = Class.forName(name, false, cl);

                    // add class name to map with empty metric object
                    classMetrics.put(cls, new Metric());

                    // set the class name for metric
                    classMetrics.get(cls).setClassName(name);

                    i++; // count the number of classes being loaded

                } // if

                // get next entry
                next = in.getNextJarEntry();

            } // while

            System.out.println(i + " classes loaded.");
            System.out.println("map size: " + classMetrics.size());

        } catch (Exception e){

            e.printStackTrace();
        } // try catch

    } // addClassNamesToMap()

    /**
     * Handles the calculation of in and our degrees for each class
     */
    private void calculateBasicMetric(){

        try {

            // get handle on jar file
            File file = new File(jarPathName);

            // create a url to file
            URL url = file.toURI().toURL();
            URL[] urls = new URL[]{url};

            // create a ClassLoader to load classes from the JAR file
            ClassLoader cl = new URLClassLoader(urls);

            // loop for each key in the classMetrics map
            for (Class cls : classMetrics.keySet()) {

                //System.out.println(className);

                // get handle on class, using the class loader, not intialising the class
               // Class cls = Class.forName(className, false, cl);

                // analyse class to calculate in and out degree
                analyseClass(cls);

            } // foreach
        } catch (Exception e){

            e.printStackTrace();
        }

    } // calculateBasicMetric()

    /**
     * analyses a class to calculate its in and out degree
     * a reference to another class increments the classes out degree
     * and increments the in degree of class being referenced
     *
     * @param cls
     * The class being analysed.
     */
    private void analyseClass(Class cls){

        int outdegree = 0;

        //Package pack = cls.getPackage(); //Get the package
        //System.out.println("Package Name: " + pack.getName());

        boolean iface = cls.isInterface(); //Is it an interface?
        //System.out.println("Is Class an Interface?: " + iface);

        Class[] interfaces = cls.getInterfaces(); //Get the set of interface it implements
        // for each interface, print name
        for(Class i : interfaces){

            if(classMetrics.containsKey(i)) {

                // increment outdegree
                outdegree++;

                // increment indegree for interface
                Metric m = classMetrics.get(i);
                m.setInDegree(m.getInDegree() + 1);

                //System.out.println("Implements Interface: " + i.getName());

            } // if
            //System.out.println("Implements Interface: " + i.getName());

        } // foreach

        Constructor[] cons = cls.getConstructors(); //Get the set of constructors
        Class[] constructorParams;

        // for each constructor, get it's parameters
        for(Constructor c : cons){

            //System.out.println("Contructor: " + c.getName());
            constructorParams = c.getParameterTypes(); //Get the parameters
            for(Class param : constructorParams){

                if(classMetrics.containsKey(param)){

                    // increment outdegree
                    outdegree++;

                    // increment indegree for other class
                    Metric m = classMetrics.get(param);
                    m.setInDegree(m.getInDegree() + 1);

                } // if

                //System.out.println("Constructor Param: " + param.getName());
            } // foreach
        } // foreach

        Field[] fields = cls.getFields(); //Get the fields / attributes

        for(Field f : fields){

            //System.out.println("Field: " + f.getName());

            if(classMetrics.containsKey(f)){

                // increment outdegree
                outdegree++;

                // increment indegree for interface
                Metric m = classMetrics.get(f);
                m.setInDegree(m.getInDegree() + 1);

            } // if
        } // foreach

        Method[] methods = cls.getMethods(); //Get the set of methods
        Class[] methodParams;

        // for each method, print its return type
        for(Method m : methods){

            //System.out.println("Method: " + m.getName());

            Class methodReturnType = m.getReturnType(); //Get a method return type
            //System.out.println("Method Return Type: " + methodReturnType.getName());

            if(classMetrics.containsKey(methodReturnType)){

                // increment outdegree
                outdegree++;

                // increment indegree for interface
                Metric bm = classMetrics.get(methodReturnType);
                bm.setInDegree(bm.getInDegree() + 1);
            }

            methodParams = m.getParameterTypes(); //Get method parameters
            for(Class mp : methodParams){

                //System.out.println("Method Param: " + mp.getName());

                if(classMetrics.containsKey(mp)){

                    // increment outdegree
                    outdegree++;

                    // increment indegree for interface
                    Metric bm = classMetrics.get(mp);
                    bm.setInDegree(bm.getInDegree() + 1);

                } // if
            } // foreach
        } // foreach

        // System.out.println("outdegree: " + outdegree + ". Class: " + cls.getName());

        classMetrics.get(cls).setOutDegree(outdegree);

    } // analyseClass()


} // class
