package ar.edu.untdf.catalogo.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByUsuarioId", query = "SELECT u FROM Usuario u WHERE u.idusuario = :usuarioid"),
    @NamedQuery(name = "Usuario.findByUsuario", query = "SELECT u FROM Usuario u WHERE u.usuario = :usuario")})
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idusuario;

	private String password;

	private String usuario;

	//bi-directional many-to-one association to OrdenCliente
	@OneToMany(mappedBy="usuario", cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	private List<OrdenCliente> ordenClientes;

	//bi-directional many-to-one association to TipoUsuario
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="idtipo_usuario")
	private TipoUsuario tipoUsuario;

	public Usuario() {
	}

	public int getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public List<OrdenCliente> getOrdenClientes() {
		return this.ordenClientes;
	}

	public void setOrdenClientes(List<OrdenCliente> ordenClientes) {
		this.ordenClientes = ordenClientes;
	}

	public OrdenCliente addOrdenCliente(OrdenCliente ordenCliente) {
		getOrdenClientes().add(ordenCliente);
		ordenCliente.setUsuario(this);

		return ordenCliente;
	}

	public OrdenCliente removeOrdenCliente(OrdenCliente ordenCliente) {
		getOrdenClientes().remove(ordenCliente);
		ordenCliente.setUsuario(null);

		return ordenCliente;
	}

	public TipoUsuario getTipoUsuario() {
		return this.tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

}