package es.curso.clientes.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import es.curso.clientes.beans.Cliente;

public class ClienteDAO implements IClienteDAO{
	
	private static final String URL = "jdbc:mysql://localhost:3306/empresa3";
	private Connection conexion;

	@Override
	public void conectar(String login, String pass) throws DAOException {
		
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			conexion = DriverManager.getConnection(URL, login, pass);
		} catch (ClassNotFoundException | SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public int grabar(Cliente c) throws DAOException {
		PreparedStatement ps;
		String sql;
		int n;
		
		try {
			sql = "INSERT INTO clientes(idcliente,nombre,pais) " +
					"VALUES (?,?,?)";
			ps = conexion.prepareStatement(sql);
			ps.setString(1, c.getIdcliente());
			ps.setString(2, c.getNombre());
			ps.setString(3, c.getPais());
			
			n = ps.executeUpdate();
			
			return n;
			
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		
	}

	@Override
	public int borrar(String idcliente) throws DAOException {
		PreparedStatement ps;
		String sql;
		int n;
		
		try {
			sql = "DELETE FROM clientes WHERE idcliente = ?";
			ps = conexion.prepareStatement(sql);
			ps.setString(1, idcliente);
			
			n = ps.executeUpdate();
			
			return n;
			
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		
	}

	@Override
	public int actualizar(Cliente cliente) throws DAOException {
		PreparedStatement ps;
		String sql;
		int n;
		
		try {
			sql = "UPDATE clientes SET " +
					"nombre = ?, " +
					"pais = ? " + 
					"WHERE idcliente = ?";
			ps = conexion.prepareStatement(sql);
			ps.setString(1, cliente.getNombre());
			ps.setString(2, cliente.getPais());
			ps.setString(3, cliente.getIdcliente());
			
			n = ps.executeUpdate();
			//conexion.commit();
			
			return n;
			
		} catch (SQLException e) {
			// With try catch
			//connexion.rollback();
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public Cliente leer(String idcliente) throws DAOException {
		PreparedStatement ps;
		String sql;
		Cliente cliente = null;
		ResultSet rst;
		
		try {
			sql = "SELECT idcliente, nombre, pais FROM clientes "+
					"WHERE idcliente = ?";
			ps = conexion.prepareStatement(sql);
			ps.setString(1, idcliente);
				
			rst = ps.executeQuery();
		
			if (rst.next()){
				cliente = new Cliente();
				cliente.setIdcliente(rst.getString("idcliente"));
				cliente.setNombre(rst.getString("nombre"));
				cliente.setPais(rst.getString("pais"));
			}
	
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return cliente;
	}

	@Override
	public List<Cliente> leerTodos() throws DAOException {
		PreparedStatement ps;
		String sql;
		List<Cliente> clientes = new ArrayList<Cliente>();
		ResultSet rst;
		
		try {
			sql = "SELECT idcliente, nombre, pais FROM clientes ";
			ps = conexion.prepareStatement(sql);
				
			rst = ps.executeQuery();
		
			while (rst.next()){
				Cliente cliente = new Cliente();
				cliente.setIdcliente(rst.getString("idcliente"));
				cliente.setNombre(rst.getString("nombre"));
				cliente.setPais(rst.getString("pais"));
				
				clientes.add(cliente);
			}
	
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return clientes;
	}

	@Override
	public void cerrar() throws DAOException {
		try {
			conexion.close();
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public List<String> getPaises() throws DAOException {
		PreparedStatement ps;
		String sql;
		List<String> paises = new ArrayList<String>();
		ResultSet rst;
		
		try {
			sql = "SELECT DISTINCT pais FROM clientes ORDER BY pais";
			ps = conexion.prepareStatement(sql);
				
			rst = ps.executeQuery();
		
			while (rst.next()){
				String pais = rst.getString("pais");
				paises.add(pais);
			}
	
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return paises;
	}

	@Override
	public List<Cliente> clientesEnPais(String pais) throws DAOException {
		PreparedStatement ps;
		String sql;
		List<Cliente> clientes = new ArrayList<Cliente>();
		ResultSet rst;
		
		try {
			sql = "SELECT idcliente, nombre, pais FROM clientes WHERE pais = ?";
			ps = conexion.prepareStatement(sql);
			ps.setString(1, pais);
				
			rst = ps.executeQuery();
		
			while (rst.next()){
				Cliente cliente = new Cliente();
				cliente.setIdcliente(rst.getString("idcliente"));
				cliente.setNombre(rst.getString("nombre"));
				cliente.setPais(rst.getString("pais"));
				
				clientes.add(cliente);
			}
	
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return clientes;
	}

}
