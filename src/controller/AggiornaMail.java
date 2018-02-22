package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entita.Utente;
import persistenza.DatabaseManager;
import persistenza.dao.UtenteDao;

/**
 * Servlet implementation class AggiornaMail
 */
@WebServlet("/AggiornaMail")
public class AggiornaMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiornaMail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mail=request.getParameter("email");
		
		HttpSession session=request.getSession();
		
		UtenteDao ud=DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
		
		Utente u=(Utente) session.getAttribute("user");
		
		System.out.println(mail);
		
		u.setMail(mail);
		
		ud.update(u);
		
		session.setAttribute("user",u);
		
		System.out.println(request.getRequestURI());
		
		//String bla=request.getRequestURL().toString();
		
		RequestDispatcher rd=getServletContext().getRequestDispatcher("/profilo.jsp");
		rd.forward(request, response);
		
		//response.sendRedirect(request.getHeader("referer"));
	}

}
