/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package planning;
import planning.Plan;
import planning.Planner;
import planning.Interview;
import static planning.Plan.buildActionPlan;

/**
 *
 * @author claude
 * // ADDED / CHANGED for THREAD IMPLEMENTATION
 */
public class ResponsePlanThread implements Runnable {
    private int projID;
    private Planner planner;
    
    public ResponsePlanThread(int projID, Planner planner){
        this.projID = projID;
        this.planner = planner;
    }
    
    @Override
    public void run(){
        try {
            // start time
            long startTime = System.currentTimeMillis();
            
            // output thread information
            System.err.println("Thread " + Thread.currentThread().getId() + " started\nProject ID: "+projID+"\nPlanner: "+planner.toString());
            // dummy data for creating interview
            String[] input = {"Bob","Jones","Building 2","Room 1"};
            String[] input2 = {"John","Woodward","Build 5","Room 105"};
            String[] input3 = {"Tom","Williams","Building 4","Room 106"};
            SpillCase material = SpillCase.acidChloride;
            SpillCase material2 = SpillCase.standard;
            SpillCase material3 = SpillCase.acidChloride;
            int spillSize = 50;
            int spillSize2 = 85;
            int spillSize3 = 45;
            
            Interview interview1 = new Interview(input,material,spillSize);
            Interview interview2 = new Interview(input2,material2,spillSize2);
            Interview interview3 = new Interview(input3,material3,spillSize3);
            
            Plan plan1 = planning.Plan.buildActionPlan(planner, interview1);
            Thread.sleep(5000);
            Plan plan2 = buildActionPlan(planner,interview2);
            Plan plan3 = buildActionPlan(planner,interview3);
            
            long endTime = System.currentTimeMillis();
            System.err.println("Thread " + Thread.currentThread().getId() +" finished\nProject ID: "+ projID + "\nPlanner: "+planner.toString()+ "\nExecution time: " + (endTime - startTime) + " milliseconds");
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        int projectID1 = 123445;
        int projectID2 = 123467;
        Planner planner1 = new Planner();
        Planner planner2 = new Planner();
        ResponsePlanThread planThread = new ResponsePlanThread(projectID1,planner1);
        ResponsePlanThread planThread2 = new ResponsePlanThread(projectID2,planner2);
        
        Thread thread1 = new Thread(planThread);
        Thread thread2 = new Thread(planThread2);
        
        thread1.start();
        thread2.start();
        
        
    }
}
