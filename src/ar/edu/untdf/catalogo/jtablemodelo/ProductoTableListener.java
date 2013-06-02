/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.untdf.catalogo.jtablemodelo;

import ar.edu.untdf.catalogo.views.Productos;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Matias
 */
    public class ProductoTableListener implements ListSelectionListener {
    Productos form;
    int rowSelected=0;

    // It is necessary to keep the table since it is not possible
    // to determine the table from the event's source
    public ProductoTableListener(Productos form) {
        this.form = form;
    }
    public void valueChanged(ListSelectionEvent e) {
        
        
        // If cell selection is enabled, both row and column change events are fired
        if ( this.rowSelected!=form.getTableProductos().getSelectedRow() && form.getTableProductos().getSelectedRow()>0)
        { 
            System.out.println(form.getTableProductos().getSelectedRow());
          this.rowSelected=form.getTableProductos().getSelectedRow();
        }
 
        if (e.getValueIsAdjusting()) {
            // The mouse button has not yet been released
        }
    }
}

