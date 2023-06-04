/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scenarios;

/**
 *
 * @author claude
 */
// ADDED for FACADE DECOUPLING IMPLEMENTATION //
public class FacadeScenario {
    public static void oneView(String[] args){
        OneView.main(args); 
    }
    public static void twoView(String[] args){
        TwoView.main(args);
    }
    public static void twoViewAdmin(String[] args){
        TwoViewAdmin.main(args);
    }
}
