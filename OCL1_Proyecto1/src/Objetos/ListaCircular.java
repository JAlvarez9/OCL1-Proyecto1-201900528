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
public class ListaCircular {

    public NodoImagenes cabeza;
    public int tama;

    public ListaCircular() {
        cabeza = null;
    }
    
    public void add(Object e) {
        Imagenes nuevo = (Imagenes) e;
        NodoImagenes novo = new NodoImagenes(nuevo);

        if (cabeza == null) {
            cabeza = novo;
            cabeza.setNext(cabeza);
            cabeza.setBack(cabeza);
            //System.out.println("aqui");
        } else {

            NodoImagenes sup = cabeza;

            while (sup.getNext() != cabeza) {

                sup = sup.getNext();
            }
            if (sup.getNext() == cabeza) {
                novo.setImage((Imagenes) e);
                //novo.setBack(ant);
                novo.setBack(sup);
                novo.setNext(cabeza);
                //ant.setNext(novo);
                sup.setNext(novo);
                cabeza.setBack(novo);
            }

        }
        tama++;
    }

}
