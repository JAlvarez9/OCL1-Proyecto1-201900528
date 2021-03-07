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
public class NTrancisiones {

    public String num;
    public LinkedList<String> conj = new LinkedList<String>();
    public LinkedList<String> moves = new LinkedList<String>();
    
    public Boolean aceptacion;
    public NTrancisiones(String num) {
        this.num = num;
        this.aceptacion = false;
    }
  
    
}
