package planning;

import java.util.Iterator;
import java.util.Vector;

//import data.Action;
import data.AreaInformation;
import data.Capability;
import data.ResponderInformation;

/**
 * Represents one plan for a single responder. Each plan is observable so that
 * the responder may view it in the (thus slightly modified)MVC pattern.
 */
public class Plan extends java.util.Observable implements Runnable {

    public ResponseState state;
    public Interview interview;
    public Vector<ResponderInformation> responders;
    public String location;
    public Vector<String> immediateActions;
    public Vector<String> containActions;
    Absorbents absorbents;
    Cautions cautions;
    public Vector<String> notifications;
    private AreaInformation areaInfo;
    protected String responsibleParty;
    private static Planner planner;
    
    // CHANGED FOR ABSTRACT FACTORY IMPLEMENTATION
    public Plan(Interview interview) {
        state = ResponseState.immediate;
        this.interview = interview;
        
        responders = new Vector<ResponderInformation>();
        location = "unknown";
        immediateActions = new Vector<String>();
        immediateActions.add("1. Notify lab personnel and neighbors of the accident.");
        immediateActions.add("2. Isolate the area. Close lab doors and evacuate the immediate area if necessary.");
        immediateActions.add("3. Remove ignition sources and unplug nearby electrical equipment.");
        immediateActions.add("4. Establish exhaust ventilation. Vent vapors to outside of building only (open windows and turn on fume hoods.");

        containActions = new Vector<String>();
        containActions.add("5. Locate spill kit.");
        containActions.add("6. Choose appropriate personal protective equipment (goggles, face shield, impervious gloves, lab coat, apron, etc.) Note: All lab personnel MUST be properly fit tested before using a respirator. Contact EH&S (855-6311) for more information.()");
        containActions.add("7. Confine and contain spill. Cover with appropriate absorbent material. Acid and base spills should be neutralized prior to cleanup. Sweep solid material into a plastic dust pan and place in a sealed 5 gallon container.)");
        containActions.add("8. Wet mop spill area. Be sure to decontaminate broom, dustpan, etc. Put all contaminated items (gloves, clothing, etc.) into a sealed 5 gallon container or plastic bag. Bring all waste to the next Waste Open House or call EH&S if a special pickup is necessary.");
       
        AbstractFactory absorbFactory = ProductFactory.getFactory("Absorbents");
        AbstractFactory cautionFactory = ProductFactory.getFactory("cautions");
        absorbents = absorbFactory.getAbsorbents(interview.materialSpilled);
        cautions = cautionFactory.getCautions(interview.materialSpilled);
        
//        if(interview.materialSpilled.equals(SpillCase.acidChloride)){
//            
//            absorbents.setAbsorbents();
//            cautions.setCautions();
//        } else {
//            absorbents.setAbsorbents();
//            cautions.setCautions();
//        }
        
        // original code
//         if (interview.materialSpilled.equals(SpillCase.acidChloride)) {
//            String[] aa = {"oil-dri", "zorb-al", "dry sand"};
//            absorbents = new Absorbents();
//            absorbents.setAbsorbents(aa);
//            String[] ac = {"avoid water", "avoid sodium bicarbonate"};
//            cautions = new Cautions();
//            cautions.setCautions(ac);
//        } else {
//            String[] ga = {"1:1:1 mixture of Flor-Dri (or unscented kitty litter), sodium bicarbonate, and sand"};
//            absorbents = new Absorbents();
//            absorbents.setAbsorbents(ga);
//            String[] gc = {""};
//            cautions = new Cautions();
//            cautions.setCautions(gc);
//        }

        notifications = new Vector<String>();
        if (getAreaInfo() != null) {
            responsibleParty = getAreaInfo().getResponsibleParty();
        }
        notifications.add(responsibleParty);
        

    }

    Plan() {
        this.responders = new Vector<ResponderInformation>();
    }

    /**
     * simple Factory Method Instructor note: comment out 'synchronized' to
     * restore the race condition
     */
    // CHANGED FOR THREAD SAFETY IMPLEMENTATION
    public static synchronized Plan buildActionPlan(Planner planner,
            Interview interview) {
        Plan.planner = new Planner();
        System.err.println("Building plan for " + interview);
        String contact = interview.firstName + " " + interview.lastName;
        Plan p = null;
        ResponderInformation r = planner.getResponder(contact, interview.materialSpilled);
        if (r != null) {
            // responder found
            if (r.capabilities().contains(Capability.chemicalResponder)) {
                // is a qualified chemical responder
                
                p = new Plan(interview);
                p.setAreaInfo(planner.findArea(interview.room,
                        interview.building));
            } else {
                p = planner.theDefaultPlan.clone(interview);
            }
            if (p.getAreaInfo() != null) {
                // area was recognized
                p.notifications.add(p.getAreaInfo().getResponsibleParty());
                String kitLoc = p.getAreaInfo().nearSpillKit();
                String[] parms = kitLoc.split(" ");
                System.err.println("Spill kit loc = " + kitLoc + " or "
                        + parms[1] + "," + parms[2]);
                planner.consumeSpillKit(parms[1], parms[2]);
            } else {
                // area was not recognized
                p.setAreaInfo(planner.getEhsAreaInfo());
            }
        } else {
            // responder is not qualified
            p = planner.theDefaultPlan.clone(interview);
        }
        p.responders.addAll(planner.defaultResponders);
        if (interview.spillSize > 12) {
            p.notifications.add("large spill must notify EHS");
        }
        // TODO: check for EHS notification requirements
        System.err.println("got plan");
        return p;
    }
                
