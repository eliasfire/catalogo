package ar.edu.untdf.catalogo.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cliente database table.
 * 
 */
@Entity
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idcliente;

	private String nombre;

	//bi-directional many-to-one association to OrdenCliente
	@OneToMany(mappedBy="cliente", cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	private List<OrdenCliente> ordenClientes;

	public Cliente() {
	}

	public int getIdcliente() {
		return this.idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<OrdenCliente> getOrdenClientes() {
		return this.ordenClientes;
	}

	public void setOrdenClientes(List<OrdenCliente> ordenClientes) {
		this.ordenClientes = ordenClientes;
	}

	public OrdenCliente addOrdenCliente(OrdenCliente ordenCliente) {
		getOrdenClientes().add(ordenCliente);
		ordenCliente.setCliente(this);

		return ordenCliente;
	}

	public OrdenCliente removeOrdenCliente(OrdenCliente ordenCliente) {
		getOrdenClientes().remove(ordenCliente);
		ordenCliente.setCliente(null);

		return ordenCliente;
	}

	public String toString(){
		return nombre;
	}
}