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
     * Gets the outDegree for the class this metric represents.
     *
     * @return
     * Returns the outDegree as an int.
     */
    public int getOutDegree();

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
