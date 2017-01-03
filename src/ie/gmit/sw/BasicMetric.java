package ie.gmit.sw;

/**
 * Created by Ross Byrne on 02/01/17.
 */
public class BasicMetric {

    private int inDegree;
    private int outDegree;
    private String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

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

        try {
            if(getOutDegree() > 0) {

                // calculate stability
                stability = ((float) getOutDegree() / ((float) getInDegree() + (float) getOutDegree()));
            } else {

                stability = 0f;
            }

        } catch (Exception e){

            stability = 0f;
        }
        return stability;

    } // getStability()
} // class
