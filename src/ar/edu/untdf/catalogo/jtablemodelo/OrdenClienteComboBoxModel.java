/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.untdf.catalogo.jtablemodelo;

import java.util.List;

import javax.swing.DefaultComboBoxModel;

import ar.edu.untdf.catalogo.controller.OrdenClienteJpaController;
import ar.edu.untdf.catalogo.controller.TipoUsuarioJpaController;
import ar.edu.untdf.catalogo.modelo.OrdenCliente;
import ar.edu.untdf.catalogo.modelo.TipoUsuario;


/**
 *
 * @author Martin
 */
public class OrdenClienteComboBoxModel extends DefaultComboBoxModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrdenClienteComboBoxModel(OrdenClienteJpaController ex){
     List<OrdenCliente> a=ex.findOrdenClienteEntities();
     for(OrdenCliente m:a)this.addElement(m);
    };
   
   
}
