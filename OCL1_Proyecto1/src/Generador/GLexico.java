package Generador;

import java.io.File;

public class GLexico 
{
    public static void main(String[] args) 
    {
        String path="D:\\Github\\OCL1-Proyecto1-201900528\\OCL1_Proyecto1\\src\\Analizadores/A_Lexico.jflex";
        generarLexer(path);
    } 
    
    public static void generarLexer(String path)
    {
        File file=new File(path);
        jflex.Main.generate(file);
    } 
}
