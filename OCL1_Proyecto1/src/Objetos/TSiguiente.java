/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 *
 * @author feral
 */
public class TSiguiente {

    String name;
    public LinkedList<NSiguiente> nodes = new LinkedList<NSiguiente>();

    public TSiguiente(LinkedList<NodeArbol> nodesA, String name) {
        this.name = name;
        LinkedList<NodeArbol> nodesaux = new LinkedList<NodeArbol>();
        for (int i = 0; i < nodesA.size(); i++) {
            if (nodesA.get(i).hoja) {
                NSiguiente aux = new NSiguiente(nodesA.get(i).token, String.valueOf(nodesA.get(i).id));
                nodes.add(aux);

            } else {
                nodesaux.add(nodesA.get(i));
            }
        }

        LinkedList<String> aux = new LinkedList<String>();
        LinkedList<String> aux2 = new LinkedList<String>();

        for (NodeArbol actual : nodesaux) {
            if (actual.token.equals(".") && !actual.hoja) {
                aux = actual.hijoizq.ultimos;
                aux2 = actual.hijoder.primeros;
                for (NSiguiente nodos : this.nodes) {
                    for (String actualu : aux) {
                        if (nodos.num.equals(actualu)) {
                            for (String actualp : aux2) {
                                if (nodos.siguientes.contains(actualp)) {

                                } else {
                                    nodos.siguientes.add(actualp);
                                }

                            }
                        }
                    }
                }

            }
            if (actual.token.equals("*") && !actual.hoja) {
                aux = actual.hijoizq.primeros;
                aux2 = actual.hijoizq.ultimos;
                for (NSiguiente nodos : this.nodes) {
                    for (String actualu : aux) {
                        if (nodos.num.equals(actualu)) {
                            for (String actualp : aux2) {
                                if (nodos.siguientes.contains(actualp)) {

                                } else {
                                    nodos.siguientes.add(actualp);
                                }

                            }
                        }
                    }
                }
            }
            if (actual.token.equals("+") && !actual.hoja) {
                aux = actual.hijoizq.primeros;
                aux2 = actual.hijoizq.ultimos;
                for (NSiguiente nodos : this.nodes) {
                    for (String actualu : aux) {
                        if (nodos.num.equals(actualu)) {
                            for (String actualp : aux2) {
                                if (nodos.siguientes.contains(actualp)) {

                                } else {
                                    nodos.siguientes.add(actualp);
                                }

                            }
                        }
                    }
                }
            }
            
        }
        

    }

    public void GenerarTablita() {
        String grafica = "digraph Siguientes" + this.name + "{\n\n";
        grafica += "node0 [shape= \"none\" label=< \n";
        grafica += "<TABLE> \n";
        grafica += "<TR>\n";
        grafica += "<TD> Hoja </TD>\n";
        grafica += "<TD> Siguientes </TD>\n";
        grafica += "</TR>\n";
        for (NSiguiente node : this.nodes) {
            grafica += "<TR>\n";
            grafica += "<TD> " + node.num + "-" + node.node + " </TD>\n";
            String aux = "";
            for (String s : node.siguientes) {
                if (s != null) {
                    aux += s + ",";
                }

            }
            grafica += "<TD> " + aux + " </TD>\n";
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
            String name = "Siguientes" + this.name + ".dot";
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

        String file_input_path = "Siguientes" + this.name + ".dot";
        String do_path = "C:\\Program Files\\Graphviz\\bin\\dot.exe";

        String file_get_path = "Siguientes" + this.name + ".jpg";
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
