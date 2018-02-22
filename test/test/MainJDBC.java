package test;

import java.util.Calendar;

import java.util.Date;

import entita.Artista;
import entita.Evento;
import entita.Experience;
import entita.Location;
import entita.MessaggioPrivato;
import entita.Prodotto;
import entita.Utente;
import persistenza.DAOFactory;
import persistenza.UtilDao;
import persistenza.dao.ArtistaDao;
import persistenza.dao.EventoDao;
import persistenza.dao.ExperienceDao;
import persistenza.dao.LocationDao;
import persistenza.dao.MessaggioPrivatoDao;
import persistenza.dao.ProdottoDao;
import persistenza.dao.UtenteDao;

public class MainJDBC {

	public static void main(String args[]) {
		
		Calendar cal = Calendar.getInstance();
		cal.set(1995, Calendar.MARCH, 21); // // 21 marzo 1995
		Date date1 = cal.getTime();
		cal.set(1996, Calendar.APRIL, 12); // 12 aprile 1996
		Date date2 = cal.getTime();
		cal.set(1998, Calendar.OCTOBER, 1); // 1 ottobre 1998
		Date date3 = cal.getTime();
		cal.set(1986,Calendar.OCTOBER, 24);
		Date date4=cal.getTime();
		cal.set(2018, Calendar.JUNE,16);
		Date date5=cal.getTime();
		cal.set(2018,Calendar.FEBRUARY,23);
		Date date6=cal.getTime();
		cal.set(2018,Calendar.APRIL,5);
		Date date7=cal.getTime();
		cal.set(2018,Calendar.JULY,3);
		Date date8=cal.getTime();
		cal.set(2018,Calendar.DECEMBER,16);
		Date date9=cal.getTime();
		cal.set(2018, Calendar.MARCH,23);
		Date date10=cal.getTime();
		
		// istanza singleton per fabbrica di oggetti DAO
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);

		// istanza per creare oggetti DAO
		UtilDao util = factory.getUtilDAO();
		util.dropDatabase();
		util.createDatabase();

		// per ogni entita va creata la propria DAO factory
		ArtistaDao artistaDao = factory.getArtistaDAO();
		UtenteDao utenteDao = factory.getUtenteDAO();
		LocationDao locationDao = factory.getLocationDAO();
		EventoDao eventoDao = factory.getEventoDAO();
		ExperienceDao experienceDao = factory.getExperienceDAO();
		MessaggioPrivatoDao msg_pvtDao = factory.getMessaggioPrivatoDAO();
		ProdottoDao prodottoDao = factory.getProdottoDAO();
		

		//DICHIARO UTENTI 
		Utente u = new Utente("turuzzu", "manuel", "donato", date1,"Catanzaro","futura95@live.it", "ciao", false);
		Utente u2 = new Utente("xgabry94", "gabriele", "noto", date2,"Molochio", "gabnt8@gmail.com", "ciao", false);
		Utente u3 = new Utente("pacciu86", "adamo", "demasi", date3,"Siderno", "pacciu86@mammt.lol", "ciao", false);
		Utente u4 = new Utente("violi_hate","domenico","violi",date4,"Catanzaro","dome.violi90@gmail.com","ciao", false);

		//DICHIARO EVENTI
		Evento ev = new Evento(date1);
		Evento ev2= new Evento(date4);
		Evento ev3= new Evento(date3);
		
		//DICHIARO LOCATION.
		Location l  = new Location("Bataclan", "Parigi", "Francia",48.8630134,2.3684271);
		Location l2 = new Location("Ippodromo del Visarno","Firenze","Italia",43.781215,11.2236607);
		Location l3 = new Location("Alcatraz","Milano","Italia",45.494689,9.1804693);
	    Location l4 = new Location("PalaLottomatica", "Roma", "Italia", 41.8253202, 12.46432);
	    Location l5 = new Location("Auditorium Live", "Roma", "Italia", 41.8171933, 12.4607955);
	    Location l6 = new Location("Auditorium Parco della Musica", "Roma", "Italia", 41.929556, 12.4724258);
	    Location l7 = new Location("Orion", "Ciampino", "Italia", 41.808872, 12.5948439);

