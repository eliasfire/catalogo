package ar.edu.untdf.catalogo.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the categoria database table.
 * 
 */
@Entity
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idcategoria;

	private String nombre;

	//bi-directional many-to-one association to CategoriaProducto
	@OneToMany(mappedBy="categoria", cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	private List<CategoriaProducto> categoriaProductos;

	public Categoria() {
	}

	public int getIdcategoria() {
		return this.idcategoria;
	}

	public void setIdcategoria(int idcategoria) {
		this.idcategoria = idcategoria;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<CategoriaProducto> getCategoriaProductos() {
		return this.categoriaProductos;
	}

	public void setCategoriaProductos(List<CategoriaProducto> categoriaProductos) {
		this.categoriaProductos = categoriaProductos;
	}

	public CategoriaProducto addCategoriaProducto(CategoriaProducto categoriaProducto) {
		getCategoriaProductos().add(categoriaProducto);
		categoriaProducto.setCategoria(this);

		return categoriaProducto;
	}

	public CategoriaProducto removeCategoriaProducto(CategoriaProducto categoriaProducto) {
		getCategoriaProductos().remove(categoriaProducto);
		categoriaProducto.setCategoria(null);

		return categoriaProducto;
	}

}