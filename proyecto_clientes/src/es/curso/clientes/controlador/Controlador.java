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
import javax.servlet.http.HttpSession;

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
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recoger petición
		String peticion = request.getServletPath();

		switch (peticion) {
		case "/nuevoCliente.do":
			nuevoCliente(request, response);
			break;
		case "/insertarCliente.do":
			insertarCliente(request, response);
			break;
		case "/listaClientes.do":
			listaClientes(request, response);
			break;
		case "/modificarCliente.do":
			modificarCliente(request, response);
			break;
		case "/eliminarCliente.do":
			eliminarCliente(request, response);
			break;
		case "/login.do":
			doLogin(request,response);
			break;
		case "/logout.do":
			doLogout(request,response);
			break;
		default:
			paginaError("La acción no se encuentra disponible", request, response);
			break;
		}
	}

	private void listaClientes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Cliente> clientes;
		try {
			clientes = clienteDAO.leerTodos();
			request.setAttribute("clientes", clientes);
		} catch (DAOException e) {
			paginaError(e.getMessage(), request, response);
		}
		RequestDispatcher rq = request.getRequestDispatcher("listadoClientes.jsp");
		rq.forward(request, response);
	}

	private void nuevoCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cliente cliente;

		try {
			List<String> paises = clienteDAO.getPaises();
			request.setAttribute("paises", paises);

		} catch (DAOException e) {
			paginaError(e.getMessage(), request, response);
		}

		String idcliente = request.getParameter("idcliente");
		if (idcliente != null) {
			try {
				cliente = clienteDAO.leer(idcliente);
				request.setAttribute("cliente", cliente);
			} catch (DAOException e) {
				paginaError(e.getMessage(), request, response);
			}
		}

		RequestDispatcher rq = request.getRequestDispatcher("nuevoCliente.jsp");
		rq.forward(request, response);

	}

	private void insertarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Cliente nuevoCliente = new Cliente();
		nuevoCliente.setIdcliente(request.getParameter("idcliente"));
		nuevoCliente.setNombre(request.getParameter("nombre"));
		nuevoCliente.setPais(request.getParameter("pais"));
		try {
			clienteDAO.grabar(nuevoCliente);
		} catch (DAOException e) {
			paginaError(e.getMessage(), request, response);
		}

		listaClientes(request,response);

	}

	private void modificarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Cliente nuevoCliente = new Cliente();
		nuevoCliente.setIdcliente(request.getParameter("idcliente"));
		nuevoCliente.setNombre(request.getParameter("nombre"));
		nuevoCliente.setPais(request.getParameter("pais"));
		try {
			clienteDAO.actualizar(nuevoCliente);
		} catch (DAOException e) {
			paginaError(e.getMessage(), request, response);
		}

		listaClientes(request,response);
	}

	private void eliminarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idcliente = request.getParameter("idcliente");
		try {
			clienteDAO.borrar(idcliente);
		} catch (DAOException e) {
			paginaError(e.getMessage(), request, response);
		}

		listaClientes(request,response);
	}
	
	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String pass = request.getParameter("pass");
		
		if (login!= null && pass != null &&
			login.equals("admin") && pass.equals("123")) {
			// Crear sesión
			HttpSession sesion = request.getSession(true);
			
			// Almacenar login en la sesión
			sesion.setAttribute("user", login);	
			
		} 

		RequestDispatcher rq = request.getRequestDispatcher("index.jsp");
		rq.forward(request, response);
	}

	private void doLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession(true).invalidate();

		RequestDispatcher rq = request.getRequestDispatcher("index.jsp");
		rq.forward(request, response);

	}
	
	private void paginaError(String error, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		request.setAttribute("error", error);
		RequestDispatcher rq = request.getRequestDispatcher("error.jsp");
		rq.forward(request, response);
	}
}
