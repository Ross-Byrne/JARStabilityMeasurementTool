package ie.gmit.sw;

/**
 * Created by Ross Byrne on 10/01/17.
 * Enums for choosing which metric calculator to get
 * from the metric calculator factory.
 */
public enum CalculatorType {

    /**
     * Used to get an instance of BasicMetricCalculator from
     * the MetricCalculatorFactory.
     */
    BASIC,

    /**
     * Used to get an instance of MetricCalculator from
     * the MetricCalculatorFactory.
     */
    BETTER

} // enum
