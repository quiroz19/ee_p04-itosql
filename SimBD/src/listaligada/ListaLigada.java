/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listaligada;

/**
 * Esta es la clase ListaLigada
 *
 * @author Anita, Andy, Zuleyka
 */
public class ListaLigada{
    /**
     * En esta linea se declaran las variables inicio y fin de tipo Nodo
     */
    private Nodo fin, inicio;
    /**
     * Esta es la variable cont de tipo int, la cual servira para obtener el
     * tamaño de la lista
     */
    private int cont;
    /**
     * Este es el constructor de la clase, el cual inicializa la varible cont en
     * 0 y las variables fin e inicio en null
     */

    public ListaLigada(){
       
        cont=0;
        fin=inicio=null;
    }
    /**
     * Este es el metodo Vacia() de tipo booleano utilizado para vaciar la lista
     *
     * @return
     */
    public boolean Vacia(){
        return inicio==null;
    }
    /**
     * Este es el metodo get de la variable inicio
     *
     * @return el inicio de la lista
     */
    public Nodo getInicio(){
        return inicio;
    }
   /**
     * Este es el metodo get de la variable fin
     *
     * @return el fin de la lista
     */
    public Nodo getFin(){
        return fin;
    }
    /**
     * Este es el metodo get de tipo Nodo, el cual recorre la lista y obtiene un
     * elemento del arreglo segun una posicion especifica
     *
     * @param i
     * @return recorre
     */
    public Nodo get(int i){
        Nodo recorre = inicio;
        int conteo = 0;
        while(conteo < i && recorre != null){
            recorre = recorre.getSig();
            conteo++;
        }
        
        return recorre;
    }
   /**
     * Este es el metodo agregarFin() con el cual se agrega un elemento al final
     * de la lista
     *
     * @param o
     * @return un valor de tipo boooleano
     */
    public boolean agregarFin(Object o){
        Nodo n=new Nodo(o);
        if(n!=null){
            if(!Vacia()){
                fin.setSig(n);
                fin=n;
            }
            else
                fin=inicio=n;
            cont++;
            return true;
        }
        return false;
    }
  /**
     * Este es el metodo agregar() con el cual se agrega un elemento a la lista
     *
     * @param o
     * @return un valor de tipo boooleano
     */
    public boolean agregar(Object o){
        Nodo n=new Nodo(o);
        if(n!=null){
            if(Vacia())
                inicio=n;
            else
                fin.setSig(n);
            fin=n;
            cont++;
            return true;
        }
        return false;
    }
  /**
     * *
     * Este es el metodo buscarNodo() de tipo Nodo con el cual se busca un nodo especifico
     * @param d
     * @return el elemento buscado o null en caso de no haberlo encontrado
     */
    public Nodo buscarNodo(Object d){

        Nodo aux=inicio;
        while(aux!=null){
            if(aux.getDato().equals(d)){
                return aux;
            } else aux=aux.getSig();
        }
        return null;
    }
    /**
     * Este es el metodo buscar() de tipo int
     * @param o
     * @return un valor de tipo int
     */
    public int buscar(Object o){
        int c = -1;
        if (!Vacia()){
            if(inicio.getDato()!=null){
                if(inicio.getDato().equals(o))return 0;
            }
            if(fin.getDato()!=null){
                if(fin.getDato().equals(o))return (int)cont-1;
            }
            Nodo aux = inicio;c++;
            while (c<size()){
                if(aux.getDato()!=null){
                    if (!aux.getDato().equals(o)){
                        c++;
                        aux = aux.getSig();
                    }
                    
                    else return c;
                }else
                    
                if(o==null){return c;}else{
                    c++;
                    aux = aux.getSig();
                }
            }  
            c=-1;
        }
        return c;
    }
   /**
     * Este es el metodo AgregarPos() de tipo booleano el cual agrega un elemento segun una posicion dada
     * @param obj
     * @param p
     * @return un valor de tipo booleano
     */
    public  boolean AgregarPos(Object obj,int p){
        if(p<=size()&&p>=0){
            Nodo aux=inicio,principio=null,fin1=null;
            if(p==0){Nodo n=new Nodo(obj);
                if(Vacia()){
                    return agregar(obj);}else{
                    n.setSig(aux);
                    inicio=n;cont++;return true;}  }
            if(p==size()){
                agregar(obj); return true;  }
            for(int i=0;i<size();i++){
                if(i==p){
                    Nodo pii=new Nodo(obj);
                    fin1.setSig(pii);
                    pii.setSig(aux);
                    inicio=principio;  cont++;
                    return true;   }
                if(i!=0){
                    Nodo pi=new Nodo(aux.getDato());
                    fin1.setSig(pi);
                    fin1=pi;
                    aux=aux.getSig(); }
                else{Nodo pi=new Nodo(aux.getDato());
                    principio=fin1=pi; aux=aux.getSig(); }
            } 
        }   
        return false;
    }
  /**
     * Este es el metodo size() de tipo int el cual regresa el tamaño de la lista
     * @return cont
     */
    public int size(){return cont;}

 /**
     * Este es el metodo cov de tipo double el cual regresa el dato del nodo que se le pasa como parametro al metodo
     * conviertiendo este mismo a double
     * @param c
     * @return el dato del nodo 
     */

