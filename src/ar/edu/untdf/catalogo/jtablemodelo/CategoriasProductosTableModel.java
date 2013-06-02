/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.untdf.catalogo.jtablemodelo;

import ar.edu.untdf.catalogo.CatalogoApp;
import ar.edu.untdf.catalogo.controller.CategoriaJpaController;
import ar.edu.untdf.catalogo.controller.CategoriaProductoJpaController;
import ar.edu.untdf.catalogo.modelo.CategoriaProducto;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Matias
 */
public class CategoriasProductosTableModel extends AbstractTableModel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CategoriaProductoJpaController categoriaProductoC;
    Object[] CategoriaProductos;
    public CategoriasProductosTableModel(){ 
        super();
       categoriaProductoC=CatalogoApp.getCategoriaProductoC();
        this.CategoriaProductos= categoriaProductoC.findCategoriaProductoEntities().toArray();
    }
    @Override
  public int getRowCount(){
      return categoriaProductoC.getCategoriaProductoCount();
  };
  
    @Override
  public int getColumnCount(){
  return 4;
  };
    @Override
  public Object getValueAt(int row, int column){
    switch(column){
        case 0:return ((CategoriaProducto)CategoriaProductos[row]).getCategoria().getNombre();
        case 1:return ((CategoriaProducto)CategoriaProductos[row]).getProducto().getDescripcion();
        default:return null;
    }};
  public   String getColumnName(int columnIndex) {
    switch(columnIndex){
        case 0:return "categoria";
        case 1:return "producto";
        default:return null;
    }}     

}
