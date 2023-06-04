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
public class AcidChloridePlanTest extends TestCase {

    public AcidChloridePlanTest(String testName) {
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
     * A valid Acid Chloride plan should have special absorbents and special
     * cautions Check the output to make sure they match the description (e.g.
     * "avoid water")
     */
    public void testValidMakeAcidChloridePlan() {
        Planner planner =
                Planner.getThePlanner(); // lastName, firstName, building, room, materialSpilled 
        // small acid chloride spill responded to by known person
        int sz = 12;
        String[] interview = {"Smith", "Bill", "1", "102"};
        Plan p = planner.getPlan(interview, SpillCase.acidChloride, sz);

        // TODO: add a few checks for the correct plan elements

        Plan x = new DefaultPlan();
        if (p.getClass() == x.getClass()) {
            fail("default plan returned for known responder + Acid Chloride Spill");
        } else {
            System.out.println();
            System.out.println("\tVVVVVV CHECK ME VVVVVVV");
            System.out.println("Check Acid Chloride plan for "
                    + interviewString(interview, SpillCase.acidChloride, sz));
            System.out.println(p);
        }
        //demarcateTestOutput("testValidMakeAcidChloridePlan");
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