    private double cov(Nodo c){
        //String  cc=(String)(c.getDato()) ;
      //  return Double.parseDouble((double)c.getDato());
      return (double)c.getDato();
    }
  /**
     * Este es el metodo cov1 el cual compara dos nodos convirtiendolos a String
     * @param c
     * @param n
     * @return el resultaado de la compracion
     */
    private int cov1(Nodo c,Nodo n){
        String  cc=c.getDato().toString() ;
        String  c1=n.getDato().toString() ;
        int resultado=cc.compareTo(c1);

        return resultado ;
    }
/**
     * Este es el metodo ordenNumerico() 
     */
    public void ordenNumerico(){
        if(!Vacia())
            for(Nodo x=inicio;x!=null;x=x.getSig())
                for(Nodo y=x.getSig();y!=null;y=y.getSig()){
                    if(cov(x)>cov(y)){
                        Object o=x.getDato();
                        x.setDato(y.getDato());
                        y.setDato(o);
                    }
                }
    }
/**
     * Este es el metodo ordenAlfabetico()
     */
    public void ordenAlfabetico(){
        if(!Vacia())
            for(Nodo x=inicio;x!=null;x=x.getSig())
                for(Nodo y=x.getSig();y!=null;y=y.getSig()){
                    if(cov1(x,y)>0){
                        Object o=x.getDato();
                        x.setDato(y.getDato());
                        y.setDato(o);
                    }
                }
    }
/**
     * Este es el metodo eliminarInicio() el cual elimina el primer elemento de la lista
     * @return el elemento borrado
     */
    public Object eliminarInicio(){
        Object o=null;
        if(!Vacia()){
            o=inicio.getDato();
            inicio=inicio.getSig();
            if(size()==1)
                fin=null;
            cont--;
        }
        return o;
    }
    /**
     * Este es el metodo verInicio() muestra el valor del primer nodo de la lista
     * @return inicio o null si la lista esta vacia
     */
       public Object verInicio(){
        if(!Vacia())
            return  inicio.getDato();
        return null;
    }
    /**
     * Este es el metodo
     * @param p
     * @return 
     */
    public Object verPos(int p){
        if(p==0){return verInicio();}
        if(p==size()-1){return verFinal();}
        Object o=null; int z=0;
        if(p>0&&p<cont){
            if(!Vacia())
                for(Nodo x=inicio;x!=null;x=x.getSig()){
                    if(p==z){
                        o=x.getDato();
                        return o;
                    }
                    z++;
                }
        }
        return o;
    }
   /***
     * Este es el metodo verFinal() con el cual se puede ver el ultimo elemento de la lista
     * @return elemento
     */
    public Object verFinal(){
        Object o=null;
        if(!Vacia()){
            o=fin.getDato();
            fin=inicio.getSig();
            if(size()==1){
                fin=null;
                inicio=null;}

        }
        return o;
    }
    /**
     * Este es el metodo eliminarPos() el cual elimina un elemento segun una posicion dada
     * @param p
     * @return elemento
     */
    public Object eliminarPos(int p){
        if(p<size()&&p>=0){
            Nodo aux=inicio,principio=null,fin1=null;
            if(p==0){
                return eliminarInicio(); }
            Object ccc=null;
            for(int i=0;i<size();i++){
                if(i==p&&i!=size()-1){
                    ccc=aux.getDato();
                    Nodo pi=new Nodo(aux.getDato());
                    fin1.setSig(pi);
                    fin1=pi;
                    aux=aux.getSig();
                    fin1.setDato(aux.getDato());
                    aux=aux.getSig();  }
                if(i==size()-1){
                    if(aux==null){
                        inicio=principio;
                        fin =fin1;
                        cont--;
                        return ccc;}else{
                        ccc=aux.getDato();
                        inicio=principio;
                        fin =fin1;   cont--;
                        return ccc;
                    }
                }
                if(i!=0&&i!=p){
                    Nodo pi=new Nodo(aux.getDato());
                    fin1.setSig(pi);
                    fin1=pi;
                    aux=aux.getSig();    }
                else{
                    if(i!=p){
                        Nodo pi=new Nodo(aux.getDato());
                        principio=fin1=pi;
                        aux=aux.getSig();} }
            } 
        }   
        return null;
    }
    /**
     * Este es el metodo imprime()  en el cual se manda a llamar al metodo sobrecargado imprime() y se le pasa como 
     * parametro el inicio de la lista
     */
    public void imprime(){
        imprime(inicio);
    }
    /**
     * Este es el metodo imprime() en el cual se recorre la lista y se imprimen los datos de la misma 
     * @param x 
     */
    public void imprime(Nodo x){
        if(x != null){
            System.out.println(x.getDato().toString());
            imprime(x.getSig());
        }
    }
     /**
     * En este metodo se sobreescribe el metodo toString de la clase Object en el cual se recorre la lista 
     * ligada y se van concatenando los datos de la lista para imprimirlos posteriormente.
     * @return el contenido de la lista
     */
    @Override
    public String toString(){
        String contenido = "";
        Nodo recorre = inicio;
        
        while(recorre != null){
            contenido += recorre.toString() + ", ";
            recorre = recorre.getSig();
        }
            
        return contenido + "\n";
    }
}