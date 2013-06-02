/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.untdf.catalogo;

import ar.edu.untdf.catalogo.controller.CategoriaProductoJpaController;
import ar.edu.untdf.catalogo.controller.OrdenClienteJpaController;
import ar.edu.untdf.catalogo.controller.OrdenItemJpaController;
import ar.edu.untdf.catalogo.controller.ProductoJpaController;
import ar.edu.untdf.catalogo.controller.ClienteJpaController;
import ar.edu.untdf.catalogo.controller.TipoUsuarioJpaController;
import ar.edu.untdf.catalogo.controller.UsuarioJpaController;
import ar.edu.untdf.catalogo.controller.CategoriaJpaController;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 */
public class CatalogoApp {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogo");
    private static ClienteJpaController clienteC=new ClienteJpaController(emf);;
    private static UsuarioJpaController usuarioC=new UsuarioJpaController(emf);
    private static ProductoJpaController productoC=new ProductoJpaController(emf);
    private static CategoriaJpaController categoriaC=new CategoriaJpaController(emf);
    private static TipoUsuarioJpaController tipoUsuarioC=new TipoUsuarioJpaController(emf);
    private static OrdenItemJpaController ordenItemC=new OrdenItemJpaController(emf);
    private static OrdenClienteJpaController ordenClienteC=new OrdenClienteJpaController(emf);
    private static CategoriaProductoJpaController categoriaProductoC=new CategoriaProductoJpaController(emf);

    public static ClienteJpaController getClienteC() {
        return clienteC;
    }

    public static void setClienteC(ClienteJpaController clienteC) {
        CatalogoApp.clienteC = clienteC;
    }

    
    public static EntityManagerFactory getEmf() {
        return emf;
    }

    public static void setEmf(EntityManagerFactory emf) {
        CatalogoApp.emf = emf;
    }


	

	public static UsuarioJpaController getUsuarioC() {
		return usuarioC;
	}

	public static void setUsuarioC(UsuarioJpaController usuarioC) {
		CatalogoApp.usuarioC = usuarioC;
	}

	public static ProductoJpaController getProductoC() {
		return productoC;
	}

	public static void setProductoC(ProductoJpaController productoC) {
		CatalogoApp.productoC = productoC;
	}

	public static CategoriaJpaController getCategoriaC() {
		return categoriaC;
	}

	public static void setCategoriaC(CategoriaJpaController categoriaC) {
		CatalogoApp.categoriaC = categoriaC;
	}

	public static TipoUsuarioJpaController getTipoUsuarioC() {
		return tipoUsuarioC;
	}

	public static void setTipoUsuarioC(TipoUsuarioJpaController tipoUsuarioC) {
		CatalogoApp.tipoUsuarioC = tipoUsuarioC;
	}

	public static OrdenItemJpaController getOrdenItemC() {
		return ordenItemC;
	}

	public static void setOrdenItemC(OrdenItemJpaController ordenItemC) {
		CatalogoApp.ordenItemC = ordenItemC;
	}

	public static OrdenClienteJpaController getOrdenClienteC() {
		return ordenClienteC;
	}

	public static void setOrdenClienteC(OrdenClienteJpaController ordenClienteC) {
		CatalogoApp.ordenClienteC = ordenClienteC;
	}

	public static CategoriaProductoJpaController getCategoriaProductoC() {
		return categoriaProductoC;
	}

	public static void setCategoriaProductoC(CategoriaProductoJpaController categoriaProductoC) {
		CatalogoApp.categoriaProductoC = categoriaProductoC;
	}


	 public static void main(String args[]) {
	        //TypedQuery<Cliente> q=ClientesApp.getClienteC().getEntityManager().createQuery("select e from Cliente as e where e.apellido='gel'",Cliente.class);
	        //List<Cliente> l =q.getResultList();
	        //for(Cliente c:l) System.out.println(c.getNombre());
	       /* Cliente c= CatalogoApp.clienteC.findCliente(new Long(53));
	        System.out.println(c.getIdcliente());*/
	    }

   
}
