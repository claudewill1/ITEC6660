/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package planning;

import junit.framework.TestCase;

public class NullPlanTest extends TestCase {

    Planner planner;

    public NullPlanTest(String testName) {
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
     * We must always have some safe plan available. 
     * We dub this the 'NullPlan'.
     * Contains references to EHS and Public Safety
     */
    public void testMakeNullPlan() {
        planner = Planner.getThePlanner();
        // lastName, firstName, building, room, materialSpilled
        String[] unknownResponder = {"Nobody", "No How", "West Hall", "413",
            "HCL"};
        // small spill responded to by unknown person
        Plan p = planner.getPlan(unknownResponder, SpillCase.standard, 12);
        try {
            // just for exception side effect
            DefaultPlan x = (DefaultPlan) p;
        } catch (Exception e) {
            fail("no default plan returned for unknown responder");
        } finally {
            System.out.println(p);
        }
        //demarcateTestOutput("testMakeNullPlan");
    }
}
