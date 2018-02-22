package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import entita.Experience;
import persistenza.DatabaseManager;
import persistenza.EventoProxy;
import persistenza.dao.EventoDao;

/**
 * Servlet implementation class DammiExperience
 */
@WebServlet("/DammiExperience")
public class DammiExperience extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DammiExperience() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String stringId = request.getParameter("eventoID");

		int id = Integer.parseInt(stringId);
		
		EventoDao eventoDao = DatabaseManager.getInstance().getDaoFactory().getEventoDAO();
		EventoProxy eventoProxy = (EventoProxy) eventoDao.findByPrimaryKey(id);
		
		HashSet<Experience> experiences = (HashSet<Experience>) eventoProxy.getExperiences();


		JSONArray jsonArray = new JSONArray();
		for (Experience experience : experiences) {
			JSONObject jo = new JSONObject(experience);
			jsonArray.put(jo);
		}
		
		PrintWriter out = response.getWriter();
		out.println(jsonArray.toString());
		
	}

}
