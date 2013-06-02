package ar.edu.untdf.catalogo.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the orden_items database table.
 * 
 */
@Entity
@Table(name="orden_items")
public class OrdenItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idorden_items")
	private int idordenItems;

	private int cantidad;

	//bi-directional many-to-one association to OrdenCliente
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="idorden")
	private OrdenCliente ordenCliente;

	//bi-directional many-to-one association to Producto
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="idproducto")
	private Producto producto;

	public OrdenItem() {
	}

	public int getIdordenItems() {
		return this.idordenItems;
	}

	public void setIdordenItems(int idordenItems) {
		this.idordenItems = idordenItems;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public OrdenCliente getOrdenCliente() {
		return this.ordenCliente;
	}

	public void setOrdenCliente(OrdenCliente ordenCliente) {
		this.ordenCliente = ordenCliente;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}