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

/**
 *
 * @author feral
 */
public class AFD {

    String[][] tablona;
    String name;
    LinkedList<NTrancisiones> estados = new LinkedList<NTrancisiones>();

    public AFD(String[][] tablona, String name, LinkedList<NTrancisiones> estados) {
        this.tablona = tablona;
        this.name = name;
        this.estados = estados;
    }

    public void GraphAfd() {

        String grafica = "digraph AFD" + this.name + "{\n\n";
        grafica += "rankdir = \"LR\"; \n";
        for (NTrancisiones node : this.estados) {
            if (node.aceptacion) {
                grafica += "S" + node.num + "[shape=\"doublecircle\"]; \n";
            }
        }
        for (int i = 1; i < this.tablona.length; i++) {

            for (int j = 1; j < this.tablona[i].length; j++) {
                String aux = this.tablona[0][j].replace("\"", "");
                if (this.tablona[i][j] != null) {
                    grafica += "S" + this.tablona[i][0] + "->S" + this.tablona[i][j] + "[label = \"" + aux + "\"] \n";
                }
            }
        }
        grafica += "} \n";
        GenerarDot(grafica);

    }

    public void GenerarDot(String cadena) {
        FileWriter fichero = null;
        PrintWriter escritor = null;
        try {
            String name = "DOTS_201900528\\AFD" + this.name + ".dot";
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

        String file_input_path = "DOTS_201900528\\AFD" + this.name + ".dot";
        String do_path = "C:\\Program Files\\Graphviz\\bin\\dot.exe";

        String file_get_path = "AFD_201900528\\AFD" + this.name + ".jpg";
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
