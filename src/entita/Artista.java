package entita;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Artista implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	private boolean on_tour;
	private String sito_web;
	private Set<Evento> eventi;
	private String genere;
	private String immagine_band;
	private String copertina_band;
	
	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public String getImmagine_band() {
		return immagine_band;
	}

	public void setImmagine_band(String immagine_band) {
		this.immagine_band = immagine_band;
	}

	public String getCopertina_band() {
		return copertina_band;
	}

	public void setCopertina_band(String copertina_band) {
		this.copertina_band = copertina_band;
	}

	public Set<Evento> getEventi() {
		return eventi;
	}

	public void setEventi(Set<Evento> eventi) {
		this.eventi = eventi;
	}

	public String getSito_web() {
		return sito_web;
	}

	public void setSito_web(String sito_web) {
		this.sito_web = sito_web;
	}
	
	public Artista()
	{
		eventi = new HashSet<>();
	}

	public Artista(String nome,boolean on_tour,String sito_web,String img,String genere,String cop)
	{
		this();
		this.nome=nome;
		this.on_tour=on_tour;
		this.sito_web=sito_web;
		this.immagine_band=img;
		this.genere=genere;
		this.copertina_band=cop;
	}

	public boolean isOn_tour() {
		return on_tour;
	}

	public void setOn_tour(boolean on_tour) {
		this.on_tour = on_tour;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void aggiungiEvento(Evento e)
	{
		this.getEventi().add(e);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artista other = (Artista) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	public String toString()
	{
		return "Artista[" + this.getNome() + ", " + 
				this.isOn_tour() + ", " + 
					this.getSito_web()+", "+ 
					this.getImmagine_band()+", "+
					this.getCopertina_band()+", "+
					this.getGenere() + "]";
	}
	
	public Set<Evento> getEventi_NoProxy() 
	{
		return eventi;
	}
}

