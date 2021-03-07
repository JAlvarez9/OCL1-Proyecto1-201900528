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
public class NodoImagenes {

    private Imagenes image;
    private NodoImagenes next;
    private NodoImagenes back;

    public NodoImagenes(Imagenes image) {
        this.image = image;
        this.next = null;
        this.back = null;
    }
    

//    public NodoImagenes(Imagenes image) {
//        this.image = image;
//        this.next = null;
//        this.back = null;
//    }

    public Imagenes getImage() {
        return image;
    }

    public void setImage(Imagenes image) {
        this.image = image;
    }

    public NodoImagenes getNext() {
        return next;
    }

    public void setNext(NodoImagenes next) {
        this.next = next;
    }

    public NodoImagenes getBack() {
        return back;
    }

    public void setBack(NodoImagenes back) {
        this.back = back;
    }
    

}
