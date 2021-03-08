/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.util.ArrayList;

/**
 *
 * @author feral
 */
public class Conjuntos {

    public String id;
    public String valores;
    public String type;
    public ArrayList<String> simbols = new ArrayList<String>();

    public Conjuntos(String id, String valores, String type) {
        this.id = id;
        this.valores = valores;
        this.type = type;
        if (valores.contains("~")) {
            String[] aux = valores.split("~");
            char sup1 = aux[0].charAt(0);
            char sup2 = aux[1].charAt(0);
            for (int i = (int) sup1; i <= (int) sup2; i++) {
                this.simbols.add(Character.toString((char) i));
            }
        } else if (valores.contains(",")) {
            String[] aux = valores.split(",");
            for (int i = 0; i < aux.length; i++) {
                this.simbols.add(aux[i]);
            }
        }

    }

    public Conjuntos(String id) {
        this.id = id;
        if (id.contains("\\")) {
            id = id.replace("\\", "");
        }
        this.simbols.add(id.replace("\"", ""));
    }

}
