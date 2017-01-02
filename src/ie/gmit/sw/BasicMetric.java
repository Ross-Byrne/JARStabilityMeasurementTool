package ie.gmit.sw;

/**
 * Created by Ross Byrne on 02/01/17.
 */
public class BasicMetric {

    private int inDegree;
    private int outDegree;

    public int getInDegree() {
        return inDegree;
    }

    public void setInDegree(int inDegree) {
        this.inDegree = inDegree;
    }

    public int getOutDegree() {
        return outDegree;
    }

    public void setOutDegree(int outDegree) {
        this.outDegree = outDegree;
    }

    // uses the inDegree and outDegree to calculate the stability
    public float getStability(){

        float stability = 1f;

        // calculate stability
        stability = (getOutDegree() / (getInDegree() + getOutDegree()));

        return stability;

    } // getStability()
} // class
