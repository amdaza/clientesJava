package es.curso.clientes.controlador;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {		

		try {
			clienteDAO = new ClienteDAO();
			clienteDAO.conectar("root", "root");
			/*
			clientes = clienteDAO.leerTodos();
			
			for (Cliente c : clientes){
				System.out.println(c);
			}
			*/
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
		
	}

}