	public String immediateActionsToString() {
        String rslt = new String();
        rslt += "\n\n\n   IMMEDIATE ACTIONS";
        Iterator<String> it = immediateActions.iterator();
        while (it.hasNext()) {
            rslt += "\n\n" + it.next();
        }
        return rslt;
    }

    public String containmentActionsToString() {
        String rslt = new String();
        // TODO: replace \n with System property value
        rslt += "\n\n\n   CONTAINMENT ACTIONS";
        Iterator<String> cit = containActions.iterator();
        while (cit.hasNext()) {
            String a = cit.next();
            rslt += "\n\n " + a.toString();
        }

        rslt += "\n\n    ABSORBENTS:";
        rslt += absorbents;
        rslt += "\n\n    SPECIAL CAUTIONS:";
        rslt += cautions;
        rslt += "\n\n    SPILL KIT LOC: " + getAreaInfo().nearSpillKit();
        return rslt;
    }

    public String notificationsToString() {
        String rslt = new String();
        Iterator<String> nit = notifications.iterator();
        while (nit.hasNext()) {
            String a = nit.next();
            if (a != null) {
                rslt += "\n\n" + a;
            }
        }
        if (!rslt.isEmpty()) {
            return "\n\n\n   REQUIRED NOTIFICATIONS" + rslt;
        } else {
            return "";
        }
    }

    public String toString() {
        String rslt = "Plan for \n" + interview.toString();
        rslt += immediateActionsToString();
        rslt += containmentActionsToString();
        rslt += notificationsToString();
        rslt += helpToString();
        return rslt;
    }

    private String helpToString() {
        String rslt = new String();
        rslt += "\n\n    FOR HELP";
        rslt += "\n\n Contact list:";
        Iterator<ResponderInformation> rit = responders.iterator();
        while (rit.hasNext()) {
            ResponderInformation r = rit.next();
            rslt += "\n" + r.getName() + " at " + r.getContacts();
        }
        return rslt;
    }

    public Plan makePlan(String[] inputs, SpillCase sc, int sz) {
        Plan p = makePlan(inputs, sc, sz);
        System.out.println("\n\n\tplan formed\n" + p);
        return p;
    }

    public void change() {
        setChanged();
        notifyObservers(this.toString());
        // if using Model Pull, then can use notifyObservers()
        // notifyObservers();
    }

    public String getLocation() {
        return interview.building + " " + interview.room;
    }

    public String getMaterial() {
        if (interview.materialSpilled == SpillCase.acidChloride) {
            return new String("acid chloride");
        }
        return "unknown";
    }

    /*
     * public String getResponder() { return interview.firstName + " " +
     * interview.lastName; }
     */
    /*
     * public String getState() { if (state != ResponseState.immediate) { return
     * new String("immediate"); } else if (state == ResponseState.contain) {
     * return new String("contain"); } else if (state == ResponseState.clean) {
     * return new String("clean"); } else if (state == ResponseState.notify) {
     * return new String("notify"); } return new String("unknown"); }
     */
    public String getResponsibleParty() {
        return this.responsibleParty;
    }

    public void setAreaInfo(AreaInformation areaInfo) {
        this.areaInfo = areaInfo;
    }

    public AreaInformation getAreaInfo() {
        return areaInfo;
    }
    
    
    // ADDED TO TEST THREAD SAFETY IMPLEMENTATION
//    public static void main(String[] args){
//      Planner planner = Planner.getThePlanner();
//        // creating two spills
//        SpillCase spill1 = SpillCase.acidChloride;
//        SpillCase spill2 = SpillCase.acidChloride;
//        // creating two arrays of input for the interview
//        String[] inputsA = {"Bob","Wallace","1","101"};
//        String[] inputsB = {"Tom","Williams","3","101"};
//        // creating two interviews
//        Interview int1 = new Interview(inputsA,spill1,1);
//        Interview int2 = new Interview(inputsB,spill2,2);
//        
//        // create two ResponsePlanTread Objects
//        ResponsePlanThread thread1 = new ResponsePlanThread(12345,planner);
//        ResponsePlanThread thread2 = new ResponsePlanThread(23456,planner);
//        // Initiating separate threads
//        Thread t1 = new Thread(thread1);
//        Thread t2 = new Thread(thread2);
//        // start both threads
//        t1.start();
//        t2.start();
//        
//        try{
//            // wait for both threads to complete through the use of the Thread join() method
//            t1.join();
//            t2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        // access plans from Planner instance to check the assigned spill kits
//        Plan planA = planner.getPlan(inputsA, spill1, 50);
//        Plan planB = planner.getPlan(inputsB, spill2, 25);
//        
//        System.out.println("Spill kit assigned for plan1: "+planA.getAreaInfo().nearSpillKit());
//        System.out.println("Spill kit assigned for plan1: "+planA.getAreaInfo().nearSpillKit());
//        if(planA == planB || planA.getAreaInfo().nearSpillKit().equals(planB.getAreaInfo().nearSpillKit())){
//            System.err.println("Same spill kit used twice");
//        }
//    }  
   
    
    // CHANGED for THREAD SAFETY IMPLEMENTATION
    @Override
    public void run() {
        Thread t = Thread.currentThread();
        
        System.err.println("The thread name " + t.getName());
        System.err.println("The process id " + t.getId());
        
        System.err.println("Thread running "+planner.getClass().toString());
        System.err.println("The thread has exited");
    }
}