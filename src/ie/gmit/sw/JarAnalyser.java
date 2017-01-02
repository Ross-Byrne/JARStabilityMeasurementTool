package ie.gmit.sw;

import java.io.*;
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

            JarInputStream in = new JarInputStream(new FileInputStream(new File(pathname)));
            JarEntry next = in.getNextJarEntry();
            while (next != null) {
                if (next.getName().endsWith(".class")) {
                    String name = next.getName().replaceAll("/", "\\.");
                    name = name.replaceAll(".class", "");
                    if (!name.contains("$")) name.substring(0, name.length() - ".class".length());
                    System.out.println(name);
                }
                next = in.getNextJarEntry();
            }
        } catch (FileNotFoundException e){

            System.out.println("JAR File cannot be found!");
            e.printStackTrace();

        } catch (Exception e){

            e.printStackTrace();
        } // try catch
    } // init()

} // class
