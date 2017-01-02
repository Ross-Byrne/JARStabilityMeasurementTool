package ie.gmit.sw;

import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import java.util.*;
import java.util.jar.*;

/**
 * Created by Ross Byrne on 02/01/17.
 * Calculates the basic metric, stability
 */

public class BasicMetricCalculator {

    private HashMap<String, BasicMetric> classMetrics = new HashMap<>();
    private String jarPathName;

    public BasicMetricCalculator(String pathname){

        // save the jar pathname
        this.jarPathName = pathname;

        // add the classes in jar to map
        addClassNamesToMap();

        // calculate the metrics for classes in map
        calculateBasicMetric();

    } // constructor

    public void addClassNamesToMap(){

        int i = 0;

        try {

            // get handle on jar file
            File file  = new File(jarPathName);

            // create inputStream for jar
            JarInputStream in = new JarInputStream(new FileInputStream(file));

            // get jar entry
            JarEntry next = in.getNextJarEntry();

            // loop through all jar entries
            while (next != null) {

                // if the file in jar is a class
                if (next.getName().endsWith(".class")) {

                    // format the class name
                    String name = next.getName().replaceAll("/", "\\.");
                    name = name.replaceAll(".class", "");
                    if (!name.contains("$")) name.substring(0, name.length() - ".class".length());
                    //System.out.println("Class Name: " + name); // print out name

                    // add class name to map with empty metric object
                    classMetrics.put(name, new BasicMetric());

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

    public void calculateBasicMetric(){

        try {

            // get handle on jar file
            File file = new File(jarPathName);

            // create a url to file
            URL url = file.toURI().toURL();
            URL[] urls = new URL[]{url};

            // create a ClassLoader to load classes from the JAR file
            ClassLoader cl = new URLClassLoader(urls);

            // loop for each key in the classMetrics map
            for (String className : classMetrics.keySet()) {

                //System.out.println(className);

                // get handle on class, using the class loader, not intialising the class
                Class cls = Class.forName(className, false, cl);

                //printClassDetails(cls);

            } // foreach
        } catch (Exception e){

            e.printStackTrace();
        }

    } // calculateBasicMetric()


    public void printClassDetails(Class cls){

        Package pack = cls.getPackage(); //Get the package
        System.out.println("Package Name: " + pack.getName());

        boolean iface = cls.isInterface(); //Is it an interface?
        System.out.println("Is Class an Interface?: " + iface);

        Class[] interfaces = cls.getInterfaces(); //Get the set of interface it implements
        // for each interface, print name
        for(Class i : interfaces){

            System.out.println("Implements Interface: " + i.getName());
        }

        Constructor[] cons = cls.getConstructors(); //Get the set of constructors
        Class[] constructorParams;

        // for each constructor, get it's parameters
        for(Constructor c : cons){

            System.out.println("Contructor: " + c.getName());
            constructorParams = c.getParameterTypes(); //Get the parameters
            for(Class param : constructorParams){

                System.out.println("Constructor Param: " + param.getName());
            }
        }

        Field[] fields = cls.getFields(); //Get the fields / attributes

        for(Field f : fields){

            System.out.println("Field: " + f.getName());
        }

        Method[] methods = cls.getMethods(); //Get the set of methods
        Class[] methodParams;

        // for each method, print its return type
        for(Method m : methods){

            System.out.println("Method: " + m.getName());

            Class methodReturnType = m.getReturnType(); //Get a method return type
            System.out.println("Method Return Type: " + methodReturnType.getName());

            methodParams = m.getParameterTypes(); //Get method parameters
            for(Class mp : methodParams){

                System.out.println("Method Param: " + mp.getName());
            }
        }


    } // printClassDetails()

} // class
