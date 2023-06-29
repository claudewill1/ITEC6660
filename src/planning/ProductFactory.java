/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package planning;

/**
 *
 * @author claude
 */
public class ProductFactory {
    public static AbstractFactory getFactory(String choice){
        if(choice.equalsIgnoreCase("ABSORBENTS")){
            return new AbsorbantsFactory();
        } else if (choice.equalsIgnoreCase("CAUTIONS")){
            return new CautionsFactory();
        }
        return null;
    }
}
