package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import entita.Utente;
import persistenza.DatabaseManager;
import persistenza.dao.UtenteDao;

/**
 * Servlet implementation class RisultatiRicerca
 */
@WebServlet("/RisultatiRicerca")
public class RisultatiRicerca extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RisultatiRicerca() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String parola = request.getParameter("cerca"); //puo essere nickname, nome e cognome

		if(parola!=null)
		{
		String[] parole=parola.split("\\W+");
		UtenteDao ut=DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();

		List<Utente> utenti=ut.findAll();


		HttpSession sessione=request.getSession();

		HashSet<Utente> risultatoutenti = new HashSet<>();

		for (String s: parole)
		{
			for(Utente u:utenti)
			{
				int lung = s.length();
				if (
						(u.getNickname().length()>= lung &&
							s.substring(0, lung).equals(u.getNickname().substring(0, lung)) )
						||

						(u.getNome().length()>= lung &&
						s.substring(0, lung).equals(u.getNome().substring(0, lung)) )
						||

						(u.getCognome().length()>= lung &&
						s.substring(0, lung).equals(u.getCognome().substring(0, lung)) )
					)
				{
					risultatoutenti.add(u);
				}
			}
		}

		sessione.setAttribute("risultati_Utenti", risultatoutenti);

		}
		
		//PRIMO PARAMETRO: SCRIVERE COME SETTI NEGLI ITEMS, SECONDO PARAMETRO OGGETTO DA PASSARE		
		
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/risultatiRicerca.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
