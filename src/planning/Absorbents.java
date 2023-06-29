 package planning;

/**
 * 
 * List of absorbents OK for a spilled material.
 *
 */
public abstract class Absorbents {
	
	protected String [] absorbents;
	
	protected abstract void setAbsorbents();
	
	public String toString() {
		String rslt = new String("\n");
		for (int i = 0; i < this.absorbents.length; i++) {
			rslt += "\n"+this.absorbents[i];
		}
		return rslt;
	}
}
