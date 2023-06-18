/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc; 
import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 *
 * @author claude
 * 
 *  ADDED / CHANGED for ITERATOR AND COMPOSITE IMPLEMENTATION
 */
public class MaterialTreeCampus extends MaterialTree {

    TreeSet<String> tree;
    public MaterialTreeCampus() {
        tree = new TreeSet<>();
    }
    public Iterator<String> alphaIterator(){
        // CHANGED FOR ITERATOR AND COMPOSITE IMPLEMENTATION
        Iterator<String> it = tree.iterator();
        
        while (it.hasNext()){
            System.out.println(it.next());
        }
        return it;
        
    }
    
}
