package ar.edu.untdf.catalogo.views;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import ar.edu.untdf.catalogo.CatalogoApp;
import ar.edu.untdf.catalogo.controller.CategoriaJpaController;
import ar.edu.untdf.catalogo.controller.ClienteJpaController;
import ar.edu.untdf.catalogo.controller.OrdenClienteJpaController;
import ar.edu.untdf.catalogo.controller.OrdenItemJpaController;
import ar.edu.untdf.catalogo.controller.ProductoJpaController;
import ar.edu.untdf.catalogo.controller.UsuarioJpaController;
import ar.edu.untdf.catalogo.controller.exceptions.NonexistentEntityException;
import ar.edu.untdf.catalogo.jtablemodelo.ClienteComboBoxModel;
import ar.edu.untdf.catalogo.jtablemodelo.OrdenItemsTableModel;
import ar.edu.untdf.catalogo.jtablemodelo.ProductoComboBoxModel;
import ar.edu.untdf.catalogo.modelo.Cliente;
import ar.edu.untdf.catalogo.modelo.OrdenCliente;
import ar.edu.untdf.catalogo.modelo.OrdenItem;
import ar.edu.untdf.catalogo.modelo.Producto;
import ar.edu.untdf.catalogo.modelo.VariablesGlobales;

import javax.swing.JComboBox;
import java.awt.Image;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JFormattedTextField;

public class Factura extends javax.swing.JInternalFrame {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProductoJpaController pro = new ProductoJpaController(Persistence.createEntityManagerFactory("catalogo"));		
	private CategoriaJpaController cat = new CategoriaJpaController(Persistence.createEntityManagerFactory("catalogo"));		
	private ClienteJpaController cli = new ClienteJpaController(Persistence.createEntityManagerFactory("catalogo"));		
	private OrdenClienteJpaController ord = new OrdenClienteJpaController(Persistence.createEntityManagerFactory("catalogo"));		
	private UsuarioJpaController usu = new UsuarioJpaController(Persistence.createEntityManagerFactory("catalogo"));		
	private OrdenItemJpaController itm = new OrdenItemJpaController(Persistence.createEntityManagerFactory("catalogo"));		

	private String NumFactura="";


