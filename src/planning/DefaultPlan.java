package planning;

import java.util.Iterator;
import java.util.Vector;

import data.ResponderInformation;

/**
 *  The default plan for any un-resolvable situation:
 *      unrecognized materials, locations, responders
 *      or unsure responder or spill too large to handle
 */

public class DefaultPlan extends Plan {

	public DefaultPlan() {
		state = ResponseState.notify;
		interview = null;
		responders = new Vector<ResponderInformation>();
		immediateActions = null;

		containActions = null;
		absorbents = null;
		cautions = null;
		notifications = new Vector<String>();
		notifications.add(new String(
				"Call one or more of the listed responders:"));
	}

	/**
	 * It is redundant to look up most of the default info.(and error prone!)
	 * So just clone = a trivial example of Prototype
	 * 
	 * @param interview
	 * @return a clone of the default plan
	 */
	protected DefaultPlan clone(Interview interview) {
		DefaultPlan p = new DefaultPlan();
		/*
		 * try { p = (DefaultPlan)this.clone(); } catch
		 * (CloneNotSupportedException e) { // TODO Auto-generated catch block
		 * System.err.println("Clone failed!!"); e.printStackTrace(); } // TODO:
		 * causes clone not supported exception
		 */
		p.immediateActions = this.immediateActions;
		p.location = this.location;
		p.responders = this.responders;
		p.state = this.state;
		p.interview = interview;
		return p;
	}

	public String toString() {
		String rslt = interview.toString();

		/*
		 * rslt += "\n   IMMEDIATE ACTIONS"; Iterator<Action> it =
		 * immediateActions.iterator(); while (it.hasNext()) { Action a =
		 * it.next(); rslt += "\n\n" + a.toString(); }
		 */

		rslt += "\n\n   NOTIFICATIONS";
		Iterator<String> nit = notifications.iterator();
		while (nit.hasNext()) {
			String a = nit.next();
			rslt += "\n\n" + a.toString();
		}

		rslt += "\n\n    FOR HELP";
		rslt += "\n\n Responder list:";
		Iterator<ResponderInformation> rit = responders.iterator();
		while (rit.hasNext()) {
			ResponderInformation r = rit.next();
			rslt += "\n" + r.getName() + " at " + r.getContacts();
		}
		return rslt;
	}

	public Plan makePlan(String[] inputs, int sz) {
		//System.out.println("\n\n\t plan forming\n" + this);
		setChanged();
		notifyObservers(this.toString());
		return this;
	}

}
