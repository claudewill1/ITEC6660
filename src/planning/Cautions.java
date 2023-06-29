package planning;

/**
 * List of special cautions for a spilled material.
 *
 */
public abstract class Cautions {

    protected String[] cautions;
    // changed for abstract implementation - fixing for unit 7
    protected abstract void setCautions();

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
