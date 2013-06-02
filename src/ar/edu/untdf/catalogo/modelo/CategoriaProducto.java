package ar.edu.untdf.catalogo.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the categoria_producto database table.
 * 
 */
@Entity
@Table(name="categoria_producto")
public class CategoriaProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_categoria_producto")
	private int idCategoriaProducto;

	//bi-directional many-to-one association to Categoria
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="idcategoria")
	private Categoria categoria;

	//bi-directional many-to-one association to Producto
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="idproducto")
	private Producto producto;

	public CategoriaProducto() {
	}

	public int getIdCategoriaProducto() {
		return this.idCategoriaProducto;
	}

	public void setIdCategoriaProducto(int idCategoriaProducto) {
		this.idCategoriaProducto = idCategoriaProducto;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}