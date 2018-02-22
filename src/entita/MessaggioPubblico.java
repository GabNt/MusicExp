package entita;

import java.text.SimpleDateFormat;

public class MessaggioPubblico {

	private int ID;
	private String testo;
	
	public MessaggioPubblico(){};
	
	public MessaggioPubblico(String testo)
	{
		this.testo=testo;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
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
		MessaggioPubblico other = (MessaggioPubblico) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
	
	public String toString()
	{	
		return "Messaggio pubblico[" + this.getID() + ", " + 
		this.getTesto() + "]";
		
	}
	
}
