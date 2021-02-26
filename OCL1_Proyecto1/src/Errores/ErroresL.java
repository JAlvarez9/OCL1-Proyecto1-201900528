/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Errores;

/**
 *
 * @author feral
 */
public class ErroresL {
    
    String tipo;
    String caracter;
    int fila;
    int columna;
    
    public ErroresL(String tipo, String caracter,int fila,int columna) {
        this.tipo = tipo;
        this.caracter = caracter;
        this.fila = fila;
        this.columna = columna;
    }
    
    public void SendError(){
        System.out.println("Hubo un error de  tipo" + tipo);
        System.out.println("No se esparaba el caracter" + caracter);
        System.out.println("En la fila" + fila);
        System.out.println("En la columna" + columna);
    }
    
    
}
