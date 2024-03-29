/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.untdf.catalogo.views;



import javax.persistence.Persistence;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import ar.edu.untdf.catalogo.CatalogoApp;
import ar.edu.untdf.catalogo.controller.CategoriaJpaController;
import ar.edu.untdf.catalogo.controller.ProductoJpaController;
import ar.edu.untdf.catalogo.jtablemodelo.CategoriaComboBoxModel;
import ar.edu.untdf.catalogo.jtablemodelo.CategoriasProductosTableListener;
import ar.edu.untdf.catalogo.jtablemodelo.CategoriasProductosTableModel;
import ar.edu.untdf.catalogo.jtablemodelo.ProductoComboBoxModel;
import ar.edu.untdf.catalogo.modelo.Categoria;
import ar.edu.untdf.catalogo.modelo.CategoriaProducto;
import ar.edu.untdf.catalogo.modelo.Producto;

import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
/**
 *
 * @author Matias
 */
public class CategoriasProductos extends javax.swing.JInternalFrame {

    
    private ProductoJpaController ex = new ProductoJpaController(Persistence.createEntityManagerFactory("catalogo"));		
    private CategoriaJpaController ex1 = new CategoriaJpaController(Persistence.createEntityManagerFactory("catalogo"));		

    private static final long serialVersionUID = 1L;
	/**
     * Creates new form CategoriasProductos
     */
    public CategoriasProductos() {
		setForeground(Color.GREEN);
		setRootPaneCheckingEnabled(false);
		setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setSize(458,448);
		setTitle("Administracion de CategoriasProductos");
		setClosable(true);
        initComponents();
        CategoriasProductosTableListener listener = new CategoriasProductosTableListener(this);
        tableCategoriasProductos.getSelectionModel().addListSelectionListener(listener);
        
        
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

   
    public JTable getTableCategoriasProductos() {
        return tableCategoriasProductos;
    }

    public void setTableCategoriasProductos(JTable tableCategoriasProductos) {
        this.tableCategoriasProductos = tableCategoriasProductos;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableCategoriasProductos = new javax.swing.JTable();
        nuevo = new javax.swing.JButton();
        aceptar = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        comboBoxCategoria = new JComboBox<Producto>();
        comboBoxProducto = new JComboBox<Producto>();


        tableCategoriasProductos.setModel(new ar.edu.untdf.catalogo.jtablemodelo.CategoriasProductosTableModel());
        tableCategoriasProductos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableCategoriasProductos.setShowHorizontalLines(false);
        jScrollPane1.setViewportView(tableCategoriasProductos);

        nuevo.setText("nuevo");
        nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoActionPerformed(evt);
            }
        });

        aceptar.setText("aceptar");
        aceptar.setEnabled(false);
        aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarActionPerformed(evt);
            }
        });

        cancelar.setText("cancelar");
        cancelar.setEnabled(false);
        
        comboBoxCategoria.setModel(new CategoriaComboBoxModel(ex1));
        comboBoxCategoria.setSelectedIndex(1);
        comboBoxProducto.setModel(new ProductoComboBoxModel(ex));
        comboBoxProducto.setSelectedIndex(1);

        JLabel lblOrigen = new JLabel("Categoria");
        
        JLabel lblDestino = new JLabel("Producto");
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
        			.addGap(18)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(lblOrigen)
        					.addGap(8)
        					.addComponent(comboBoxCategoria, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(lblDestino)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(comboBoxProducto, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        			.addPreferredGap(ComponentPlacement.UNRELATED, 20, Short.MAX_VALUE)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(cancelar, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
        				.addComponent(aceptar, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
        				.addComponent(nuevo, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
        			.addGap(17))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(30)
        					.addComponent(nuevo)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(aceptar))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addGroup(groupLayout.createSequentialGroup()
        							.addGap(44)
        							.addComponent(lblOrigen))
        						.addGroup(groupLayout.createSequentialGroup()
        							.addGap(41)
        							.addComponent(comboBoxCategoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        					.addGap(15)
        					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(lblDestino)
        						.addComponent(comboBoxProducto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(cancelar)
        			.addGap(27)
        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
        			.addGap(41))
        );
        getContentPane().setLayout(groupLayout);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoActionPerformed
    aceptar.setEnabled(true);
    cancelar.setEnabled(true);
    
    }//GEN-LAST:event_nuevoActionPerformed

    private void aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarActionPerformed
        CategoriaProducto c=new CategoriaProducto();
        c.setCategoria((Categoria) comboBoxCategoria.getSelectedItem());
        c.setProducto((Producto) comboBoxProducto.getSelectedItem());
        CatalogoApp.getCategoriaProductoC().create(c);
        tableCategoriasProductos.setModel(new CategoriasProductosTableModel());
        aceptar.setEnabled(false);
        cancelar.setEnabled(false);
        
        
    }//GEN-LAST:event_aceptarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptar;
    private javax.swing.JButton cancelar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton nuevo;
    private javax.swing.JTable tableCategoriasProductos;
    private javax.swing.JComboBox<Producto> comboBoxProducto;
    private javax.swing.JComboBox<Producto> comboBoxCategoria;

}
