package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entita.Utente;
import persistenza.DatabaseManager;
import persistenza.dao.UtenteDao;

/**
 * Servlet implementation class AggiornaProfilo
 */
@WebServlet("/AggiornaProfilo")
public class AggiornaProfilo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiornaProfilo() {
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
		
		UtenteDao u=DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
		
		Utente user=(Utente) request.getSession().getAttribute("user");
		
		if (!user.getMail().equals((String)request.getParameter("mail")))
		{
			user.setMail((String)request.getParameter("mail"));
			u.update(user);
			request.getSession().setAttribute("user", user);
		}
		
		if(!user.getPassword().equals((String)request.getParameter("password"))) {
			user.setPassword((String) request.getParameter("password"));
			u.update(user);
			request.getSession().setAttribute("user",user);
		}
			
		if(!user.getAvatar().equals((String)request.getParameter("avatar"))) {
			
		}
			
		
		
		if(!user.getCitta_residenza().equals((String)request.getParameter("residenza"
				+ ""))) {
			
		}
		
		
		RequestDispatcher rd=getServletContext().getRequestDispatcher("");
		rd.forward(request, response);
	}

}
