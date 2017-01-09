package ie.gmit.sw;

import java.util.*;

/**
 * Created by Ross Byrne on 09/01/17.
 */
public class Metric {

    private Class cls;
    private String className;
    private Set<Class> adjacentClasses = new HashSet<>();
    private int outDegree;
    private int inDegree;


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

    public void addClass(Class cls) {

        adjacentClasses.add(cls);
    }

    public Class getTheClass() {
        return cls;
    }

    public void setTheClass(Class cls) {
        this.cls = cls;
    }

    public int getOutDegree() {
        return outDegree;
    }

    public void setOutDegree(int outDegree) {
        this.outDegree = outDegree;
    }

    public int getInDegree() {
        return inDegree;
    }

    public void setInDegree(int inDegree) {
        this.inDegree = inDegree;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
} // class
