/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package planning;

import data.AreaInformation;
import data.ResponderInformation;
import junit.framework.TestCase;
import mvc.ResponderView;

/**
 *
 * @author riggs
 */
public class UniquePlannerTest extends TestCase {
    
    Planner planner;
    
    public UniquePlannerTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * There should be only 1 planner running for coordination reasons.
     */
    public void testGetThePlanner() {
        planner = Planner.getThePlanner();
        if (planner == null) {
            fail("No planner active after get!!");
        }
        Planner p2 = Planner.getThePlanner();
        if (p2 != planner) {
            fail("Planner not a singleton!!");
        }
        //demarcateTestOutput("testGetThePlanner");
    }

}
