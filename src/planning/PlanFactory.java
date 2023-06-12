/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package planning;

import data.AreaInformation;
import data.ResponderInformation;
import java.util.Vector;

/**
 *
 * @author claude
 */
// CHANGED FOR ABSTRACT FACTORY IMPLEMENTATION
public class PlanFactory extends Plan implements IPlan {
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
    private String responsibleParty;
    @Override
    public Plan createPlan(Interview interview){
        Plan plan = null;
        
        plan.state = ResponseState.immediate;
        plan.interview = interview;

        plan.responders = new Vector<ResponderInformation>();
        plan.location = "unknown";
        plan.immediateActions = new Vector<String>();
        plan.immediateActions.add("1. Notify lab personnel and neighbors of the accident.");
        plan.immediateActions.add("2. Isolate the area. Close lab doors and evacuate the immediate area if necessary.");
        plan.immediateActions.add("3. Remove ignition sources and unplug nearby electrical equipment.");
        plan.immediateActions.add("4. Establish exhaust ventilation. Vent vapors to outside of building only (open windows and turn on fume hoods.");

        plan.containActions = new Vector<String>();
        plan.containActions.add("5. Locate spill kit.");
        plan.containActions.add("6. Choose appropriate personal protective equipment (goggles, face shield, impervious gloves, lab coat, apron, etc.) Note: All lab personnel MUST be properly fit tested before using a respirator. Contact EH&S (855-6311) for more information.()");
        plan.containActions.add("7. Confine and contain spill. Cover with appropriate absorbent material. Acid and base spills should be neutralized prior to cleanup. Sweep solid material into a plastic dust pan and place in a sealed 5 gallon container.)");
        plan.containActions.add("8. Wet mop spill area. Be sure to decontaminate broom, dustpan, etc. Put all contaminated items (gloves, clothing, etc.) into a sealed 5 gallon container or plastic bag. Bring all waste to the next Waste Open House or call EH&S if a special pickup is necessary.");

        // original code for system
        if (interview.materialSpilled.equals(SpillCase.acidChloride)) {
            String[] aa = {"oil-dri", "zorb-al", "dry sand"};
            plan.absorbents = new Absorbents();
            plan.absorbents.setAbsorbents(aa);
            String[] ac = {"avoid water", "avoid sodium bicarbonate"};
            plan.cautions = new Cautions();
            plan.cautions.setCautions(ac);
        } else {
            String[] ga = {"1:1:1 mixture of Flor-Dri (or unscented kitty litter), sodium bicarbonate, and sand"};
            plan.absorbents = new Absorbents();
            plan.absorbents.setAbsorbents(ga);
            String[] gc = {""};
            plan.cautions = new Cautions();
            plan.cautions.setCautions(gc);
        }

        plan.notifications = new Vector<String>();
        if (plan.getAreaInfo() != null) {
            plan.responsibleParty = plan.getAreaInfo().getResponsibleParty();
        }
        plan.notifications.add(plan.responsibleParty);
        
        return plan;
    }
    
    
}
