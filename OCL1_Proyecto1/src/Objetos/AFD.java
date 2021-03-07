/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.util.LinkedList;

/**
 *
 * @author feral
 */
public class AFD {
    LinkedList<NTrancisiones> estados = new LinkedList<NTrancisiones>();
    String name;

    public AFD(String name) {
        this.name = name;
    }
    
    
}
