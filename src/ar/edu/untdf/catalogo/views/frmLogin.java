
package ar.edu.untdf.catalogo.views;

import java.awt.*;
import java.awt.event.*;

import javax.persistence.Persistence;
import javax.swing.*;

import ar.edu.untdf.catalogo.controller.UsuarioJpaController;
import ar.edu.untdf.catalogo.modelo.Usuario;
import ar.edu.untdf.catalogo.modelo.VariablesGlobales;

public class frmLogin extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtusuario;
	private JPasswordField txtpassword;
	private JButton btnAceptar, btnCancelar;
	private JLabel lblusuario, lblpassword;
	private Container contenedor;
	private GridLayout cuadricula1;
	public frmLogin()
	{
		super("Acceso al Sistema");
		cuadricula1 = new GridLayout(3, 3, 10, 10);
		
		lblusuario = new JLabel("Usuario:");
		lblpassword = new JLabel("Clave:");
		
		txtusuario = new JTextField(10);
		txtpassword = new JPasswordField(10);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setMnemonic('A');
		ImageIcon imgAceptar = new ImageIcon("imagenes/user_go.png");
		btnAceptar.setIcon(imgAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setMnemonic('C');
		ImageIcon imgCancelar = new ImageIcon("imagenes/sign_cancel.gif");
		btnCancelar.setIcon(imgCancelar);
		
		contenedor = getContentPane();
		contenedor.setLayout(cuadricula1);
		contenedor.add(lblusuario);
		contenedor.add(txtusuario);
		contenedor.add(lblpassword);
		contenedor.add(txtpassword);
		contenedor.add(btnAceptar);
		contenedor.add(btnCancelar);
		
		btnAceptar.addActionListener( 
			new ActionListener()
			{
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent evento)
				{
					UsuarioJpaController user = new UsuarioJpaController(Persistence.createEntityManagerFactory("catalogo"));
					Interfaz principal = new Interfaz();
					
					String usuario;
					String clave;
					
					usuario = txtusuario.getText();
					clave = txtpassword.getText();
					
					try {
						Usuario itemUsuario = user.validarCredencial(usuario, clave);
						String x_user = itemUsuario.getUsuario();
						String x_pass = itemUsuario.getPassword();
						if ((x_user != "") && (x_pass != ""))
						{
							VariablesGlobales.idusuario=itemUsuario.getIdusuario();
							VariablesGlobales.usuario=itemUsuario.getUsuario();
							JOptionPane.showMessageDialog( frmLogin.this,
									"Bienvenido al sistema usuario: " + x_user + ", con Nivel: " + itemUsuario.getTipoUsuario().getDescripcion(),
									"Acerca de", JOptionPane.INFORMATION_MESSAGE );
							principal.setVisible(true);
							principal.setLocationRelativeTo(principal.getParent());
							dispose();
						}
					}
					catch(Exception e){
						JOptionPane.showMessageDialog( frmLogin.this,
								"Usuario o contraseña incorrecto",
								"Acerca de", JOptionPane.ERROR_MESSAGE );
						e.printStackTrace();
					}
				}
			}
		);
		
		btnCancelar.addActionListener(
			new ActionListener() {
				public void actionPerformed( ActionEvent evento )
				{
					System.exit(0);
				}
			}
		);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(300, 150);
		setVisible(true);
		setLocationRelativeTo(this.getParent());
	}
}
