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
public class Encabezado {
    public LinkedList<Conjuntos> conj;
    public LinkedList<Expresiones> expre;

    public Encabezado() {
       
    }

    public Encabezado(LinkedList<Conjuntos> conj, LinkedList<Expresiones> expre) {
        this.conj = conj;
        this.expre = expre;
    }

    
}
