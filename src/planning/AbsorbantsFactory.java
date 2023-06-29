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
public class AbsorbantsFactory extends AbstractFactory {

    @Override
    Absorbents getAbsorbents(SpillCase s) {
        if(s==SpillCase.acidChloride){
            AbsorbentWet obj = new AbsorbentWet();
            obj.setAbsorbents();
            return obj;
        }
        else
        {
            AbsorbentDry obj = new AbsorbentDry();
            obj.setAbsorbents();
            return obj;
        }
    }

    @Override
    Cautions getCautions(SpillCase s) {
        return null;
    }
    
}
