/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 *
 * @author feral
 */
public class Expresiones {

    public String id;
    public Arbol raiz;
    public String type;
    public TSiguiente tablita;
    public LinkedList<NodeArbol> hojitas;
    public TTransiciones tablitatransi;
    public AFD afedesito;
    public LinkedList<NAfnd> hojitasafnd = new LinkedList<NAfnd>();
    public AFND afede;
    public LinkedList<Validaciones> list_valida = new LinkedList<Validaciones>();
    private LinkedList<Conjuntos> cinjuntos;
    public LinkedList<EstadosVali> estados = new LinkedList<EstadosVali>();
    String actual;

    public Expresiones(String id, Arbol raiz, String type) {
        this.raiz = raiz;
        this.id = id;
        this.type = type;
        this.hojitas = new LinkedList<NodeArbol>();
        this.cinjuntos = new LinkedList<Conjuntos>();

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

    public TSiguiente getTablita() {
        return tablita;
    }

    public void setTablita(TSiguiente aux) {
        this.tablita = aux;
    }

    public TTransiciones getTablitatransi() {
        return tablitatransi;
    }

    public void setTablitatransi(TTransiciones tablitatransi) {
        this.tablitatransi = tablitatransi;
    }

    public synchronized void recorridoPreorden() {
        ayudantePreorden(this.raiz.raiz);

    }

    public AFD getAfedesito() {
        return afedesito;
    }

    public void setAfedesito(AFD afedesito) {
        this.afedesito = afedesito;
    }

    public AFND getAfede() {
        return afede;
    }

    public void setAfede(AFND afede) {
        this.afede = afede;
    }

    public LinkedList<Conjuntos> getCinjuntos() {
        return cinjuntos;
    }

    public void setCinjuntos(LinkedList<Conjuntos> cinjuntos) {
        this.cinjuntos = cinjuntos;
    }

    private void ayudantePreorden(NodeArbol aux) {

        if (aux != null) {

            if (aux.hoja) {
                this.hojitas.add(aux);
            }
            if (aux.token.equals(".") || aux.token.equals("+") || aux.token.equals("*")) {
                if (!aux.hoja) {
                    this.hojitas.add(aux);
                }
            }
            ayudantePreorden(aux.hijoizq);
            ayudantePreorden(aux.hijoder);
        }

    }

    public synchronized void recorridoPostorden() {
        ayudantePostorden(this.raiz.raiz);

    }

    private void ayudantePostorden(NodeArbol aux) {

        if (aux != null) {

            ayudantePostorden(aux.hijoizq);
            ayudantePostorden(aux.hijoder);

            if (!aux.hoja) {
                NAfnd sup = new NAfnd(aux.token);
                this.hojitasafnd.add(sup);
            }

        }

    }

    public void Validacion() {
        
        
        
        for (int i = 0; i < this.list_valida.size(); i++) {
            this.actual = "0";
            boolean si = true;
            //this.list_valida.get(i).cadena = this.list_valida.get(i).cadena.replace("\\\"", "");
            for (int j = 0; j < this.list_valida.get(i).cadena.length(); j++) {
                for (EstadosVali v : this.estados) {
                    if (si) {
                        if (actual.equals(v.estado)) {
                            String asdf = String.valueOf(this.list_valida.get(i).cadena.charAt(j));
                            si = Vali(String.valueOf(this.list_valida.get(i).cadena.charAt(j)), this.cinjuntos, v.moves);
                            break;
                        }
                    }

                }

            }
            this.list_valida.get(i).aceptada = si;
            
        }

    }

    public Boolean Vali(String a, LinkedList<Conjuntos> conj, LinkedList<Direccion> dire) {

        for (Conjuntos conju : conj) {
            for (Direccion d : dire) {
                if (conju.id.equals(d.move)) {
                    if (conju.simbols.contains(a)) {
                        this.actual = d.next;
                        return true;
                    }
                }

            }
        }

        return false;
    }

    public void CompletacionConj() {
        LinkedList<String> aux = new LinkedList<String>();
        for (int i = 0; i < this.tablita.nodes.size() - 1; i++) {
            if (!aux.contains(this.tablita.nodes.get(i).node)) {
                aux.add(this.tablita.nodes.get(i).node);
            }
        }
        LinkedList<String> sup = new LinkedList<String>();
        for (int i = 0; i < this.cinjuntos.size(); i++) {
            sup.add(this.cinjuntos.get(i).id);
        }

        for (String string : aux) {
            if (sup.contains(string)) {

            } else {
                Conjuntos nuevo = new Conjuntos(string);
                this.cinjuntos.add(nuevo);
            }
        }

    }

    public void CreacionEstados() {
        LinkedList<NTrancisiones> aux = this.tablitatransi.estados;
        for (NTrancisiones a : aux) {
            EstadosVali sup = new EstadosVali(a.num, a.aceptacion);
            this.estados.add(sup);
        }
        String[][] tablitaaux = this.tablitatransi.tablona;
        for (int k = 0; k < this.estados.size(); k++) {
            for (int i = 1; i < tablitaaux.length; i++) {
                for (int j = 1; j < tablitaaux[i].length; j++) {
                    if (this.estados.get(k).estado.equals(tablitaaux[i][0])) {
                        if (tablitaaux[i][j] != null) {
                            Direccion r = new Direccion(tablitaaux[i][j], tablitaaux[0][j]);
                            this.estados.get(k).moves.add(r);
                        }
                    }
                }
            }
        }
        System.out.println("as");
    }

    public String ColocarValidaciones() {
        String cadenita = "";
        for (Validaciones v : this.list_valida) {
            if (v.aceptada) {
                cadenita += "La expresion \"" + v.cadena + "\" es valida con la expresion Regular" + v.id + "\n";
            } else {
                cadenita += "La expresion \"" + v.cadena + "\" no es valida con la expresion Regular" + v.id + "\n";
            }
        }

        return cadenita;

    }
    
    public void CrearJson(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String aux = "";
        
        aux += gson.toJson(this.list_valida);
        
        GenerarDot(aux);
    }
    public void GenerarDot(String cadena) {
        FileWriter fichero = null;
        PrintWriter escritor = null;
        try {
            String name = "SALIDAS_201900528\\"+this.id + ".json";
            fichero = new FileWriter(name);
            escritor = new PrintWriter(fichero);
            escritor.println(cadena);
            escritor.close();
            fichero.close();
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }

}
