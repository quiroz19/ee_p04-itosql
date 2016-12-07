/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listaligada;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.text.JTextComponent;

/**
 * Esta es la clase Bd desde la cual se manda a llamar a la clase Principal del proyecto para ejecutar el programa.
 * @author Anita, Andy, Zuleyka 
 */
public class Bd {
    /**
     * Esta es la variable based de tipo ListaLigada
     */
    public static ListaLigada based;
    /**
     * Este es el metodo main de la clase que contiene el metodo interno run() desde el cual se manda a llamar a la clase
     * Principal() para ejecutar el programa
     * @param args 
     */
    public static void main(String [] args){
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }
}
