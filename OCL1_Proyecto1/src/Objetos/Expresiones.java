/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

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

    public Expresiones(String id, Arbol raiz, String type) {
        this.raiz = raiz;
        this.id = id;
        this.type = type;
        this.hojitas = new LinkedList<NodeArbol>();

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

}
