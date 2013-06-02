/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.untdf.catalogo.jtablemodelo;

import ar.edu.untdf.catalogo.CatalogoApp;
import ar.edu.untdf.catalogo.controller.TipoUsuarioJpaController;
import ar.edu.untdf.catalogo.modelo.TipoUsuario;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Matias
 */
public class TipoUsuarioTableModel extends AbstractTableModel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TipoUsuarioJpaController clienteC;
    Object[] clientes;
    public TipoUsuarioTableModel(){ 
        super();
       clienteC=CatalogoApp.getTipoUsuarioC();
        this.clientes= clienteC.findTipoUsuarioEntities().toArray();
    }
    @Override
  public int getRowCount(){
      return clienteC.getTipoUsuarioCount();
  };
  
    @Override
  public int getColumnCount(){
  return 3;
  };
    @Override
  public Object getValueAt(int row, int column){
    switch(column){
        case 0:return ((TipoUsuario)clientes[row]).getIdtipoUsuario();
        case 1:return ((TipoUsuario)clientes[row]).getDescripcion();
        default:return null;
    }};
  public   String getColumnName(int columnIndex) {
    switch(columnIndex){
        case 0:return "id";
        case 1:return "nombre";
        default:return null;
    }}     

}
