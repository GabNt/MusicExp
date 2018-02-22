package persistenza;

import java.sql.Connection;




import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import entita.Experience;
import entita.Prodotto;
import entita.Utente;
import persistenza.dao.ExperienceDao;
import persistenza.dao.ProdottoDao;
import persistenza.dao.UtenteDao;

public class UtenteDaoJDBC implements UtenteDao {

	private DataSource dataSource;

	public UtenteDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void save(Utente ut) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into utente(nickname, nome, cognome, data_nascita, citta_residenza, mail, password) values (?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, ut.getNickname());
			statement.setString(2, ut.getNome());
			statement.setString(3, ut.getCognome());
			long secs = ut.getData_nascita().getTime();
			statement.setDate(4, new java.sql.Date(secs));
			statement.setString(5, ut.getCitta_residenza());
			statement.setString(6, ut.getMail());
			statement.setString(7,ut.getPassword());

			statement.executeUpdate();
			
			this.updateAcquisti(ut,connection);
			this.updateProdottiRealizzati(ut,connection);
			this.updateExperiencesCreate(ut,connection);
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

	public void saveBio(Utente u)
	{
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "update utente SET bio=? WHERE nickname=?"; //DA RIVEDERE
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1,u.getBio());
			statement.setString(2, u.getNickname());
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

	/*public void saveAvatar(Utente u)
	{
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "update utente SET avatar=? WHERE nickname=?"; //DA RIVEDERE
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1,u.getAvatar());
			statement.setString(2,u.getNickname());
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
	}*/

	private void updateExperiencesCreate(Utente ut, Connection connection) throws SQLException 
	{
		ExperienceDao experienceDao = new ExperienceDaoJDBC(dataSource);
		for (Experience exp : ut.getExperiences_create_NoProxy()) 
		{
			if (experienceDao.findByPrimaryKey(exp.getID()) == null)
			{
				experienceDao.save(exp);
			}
			String update = "update experience SET organizzatore = ? WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, ut.getNickname());
			statement.setInt(2, exp.getID());
			statement.executeUpdate();
		}
	}

	private void removeForeignKeyFromExperienceCreata(Utente ut,Connection c) throws SQLException
	{
		for (Experience exp : ut.getExperiences_create_NoProxy()) 
		{
			String update = "update experience SET organizzatore=NULL WHERE id = ?";
			PreparedStatement statement = c.prepareStatement(update);
			statement.setInt(1, exp.getID());
			statement.executeUpdate();
		}
	}


	private void updateProdottiRealizzati(Utente ut, Connection connection) throws SQLException {
		ProdottoDao prodottoDao = new ProdottoDaoJDBC(dataSource);
		for (Prodotto prodotto : ut.getProdotti_realizzati_NoProxy()) 
		{
			if (prodottoDao.findByPrimaryKey(prodotto.getID()) == null)
			{
				prodottoDao.save(prodotto);
			}
			
			String update = "update prodotto SET realizzatore = ? WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, ut.getNickname());
			statement.setInt(2, prodotto.getID());
			statement.executeUpdate();
		}
	}

	private void removeForeignKeyFromProdottoRealizzato(Utente utente, Connection connection) throws SQLException 
	{
		for (Prodotto p : utente.getProdotti_realizzati_NoProxy()) 
		{
			String update = "update prodotto SET realizzatore=NULL WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setInt(1, p.getID());
			statement.executeUpdate();
		}	
	}



	private void updateAcquisti(Utente ut, Connection connection) throws SQLException
	{
		ProdottoDao prodottoDao = new ProdottoDaoJDBC(dataSource);
		for (Prodotto prodotto : ut.getAcquisti_NoProxy())
		{
			if (prodottoDao.findByPrimaryKey(prodotto.getID()) == null)
			{
				prodottoDao.save(prodotto);
			}

			
			System.out.println("ACQUISTO "+prodotto.getID()+" "+prodotto.getDescrizione());
		
			
			String acquistato = "select id from acquisto where utente=? AND prodotto=?";
			PreparedStatement statementAcquistato = connection.prepareStatement(acquistato);
			statementAcquistato.setString(1, ut.getNickname());
			statementAcquistato.setInt(2, prodotto.getID());
			ResultSet result = statementAcquistato.executeQuery();
//			if(result.next())
//			{
//				String update = "update acquisto SET utente = ?  WHERE id = ?";
//				PreparedStatement statement = connection.prepareStatement(update);
//				statement.setString(1, ut.getNickname());
//				statement.setInt(2, result.getInt("ID"));
//				statement.executeUpdate();
//			}else{
			if(!result.next())
			{
				String acquista = "insert into acquisto(id,utente,prodotto) values (?,?,?)";
				PreparedStatement statementAcquista = connection.prepareStatement(acquista);
				int id = AssegnaID.getId(connection);
				statementAcquista.setInt(1, id);
				statementAcquista.setString(2,ut.getNickname());
				statementAcquista.setInt(3,prodotto.getID());
				statementAcquista.executeUpdate();
			}
		}
	}

