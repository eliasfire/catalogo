/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.untdf.catalogo.jtablemodelo;

import java.util.List;

import javax.swing.DefaultComboBoxModel;

import ar.edu.untdf.catalogo.controller.TipoUsuarioJpaController;
import ar.edu.untdf.catalogo.modelo.TipoUsuario;


/**
 *
 * @author Martin
 */
public class TipoUsuarioComboBoxModel extends DefaultComboBoxModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TipoUsuarioComboBoxModel(TipoUsuarioJpaController ex){
     List<TipoUsuario> a=ex.findTipoUsuarioEntities();
     for(TipoUsuario m:a)this.addElement(m.getDescripcion());
    };
   
   
}
