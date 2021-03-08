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
public class EstadosVali {
    String estado;
    LinkedList<Direccion> moves = new LinkedList<Direccion>();
    Boolean aceptacion;

    public EstadosVali(String estado, Boolean aceptacion) {
        this.estado = estado;
        this.aceptacion = aceptacion;
    }
    
    
    
}
