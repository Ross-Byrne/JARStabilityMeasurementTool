package ie.gmit.sw;

import java.util.*;

/**
 * Created by Ross Byrne on 09/01/17.
 * Creates an adjacency list for each class read from the JAR.
 * Saves the adjacent classes to the class the metric appiles to.
 * Saves the dependant classes, the classes that depend on this class.
 */
public class Metric implements Metricable {

    // using a Set so dupes aren't possible (in case a class references another class loads of times)
    // adjacent classes, for adjacency list (class metric class is pointing at, ie. outDegree)
    private Set<Class> adjacentClasses = new HashSet<>();

    // a set of classes with a reference pointing at the class this metric applies to, ie inDegree
    private Set<Class> dependentClasses = new HashSet<>();

    private Class cls;                                      // the class the metric applies to
    private String className;                               // the name of the class as String
    private int outDegree;                                  // number of classes metric class is pointing at
    private int inDegree;                                   // number of classes pointing at metric class


    /**
     * Uses the inDegree and outDegree to calculate the stability.
     * If the outDegree is Zero, the calculation can't divide and
     * it returns the stability as Zero.
     *
     * @return
     * Returns the positional stability as a float
     */
    public float getStability(){

        float stability = 1f;

        // use try in case of divide by zero exception
        try {

            if(getOutDegree() > 0) { // if outDegree is greater then Zero

                // calculate stability
                stability = ((float) getOutDegree() / ((float) getInDegree() + (float) getOutDegree()));

            } else { // otherwise, can't divide by Zero

                // set stability to Zero
                stability = 0f;

            } // if

        } catch (Exception e){

            // in case of exception, set stability to Zero
            stability = 0f;

        } // try

        return stability;

    } // getStability()

    /**
     * Adds a class object to the list of adjacent classes.
     *
     * @param cls
     * The class that is adjacent to (outDegree) the class the metric applies to.
     */
    public void addAdjacentClass(Class cls) {

        adjacentClasses.add(cls);
    }

    /**
     * Adds the class to a set of classes that are depending on the class the metric applies to. ie inDegree.
     *
     * @param cls
     * The class that is depending on the class that the metric applies to.
     */
    public void addDependantClass(Class cls){

        dependentClasses.add(cls);
    }

    /**
     * Gets the list of classes that are adjacent to class the metric applies to,
     * in the format of a Set.
     *
     * @return
     * Returns the adjacent classes in a Set of Class objects.
     */
    public Set<Class> getAdjacentClasses(){

        Set<Class> classes = new HashSet<>(this.adjacentClasses);

        return classes;

    }

    /**
     * Gets the class object that the metric applies to.
     *
     * @return
     * Returns the class as a Class object.
     */
    public Class getTheClass() {
        return cls;
    }

    /**
     * Sets the class that the metric applies to.
     *
     * @param cls
     * The Class object that the metric applies to.
     */
    public void setTheClass(Class cls) {
        this.cls = cls;
    }

    /**
     * Gets the outDegree for the class this metric represents.
     *
     * @return
     * Returns the outDegree as an int.
     */
    public int getOutDegree() {

        // the outDegree is calculated by the number of classes added to the adjacentClasses set
        return adjacentClasses.size();
    }

    /**
     * Gets the indegree for the class this metric represents
     *
     * @return
     * Returns the the inDegree as an int.
     */
    public int getInDegree() {

        // the inDegree is calculated by the number of classes added to the dependantClasses set
        return dependentClasses.size();
    }

    /**
     * Gets the name of the class the metric applies to, as a String.
     *
     * @return
     * Returns the name of the class as a String.
     */
    public String getClassName() {

        return this.getTheClass().getName();
    }


} // class
