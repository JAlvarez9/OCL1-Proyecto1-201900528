/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import javax.swing.ImageIcon;

/**
 *
 * @author feral
 */
public class Imagenes {

    public String name;
    public String location;
    public ImageIcon imagen;

    public Imagenes(String name, String location) {
        this.name = name;
        this.imagen = new ImageIcon();
        this.location = location;
    }

}
