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

/**
 *
 * @author feral
 */
public class Arbol {

    public NodeArbol raiz;
    public String id;
    public int ulthoja;

    public Arbol(NodeArbol raiz, String id, int ulthoja) {
        this.raiz = raiz;
        this.id = id;
        NodeArbol hash = new NodeArbol("#", "", ulthoja, null, null, false, String.valueOf(ulthoja), String.valueOf(ulthoja));
        NodeArbol last = new NodeArbol(".", "", -1, this.raiz, hash, false, null, null);
        this.raiz = last;

    }

    public void GraficarSintactico() {
        String grafica = "Digraph " + id + "{\n\n" + GraficaNodos(this.raiz, "0") + "\n\n}";
        GenerarDot(grafica);

    }

    private String GraficaNodos(NodeArbol nodo, String i) {
        int k = 0;
        String r = "";
        String nodoTerm = nodo.token;
        String primeros ="";
        String ultimos ="";
        String anulable = "";
        String id = String.valueOf(nodo.id);
        if (nodo.id <= 0) {
            id = String.valueOf(nodo.id);
        }
        for (int j = 0; j < nodo.primeros.size(); j++) {
            primeros += ","+ nodo.primeros.get(j);
        }
        for (int j = 0; j < nodo.ultimos.size(); j++) {
            ultimos += ","+ nodo.ultimos.get(j);
        }
        if (nodo.anulable) {
            anulable = "Anulable";
        }else{
            anulable = "No Anulable";
        }
        nodoTerm = nodoTerm.replace("\"", "");
        //r = "node" + i + "[label = \"" + nodoTerm + "\"];\n";
        r = "node" + i +"[shape =\"none\" label=< \n"
                + "<TABLE ALIGN=\"LEFT\"> \n"
                + "<TR> \n"
                + "<TD >"+primeros +"</TD> \n"
                + "<TD >"+ultimos+" </TD> \n"
                + "</TR> \n"
                + "<TR> \n"
                + "<TD >"+anulable+"</TD> \n"
                + "<TD>"+ nodo.token +"</TD> \n"
                + "</TR> \n"
                + "<TR> \n"
                + "<TD > id:"+ id+"</TD> \n"
                + "</TR>\n"
                + "</TABLE> \n"
                + ">, ];";
        for (int j = 0; j <= nodo.hijos.size() - 1; j++) {
            r = r + "node" + i + " -> node" + i + k + "\n";
            r = r + GraficaNodos(nodo.hijos.get(j), "" + i + k);
            k++;
        }

        if (!(nodo.lexema.equals(""))) {
            String nodoToken = nodo.lexema;
            nodoToken = nodoToken.replace("\"", "");
            r += "node" + i + "c[label = \"" + nodoToken + "\"];\n";
            r += "node" + i + " -> node" + i + "c\n";
        }

        return r;
    }

    private void GenerarDot(String cadena) {
        FileWriter fichero = null;
        PrintWriter escritor = null;
        try {
            String name = this.id + ".dot";
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

        String file_input_path = this.id + ".dot";
        String do_path = "C:\\Program Files\\Graphviz 2.44.1\\bin\\dot.exe";

        String file_get_path = this.id + ".jpg";
        try {
            ProcessBuilder pBuilder;
            pBuilder = new ProcessBuilder(do_path, "-Tjpg", "-o", file_get_path, file_input_path);
            pBuilder.redirectErrorStream(true);
            pBuilder.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Desktop.getDesktop().open(new File(file_get_path));
    }

}
