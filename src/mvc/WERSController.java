package mvc;

import java.util.HashMap;

import planning.Plan;
import planning.Planner;
import planning.SpillCase;

/**
 * A controller for the WERS systems in the MVC paradigm.
 * Expected model is a planner.
 * Current expected view is ResponderView.
 * It is possible to add further views or to 
 *  develop new controllers for them.
 */
public class WERSController {

	Planner planner; /** planning model */
	
	/** each responder View has own plan so we keep a map from responder to plan - not exactly standard MVC! */
	HashMap<ResponderView, Plan> viewPlanMap = new HashMap<ResponderView, Plan>();
        // CHANGED for OBSERVER IMPLEMENTATION
        HashMap<AdminView, Plan> adminViewPlanMap = new HashMap<AdminView, Plan>();
	private AdminView adminView = new AdminView();

	public WERSController() {
		System.err.println("Controller()");
		System.err.println("Controller: adding model");
		planner = Planner.getThePlanner();
	}

	public void addView(ResponderView view) {
		System.err.println(" adding view" + view + " to " + this);
		viewPlanMap.put(view, null);
		if (adminView != null){
                     // CHANGED for OBSERVER IMPLEMENTATION
			adminView.update(null, (Object)"new responder");
		}
	}
        
        // CHANGED for OBSERVER IMPLEMENTATION
        public void addView(AdminView av){
            adminViewPlanMap.put(av,null);
            System.err.println(" adding admin view " + av + " to " + this);
        }
	/**
	 * 
	 * @param view
	 * 
	 * Add a responder view and its constructed planner to the responder --> plan map.
	 */
	public void requestPlan(ResponderView view) {
		// un-comment to see what action happened at view
		//System.err.println("initPlan from " + view + " to " + this);
		String[] responder = view.getResponderInfo();
		SpillCase material = view.getMaterial();
		int size = 100;                          // fail-safe: assume large until known otherwise
		try {
			size = Integer.parseInt(view.getSize());
		} catch (java.lang.NumberFormatException e) {
			size = 100;
		}
		if (viewPlanMap.get(view) == null) {
			Plan plan = planner.getPlan(responder, material, size);
			plan.addObserver(view);                               // let view observe the plan
			//  un-comment net line to see MVC action
			System.err.println("\nAdded observer " + view + " to plan "
					+ plan.getClass().getSuperclass().toString());
			viewPlanMap.put(view, plan);
			if (adminView != null) {
                            // CHANGED for OBSERVER IMPLEMENTATION
				plan.addObserver(adminView);
				// un-comment to watch observer connection
				System.err.println("\nAdded observer " + adminView
					+ " to plan " + plan.getClass().getSuperclass().toString());
			} 
			plan.change();
		} else {
			
		}

	}

        
}
