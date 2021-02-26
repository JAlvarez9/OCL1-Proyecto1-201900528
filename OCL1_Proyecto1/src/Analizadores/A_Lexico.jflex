/*--------------------------------------------------
 ------------  1ra Area: Codigo de Usuario ---------
 ---------------------------------------------------*/

//------> Paquetes,importaciones
package Analizadores;
import Errores.ErroresL;
import java_cup.runtime.*;
import javax.swing.JOptionPane;

/*----------------------------------------------------------
  ------------  2da Area: Opciones y Declaraciones ---------
  ----------------------------------------------------------*/
%%
%{
    //----> Codigo de usuario en sintaxis java
%}

//-------> Directivas
%public 
%class Analizador_Lexico
%cupsym Simbolos
%cup
%char
%column
%full
%ignorecase
%line
%unicode

//------> Expresiones Regulares
digito = [0-9]
letra = [a-zA-ZÑñ]
esp = [!-/] | [:-@] | [\\-`] | [{-}]
cadena = [\"][^\"\n]+[\"]
id = {letra}({letra}|{ digito }|_)*


LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]


comentariosimple    = "//" {InputCharacter}* {LineTerminator}?

//------> Estados

%%

/*------------------------------------------------
  ------------  3ra Area: Reglas Lexicas ---------
  ------------------------------------------------*/

//-----> Simbolos

"."         { System.out.println("Reconocio "+yytext()+" conca"); return new Symbol(Simbolos.conca, yycolumn, yyline, yytext()); }
"|"         { System.out.println("Reconocio "+yytext()+" or"); return new Symbol(Simbolos.or, yycolumn, yyline, yytext()); }
"*"         { System.out.println("Reconocio "+yytext()+" cierre"); return new Symbol(Simbolos.cierre, yycolumn, yyline, yytext()); }
"+"         { System.out.println("Reconocio "+yytext()+" mas"); return new Symbol(Simbolos.mas, yycolumn, yyline, yytext()); }
"{"         { System.out.println("Reconocio "+yytext()+" corchea"); return new Symbol(Simbolos.corchea, yycolumn, yyline, yytext()); }
"}"         { System.out.println("Reconocio "+yytext()+" corchec"); return new Symbol(Simbolos.corchec, yycolumn, yyline, yytext()); }
"-"         { System.out.println("Reconocio "+yytext()+" guion"); return new Symbol(Simbolos.guion, yycolumn, yyline, yytext()); }
">"         { System.out.println("Reconocio "+yytext()+" mayork"); return new Symbol(Simbolos.mayork, yycolumn, yyline, yytext()); }
"<"         { System.out.println("Reconocio "+yytext()+" menork"); return new Symbol(Simbolos.menork, yycolumn, yyline, yytext()); }
"%"         { System.out.println("Reconocio "+yytext()+" prct"); return new Symbol(Simbolos.prct, yycolumn, yyline, yytext()); }
":"         { System.out.println("Reconocio "+yytext()+" dspts"); return new Symbol(Simbolos.dspts, yycolumn, yyline, yytext()); }
";"         { System.out.println("Reconocio "+yytext()+" ptcoma"); return new Symbol(Simbolos.ptcoma, yycolumn, yyline, yytext()); }
"~"         { System.out.println("Reconocio "+yytext()+" curv"); return new Symbol(Simbolos.curv, yycolumn, yyline, yytext()); }
"?"         { System.out.println("Reconocio "+yytext()+" inte"); return new Symbol(Simbolos.inte, yycolumn, yyline, yytext()); }
"\\n"         { System.out.println("Reconocio "+yytext()+" sltln"); return new Symbol(Simbolos.sltln, yycolumn, yyline, yytext()); }
"\\'"         { System.out.println("Reconocio "+yytext()+" cmllsmpl"); return new Symbol(Simbolos.cmllsmpl, yycolumn, yyline, yytext()); }
"\\\""         { System.out.println("Reconocio "+yytext()+" cmlldbl"); return new Symbol(Simbolos.cmlldbl, yycolumn, yyline, yytext()); }

//-----> Palabras reservadas

"CONJ"         { System.out.println("Reconocio "+yytext()+" conj"); return new Symbol(Simbolos.conj, yycolumn, yyline, yytext()); }

//-------> Simbolos ER
{digito}    { System.out.println("Reconocio "+yytext()+" digito"); return new Symbol(Simbolos.digito, yycolumn, yyline, yytext()); }
{letra}    { System.out.println("Reconocio "+yytext()+" letra"); return new Symbol(Simbolos.letra, yycolumn, yyline, yytext()); }
{esp}    { System.out.println("Reconocio "+yytext()+" esp"); return new Symbol(Simbolos.esp, yycolumn, yyline, yytext()); }
{cadena}    { System.out.println("Reconocio "+yytext()+" cadena"); return new Symbol(Simbolos.cadena, yycolumn, yyline, yytext()); }
{id}    { System.out.println("Reconocio "+yytext()+" id"); return new Symbol(Simbolos.id, yycolumn, yyline, yytext()); }

//------> Espacios
{comentariosimple}      {System.out.println("Comentario: "+yytext()); }
[ \t\r\n\f]             {/* Espacios en blanco, se ignoran */}

//------> Errores Lexicos
.                       { ErroresL err = new ErroresL("Lexico",yytext(), yyline, yycolumn); err.SendError();}


