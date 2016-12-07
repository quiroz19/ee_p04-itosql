package listaligada;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *   Esta es la clase Campo el cual se manda a llamar en la clase Crea para crear los campos de la tabla
 * @author Anita, Andy, Zuleyka 
 */
public class Campo {
    /**
     * Esta es la variable nombre de tipo String
     */
    private String nombre;
    /**
     * Esta es la variable tipo de tipo int
     */
    private int tipo;
    /**
     * Este es el constuctor de la clase
     * @param nombre
     * @param tipo 
     */
    
    public Campo(String nombre, int tipo){
        this.nombre = nombre;
        this.tipo = tipo;
    }
    /**
     * Este es el metodo get de la variable nombre
     * @return nombre
     */
    public String getNombre(){
        return nombre;
    }
    /**
     * Este es el metodo get de la variable tipo
     * @return 
     */
    
    public int getTipo(){
        return tipo;
    }
    /**
     * En este metodo se sobreescribe el metodo toString de la clase Object para poder imprimir 
     * el nombre y el tipo del campo 
     * @return el nombre y tipo del campo
     */
    
    @Override
    public String toString(){
        return nombre + "=>" + tipo; 
    }
}
