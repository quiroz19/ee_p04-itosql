/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listaligada;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import listaligada.Bd;


/**
 * Esta es la clase Mostrar() en la cual se muestran los datos de la tabla y se ordenan segun el campo seleccionado
 * @author Anita, Andy, Zuleyka 
 */
public class Mostrar extends javax.swing.JFrame {
    Tabla actual = null;
    
    /**
     * Creates new form Mostrar
     */
    public Mostrar() {
        lf();
        initComponents();
        llenaTablas();
    }
  /**
     * Este es el metodo llenaTablas() en el cual se recorre la base de datos y se añaden los nombres de las tablas
     * al boton deslizable (que esta al lado del boton mostrar) para poder seleccionar la tabla a mostrar
     */
    public void llenaTablas(){
        for(int i = 0; i < Bd.based.size(); i++)
            jComboBox1.addItem(((Tabla) Bd.based.get(i).getDato()).getNombre());
    }
    /***
     * Este es el metodo llenaCampos() en el cual se agregan los datos de la tabla a cada Item del boton despegable para 
     * mostrar los nombres de los campos y poder seleccionar el campo segun el cual el usuario desea que se ordenen los 
     * datos de la tabla
     */
    public void llenaCampos(){
        jComboBox2.removeAllItems();
        ListaLigada campos = (ListaLigada) actual.getRegistros().get(0).getDato();
        for(int j = 0; j < campos.size(); j++)
            jComboBox2.addItem(((Campo) campos.get(j).getDato()).getNombre());
    }
    /***
     * Este es el metodo seleccionaTabla() en el cual se recorre la lista based y  seleccionan los 
     * nombres de las tablas agregadas a la misma 
     */
    public void seleccionaTabla(){
        for(int i = 0; i < Bd.based.size(); i++){
           actual = (Tabla) Bd.based.get(i).getDato();
            if(actual.getNombre().equalsIgnoreCase(jComboBox1.getSelectedItem().toString())){
                break;
            }
        }
        llenaCampos();
    }
    /**
     * Este es el metodo muestraCampos() en el cual se crea las listas ligadas registros y campos para poder seleccionar
     * los campos de la tabla a mostrar los datos de los mismos
     */
    public void muestraCampos(){
        ListaLigada registros, campos;
        Campo datos;
        DefaultTableModel dtm;
        ArrayList nombres, reg, c;
        Object [][] info;
        int x = 0, y = 0;
        
        if(actual != null){
            registros = (ListaLigada) actual.getRegistros();
            campos = (ListaLigada) registros.getInicio().getDato();
            nombres = new ArrayList();
            for(int i = 0; i < campos.size(); i++){
                System.out.println(i+","+campos.get(i).getDato());
                datos = (Campo) campos.get(i).getDato();
                nombres.add(datos.getNombre());
            }
            
            reg = new ArrayList();
            x = registros.size();
            for(int i = 1; i < x; i++){
                c = new ArrayList();
                campos = (ListaLigada) registros.get(i).getDato();
                y = campos.size();
                for(int j = 0; j < y; j++)
                    c.add(campos.get(j).getDato());
                reg.add(c);
            }
                
            info = new Object[x - 1][y];
            for(int i = 0; i < x - 1; i++)
                info[i] = ((ArrayList) reg.get(i)).toArray();

            dtm = new DefaultTableModel(info, nombres.toArray());
            jTable1.setModel(dtm);
        }
    }
    
    //nuevo
    public int getNumRegistros(){
        return ((ListaLigada) actual.getRegistros()).size();
    }

    public ListaLigada getRegistro(int pos){
        return ((ListaLigada) actual.getRegistros().get(pos).getDato());
    }

    public String getDato(ListaLigada reg){
        return reg.get(jComboBox2.getSelectedIndex()).getDato().toString();
    }
    
    public boolean esMayor(ListaLigada reg1, ListaLigada reg2){
        return getDato(reg1).compareToIgnoreCase(getDato(reg2)) > 0;
    }
    
