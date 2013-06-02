/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.untdf.catalogo.jtablemodelo;

import ar.edu.untdf.catalogo.CatalogoApp;
import ar.edu.untdf.catalogo.controller.CategoriaJpaController;
import ar.edu.untdf.catalogo.controller.OrdenClienteJpaController;
import ar.edu.untdf.catalogo.modelo.OrdenCliente;
import ar.edu.untdf.catalogo.modelo.Producto;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Matias
 */
public class OrdenClienteTableModel extends AbstractTableModel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	OrdenClienteJpaController productoC;
    Object[] OrdenClientes;
    public OrdenClienteTableModel(){ 
        super();
       productoC=CatalogoApp.getOrdenClienteC();
        this.OrdenClientes= productoC.findOrdenClienteEntities().toArray();
    }
    @Override
  public int getRowCount(){
      return productoC.getOrdenClienteCount();
  };
  
    @Override
  public int getColumnCount(){
  return 4;
  };
    @Override
  public Object getValueAt(int row, int column){
    switch(column){
        case 0:return ((Producto) OrdenClientes[row]).getIdproducto();
        case 1:return ((Producto) OrdenClientes[row]).getDescripcion();
        case 2:return ((Producto) OrdenClientes[row]).getPrecio();
        default:return null;
    }};
  public   String getColumnName(int columnIndex) {
    switch(columnIndex){
        case 0:return "id";
        case 1:return "descripcion";
        case 2:return "precio";


        default:return null;
    }}     

}
