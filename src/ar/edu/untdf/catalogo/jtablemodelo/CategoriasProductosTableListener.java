/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.untdf.catalogo.jtablemodelo;

import ar.edu.untdf.catalogo.views.CategoriasProductos;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Matias
 */
    public class CategoriasProductosTableListener implements ListSelectionListener {
    CategoriasProductos form;
    int rowSelected=0;

    // It is necessary to keep the table since it is not possible
    // to determine the table from the event's source
    public CategoriasProductosTableListener(CategoriasProductos form) {
        this.form = form;
    }
    public void valueChanged(ListSelectionEvent e) {
        
        
        // If cell selection is enabled, both row and column change events are fired
        if ( this.rowSelected!=form.getTableCategoriasProductos().getSelectedRow() && form.getTableCategoriasProductos().getSelectedRow()>0)
        { 
            System.out.println(form.getTableCategoriasProductos().getSelectedRow());
          this.rowSelected=form.getTableCategoriasProductos().getSelectedRow();
        }
 
        if (e.getValueIsAdjusting()) {
            // The mouse button has not yet been released
        }
    }
}

