/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.untdf.catalogo.jtablemodelo;

import ar.edu.untdf.catalogo.CatalogoApp;
import ar.edu.untdf.catalogo.controller.UsuarioJpaController;
import ar.edu.untdf.catalogo.modelo.Usuario;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Matias
 */
public class UsuarioTableModel extends AbstractTableModel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UsuarioJpaController usuarioC;
    Object[] Usuarios;
    public UsuarioTableModel(){ 
        super();
       usuarioC=CatalogoApp.getUsuarioC();
        this.Usuarios= usuarioC.findUsuarioEntities().toArray();
    }
    @Override
  public int getRowCount(){
      return usuarioC.getUsuarioCount();
  };
  
    @Override
  public int getColumnCount(){
  return 4;
  };
    @Override
  public Object getValueAt(int row, int column){
    switch(column){
        case 0:return ((Usuario)Usuarios[row]).getIdusuario();
        case 1:return ((Usuario)Usuarios[row]).getUsuario();
        case 2:return ((Usuario)Usuarios[row]).getPassword();
        case 3:return ((Usuario)Usuarios[row]).getTipoUsuario().getDescripcion();
        
        default:return null;
    }};
  public   String getColumnName(int columnIndex) {
    switch(columnIndex){
        case 0:return "id";
        case 1:return "cliente";
        case 2:return "desde";
        case 3:return "hasta";
        default:return null;
    }}     

}
