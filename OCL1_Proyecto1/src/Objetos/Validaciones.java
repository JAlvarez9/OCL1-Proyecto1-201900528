/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author feral
 */
public class Validaciones {
    @SerializedName("Expresion Regular")
    public String id;
    @SerializedName("Valor")
    public String cadena;
    @SerializedName("Resultado")
    public boolean aceptada;

    public Validaciones(String id, String cadena) {
        this.id = id;
        this.aceptada = false;
        if (cadena.contains("\\")) {
            cadena = cadena.replace("\\", "");
        }
        this.cadena = cadena.replace("\"", "");
    }
    
    
}
