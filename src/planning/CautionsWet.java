/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package planning;

/**
 *
 * @author claude
 */
class CautionsWet extends Cautions {

    @Override
    protected void setCautions() {
        String[] ac = {"avoid water","avoid sodium bicarbonate"};
        cautions = ac;
    }
    
}
