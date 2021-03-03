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
public class NodeArbol {

    public String token;
    public String lexema;
    public int id;
    public ArrayList<String> primeros = new ArrayList<String>();
    public ArrayList<String> ultimos = new ArrayList<String>();

    public NodeArbol hijoizq;
    public NodeArbol hijoder;

    public ArrayList<NodeArbol> hijos = new ArrayList<NodeArbol>();
    public Boolean anulable;

    public NodeArbol(String token, String lexema, int id, NodeArbol hijoizq, NodeArbol hijoder, Boolean anulable, String primeros, String ultimos) {
        this.token = token;
        this.lexema = lexema;
        this.id = id;
        this.anulable = anulable;
        this.primeros.add(primeros);
        this.ultimos.add(ultimos);

        if (hijoizq != null) {
            this.hijos.add(hijoizq);
        }
        if (hijoder != null) {
            this.hijos.add(hijoder);
        }
        if (token == ".") {
            if (hijoizq.anulable && hijoder.anulable) {
                this.anulable = true;
            } else {
                this.anulable = false;
            }
            if (hijoizq.anulable) {
                for (int i = 0; i < hijoizq.primeros.size(); i++) {
                    for (int j = 0; j < hijoder.primeros.size(); j++) {
                        if (hijoizq.primeros.get(i) != null) {
                            this.primeros.add(hijoizq.primeros.get(i));
                        }
                        if (hijoder.primeros.get(i) != null) {
                            this.primeros.add(hijoder.primeros.get(j));
                        }
                        
                    }
                }
            } else {
                this.primeros = hijoizq.primeros;
            }
            if (hijoder.anulable) {  
                for (int i = 0; i < hijoizq.ultimos.size(); i++) {
                    for (int j = 0; j < hijoder.ultimos.size(); j++) {
                        if (hijoizq.ultimos.get(i) != null) {
                            this.ultimos.add(hijoizq.ultimos.get(i));
                        }
                        if (hijoder.ultimos.get(j) != null) {
                            this.ultimos.add(hijoder.ultimos.get(j));
                        }

                    }
                }
            } else {
                this.ultimos = hijoder.ultimos;
            }
        }
        if (token == "|") {
            if (hijoizq.anulable || hijoder.anulable) {
                this.anulable = true;
            } else {
                this.anulable = false;
            }
            for (int i = 0; i < hijoizq.primeros.size(); i++) {
                for (int j = 0; j < hijoder.primeros.size(); j++) {
                    if (hijoizq.primeros.get(i) != null) {
                        this.primeros.add(hijoizq.primeros.get(i));
                    }
                    if (hijoder.primeros.get(j) != null) {
                        this.primeros.add(hijoder.primeros.get(j));
                    }

                }
            }
            for (int i = 0; i < hijoizq.ultimos.size(); i++) {
                for (int j = 0; j < hijoder.ultimos.size(); j++) {
                    if (hijoizq.ultimos.get(i) != null) {
                        this.ultimos.add(hijoizq.ultimos.get(i));
                    }
                    if (hijoder.ultimos.get(j) != null) {
                        this.ultimos.add(hijoder.ultimos.get(j));
                    }

                }
            }
        }
        if (this.token == "*") {
            this.anulable = true;
            this.primeros = hijoizq.primeros;
            this.ultimos = hijoizq.ultimos;
        }
        if (this.token == "+") {

            this.primeros = hijoizq.primeros;
            this.ultimos = hijoizq.ultimos;
        }
        if (this.token == "?") {
            this.anulable = true;
            this.primeros = hijoizq.primeros;
            this.ultimos = hijoizq.ultimos;
        }

    }

}
