/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package planning;

/**
 *
 * @author claude
 */
public interface IPlan {
    // CHANGED FOR ABSTRACT FACTORY IMPLEMENTATION
    IPlan createPlan(Interview interview);
}
