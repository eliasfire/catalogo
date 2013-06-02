/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.untdf.catalogo.jtablemodelo;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ar.edu.untdf.catalogo.views.Usuarios;

/**
 *
 * @author Matias
 */
    public class UsuarioTableListener implements ListSelectionListener {
    Usuarios form;
    int rowSelected=0;

    // It is necessary to keep the table since it is not possible
    // to determine the table from the event's source
    public UsuarioTableListener(Usuarios form) {
        this.form = form;
    }
    public void valueChanged(ListSelectionEvent e) {
        
        
        // If cell selection is enabled, both row and column change events are fired
        if ( this.rowSelected!=form.getTableUsuarios().getSelectedRow() && form.getTableUsuarios().getSelectedRow()>0)
        { 
            System.out.println(form.getTableUsuarios().getSelectedRow());
          this.rowSelected=form.getTableUsuarios().getSelectedRow();
        }
 
        if (e.getValueIsAdjusting()) {
            // The mouse button has not yet been released
        }
    }
}

