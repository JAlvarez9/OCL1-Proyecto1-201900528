/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Arrays;

/**
 *
 * @author feral
 */
public class TTransiciones {

    public String name;
    public LinkedList<NTrancisiones> estados;
    public LinkedList<NSiguiente> nodes;
    public String[][] tablona;
    int cont = 1;

    public TTransiciones(LinkedList<String> primeros, LinkedList<NSiguiente> nodes, String name, LinkedList<NTrancisiones> estados) {
        this.estados = estados;
        this.name = name;
        this.nodes = nodes;
        NTrancisiones pos_cero = new NTrancisiones("0");
        pos_cero.conj = primeros;
        this.estados.add(pos_cero);
        for (int i = 0; i < pos_cero.conj.size(); i++) {
            for (int j = 0; j < this.nodes.size() - 1; j++) {
                if (pos_cero.conj.get(i).equals(this.nodes.get(j).num)) {
                    pos_cero.moves.add(this.nodes.get(j).node);
                }
            }
        }

        Recurson(pos_cero.conj);
        this.estados.remove(this.estados.size() - 1);
        LinkedList<String> aux = new LinkedList<String>();
        for (int i = 0; i < this.nodes.size() - 1; i++) {
            if (!aux.contains(this.nodes.get(i).node)) {
                aux.add(this.nodes.get(i).node);
            }
        }
        this.tablona = new String[this.estados.size() + 1][aux.size() + 1];
        for (int i = 0; i < tablona.length; i++) {
            if (i != 0) {
                tablona[i][0] = String.valueOf(i - 1);
            }

        }
        for (int i = 0; i < tablona[0].length; i++) {
            if (i != 0) {
                tablona[0][i] = aux.get(i - 1);
            }
        }
        Tablas();
        //System.out.println("holis");
    }

    public void Recurson(LinkedList<String> sig) {
        Boolean create = false;
        for (int i = 0; i < sig.size(); i++) {
            if (sig.get(i) != null) {

                LinkedList<String> aux = this.nodes.get(Integer.parseInt(sig.get(i)) - 1).siguientes;

                for (int j = 0; j < this.estados.size(); j++) {

                    Object[] sup1 = this.estados.get(j).conj.toArray();
                    Object[] sup2 = aux.toArray();
                    if (sup1.length > 0 && sup2.length > 0) {

                        if (Arrays.equals(sup1, sup2)) {
                            create = true;
                        }
                    }

                }
                if (!create) {

                    NTrancisiones support = new NTrancisiones(String.valueOf(cont));
                    support.conj = aux;
                    this.estados.add(support);
                    for (int j = 0; j < support.conj.size(); j++) {
                        for (int k = 0; k < this.nodes.size() - 1; k++) {
                            if (this.nodes.get(k) != null && support.conj.get(j) != null) {
                                if (support.conj.get(j).equals(this.nodes.get(k).num)) {

                                    support.moves.add(this.nodes.get(k).node);
                                }
                            }

                        }
                    }
                    if (support.conj.contains(String.valueOf(nodes.size()))) {
                        support.aceptacion = true;
                    }
                    cont++;
                    Recurson(support.conj);
                }

            }
        }

    }

    public void Tablas() {
        Object[] aux3 = new Object[0];
        String aux2 = "";
        for (int i = 0; i < this.estados.size(); i++) {
            for (int j = 0; j < this.estados.get(i).conj.size(); j++) {
                if (this.estados.get(i).conj.get(j) != null) {

                    int aux = Conseguir(this.estados.get(i).conj.get(j));
                    if (aux >= 0) {
                        for (int k = 0; k < this.estados.size(); k++) {
                            Object[] sup1 = this.nodes.get(aux).siguientes.toArray();
                            Object[] sup2 = this.estados.get(k).conj.toArray();
                            if (Arrays.equals(sup1, sup2)) {
                                aux2 = this.estados.get(k).num;
                                aux3 = this.estados.get(i).moves.toArray();
                                //System.out.println("asd");
                            }
                        }
                        RecorridoTablona(i, aux3, aux2);
                    }

                }

            }
        }

    }

    public void RecorridoTablona(int j, Object[] aux3, String aux2) {
        
        outer: for (int k = 0; k < aux3.length; k++) {
            String aux = (String) aux3[k];
            inner: for (int i = 1; i < this.tablona[j + 1].length; i++) {
                if (aux.equals(tablona[0][i])) {
                    this.tablona[j + 1][i] = aux2;
                    this.estados.get(j).moves.remove(aux);
                    break outer;

                }

            }
        }

    }

    public int Conseguir(String a) {
        for (int i = 0; i < this.nodes.size(); i++) {
            if (i != this.nodes.size() - 1) {
                if (this.nodes.get(i).num.equals(a)) {
                    return i;
                }
            }

        }
        return -1;
    }

    public void GraphTablita() {

        LinkedList<String> aux = new LinkedList<String>();
        for (int i = 0; i < this.nodes.size() - 1; i++) {
            if (!aux.contains(this.nodes.get(i).node)) {
                aux.add(this.nodes.get(i).node);
            }
        }
        LinkedList<String> aux2 = new LinkedList<String>();
        for (int i = 0; i < this.nodes.size() - 1; i++) {
            if (!aux2.contains(this.nodes.get(i).num)) {
                aux2.add(this.nodes.get(i).num);
            }
        }

        String grafica = "digraph Transiciones" + this.name + "{\n\n";
        grafica += "node0 [shape= \"none\" label=< \n";
        grafica += "<TABLE> \n";
        grafica += "<TR>\n";
        grafica += "<TD> Estados </TD>\n";
        for (String string : aux) {
            grafica += "<TD> " + string + " </TD>\n";
        }

        grafica += "</TR>\n";
        for (int i = 1; i < this.tablona.length; i++) {
            grafica += "<TR>\n";
            for (int j = 0; j < this.tablona[i].length; j++) {
                if (this.tablona[i][j] != null) {
                    grafica += "<TD> S" + this.tablona[i][j] + " </TD>\n";;
                } else {
                    grafica += "<TD> --- </TD>\n";;
                }

            }
            grafica += "</TR>\n";
        }
        grafica += "</TABLE>\n";
        grafica += ">, ]; \n";
        grafica += "} \n";
        GenerarDot(grafica);

    }

    public void GenerarDot(String cadena) {
        FileWriter fichero = null;
        PrintWriter escritor = null;
        try {
            String name = "DOTS_201900528\\Transicion" + this.name + ".dot";
            fichero = new FileWriter(name);
            escritor = new PrintWriter(fichero);
            escritor.println(cadena);
            escritor.close();
            fichero.close();
            reportar();
        } catch (Exception e) {
            System.out.println("error en generar dot");
            e.printStackTrace();
        }
    }

    public void reportar() throws IOException {

        String file_input_path = "DOTS_201900528\\Transicion" + this.name + ".dot";
        String do_path = "C:\\Program Files\\Graphviz\\bin\\dot.exe";

        String file_get_path = "TRANCISIONES_201900528\\Transicion" + this.name + ".jpg";
        try {
            ProcessBuilder pBuilder;
            pBuilder = new ProcessBuilder(do_path, "-Tjpg", "-o", file_get_path, file_input_path);
            pBuilder.redirectErrorStream(true);
            pBuilder.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
