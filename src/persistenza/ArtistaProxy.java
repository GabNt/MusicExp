package persistenza;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import entita.Artista;
import entita.Evento;

public class ArtistaProxy extends Artista implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DataSource dataSource;

	public ArtistaProxy(DataSource dataSource) 
	{
		this.dataSource = dataSource;
	}

	@Override
	public Set<Evento> getEventi() 
	{
		Set<Evento> eventi = new HashSet<>();
		Connection connection=dataSource.getConnection();
		try {
			String cerca = "select evento from esibizione where artista= ?";
			PreparedStatement statement = connection.prepareStatement(cerca);
			statement.setString(1, this.getNome());
			ResultSet result = statement.executeQuery();
			while (result.next())
			{
				Evento e=new Evento();
				e.setID(result.getInt("evento"));
				eventi.add(e);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}	
		this.setEventi(eventi);
		return super.getEventi(); 
	}

}
