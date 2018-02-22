package persistenza.dao;

import java.util.List;

import entita.Artista;
import entita.MessaggioPubblico;

public interface MessaggioPubblicoDao {

	public void save(MessaggioPubblico msg);
	public void update(MessaggioPubblico msg);
	public void delete(MessaggioPubblico msg);
	public MessaggioPubblico findByPrimaryKey(int id);
	public List<MessaggioPubblico> findAll();
	
}
