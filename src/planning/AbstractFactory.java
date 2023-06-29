/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package planning;

/**
 *
 * @author claude
 */
// CREATED / CHANGED FOR ABSTRACT FACTORY IMPLEMENTATION - fix for unit 7
public abstract class AbstractFactory {
    abstract Absorbents getAbsorbents(SpillCase s);
    abstract Cautions getCautions(SpillCase s);
}

