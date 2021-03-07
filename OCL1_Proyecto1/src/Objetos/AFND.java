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
public class AFND {
    
    String name;
    public LinkedList<NAfnd> hojitas = new LinkedList<NAfnd>();

    public AFND(String name,LinkedList<NAfnd> hojitas) {
        this.name = name;
        this.hojitas = hojitas;
        int cont = 0;
        for (NAfnd hojita : hojitas) {
            if (hojita.symbol == ".") {
                
                
            }
            
        }
    }
    
    public void Cerradura(){
    
    }
    
}