    public int swap(int i, int j){
        ListaLigada registros = (ListaLigada) actual.getRegistros();
        ListaLigada reg1 = getRegistro(i);
        ListaLigada reg2 = getRegistro(j);
        int ultimo = -1;

        if(esMayor(reg1, reg2)){
            registros.eliminarPos(j);
            registros.eliminarPos(i);
            registros.AgregarPos(reg2, i);
            registros.AgregarPos(reg1, j);
            ultimo = i;
        }
        
        return ultimo;
    }
  /*
    * Este es el metodo burbuja en el cual empleamos una variable auxiliar y dos ciclos for,
    * de los cuales uno comienza por el inicio y el otro por el final hacia la mitad de la lista,
    * despues comparamos mediante condiciones si un dato es mayor que el otro de manera que nos
    * indique si debemos relizar intercambios y si es necesario se realizan estos intercambios 
    */
    public void burbuja(){
        Integer aux;
        int comparacion =0;
        int intercambios=0;
        int sel = jComboBox2.getSelectedIndex();
        
        ListaLigada registros = (ListaLigada) actual.getRegistros(), reg1, reg2;
        String dato1, dato2;

        for(int i = 1; i < registros.size(); i++){
            for(int j = registros.size() - 1; j > i ; j--){
                reg1 = (ListaLigada) registros.get(j - 1).getDato();
                dato1 = reg1.get(sel).getDato().toString();
                reg2 = (ListaLigada) registros.get(j).getDato();
                dato2 = reg2.get(sel).getDato().toString();
                if(dato1.compareToIgnoreCase(dato2) > 0){
                    registros.eliminarPos(j);
                    registros.eliminarPos(j - 1);
                    registros.AgregarPos(reg2, j - 1);
                    registros.AgregarPos(reg1, j);
                }
            }
        }

    }
    /*
    * Este es el metodo de burbujaSeñal() el cual  es practicamente similar con la diferencia de que como
    * su nombre lo indica, este metodo incluye una señal de donde debemos detener nuestro ciclo while
    * utilizando una bandera, de la misma forma realizamos tambien comparaciones para ver 
    * que intercambios realizar dentro de la lista de datos
    */
    public void burbujaSeñal(){
        Integer aux=0 , i=0;
        int comparacion=0;
        int intercambio=0;
        boolean band =false;
        int sel=jComboBox2.getSelectedIndex();
        
      ListaLigada registros=(ListaLigada)actual.getRegistros(),re1, re2 ;
      String dat1, dat2;
      
      while( i < registros.size()-1 && band == false){
          band =true;
          
        for(int j= 1; j < registros.size()-1; j++){
            comparacion ++;
            re1=(ListaLigada) registros.get(j+1).getDato();
            dat1=re1.get(sel).getDato().toString();
            re2=(ListaLigada) registros.get(j).getDato();
            dat2=re2.get(sel).getDato().toString();
         if(dat1.compareToIgnoreCase(dat2)>0){
             registros.eliminarPos(j+1);
             registros.eliminarPos(j);
             registros.AgregarPos(re1, j);
             registros.AgregarPos(re2, j+1);
             band=false;
         }   
            
        }
       i++;
      }
    }
    /*
    * Este es el metodo shaker en el cual empleamos dos variables (izq y der) de manera que ambos se van
    * recorriendo hacia el centro de la lista de datos y de igual forma que en los metodos anteriores mediante
    * comparaciones verificamos que datos es menor en comparacion con otro y se realizan los intercambios en caso
    * de ser necesario. 
    */
    public void shakersort(){
        int i, retorno, ultimo = 1, izq = 2, der = getNumRegistros() - 1;
      
        while(izq <= der){
            for(i = der; i >= izq; i--){
                retorno = swap(i - 1, i);
                if(retorno > -1)
                    ultimo = retorno;
            }
            
            izq = ultimo + 1;
            for(i = izq; i <= der; i++){
                retorno = swap(i - 1, i);
                if(retorno > -1)
                    ultimo = retorno;
            }
            der = ultimo - 1;
        }
    }
     /*
    * Este es el metodo de insercionBinaria()que en si es el metodo de 
    *la baraja binaria, el cual toma dos variables (izquierda y derecha)y con la ayuda
    *de un for inicia un recorrido al frente y guarda en una variable auxiliar el dato en la posicion actual
    *utilizamos un while para comparar si el dato es menor o igual enseguida realizamos comparaciones 
    *para poder ver que intercambios realizar
    */
   public void insercionBinaria(){
    	int izq, der, med;
        ListaLigada registros = (ListaLigada) actual.getRegistros(), aux;
       
        for(int i = 3; i < getNumRegistros(); i++){
        	aux = getRegistro(i);
            izq = 1;
            der = i - 1;
            
	        while(izq <= der){
	            med = (izq + der) / 2;
	            if(esMayor(aux, getRegistro(med)))
	                izq = med + 1;
	            else
	                der = med - 1;
	        }
            registros.eliminarPos(i);
            registros.AgregarPos(aux, izq);
        }
   }
      /*
    * Este es el metodo seleccionDirecta() el cual esta apoyado por variables para hacer recorridos
    * dentro de la misma lista del cual va tomando un dato y los compara mediante
    *el recorrido de dos for y comparado si es menor que el dato tomado si es asi 
    * el dato con posicion actual es el nuevo dato "menor"
    */
   public void seleccionDirecta(){
	   int minimo;
	   
	   for(int i = 2; i < getNumRegistros() - 1; i++){
		   minimo = i;
		   for(int j = i + 1; j < getNumRegistros(); j++)
			   if(esMayor(getRegistro(minimo), getRegistro(j)))
				   minimo = j;
		   if(i != minimo)
			   swap(i, minimo);
	   }
	}
   private int partition(int left, int right){
    	int i = left, j = right;

        while (i <= j) {
        	while (esMayor(getRegistro((left + right) / 2), getRegistro(i)))
        		i++;
        	while (esMayor(getRegistro(j), getRegistro((left + right) / 2)))
        		j--;

            if (i <= j){
            	swap(i, j);
            	i++;
            	j--;
            }
        }

        return i;
    }
    /*
    * Este es el metodo shellsort() el cual es un metodo de ordenamiento que emplea variables
    *partiendo en mitades el numero de datos y comparandolos para ordenarlos,
    * apoyadose de una bandera que permite ver al while cuando terminar
    *si cierta condicion se cumple al comparar
    */
    public void shellsort(){
        int i, intercambio = getNumRegistros();
        boolean band;
      
        while(intercambio > 1){
            
            intercambio /= 2;
            band = true;
          
            while(band){
                band = false;
                i = 1;
          
                while((i + intercambio) <= getNumRegistros() - 1){
                    if(swap(i - 2, i) > -1)
                        band = true;
                    i++;

                }    
            }    
        }
    }
    /*
    * Este es el metodo baraja() el cual toma una variable auxiliar que le permite almacenar el primer dato,
    *antes de recorrer la lista utilizamos una de las variables de posicion para almacenar una posicion y
    *realizamos con un while una comparacion entre el dato auxiliar y el que esta en la posicion "x"
    */
  public void Baraja(){
        for(int j = 2; j < getNumRegistros(); j++)
            for(int i = j - 2; i > 2 && esMayor(getRegistro(i), getRegistro(j)); j--)
                swap(j, j + 1);
    }


