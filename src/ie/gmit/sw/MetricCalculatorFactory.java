package ie.gmit.sw;

/**
 * Created by Ross Byrne on 10/01/17.
 * A singleton factory for handling the instances of
 * the metric calculators.
 */
public class MetricCalculatorFactory {

    // private, instantiated factory object for singleton pattern
    private MetricCalculatorFactory factory = new MetricCalculatorFactory();

    // private constructor for singleton pattern, so no one can create another instance.
    private MetricCalculatorFactory(){}

    /**
     * Method for obtaining an instance of the singleton factory
     *
     * @return
     * Returns the instance of the singleton factory, MetricCalculatorFactory.
     */
    public MetricCalculatorFactory getInstance(){

        return this.factory;

    } // getInstance()

    /**
     * Gets a selected instance of a MetricCalculatorable.
     * Uses the CalculatorType enum for decide which instance to return.
     *
     * @param metricCalc
     * The Enum CalculatorType, used to decide which metric calculator instance
     * to return.
     *
     * @return
     * Returns an instance of MetricCalculatorable. used to calculate metric for a class.
     */
    public MetricCalculatorable getMetricCalculator(CalculatorType metricCalc){

        MetricCalculatorable selectedCalc = null;

        // switch to use the selected enum to decide which instance to return.
        switch (metricCalc){

            case BASIC:

                // create an instance of basic metric calculator
                selectedCalc = new BasicMetricCalculator();

                break;

            case BETTER:

                // create an instance of metric calculator
                selectedCalc = new MetricCalculator();

                break;
        } // switch

        return selectedCalc;

    } // getMetricCalculator()

} // class
