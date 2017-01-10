package ie.gmit.sw;

/**
 * Created by Ross Byrne on 10/01/17.
 * An interface for a class that can calculate a metric.
 */
public interface MetricCalculatorable {

    /**
     * Calculates stability for selected jar
     *
     * @param jarPathName
     * The file path to a .jar file as a String.
     */
    public void analyseJarFile(String jarPathName);

    /**
     * Gets the metrics in the format of a 2 dimensional array.
     * This is for updating the model of the summary dialog table.
     *
     * @return
     * Returns the metrics in a 2 dimensional array, for the format required for the table of a swing component.
     */
    public Object[][] getMetricData();

} // interface
