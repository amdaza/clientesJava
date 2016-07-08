package es.curso.clientes.dao;

import java.util.List;
import es.curso.clientes.beans.Cliente;

public interface IClienteDAO {
	
	public void conectar(String login, String pass) throws DAOException;

	public int grabar(Cliente c) throws DAOException;
	
	public int borrar(String idcliente) throws DAOException;
	
	public int actualizar(Cliente cliente) throws DAOException;
	
	public Cliente leer(String idcliente) throws DAOException;
	
	public List<Cliente> leerTodos() throws DAOException;
	
	public List<String> getPaises() throws DAOException;
	
	public List<Cliente> clientesEnPais(String pais) throws DAOException;
	
	public void cerrar() throws DAOException;
}
