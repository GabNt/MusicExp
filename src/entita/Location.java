package entita;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Location implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	private String citta;
	private String stato;
	private Set<Evento> eventi;
	private double lat;
	private double lng;
	
	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public Location()
	{
		eventi = new HashSet<>();
	}
	
	public Location(String nome,String citta,String stato,double lat,double lng)
	{
		this();
		this.nome=nome;
		this.citta=citta;
		this.stato=stato;
		this.lat=lat;
		this.lng=lng;
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public Set<Evento> getEventi() {
		return eventi;
	}

	public void setEventi(Set<Evento> eventi) {
		this.eventi = eventi;
	}
	
	public void aggiungiEvento(Evento ev)
	{
		this.getEventi().add(ev);
	}
	
	public void rimuoviEvento(Evento ev)
	{
		this.getEventi().remove(ev);
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
		Location other = (Location) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	public String toString()
	{
		return "Location[" + this.getNome() + ", " + 
				this.getCitta() + ", " 
				+ this.getStato() +"]";
	}
	
	public Set<Evento> getEventi_NoProxy()
	{
		return eventi;
	}
}
