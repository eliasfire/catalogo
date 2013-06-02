/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.untdf.catalogo.jtablemodelo;

import java.util.List;

import javax.swing.DefaultComboBoxModel;

import ar.edu.untdf.catalogo.controller.ProductoJpaController;
import ar.edu.untdf.catalogo.modelo.Producto;


/**
 *
 * @author Martin
 */
public class ProductoComboBoxModel extends DefaultComboBoxModel<Producto> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductoComboBoxModel(ProductoJpaController ex){
     List<Producto> a=ex.findProductoEntities();
     for(Producto m:a)this.addElement(m);
    };
   
   
}
