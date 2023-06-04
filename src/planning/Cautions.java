package planning;

/**
 * List of special cautions for a spilled material.
 *
 */
public class Cautions {

    protected String[] cautions;

    protected void setCautions(String[] items) {
        cautions = items;
    }

    public String toString() {
        String rslt = new String("\n");
        if (cautions.length > 0) {
            for (int i = 0; i < cautions.length; i++) {
                rslt += "\n" + cautions[i];
            }
        } else {
            rslt += " none";
        }

        return rslt;
    }
}
