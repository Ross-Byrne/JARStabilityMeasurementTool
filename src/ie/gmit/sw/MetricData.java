package ie.gmit.sw;

/**
 * Created by Ross Byrne on 10/01/17.
 * Data object, for saving the last calculated metrics in DB4O.
 */
public class MetricData {

    private Object[][] data;

    /**
     * Gets the metric data in 2 dimensional array format.
     *
     * @return
     * returns the metric data as a 2 dimensional object array.
     */
    public Object[][] getData() {
        return data;
    }

    /**
     * Sets the metric data.
     *
     * @param data
     * The metric data in 2 dimensional object array format.
     *
     */
    public void setData(Object[][] data) {
        this.data = data;
    }

} // class
