/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.untdf.catalogo.jtablemodelo;

import java.util.List;

import javax.swing.DefaultComboBoxModel;

import ar.edu.untdf.catalogo.controller.CategoriaJpaController;
import ar.edu.untdf.catalogo.controller.ProductoJpaController;
import ar.edu.untdf.catalogo.modelo.Categoria;

/**
 *
 * @author Martin
 */
public class CategoriaComboBoxModel extends DefaultComboBoxModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CategoriaComboBoxModel(CategoriaJpaController ex){
     List<Categoria> a=ex.findCategoriaEntities();
     for(Categoria m:a)this.addElement(m.getNombre());
    };
   
   
}
