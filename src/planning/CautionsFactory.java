/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package planning;

/**
 *
 * @author claude will
 */
// CREATED FOR ABSTRACT FACTORY IMPLEMENTATION - FIX FOR UNIT 7
public class CautionsFactory extends AbstractFactory {
    @Override 
    Absorbents getAbsorbents(SpillCase s){
        return null;
    }
    
    @Override
    Cautions getCautions(SpillCase s){
        if(s==SpillCase.acidChloride){
            CautionsWet obj = new CautionsWet();
            obj.setCautions();
            return obj;
        }
        else
        {
            CautionsDry obj = new CautionsDry();
            obj.setCautions();
            return obj;
        }
    }
}
