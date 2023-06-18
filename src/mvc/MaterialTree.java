/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.TreeSet;

/**
 *
 * @author claude
 * 
 * 
 * ADDED / CHANGED for ITERATOR AND COMPOSITE IMPLEMENTATION
 */
public class MaterialTree {
    
    TreeSet<String> tree;
    public Object parent;
    public String material;
    // CHANGED FOR ITERATOR AND COMPOSITE IMPLEMENTATION
    public MaterialTree(){
        tree = new TreeSet<>();
        
    }
// CHANGED FOR ITERATOR AND COMPOSITE IMPLEMENTATION
    public TreeSet<String> insertMaterialRecord(MaterialRecord mr) {
        tree.add(mr.toString());
        return tree;
    }
}
