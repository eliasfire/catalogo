package ar.edu.untdf.catalogo.modelo;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;


/**
 * The persistent class for the producto database table.
 * 
 */
@Entity
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idproducto;

	private String descripcion;

	@Lob
	private byte[] foto;

	private BigDecimal precio;

	private String titulo;

	//bi-directional many-to-one association to CategoriaProducto
	@OneToMany(mappedBy="producto", cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	private List<CategoriaProducto> categoriaProductos;

	//bi-directional many-to-one association to OrdenItem
	@OneToMany(mappedBy="producto", cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	private List<OrdenItem> ordenItems;

	public Producto() {
	}

	public int getIdproducto() {
		return this.idproducto;
	}

	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public byte[] getFoto() {
		return this.foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public BigDecimal getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<CategoriaProducto> getCategoriaProductos() {
		return this.categoriaProductos;
	}

	public void setCategoriaProductos(List<CategoriaProducto> categoriaProductos) {
		this.categoriaProductos = categoriaProductos;
	}

	public CategoriaProducto addCategoriaProducto(CategoriaProducto categoriaProducto) {
		getCategoriaProductos().add(categoriaProducto);
		categoriaProducto.setProducto(this);

		return categoriaProducto;
	}

	public CategoriaProducto removeCategoriaProducto(CategoriaProducto categoriaProducto) {
		getCategoriaProductos().remove(categoriaProducto);
		categoriaProducto.setProducto(null);

		return categoriaProducto;
	}

	public List<OrdenItem> getOrdenItems() {
		return this.ordenItems;
	}

	public void setOrdenItems(List<OrdenItem> ordenItems) {
		this.ordenItems = ordenItems;
	}

	public OrdenItem addOrdenItem(OrdenItem ordenItem) {
		getOrdenItems().add(ordenItem);
		ordenItem.setProducto(this);

		return ordenItem;
	}

	public OrdenItem removeOrdenItem(OrdenItem ordenItem) {
		getOrdenItems().remove(ordenItem);
		ordenItem.setProducto(null);

		return ordenItem;
	}
	
	
	
	 public String toString(){
	        return descripcion;
	        }

}