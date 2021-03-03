/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author feral
 */
public class Expresiones {

    public String id;
    public Arbol raiz;
    public String type;

    public Expresiones(String id, Arbol raiz, String type) {
        this.raiz = raiz;
        this.id = id;
        this.type = type;
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Arbol getArbol() {
        return raiz;
    }

}
