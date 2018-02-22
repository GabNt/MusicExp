package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import entita.MessaggioPrivato;
import entita.Prodotto;
import entita.Utente;
import persistenza.DatabaseManager;
import persistenza.UtenteProxy;
import persistenza.dao.MessaggioPrivatoDao;
import persistenza.dao.ProdottoDao;
import persistenza.dao.UtenteDao;

/**
 * Servlet implementation class DammiProdottiJson
 */
@WebServlet("/DammiProdottiJson")
public class DammiProdottiJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DammiProdottiJson() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();

		UtenteProxy u = (UtenteProxy) request.getSession().getAttribute("user");
	
//		ProdottoDao prodDao = DatabaseManager.getInstance().getDaoFactory().getProdottoDAO();

		Set<Prodotto> prodotti_realizzati= u.getProdotti_realizzati();
		
		Set<Prodotto> prodottiAcquistati = u.getAcquisti(); 
		
		//AGGIORNARE UTENTE PROXY CON METODO PRENDI ACQUISTI E POI CONTINUARE LA SERVLET

		JSONArray JsonArrayCreati=new JSONArray();
		JSONArray JsonArrayAcquisti=new JSONArray();
		
		for (Prodotto p: prodotti_realizzati)
		{
//			System.out.println(p.toString());
			JSONObject o=new JSONObject(p);
//			System.out.println(o.toString());
			JsonArrayCreati.put(o);
		}
		
		for (Prodotto p: prodottiAcquistati)
		{
//			System.out.println(p.toString());
			JSONObject o=new JSONObject(p);
//			System.out.println(o.toString());
			JsonArrayAcquisti.put(o);
		}

		JSONArray jsonArray = new JSONArray();
		
		jsonArray.put(JsonArrayCreati);
		jsonArray.put(JsonArrayAcquisti);

		System.out.println(jsonArray.toString());
		PrintWriter out = response.getWriter();
		out.println(jsonArray.toString());

	}

}
