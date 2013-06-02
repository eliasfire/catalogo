/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.untdf.catalogo.jtablemodelo;

import ar.edu.untdf.catalogo.CatalogoApp;
import ar.edu.untdf.catalogo.controller.CategoriaJpaController;
import ar.edu.untdf.catalogo.modelo.Categoria;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Abraham
 */
public class CategoriaTableModel extends AbstractTableModel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CategoriaJpaController categoriaC;
	Object[] categorias;
    public CategoriaTableModel(){ 
        super();
       categoriaC=CatalogoApp.getCategoriaC();
        this.categorias= categoriaC.findCategoriaEntities().toArray();
    }
    @Override
  public int getRowCount(){
      return categoriaC.getCategoriaCount();
  };
  
    @Override
  public int getColumnCount(){
  return 4;
  };
    @Override
  public Object getValueAt(int row, int column){
    switch(column){
        case 0:return ((Categoria) categorias[row]).getIdcategoria();
        case 1:return ((Categoria) categorias[row]).getNombre();
         default:return null;
    }};
  public   String getColumnName(int columnIndex) {
    switch(columnIndex){
        case 0:return "id";
        case 1:return "codigo";
        default:return null;
    }}     

}