		//DICHIARO ARTISTI
		Artista a1 = new Artista("Avenged Sevenfold",true,"www.avengedsevenfold.com","img/a7x.jpg","Alternative Metal","img/a7x.jpg");
		Artista a2 = new Artista("Deftones",true,"www.deftones.com","img/deftones.jpeg","Alternative Metal","img/trasferimento.png");
		Artista a3 = new Artista("Slipknot",false,"www.slipknot1.com","img/slipknot.jpg","Alternative/Death metal","img/deftones.jpeg");
		
		//DICHIARO EXPERIENCES
		Experience e1 = new Experience(date5,10,"Viaggio in autobus");
		Experience e2 = new Experience(date6,8,"Viaggio in treno");
		Experience e3 = new Experience(date7,3,"Tresca feroce");
		Experience e4 = new Experience(date8,4,"Gangbang a tutto spiano");
		Experience e5 = new Experience(date9,12,"Eddie Van Halen che fa cose");
		Experience e6 = new Experience(date10,3,"Tom Morello dio");
		
		//DICHIARO PRODOTTI
		Prodotto p1 = new Prodotto();
		p1.setNome("Felpa Slipknot");
		p1.setDescrizione("descsorgjsojosgkoew1");
		
		Prodotto p2 = new Prodotto();
		p2.setNome("Cappello A7X");
		p2.setDescrizione("DESCRIZIONE 2222222222222222");
		
		
		Prodotto p3 = new Prodotto();
		p3.setNome("Portachiavi Guns n' Roses");
		p3.setDescrizione("DESCRIZIONE 4oògggmòogrkords");
		
		//DICHIARO MESSAGGI
		MessaggioPrivato m1 = new MessaggioPrivato(date1,"MSG1","msg1",u,u2);
		MessaggioPrivato m2 = new MessaggioPrivato(date2,"MSG2","msg2",u,u3);
		MessaggioPrivato m3 = new MessaggioPrivato(date3,"MSG3","msg3",u,u4);
		MessaggioPrivato m4 = new MessaggioPrivato(date4,"MSG4","msg4",u2,u);
		MessaggioPrivato m5 = new MessaggioPrivato(date5,"MSG5","msg5",u3,u);
		MessaggioPrivato m6 = new MessaggioPrivato(date6,"MSG6","msg6",u4,u);
		
		
		//SALVO UTENTI
		utenteDao.save(u);
		utenteDao.save(u2);
		utenteDao.save(u3);
		utenteDao.save(u4);
		
		//SALVO EVENTI
		eventoDao.save(ev);
		eventoDao.save(ev2);
		eventoDao.save(ev3);
		
		//SALVO LOCATIONS
		locationDao.save(l);
		locationDao.save(l2);
		locationDao.save(l3);
		locationDao.save(l4);
		locationDao.save(l5);
		locationDao.save(l6);
		locationDao.save(l7);
		
		//SALVO ARTISTI
		artistaDao.save(a1);
		artistaDao.save(a2);
		artistaDao.save(a3);
		
		//SALVO EXPERIENCES
		experienceDao.save(e1);
		experienceDao.save(e2);
		experienceDao.save(e3);
		experienceDao.save(e4);
		experienceDao.save(e5);
		experienceDao.save(e6);
		
		//SALVO PRODOTTO 
		prodottoDao.save(p1);
		prodottoDao.save(p2);
		prodottoDao.save(p3);
		
		//SALVO MESSAGGI
		msg_pvtDao.save(m1);
		msg_pvtDao.save(m2);
		msg_pvtDao.save(m3);
		msg_pvtDao.save(m4);
		msg_pvtDao.save(m5);
		msg_pvtDao.save(m6);

		//AGGIUNGO EVENTO 2018 ALL'IPPODROMO 
		l2.aggiungiEvento(ev2);
		
		//AGGIUNGO EVENTO 2018 AGLI AVENGED
		a1.aggiungiEvento(ev2);
		
