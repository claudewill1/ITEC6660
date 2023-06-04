package planning;

/**
 * 
 * List of absorbents OK for a spilled material.
 *
 */
public class Absorbents {
	
	protected String [] absorbents;
	
	protected void setAbsorbents(String [] items){
		absorbents = items;
	}
	
	public String toString() {
		String rslt = new String("\n");
		for (int i = 0; i < this.absorbents.length; i++) {
			rslt += "\n"+this.absorbents[i];
		}
		return rslt;
	}
}
