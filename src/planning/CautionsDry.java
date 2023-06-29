/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package planning;

/**
 *
 * @author claude
 */
// ADDED TO ADD ABSTRACTION AND FIX ISSUE TO RESOLVE PROBLEM RELATED TO UNIT 7
class CautionsDry extends Cautions {
    
    @Override
    protected void setCautions() {
        String[] gc = {""};
        cautions = gc;
    }
}
