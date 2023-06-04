package mvc;
import java.awt.BorderLayout;

/**
 *  Administrator  view
 *  This view observes all plans through interaction with the model
 *  and through callbacks from plans as they change. 
 */

import java.awt.Frame;
import java.awt.event.WindowEvent; 		//for CloseListener()
import java.awt.event.WindowAdapter; 	//for CloseListener()
import java.util.HashMap;
import java.util.Observable; 			//for update();

import javax.swing.JTextArea;
import planning.Plan;




public class AdminView implements java.util.Observer {

	// attributes as must be visible within class
        // CHANGED for OBSERVER IMPLEMENTATION    
	private JTextArea responseArea;
        // CHANGED for OBSERVER IMPLEMENTATION
        private WERSController controller;
        private AdminView adminView;
/**
 * Construct an Administrator view
 */
	public AdminView() {
		System.out.println("View()"+this);

		// frame in constructor and not an attribute as doesn't need to be
		// visible to whole class
		Frame frame = new Frame("Walden Emergency Response System");
		// panel in constructor and not an attribute as doesn't need to be
		// visible to whole class
		frame.addWindowListener(new CloseListener());
		frame.setSize(400, 600);
		frame.setLocation(100, 100);
		frame.setVisible(true);
        frame.setPreferredSize(new java.awt.Dimension(557, 277));
        frame.setTitle("WERS Admin View");
        {
        	responseArea = new JTextArea();
        	frame.add(responseArea, BorderLayout.CENTER);
        	responseArea.setPreferredSize(new java.awt.Dimension(557, 95));
        }

	}

	/**
	 *  Called from the Model(s) (i.e. plans with changes
	 */
        // CHANGED for OBSERVER IMPLEMENTATION
	public void update(Observable obs, Object obj) {
            System.err.println("Update from " + obs.hashCode() + " to " + this);
            System.err.println(obj + "\n END OF MSG");
            responseArea.setLineWrap(true);
            responseArea.setText((String) obj);
	} 
// CHANGED for OBSERVER IMPLEMENTATION
    public void addController(WERSController myController) {
        adminView.controller = myController;
        
        
    }

	public static class CloseListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			e.getWindow().setVisible(false);
			System.exit(0);
		} 
	}

	public void init(String viewsData) {
		responseArea.setText(viewsData);
	}

} 
