package persistenza;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import entita.Evento;
import entita.Experience;

public class EventoProxy extends Evento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DataSource ds;

	public EventoProxy(DataSource ds)
	{
		this.ds=ds;
	}

	@Override
	public Set<Experience> getExperiences()
	{
			Set<Experience> experiences_disponibili = new HashSet<>();
			Connection connection = this.ds.getConnection();
			try {
				PreparedStatement statement;
				String query = "select * from experience where evento = ?";
				statement = connection.prepareStatement(query);
				statement.setInt(1, this.getID());
				ResultSet result = statement.executeQuery();
				while (result.next()) {
					Experience exp = new Experience();
					exp.setID(result.getInt("id"));				
					exp.setLimite_adesione(result.getDate("limite_adesione"));
					exp.setPosti(result.getInt("posti"));	
					exp.setDescrizione(result.getString("descrizione"));
					experiences_disponibili.add(exp);
				}
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new PersistenceException(e.getMessage());
				}
			}	

			this.setExperiences(experiences_disponibili);
		return super.getExperiences();
	}

}
