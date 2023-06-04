package scenarios;

import java.awt.Graphics;
import java.awt.Shape;
import javax.swing.text.BadLocationException;
import javax.swing.text.Position;
import javax.swing.text.View;
import mvc.AdminView;
import mvc.ResponderView;
import mvc.WERSController;

/**
 * Simple simulation of two responders and one administrator situation. Connects
 * responder and admin views and controller. Controller connects to plans and
 * administrator and registers then the plan for the Observer pattern.
 */
// CHANGED for FAÇADE DECOUPLING IMPLEMENTATION
//public class TwoViewAdmin {
public class TwoViewAdmin // CHANGED for OBSERVER IMPLEMENTATION there is no generateView() method within the View Class extends View {
{

    // CHANGED for FAÇADE DECOUPLING IMPLEMENTATION
	//public static void main(String[] args) {

//		 create Model
//		WERSController controller = new WERSController();
//
//		 add views to Controller (thence to Model).
//		ResponderView myView1 = new ResponderView();
//		controller.addView(myView1);
//		myView1.addController(controller);
//
//		ResponderView myView2 = new ResponderView();
//		controller.addView(myView2);
//		myView2.addController(controller);
//
//		AdminView av = new AdminView();
//		 lines below will not work until after week3 assignment
//		 controller.addView(av);
//		 av.addController(controller);
//                generateView();
//	}
//    
    /**
     * Replaces the usage of the main method to create model and view
     */
    
    public static void generateView(String[] args) {
        // create Model
        WERSController controller = new WERSController();

        // add views to Controller (thence to Model).
        ResponderView myView1 = new ResponderView();
        controller.addView(myView1);
        myView1.addController(controller);

        ResponderView myView2 = new ResponderView();
        controller.addView(myView2);
        myView2.addController(controller);

        AdminView av = new AdminView();
        // lines below will not work until after week3 assignment
        // CHANGED for OBSERVER IMPLEMENTATION
        controller.setAdminView(av);
        // av.addController(controller);
    }

    
}
