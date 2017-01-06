package ie.gmit.sw;

/**
 * Created by Ross Byrne on 02/01/17.
 * A Class used for calculating the positional stability metric
 * This uses the very basic method of calculation with retaining a
 * class adjacency list
 */
public class BasicMetric {

    private int inDegree;
    private int outDegree;
    private String className;

    /**
     *Gets the name of the class that this metric represents.
     *
     * @return
     * Returns the name of the class as a String.
     */
    public String getClassName() {

        return className;
    }

    /**
     * Sets the name of the class that this metric represents.
     *
     * @param className
     * The name of the class as a String.
     */
    public void setClassName(String className) {

        this.className = className;
    }

    /**
     * Gets the indegree for the class this metric represents
     *
     * @return
     * Returns the the inDegree as an int.
     */
    public int getInDegree() {

        return inDegree;
    }

    /**
     * Sets the inDegree for the class this metric represents.
     *
     * @param inDegree
     * The in degree of the class, which is the number of classes that
     * depend on this class.
     */
    public void setInDegree(int inDegree) {

        this.inDegree = inDegree;
    }

    /**
     * Gets the outDegree for the class this metric represents.
     *
     * @return
     * Returns the outDegree as an int.
     */
    public int getOutDegree() {

        return outDegree;
    }

    /**
     * Sets the outDegree for the class this metric represents.
     *
     * @param outDegree
     * The out degree of the class, which is the number of
     * classes this class depends on.
     */
    public void setOutDegree(int outDegree) {

        this.outDegree = outDegree;
    }

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

} // class
