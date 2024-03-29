/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.untdf.catalogo.views;
import ar.edu.untdf.catalogo.CatalogoApp;
import ar.edu.untdf.catalogo.controller.ClienteJpaController;
import ar.edu.untdf.catalogo.controller.exceptions.NonexistentEntityException;
import ar.edu.untdf.catalogo.jtablemodelo.ClienteTableModel;
import ar.edu.untdf.catalogo.jtablemodelo.ClientesTableListener;
import ar.edu.untdf.catalogo.modelo.Cliente;

import javax.persistence.Persistence;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.border.TitledBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
/**
 *
 * @author Matias
 */
public class Clientes extends javax.swing.JInternalFrame {

	private ClienteJpaController cli = new ClienteJpaController(Persistence.createEntityManagerFactory("catalogo"));		
	private static final long serialVersionUID = 1L;
	/**
     * Creates new form Clientes
     */
    public Clientes() {
		setRootPaneCheckingEnabled(false);
		setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		setSize(401,352);
		setTitle("Administracion de Clientes");
		setClosable(true);
        initComponents();
        ClientesTableListener listener = new ClientesTableListener(this);
        tableClientes.getSelectionModel().addListSelectionListener(listener);
        
        
    }

    public JButton getAceptar() {
        return aceptar;
    }

    public void setAceptar(JButton aceptar) {
        this.aceptar = aceptar;
    }

    public JButton getCancelar() {
        return cancelar;
    }

    public void setCancelar(JButton cancelar) {
        this.cancelar = cancelar;
    }

    public JTextField getFieldNombre() {
        return fieldNombre;
    }

    public void setFieldNombre(JTextField fieldNombre) {
        this.fieldNombre = fieldNombre;
    }

    public JTable getTableClientes() {
        return tableClientes;
    }

    public void setTableClientes(JTable tableClientes) {
        this.tableClientes = tableClientes;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableClientes = new javax.swing.JTable();
        tableClientes.addMouseListener(new MouseAdapter() {
        	@Override
        	  public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        fieldNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        nuevo = new javax.swing.JButton();
        aceptar = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();

        tableClientes.setModel(new ar.edu.untdf.catalogo.jtablemodelo.ClienteTableModel());
        tableClientes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableClientes.setShowHorizontalLines(false);
        jScrollPane1.setViewportView(tableClientes);

        jLabel1.setText("Nombre");

        nuevo.setText("Agregar");
        nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoActionPerformed(evt);
            }
        });

        aceptar.setText("Guardar");
        aceptar.setEnabled(false);
        aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarActionPerformed(evt);
            }
        });

        cancelar.setText("Cancelar");
        cancelar.setEnabled(false);
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        	    cmdEliminarActionPerformed(evt);
        	}
        });
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(258)
        			.addComponent(nuevo, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(19)
        			.addComponent(jLabel1)
        			.addGap(10)
        			.addComponent(fieldNombre, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
        			.addGap(42)
        			.addComponent(aceptar, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(258)
        			.addComponent(cancelar, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(10)
        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(258)
        			.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(11)
        			.addComponent(nuevo)
        			.addGap(3)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(3)
        					.addComponent(jLabel1))
        				.addComponent(fieldNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(3)
        					.addComponent(aceptar)))
        			.addGap(6)
        			.addComponent(cancelar)
        			.addGap(16)
        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(btnEliminar))
        );
        getContentPane().setLayout(groupLayout);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoActionPerformed
    fieldNombre.setText("");
    aceptar.setEnabled(true);
    cancelar.setEnabled(true);
    
    }//GEN-LAST:event_nuevoActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        fila = tableClientes.rowAtPoint(evt.getPoint());                 
         if (fila > -1){                          
             //txtCodDep.setText(String.valueOf(tabla.getValueAt(fila, 0)));
        	 fieldNombre.setText(String.valueOf(tableClientes.getValueAt(fila, 1)));
             
         }
    }//GEN-LAST:event_tablaMouseClicked
    
    private void aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarActionPerformed
        Cliente c=new Cliente();
        c.setNombre(fieldNombre.getText());
        CatalogoApp.getClienteC().create(c);
        tableClientes.setModel(new ClienteTableModel());
        aceptar.setEnabled(false);
        cancelar.setEnabled(false);
    }//GEN-LAST:event_aceptarActionPerformed

    private void cmdEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEliminarActionPerformed
        if (fila > -1){
            int codigo =  (int) (tableClientes.getValueAt(fila, 0));            
            try {
				cli.destroy(codigo);
		        tableClientes.setModel(new ClienteTableModel());

			} catch (NonexistentEntityException e) {
				e.printStackTrace();
			}
            
            fila=-1;
        }
    }
    int fila = -1;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptar;
    private javax.swing.JButton cancelar;
    private javax.swing.JTextField fieldNombre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton nuevo;
    private javax.swing.JTable tableClientes;
}
