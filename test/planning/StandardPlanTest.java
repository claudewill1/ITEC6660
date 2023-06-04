/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package planning;

import junit.framework.TestCase;

/**
 *
 * @author riggs
 */
public class StandardPlanTest extends TestCase {
    
    public StandardPlanTest(String testName) {
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
        // the standard plan applies when none of the materials with special
    // cautions or absorbents is involved - i.r. it is the standard plan for a known responder with a small spill
    public void testMakeStandardPlan() {
        Planner planner = Planner.getThePlanner();
        // lastName, firstName, building, room, materialSpilled
        String[] interview = {"Smith", "Bill", "1", "102"};
        // small spill responded to by known person
        SpillCase material = SpillCase.standard;
        int sz = 12;
        Plan p = planner.getPlan(interview, material, sz);
        Plan x = new DefaultPlan();
        if (p.getClass() == x.getClass()) {
            fail("default plan returned for known responder + standard spill");
        } else {
            System.out.println();
            System.out.println("\tVVVVVV CHECK ME VVVVVVV");
            System.out.println("Check Standard plan for "
                    + interviewString(interview, material, sz));
            System.out.println(p);
        }
        //demarcateTestOutput("testMakeStandardPlan");
    }
    
       /**
     * Used mostly for error printing of details about the interview.
     *
     * @param s
     * @param sc
     * @param sz
     * @return
     */
    String interviewString(String[] s, SpillCase sc, int sz) {
        return new String("[" + s[1] + "," + s[0] + "," + s[2] + "," + s[3]
                + "," + sc + "," + sz + "]");
    }
 

}
