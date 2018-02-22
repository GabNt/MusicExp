package persistenza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import entita.Artista;
import entita.MessaggioPubblico;
import entita.Utente;
import persistenza.dao.MessaggioPubblicoDao;
import persistenza.dao.UtenteDao;

public class MessaggioPubblicoDaoJDBC implements MessaggioPubblicoDao{

	private DataSource ds;
	
	public MessaggioPubblicoDaoJDBC(DataSource ds)
	{
		this.ds=ds;
	}
	
	@Override
	public void save(MessaggioPubblico msg) {
		// TODO Auto-generated method stub
		Connection connection = this.ds.getConnection();
		try {
			int id = AssegnaID.getId(connection);
			msg.setID(id); 	
			String insert = "insert into messaggio_pubblico(id,testo) values (?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setInt(1, msg.getID());
			statement.setString(2, msg.getTesto());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public void update(MessaggioPubblico msg) {
		// TODO Auto-generated method stub
		Connection connection = this.ds.getConnection();
		try {
			String update = "update messaggio_pubblico SET testo = ? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, msg.getTesto());
			statement.setInt(2, msg.getID());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public void delete(MessaggioPubblico msg) {
		// TODO Auto-generated method stub
		Connection connection = this.ds.getConnection();
		try {
			String delete = "delete FROM messaggio_pubblico WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, msg.getID());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public MessaggioPubblico findByPrimaryKey(int id) {
		Connection connection = this.ds.getConnection();
		MessaggioPubblico msg = null;
		try {
			PreparedStatement statement;
			String query = "select * from messaggio_pubblico where id = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				msg = new MessaggioPubblico();
				msg.setID(result.getInt("id"));	
				msg.setTesto(result.getString("testo"));
				
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
		return msg;
	}

	@Override
	public List<MessaggioPubblico> findAll() {
		Connection connection = this.ds.getConnection();
		List<MessaggioPubblico> messaggi = new LinkedList<>();
		try {
			MessaggioPubblico msg;
			PreparedStatement statement;
			String query = "select * from messaggio_pubblico";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				msg = new MessaggioPubblico();
				msg.setID(result.getInt("id"));			
				msg.setTesto(result.getString("testo"));
				
				
				messaggi.add(msg);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}	 finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return messaggi;
	}


}
