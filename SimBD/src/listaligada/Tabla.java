/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listaligada;

/**
 * Esta es la clase Tabla en la 
 * @author Anita, Andy, Zuleyka 
 */
public class Tabla {
    /**
     * Esta es la variable nombre de tipo String utilizada para guargar el nombre de la tabla
     */
    private String nombre;
    /***
     * Esta es la variable registros de tipo ListaLigada
     */
    private ListaLigada registros;
    /***
     * Este es el constructor de la clase
     * @param nombre 
     */
    public Tabla(String nombre){
        this.nombre = nombre;
        registros = new ListaLigada();
    }
/**
     * Este es el metodo get de la variable nombre
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }
    /***
     * Este es el metodo get de la variable resgistros
     * @return registros
     */
    public ListaLigada getRegistros() {
        return registros;
    }
    /**
     * En este metodo se sobreescribe el metodo toString de la clase Object para poder imprimir 
     * el nombre de la tabla y los registros de la misma
     * @return el nombre de la tabla y sus registros
     */
    @Override
    public String toString(){
        return "Tabla " + nombre + ":\n" + registros;
    }
}