    /** Creates new form Factura */
    public Factura() {
    	setMinimumSize(new Dimension(800, 800));
    	setMaximizable(true);
    	setResizable(true);
    	
        initComponents();
    
        this.setTitle("Registro de Ventas");
      
        //se genera un codigo Random para Numero de factura
        txtNumFactura.setText(GenerarNumFactura() );
        txtVendedor.setText(VariablesGlobales.usuario);
        //obtiene la fecha actual del sistema y la muestra en pantalla
        Calendar c = new GregorianCalendar();
        textFecha.setText( Integer.toString(c.get(Calendar.DATE))+"/"+Integer.toString(c.get(Calendar.MONTH)) +"/"+Integer.toString(c.get(Calendar.YEAR)) );
        
        JButton buttonGuardar = new JButton();
        buttonGuardar.setBounds(375, 588, 197, 58);
        buttonGuardar.setIcon(new ImageIcon(Factura.class.getResource("/icon/update.png")));
        buttonGuardar.setText("Guardar Factura");
        
        btnEliminarProducto = new JButton();
        btnEliminarProducto.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	    int fila = -1;
				if (fila  > -1){
                    int codigo =  (int) (tableItems.getValueAt(fila, 0));            
                    try {
        				itm.destroy(codigo);
        				tableItems.setModel(new OrdenItemsTableModel());

        			} catch (NonexistentEntityException e1) {
        				e1.printStackTrace();
        			}
                    
                    fila=-1;
                }
        	}
        });
        btnEliminarProducto.setBounds(375, 323, 197, 58);
        btnEliminarProducto.setIcon(new ImageIcon(Factura.class.getResource("/javax/swing/plaf/metal/icons/Error.gif")));
        btnEliminarProducto.setText("Eliminar Producto");
        GroupLayout gl_jPanel7 = new GroupLayout(jPanel7);
        gl_jPanel7.setHorizontalGroup(
        	gl_jPanel7.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_jPanel7.createSequentialGroup()
        			.addGap(62)
        			.addComponent(jLabel15)
        			.addGap(5)
        			.addComponent(txtCantidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(10)
        			.addComponent(jLabel9)
        			.addGap(15)
        			.addComponent(jLabel14)
        			.addGap(5)
        			.addComponent(txtTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        gl_jPanel7.setVerticalGroup(
        	gl_jPanel7.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_jPanel7.createSequentialGroup()
        			.addGap(5)
        			.addComponent(jLabel15))
        		.addGroup(gl_jPanel7.createSequentialGroup()
        			.addGap(5)
        			.addComponent(txtCantidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        		.addGroup(gl_jPanel7.createSequentialGroup()
        			.addGap(13)
        			.addComponent(jLabel9))
        		.addGroup(gl_jPanel7.createSequentialGroup()
        			.addGap(5)
        			.addComponent(jLabel14))
        		.addGroup(gl_jPanel7.createSequentialGroup()
        			.addGap(6)
        			.addComponent(txtTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        jPanel7.setLayout(gl_jPanel7);
        getContentPane().setLayout(null);
        getContentPane().add(btnEliminarProducto);
        getContentPane().add(lblDireccion);
        getContentPane().add(textDireccion);
        getContentPane().add(jPanel7);
        getContentPane().add(btnRegistrar);
        getContentPane().add(jPanel5);
        getContentPane().add(jPanel1);
        getContentPane().add(jPanel10);
        getContentPane().add(buttonGuardar);
        getContentPane().add(tableItems);
        
        JLabel label = new JLabel();
        label.setText("Precio Total:");
        label.setFont(new Font("Tahoma", Font.BOLD, 14));
        label.setBounds(339, 558, 86, 17);
        getContentPane().add(label);
        
        JFormattedTextField formattedTextField = new JFormattedTextField();
        formattedTextField.setPreferredSize(new Dimension(100, 20));
        formattedTextField.setEditable(false);
        formattedTextField.setBounds(430, 559, 100, 20);
        getContentPane().add(formattedTextField);
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel1.setBounds(10, 95, 701, 63);
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNumFactura = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtVendedor = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel5.setBounds(10, 164, 701, 110);
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        comboBoxCliente = new javax.swing.JComboBox<Cliente>();
        comboBoxProducto = new javax.swing.JComboBox<Producto>();
        comboBox_1 = new javax.swing.JComboBox();
        comboBox = new javax.swing.JComboBox();

        jPanel7 = new javax.swing.JPanel();
        jPanel7.setBounds(10, 280, 562, 32);
        jLabel9 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JFormattedTextField();
        btnRegistrar = new javax.swing.JButton();
        btnRegistrar.setBounds(10, 323, 196, 58);
        jPanel10 = new javax.swing.JPanel();
        jPanel10.setBounds(10, 11, 701, 48);
        jLabel11 = new javax.swing.JLabel();

        jButton4.setText("Terminar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        setClosable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel1.setText("Factura N\u00B0:");

        txtNumFactura.setEditable(false);
        txtNumFactura.setFont(new java.awt.Font("Tahoma", 0, 18));
        txtNumFactura.setPreferredSize(new java.awt.Dimension(160, 28));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel3.setText("Vendedor:");

        txtVendedor.setEditable(false);
        txtVendedor.setFont(new java.awt.Font("Tahoma", 0, 18));
        txtVendedor.setPreferredSize(new java.awt.Dimension(380, 28));

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel9.setText("Unidades");

        txtCantidad.setFont(new java.awt.Font("Tahoma", 0, 14));
        txtCantidad.setText("0");
        txtCantidad.setPreferredSize(new java.awt.Dimension(100, 23));
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadKeyReleased(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel14.setText("Precio Total:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel15.setText("Cantidad:");

        txtTotal.setEditable(false);
        txtTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtTotal.setPreferredSize(new java.awt.Dimension(100, 20));

        btnRegistrar.setIcon(new ImageIcon(Factura.class.getResource("/icon/Tips.png"))); // NOI18N
        btnRegistrar.setText("Registrar Producto");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel11.setText("Cliente: ");
        jLabel5 = new javax.swing.JLabel();
        
                jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18));
                jLabel5.setText("Producto:");
        jLabel12 = new javax.swing.JLabel();
        
                jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14));
                jLabel12.setText("Precio/Unidad:");
        
        final JLabel lblFoto = new JLabel("Foto");
        pPrecio = new javax.swing.JFormattedTextField();
        
                pPrecio.setEditable(false);
                pPrecio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
                pPrecio.setText("0");
                pPrecio.setPreferredSize(new java.awt.Dimension(100, 20));
        
        comboBox.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        	 System.out.println(comboBox.getModel().toString());
        	 System.out.println(comboBox.getSelectedIndex());
        	 Producto datos =  (Producto) comboBox.getSelectedItem();
            
             pPrecio.setText(String.valueOf(datos.getPrecio()));
             Image imagen = null;
			try {
				imagen = pro.getImage(datos.getFoto(), false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             Icon icon = new ImageIcon(imagen);
             lblFoto.setText("");
             lblFoto.setIcon(icon);
             txtCantidad.setText("0");
             txtTotal.setText("0");
    
        	}
        });
       comboBox.setModel(new ProductoComboBoxModel(pro));
        GroupLayout gl_jPanel5 = new GroupLayout(jPanel5);
        gl_jPanel5.setHorizontalGroup(
        	gl_jPanel5.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_jPanel5.createSequentialGroup()
        			.addGap(25)
        			.addGroup(gl_jPanel5.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_jPanel5.createSequentialGroup()
        					.addComponent(jLabel12)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(pPrecio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_jPanel5.createSequentialGroup()
        					.addComponent(jLabel5)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(gl_jPanel5.createParallelGroup(Alignment.LEADING, false)
        						.addComponent(jPanel6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(jPanel9, 0, 0, Short.MAX_VALUE))
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)))
        			.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
        			.addComponent(lblFoto, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
        			.addGap(38))
        );
        gl_jPanel5.setVerticalGroup(
        	gl_jPanel5.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_jPanel5.createSequentialGroup()
        			.addGap(10)
        			.addGroup(gl_jPanel5.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblFoto, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
        				.addGroup(gl_jPanel5.createSequentialGroup()
        					.addGroup(gl_jPanel5.createParallelGroup(Alignment.LEADING)
        						.addComponent(jLabel5)
        						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addGap(18)
        					.addGroup(gl_jPanel5.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jLabel12)
        						.addComponent(pPrecio, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
        				.addGroup(gl_jPanel5.createSequentialGroup()
        					.addComponent(jPanel9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addGap(53)
        					.addComponent(jPanel6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap())
        );
                GroupLayout gl_jPanel9 = new GroupLayout(jPanel9);
                gl_jPanel9.setHorizontalGroup(
                	gl_jPanel9.createParallelGroup(Alignment.LEADING)
                		.addGap(0, 228, Short.MAX_VALUE)
                );
                gl_jPanel9.setVerticalGroup(
                	gl_jPanel9.createParallelGroup(Alignment.LEADING)
                		.addGap(0, 34, Short.MAX_VALUE)
                );
                jPanel9.setLayout(gl_jPanel9);
        jPanel5.setLayout(gl_jPanel5);
        
        textDireccion = new JTextField();
        textDireccion.setBounds(123, 69, 305, 20);
        textDireccion.setColumns(10);
        
        lblDireccion = new JLabel();
        lblDireccion.setBounds(10, 65, 107, 22);
        lblDireccion.setText("Direccion:");
        lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 18));
        GroupLayout gl_jPanel3 = new GroupLayout(jPanel3);
        gl_jPanel3.setHorizontalGroup(
        	gl_jPanel3.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_jPanel3.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jLabel3)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(txtVendedor, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
        			.addContainerGap())
        );
        gl_jPanel3.setVerticalGroup(
        	gl_jPanel3.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_jPanel3.createSequentialGroup()
        			.addGap(5)
        			.addGroup(gl_jPanel3.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel3)
        				.addComponent(txtVendedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        );
        jPanel3.setLayout(gl_jPanel3);
        GroupLayout gl_jPanel1 = new GroupLayout(jPanel1);
        gl_jPanel1.setHorizontalGroup(
        	gl_jPanel1.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_jPanel1.createSequentialGroup()
        			.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
        				.addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addGroup(gl_jPanel1.createSequentialGroup()
        					.addGap(7)
        					.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(119, Short.MAX_VALUE))
        );
        gl_jPanel1.setVerticalGroup(
        	gl_jPanel1.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_jPanel1.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
        				.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap())
        );
        GroupLayout gl_jPanel2 = new GroupLayout(jPanel2);
        gl_jPanel2.setHorizontalGroup(
        	gl_jPanel2.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_jPanel2.createSequentialGroup()
        			.addGap(7)
        			.addComponent(jLabel1)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(txtNumFactura, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
        );
        gl_jPanel2.setVerticalGroup(
        	gl_jPanel2.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_jPanel2.createSequentialGroup()
        			.addGap(5)
        			.addGroup(gl_jPanel2.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel1)
        				.addComponent(txtNumFactura, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap())
        );
        jPanel2.setLayout(gl_jPanel2);
        jPanel1.setLayout(gl_jPanel1);
        
        JPanel panel = new JPanel();
        
        JLabel lblFecha = new JLabel();
        lblFecha.setText("Fecha:");
        lblFecha.setFont(new Font("Tahoma", Font.BOLD, 18));
        
        textFecha = new JTextField();
        textFecha.setPreferredSize(new Dimension(380, 28));
        textFecha.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFecha.setEditable(false);
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(lblFecha)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(textFecha, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
        			.addContainerGap())
        );
        gl_panel.setVerticalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGap(5)
        			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblFecha)
        				.addComponent(textFecha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        );
        panel.setLayout(gl_panel);
        
        comboBox_1.setModel(new ClienteComboBoxModel(cli));

        comboBox_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println(comboBox_1.getSelectedIndex());
        	}
        });
        GroupLayout gl_jPanel10 = new GroupLayout(jPanel10);
        gl_jPanel10.setHorizontalGroup(
        	gl_jPanel10.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_jPanel10.createSequentialGroup()
        			.addComponent(jLabel11)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(124))
        );
        gl_jPanel10.setVerticalGroup(
        	gl_jPanel10.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_jPanel10.createSequentialGroup()
        			.addGroup(gl_jPanel10.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_jPanel10.createSequentialGroup()
        					.addGap(3)
        					.addGroup(gl_jPanel10.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jLabel11)
        						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        				.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10.setLayout(gl_jPanel10);
        
        tableItems = new JTable();
        tableItems.setBorder(UIManager.getBorder("EditorPane.border"));
        tableItems.setBounds(48, 399, 492, 148);
        tableItems.setModel(new ar.edu.untdf.catalogo.jtablemodelo.OrdenItemsTableModel());

        pack();
    }// </editor-fold>//GEN-END:initComponents



    /* */
    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
        double suma= Integer.valueOf(pPrecio.getText()) * Integer.valueOf( txtCantidad.getText() ) ;
        txtTotal.setText(Double.toString(suma));
    }//GEN-LAST:event_txtCantidadKeyReleased

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        //txtTotal.lostFocus(null, evt);
        if(!txtCantidad.getText().equals("0")){
     
           	 OrdenCliente c=new OrdenCliente();
        	 c.setCliente((Cliente) comboBox_1.getSelectedItem());
        	 c.setDireccion(textDireccion.getText());
        	 c.setUsuario(usu.findUsuario(VariablesGlobales.idusuario));
             CatalogoApp.getOrdenClienteC().create(c);
             
             OrdenItem o=new OrdenItem();
             o.setCantidad(Integer.parseInt(txtCantidad.getText()));
             o.setProducto((Producto) comboBox.getSelectedItem());
             o.setOrdenCliente(c);
       
             CatalogoApp.getOrdenItemC().create(o);
             tableItems.setModel(new OrdenItemsTableModel());

        }else{
            JOptionPane.showMessageDialog(this, "Error: La cantidad debe ser mayor a cero");
        }

    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    public String GenerarNumFactura(){
        String cod="FACT-";
        for(int i=1; i<=5;i++){
           int num = (int)((Math.random()*(90-65))+65);
           cod = cod  + (char) num;
        }
        NumFactura = cod;
        return cod;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
	private javax.swing.JComboBox<Cliente> comboBoxCliente;
    private javax.swing.JComboBox<Producto>  comboBoxProducto;
    private javax.swing.JComboBox  comboBox_1;
    private javax.swing.JComboBox  comboBox;

    private javax.swing.JFormattedTextField pPrecio;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtNumFactura;
    private javax.swing.JFormattedTextField txtTotal;
    private javax.swing.JTextField txtVendedor;
    private JTextField textDireccion;
    private JTable tableItems;
    private JTextField textFecha;
    private JLabel lblDireccion;
    private JButton btnEliminarProducto;
}
