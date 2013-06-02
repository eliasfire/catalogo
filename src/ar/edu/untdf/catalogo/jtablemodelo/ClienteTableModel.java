/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.untdf.catalogo.jtablemodelo;

import ar.edu.untdf.catalogo.CatalogoApp;
import ar.edu.untdf.catalogo.controller.ClienteJpaController;
import ar.edu.untdf.catalogo.modelo.Cliente;

import javax.swing.table.AbstractTableModel;

/**
 * @author Abraham
 */
public class ClienteTableModel extends AbstractTableModel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ClienteJpaController clienteC;
    Object[] clientes;
    public ClienteTableModel(){ 
        super();
       clienteC=CatalogoApp.getClienteC();
        this.clientes= clienteC.findClienteEntities().toArray();
    }
    @Override
  public int getRowCount(){
      return clienteC.getClienteCount();
  };
  
    @Override
  public int getColumnCount(){
  return 3;
  };
    @Override
  public Object getValueAt(int row, int column){
    switch(column){
        case 0:return ((Cliente)clientes[row]).getIdcliente();
        case 1:return ((Cliente)clientes[row]).getNombre();
        default:return null;
    }};
  public   String getColumnName(int columnIndex) {
    switch(columnIndex){
        case 0:return "id";
        case 1:return "nombre";
        default:return null;
    }}     

}
