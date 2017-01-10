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

} // class
