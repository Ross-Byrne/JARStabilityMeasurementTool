package ie.gmit.sw;

/**
 * Created by Ross Byrne on 30/12/16.
 */
public class Runner {

    public static void main(String[] args) {

        //JarAnalyser jarAnalyser = new JarAnalyser("/home/rossbyrne/Downloads/OO/string-service.jar");
        BasicMetricCalculator basicMetricCalculator = new BasicMetricCalculator("/home/rossbyrne/Downloads/OO/string-service.jar");

        // start the swing app window
        new AppWindow();

    } // main()

} // class
