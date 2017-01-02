package ie.gmit.sw;

import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import java.util.jar.*;

/**
 * Created by Ross Byrne on 02/01/17.
 */

public class JarAnalyser {

    public JarAnalyser(String pathname){

        init(pathname);

    }

    public void init(String pathname){

        try {

            File file  = new File(pathname);

            URL url = file.toURI().toURL();
            URL[] urls = new URL[]{url};

            ClassLoader cl = new URLClassLoader(urls);

            JarInputStream in = new JarInputStream(new FileInputStream(new File(pathname)));
            JarEntry next = in.getNextJarEntry();

            while (next != null) {
                if (next.getName().endsWith(".class")) {
                    String name = next.getName().replaceAll("/", "\\.");
                    name = name.replaceAll(".class", "");
                    if (!name.contains("$")) name.substring(0, name.length() - ".class".length());
                    //System.out.println("Class Name: " + name); // same as below

                    // get handle on class, using the class loader
                    Class cls = Class.forName(name, false, cl);

                    System.out.println("Class Name: " + cls.getName());

                    // print its details
                    printClassDetails(cls);
                }
                next = in.getNextJarEntry();

            } // while


        } catch (FileNotFoundException e){

            System.out.println("JAR File cannot be found!");
            e.printStackTrace();

        } catch (Exception e){

            e.printStackTrace();
        } // try catch
    } // init()

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
