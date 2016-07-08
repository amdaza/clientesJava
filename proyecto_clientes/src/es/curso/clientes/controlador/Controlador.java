package es.curso.clientes.controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.curso.clientes.beans.Cliente;
import es.curso.clientes.dao.ClienteDAO;
import es.curso.clientes.dao.DAOException;
import es.curso.clientes.dao.IClienteDAO;


/**
 * Servlet implementation class Controlador
 */
@WebServlet("*.do")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IClienteDAO clienteDAO;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controlador() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
        clienteDAO = new ClienteDAO();
		try {
			clienteDAO.conectar("root", "root");
		} catch (DAOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try {
			clienteDAO.cerrar();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recoger petición
		String peticion = request.getServletPath();
		switch (peticion) {
		
		case "/nuevoCliente.do":
			nuevoCliente(request,response);
			break;
		case "/insertarCliente.do":
			insertarCliente(request,response);
			break;
		case "/listaClientes.do":
			listaClientes(request,response);
			break;

		default:
			System.out.println("Error");
			break;
		}
	}

	private void listaClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Cliente> clientes;
		try {
			clientes = clienteDAO.leerTodos();
			request.setAttribute("clientes", clientes);
		} catch (DAOException e) {
			System.out.println(e.getMessage());
		}
		RequestDispatcher rq = request.getRequestDispatcher("listadoClientes.jsp");
		rq.forward(request, response);
	}

	private void nuevoCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente cliente;
	
		try {
			List<String> paises = clienteDAO.getPaises();
			request.setAttribute("paises", paises);
			
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		String idcliente = request.getParameter("idcliente");
		if(idcliente != null){
			try {
				cliente = clienteDAO.leer(idcliente);
				request.setAttribute("cliente", cliente);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		RequestDispatcher rq = request.getRequestDispatcher("nuevoCliente.jsp");
		rq.forward(request, response);
		
	}

	private void insertarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		
		Cliente nuevoCliente = new Cliente();
		nuevoCliente.setIdcliente(request.getParameter("idcliente"));
		nuevoCliente.setNombre(request.getParameter("nombre"));
		nuevoCliente.setPais(request.getParameter("pais"));
		try {
			clienteDAO.grabar(nuevoCliente);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher rq = request.getRequestDispatcher("index.jsp");
		rq.forward(request, response);
		
	}

}
