/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listaligada;

/**
 * Esta es la clase Nodo utilizada en la clase lista ligada
 * @author Anita, Andy, Zuleyka 
 */
public class Nodo 
{
    /**
     * Esta es la variable dato de tipo Object
     */
    private Object dato;
    /**
     * Esta es la variable sig de tipo Nodo
     */
    private Nodo sig;
    /***
     * Este es el constructor de la clase, el cual le asigna un valor a las variables
     * dato y sig 
     * @param d
     * @param sig 
     */
    public Nodo(Object d,Nodo sig){
        dato=d;
        this.sig=sig;
    }
    /**
     * Este es el constructor por default de la clase, en el cual se le asigna un valor a la variable dato
     * e inicializa la variable sig en null
     * @param o 
     */
    public Nodo(Object o){
        dato=o;
        sig=null;
    }
    /**
     *Este es el constructor por default de la clase en el cual se inicializan las variables
     * dato y sig en null
     */
    public Nodo(){
        dato=null;
        sig=null;
    }
    /**
     * Este es el metodo get de la variable dato
     * @return dato
     */

    public Object getDato(){return dato;}
    /**
     * Este es el metodo set de la variable dato
     * @param d 
     */
    public void setDato(Object d){dato=d;}
/**
     * Este es el metodo get de la variable sig
     * @return sig
     */
    public Nodo getSig(){return sig;}
/**
     * Este es el metodo set de la variable sig
     * @param s 
     */
    public void setSig(Nodo s){sig=s;}
  /**
     * En este metodo se sobreescribe el metodo toString de la clase Object para imprimir 
     * el dato del nodo, convirtiendo este mismo a un dato de tipo String 
     * @return dato
     */
    public String toString(){
        return dato.toString();
    }

}