		//AGGIUNGO EVENTO 1998 ALL'ALCATRAZ
		l3.aggiungiEvento(ev3);
		
		//AGGIUNGO EVENTO 1998 AGLI AVENGED
		a1.aggiungiEvento(ev3);
		
		//AGGIUNGO EVENTO 1995 AL BATACLAN
		l.aggiungiEvento(ev);
		
		//AGGIUNGO EVENTO 1995 AGLI AVENGED
		a1.aggiungiEvento(ev);
		
		//AGGIORNO AVENGED
		artistaDao.update(a1);
		
		//AGGIORNO LOCATION
		locationDao.update(l2);
		locationDao.update(l3);
		locationDao.update(l);
		
		//SETTO IMMAGINE AL MIO PROFILO
		u.setAvatar("img/diamond.png");
		utenteDao.update(u);
//		
		//SETTO IMMAGINE AL PROFILO DI GABRIELE
		u2.setAvatar("img/pearljam.jpg");
		utenteDao.update(u2);
//		
		//SETTO IMMAGINE AL PROFILO DI ADAMO
		u3.setAvatar("img/foof.png");
		utenteDao.update(u3);
		
		//AGGIUNGO EXPERIENCE AGLI UTENTI
		u.aggiungiExperience(e1);
		u2.aggiungiExperience(e2);
		u3.aggiungiExperience(e3);
		u4.aggiungiExperience(e4);
		u.aggiungiExperience(e5);
		u2.aggiungiExperience(e6);
		
		//AGGIUNGO EXPERIENCE ALL'EVENTO
		ev.aggiungiExperience(e1);
		ev.aggiungiExperience(e2);
		ev2.aggiungiExperience(e3);
		ev2.aggiungiExperience(e4);
		ev3.aggiungiExperience(e5);
		ev3.aggiungiExperience(e6);
		
		//UPDATE UTENTI
		utenteDao.update(u);
		utenteDao.update(u2);
		utenteDao.update(u3);
		utenteDao.update(u4);
		
		//UPDATE EVENTI
		eventoDao.update(ev);
		eventoDao.update(ev2);
		eventoDao.update(ev3);
		
		u.setBio("Il mio nome e' Manuel, amo il metal e vengo da Catanzaro! Non vedo l'ora di partire per una nuova avventura insieme a tutti voi");
		utenteDao.update(u);
		
		//AGGIUNGO PRODOTTI REALIZZATI 
		u.aggiungiProdottoRealizzato(p1);
		u.aggiungiProdottoRealizzato(p2);
		u2.aggiungiProdottoRealizzato(p3);
		
		//AGGIUNGO PRODOTTO COME ACQUISTO
		u.aggiungiAcquisto(p3);
		
		//AGGIUNGO MESSAGGI RICEVUTI ALL'UTENTE
//		u.add_messaggio_ricevuto(m1);
//		u.add_messaggio_ricevuto(m2);
//		
//		
//		u2.aggiungiMessaggioPrivato(m1);
//		u2.aggiungiMessaggioPrivato(m2);
//		
//		u.aggiungiMessaggioPrivato(m3);
//		u.aggiungiMessaggioPrivato(m4);
//		
//		u2.add_messaggio_ricevuto(m3);
//		u2.add_messaggio_ricevuto(m4);
		
		//UPDATE UTENTI
		utenteDao.update(u);
		utenteDao.update(u2);
		
		//AGGIUNGI PARTECIPAZIONE
		e3.aggiungiPartecipante(u);
		e6.aggiungiPartecipante(u);
		e1.aggiungiPartecipante(u3);
		e4.aggiungiPartecipante(u4);
		e2.aggiungiPartecipante(u3);
		e1.aggiungiPartecipante(u4);
		e1.aggiungiPartecipante(u2);
//		e1.aggiungiPartecipante(u);
		
		//UPDATE EXPERIENCES
		experienceDao.update(e1);
		experienceDao.update(e2);
		experienceDao.update(e3);
		experienceDao.update(e4);
		experienceDao.update(e5);
		experienceDao.update(e6);
	}
}