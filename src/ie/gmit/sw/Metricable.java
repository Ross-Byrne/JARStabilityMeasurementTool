package ie.gmit.sw;

/**
 * Created by Ross Byrne on 10/01/17.
 * Interface for Metric objects that calculate positional stability.
 */
public interface Metricable {

    /**
     * Gets the indegree for the class this metric represents
     *
     * @return
     * Returns the the inDegree as an int.
     */
    public int getInDegree();

    /**
     * Sets the inDegree for the class this metric represents.
     *
     * @param inDegree
     * The in degree of the class, which is the number of classes that
     * depend on this class.
     */
    public void setInDegree(int inDegree);

    /**
     * Gets the outDegree for the class this metric represents.
     *
     * @return
     * Returns the outDegree as an int.
     */
    public int getOutDegree();

    /**
     * Sets the outDegree for the class this metric represents.
     *
     * @param outDegree
     * The out degree of the class, which is the number of
     * classes this class depends on.
     */
    public void setOutDegree(int outDegree);

    /**
     * Uses the inDegree and outDegree to calculate the stability.
     * If the outDegree is Zero, the calculation can't divide and
     * it returns the stability as Zero.
     *
     * @return
     * Returns the positional stability as a float
     */
    public float getStability();

} // interface
