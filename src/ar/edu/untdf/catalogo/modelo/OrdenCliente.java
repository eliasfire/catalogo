package ar.edu.untdf.catalogo.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the orden_cliente database table.
 * 
 */
@Entity
@Table(name="orden_cliente")
public class OrdenCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idorden;

	private String direccion;

	//bi-directional many-to-one association to Cliente
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="idcliente")
	private Cliente cliente;

	//bi-directional many-to-one association to Usuario
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="idusuario")
	private Usuario usuario;

	//bi-directional many-to-one association to OrdenItem
	@OneToMany(mappedBy="ordenCliente", cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	private List<OrdenItem> ordenItems;

	public OrdenCliente() {
	}

	public int getIdorden() {
		return this.idorden;
	}

	public void setIdorden(int idorden) {
		this.idorden = idorden;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<OrdenItem> getOrdenItems() {
		return this.ordenItems;
	}

	public void setOrdenItems(List<OrdenItem> ordenItems) {
		this.ordenItems = ordenItems;
	}

	public OrdenItem addOrdenItem(OrdenItem ordenItem) {
		getOrdenItems().add(ordenItem);
		ordenItem.setOrdenCliente(this);

		return ordenItem;
	}

	public OrdenItem removeOrdenItem(OrdenItem ordenItem) {
		getOrdenItems().remove(ordenItem);
		ordenItem.setOrdenCliente(null);

		return ordenItem;
	}

}