	private void removeForeignKeyFromProdotto(Utente ut, Connection connection) throws SQLException {
		for (Prodotto prodotto : ut.getAcquisti_NoProxy()) {
			String update = "update acquisto SET utente = NULL WHERE prodotto = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setInt(1, prodotto.getID());
			statement.executeUpdate();
		}	
	}



	@Override
	public void update(Utente ut) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update utente SET nome = ?, cognome = ?, data_nascita = ?, citta_residenza=?, mail = ?, password = ?, bio=?, avatar=? WHERE nickname=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, ut.getNome());
			statement.setString(2, ut.getCognome());
			long secs = ut.getData_nascita().getTime();
			statement.setDate(3, new java.sql.Date(secs));
			statement.setString(4, ut.getCitta_residenza());
			statement.setString(5, ut.getMail());
			statement.setString(6, ut.getPassword());
			statement.setString(7, ut.getBio());
			statement.setString(8, ut.getAvatar());
			statement.setString(9, ut.getNickname());
			statement.executeUpdate();
			this.updateAcquisti(ut, connection);
			this.updateExperiencesCreate(ut, connection);
			this.updateProdottiRealizzati(ut, connection);
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
	public void delete(Utente ut) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM utente WHERE nickname = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, ut.getNickname());
			
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);			
			this.removeForeignKeyFromProdotto(ut, connection);
			this.removeForeignKeyFromExperienceCreata(ut, connection);
			this.removeForeignKeyFromProdottoRealizzato(ut, connection);
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

	//	private void removeForeignKeyFromMessaggiRicevuti(Utente ut, Connection connection) throws SQLException {
	//		// TODO Auto-generated method stub
	//		for (MessaggioPrivato msg : ut.getMessaggi_privati_ricevuti()) {
	//			String update = "update messaggio_privato SET destinatario=NULL WHERE id = ?";
	//			PreparedStatement statement = connection.prepareStatement(update);
	//			statement.setInt(1, msg.getID());
	//			statement.executeUpdate();
	//		}	
	//	}

	@Override
	public Utente findByPrimaryKey(String nick) {
		Connection connection = this.dataSource.getConnection();
		Utente utente = null;
		try {
			PreparedStatement statement;
			String query = "select * from utente where nickname = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1,nick);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				utente = new UtenteProxy(dataSource);
				utente.setNickname(result.getString("nickname"));				
				utente.setNome(result.getString("nome"));
				utente.setCognome(result.getString("cognome"));
				long secs = result.getDate("data_nascita").getTime();
				utente.setData_nascita(new java.util.Date(secs));
				utente.setCitta_residenza(result.getString("citta_residenza"));
				utente.setMail(result.getString("mail"));
				utente.setPassword(result.getString("password"));
				utente.setMod(result.getBoolean("admin"));
				utente.setAvatar(result.getString("avatar"));
				utente.setBio(result.getString("bio"));
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
		return utente;
	}

	@Override
	public List<Utente> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<Utente> utenti = new LinkedList<>();
		try {
			Utente utente;
			PreparedStatement statement;
			String query = "select * from utente";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				utente = new UtenteProxy(dataSource);
				utente.setNickname(result.getString("nickname"));				
				utente.setNome(result.getString("nome"));
				utente.setCognome(result.getString("cognome"));
				long secs = result.getDate("data_nascita").getTime();
				utente.setData_nascita(new java.util.Date(secs));
				utente.setCitta_residenza(result.getString("citta_residenza"));
				utente.setMail(result.getString("mail"));
				utente.setPassword(result.getString("password"));
				utente.setMod(result.getBoolean("admin"));
				utente.setAvatar(result.getString("avatar"));
				utente.setBio(result.getString("bio"));
				utenti.add(utente);
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
		return utenti;
	}

	
	
}
