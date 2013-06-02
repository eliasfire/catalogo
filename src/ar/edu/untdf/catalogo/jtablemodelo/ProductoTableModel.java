/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.untdf.catalogo.jtablemodelo;

import ar.edu.untdf.catalogo.CatalogoApp;
import ar.edu.untdf.catalogo.controller.CategoriaJpaController;
import ar.edu.untdf.catalogo.controller.ProductoJpaController;
import ar.edu.untdf.catalogo.modelo.Producto;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Matias
 */
public class ProductoTableModel extends AbstractTableModel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ProductoJpaController productoC;
    Object[] Productos;
    public ProductoTableModel(){ 
        super();
       productoC=CatalogoApp.getProductoC();
        this.Productos= productoC.findProductoEntities().toArray();
    }
    @Override
  public int getRowCount(){
      return productoC.getProductoCount();
  };
  
    @Override
  public int getColumnCount(){
  return 4;
  };
    @Override
  public Object getValueAt(int row, int column){
    switch(column){
        case 0:return ((Producto)Productos[row]).getIdproducto();
        case 1:return ((Producto)Productos[row]).getDescripcion();
        case 2:return ((Producto)Productos[row]).getPrecio();
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
