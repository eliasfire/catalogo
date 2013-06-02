package ar.edu.untdf.catalogo.views;

import javax.swing.*;

public class Login {
	public static void main( String args[] )
	{
	    JFrame.setDefaultLookAndFeelDecorated(true);
	    try {
	    	///UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NapkinLookAndFeel");
	    	UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

		}
		catch (Exception e){   
			System.out.println(e);
		}
		frmLogin aplicacion = new frmLogin();
		aplicacion.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
	} 
}