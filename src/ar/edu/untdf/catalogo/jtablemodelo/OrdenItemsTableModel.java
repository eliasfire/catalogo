/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.untdf.catalogo.jtablemodelo;

import ar.edu.untdf.catalogo.CatalogoApp;
import ar.edu.untdf.catalogo.controller.OrdenItemJpaController;
import ar.edu.untdf.catalogo.modelo.OrdenItem;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Matias
 */
public class OrdenItemsTableModel extends AbstractTableModel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	OrdenItemJpaController usuarioC;
    Object[] OrdenItems;
    public OrdenItemsTableModel(){ 
        super();
       usuarioC=CatalogoApp.getOrdenItemC();
        this.OrdenItems= usuarioC.findOrdenItemEntities().toArray();
    }
    @Override
  public int getRowCount(){
      return usuarioC.getOrdenItemCount();
  };
  
    @Override
  public int getColumnCount(){
  return 3;
  };
    @Override
  public Object getValueAt(int row, int column){
    switch(column){
        case 0:return ((OrdenItem)OrdenItems[row]).getProducto().getIdproducto();
        case 1:return ((OrdenItem)OrdenItems[row]).getProducto().getDescripcion();
        case 2:return ((OrdenItem)OrdenItems[row]).getCantidad();
        
        default:return null;
    }};
  public   String getColumnName(int columnIndex) {
    switch(columnIndex){
        case 0:return "id";
        case 1:return "cliente";
        case 2:return "desde";
        
        default:return null;
    }}     

}