   public void quickSort(int left, int right) {
    	int index = partition(left, right);

        if (left < index - 1)
        	quickSort(left, index - 1);
        if (index < right)
        	quickSort(index, right);
	}
    
    public void quickSort(){
    	quickSort(1, getNumRegistros() - 1);
    }
   
    
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Tablas");

        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jButton1.setText("Mostrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Ordenar por");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Burbuja", "Burbuja con señal", "Shaker Sort", "Inserción Binaria", "Selección Directa", "baraja", "Quick Sort" }));

        jButton2.setText("Aceptar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(139, 139, 139))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/**
     * Este metodo pertenece al boton deslizable en el que se puede seleccionar el metodo 
     * con el que el usuario desea que se ordene la lista y segun la opcion que seleccione el usuario se manda 
     * a llamar al metodo de ordenacion correspondiente, en el cual se ordena la tabla segun el campo
     * con el que se ordenara la misma y posteriormente se manda a llamar al metodo muestraCampos() de la 
     * clase para mostrar la tabla ordenada.
     * @param evt 
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        switch(jComboBox3.getSelectedIndex()){
            case 0:
                burbuja();
                break;
            case 1:
                burbujaSeñal();
                break;
            case 2:
                shakersort();
                break;
            case 3:
                insercionBinaria();
                break;
            
            case 4:
                seleccionDirecta();  
                break;
            case 5:
                Baraja();
                break;
            case 6:
                quickSort();
        }
        muestraCampos();
    }//GEN-LAST:event_jButton1ActionPerformed
/**
     * Este metodo pertenece al boton de Aceptar con el cual se cerrara la ventana de la clase
     * @param evt 
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        System.exit(WIDTH);
    }//GEN-LAST:event_jButton2ActionPerformed
  /**
     * En este metodo se mandan a llamar los metodos seleccionaTabla() y muestraCampos() de la clase
     * @param evt 
     */
    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        seleccionaTabla();
        muestraCampos();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void lf() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Mostrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mostrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mostrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mostrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
