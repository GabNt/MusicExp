package controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entita.MessaggioPrivato;
import entita.Utente;
import persistenza.DatabaseManager;
import persistenza.dao.MessaggioPrivatoDao;
import persistenza.dao.UtenteDao;

/**
 * Servlet implementation class InviaMessaggio
 */
@WebServlet("/InviaMessaggio")
public class InviaMessaggio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InviaMessaggio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente destinatario=(Utente) request.getSession().getAttribute("utente_trovato");
		
		Utente mittente=(Utente) request.getSession().getAttribute("user");
		
		String oggetto=request.getParameter("oggetto");
		
		String testo=request.getParameter("testo");
		
		MessaggioPrivatoDao msg_dao=DatabaseManager.getInstance().getDaoFactory().getMessaggioPrivatoDAO();
		
		UtenteDao ut_dao=DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
		
		MessaggioPrivato msg=new MessaggioPrivato();
		
		Date d=new Date();
		msg.setOggetto(oggetto);
		msg.setTesto(testo);
		msg.setData_invio(d);
		msg.setMittente(mittente);
		msg.setDestinatario(destinatario);
		msg_dao.save(msg);
		
//		mittente.aggiungiMessaggioPrivato(msg);
//		destinatario.add_messaggio_ricevuto(msg);
		
//		ut_dao.update(mittente);
//		ut_dao.update(destinatario);
		
		RequestDispatcher rd=getServletContext().getRequestDispatcher("/profiloUtente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
//		Utente destinatario = (Utente) request.getAttribute("utente_trovato");
//		
//		
//		Utente mittente=(Utente) request.getSession().getAttribute("user");
//		
//		String oggetto=request.getParameter("oggetto");
//		
//		String testo=request.getParameter("testo");
//		
//		MessaggioPrivatoDao msg_dao=DatabaseManager.getInstance().getDaoFactory().getMessaggioPrivatoDAO();
//		
////		UtenteDao ut_dao=DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
//		
//		MessaggioPrivato msg=new MessaggioPrivato();
//		
//		Date d=new Date();
//		msg.setOggetto(oggetto);
//		msg.setTesto(testo);
//		msg.setData_invio(d);
//		msg.setMittente(mittente);
//		msg.setDestinatario(destinatario);
//		msg_dao.save(msg);
//		
////		mittente.aggiungiMessaggioPrivato(msg);
////		destinatario.add_messaggio_ricevuto(msg);
//		
////		ut_dao.update(mittente);
////		ut_dao.update(destinatario);
//		
//		RequestDispatcher rd=getServletContext().getRequestDispatcher("/ProfiloUtente.jsp");
//		rd.forward(request, response);
	}

}
