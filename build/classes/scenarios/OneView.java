package scenarios;

import mvc.ResponderView;
import mvc.WERSController;


/**
 * Simple simulation of one responder view.
 * Connects responder view and controller.
 * Controller connects to planer and registers 
 *   the view with the plan for the Observer pattern.
 *
 */
public class OneView  {

	public static void main(String [] args) {
		
                
                //create Model and View
                    ResponderView view 	= new ResponderView();
                    // create model
                        //create Controller. tell it about Model and View, initialize model
                    WERSController controller;
                controller = new WERSController();
                    controller.addView(view);
                    view.addController(controller);
                    // controller will add view to any created plan as observer
                
		

	} 

}
