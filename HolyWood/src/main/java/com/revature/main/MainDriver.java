package com.revature.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.revature.dao.ActorDao;
import com.revature.dao.DirectorDao;
import com.revature.dao.MovieDao;
import com.revature.dao.PlotDao;
import com.revature.model.Actor;
import com.revature.model.Director;
import com.revature.model.Movie;
import com.revature.model.Plot;
import com.revature.util.HibernateUtil;

public class MainDriver {
	public static MovieDao movdao = new MovieDao();
	public static PlotDao plodao = new PlotDao();
	public static ActorDao actdao = new ActorDao();
	public static DirectorDao dirdao = new DirectorDao();

	public static void main(String[] args) {
		InsertInitialValues();
		System.out.println("all the prizons: " + movdao.selectAll());

		HibernateUtil.closeSes();
	}

	public static void InsertInitialValues() {
		// Plot
		Plot plot1 = new Plot(
				"Aliens invade and are defeated by scrappy americans");
		Plot plot2 = new Plot(
				"The fire nation invades the earth, water, and air nations and the avatar must stop them");
		
		
		Actor a1 = new Actor("Will Smith", "male");
	    Actor a2 = new Actor("Jeff Goldum", "male");
	    Actor a3 = new Actor("Kevin Hart", "male");
	    Actor a4 = new Actor("Mark Hamhill", "male");
	    Actor a5 = new Actor("Butcher Names", "female");
	    Actor a6 = new Actor("Jack Black", "female");
	    
	    Director director1 = new Director("James Cameron", "male");
	    Director director2 = new Director("Roland Emmerich", "male");
		// SuperPrisons

		Date date = (new GregorianCalendar(109 + 1900, 12, 18))
				.getGregorianChange();
		List<Actor> indepActors = new ArrayList();
		indepActors.add(a1);
		indepActors.add(a2);
		Movie movie1 = new Movie("Independence Day", date, plot1, director1, indepActors);
		date = (new GregorianCalendar(96 + 1900, 7, 2))
				.getGregorianChange();
		List<Actor> avaActors = new ArrayList();
		avaActors.add(a3);
		avaActors.add(a4);
		avaActors.add(a5);
		avaActors.add(a6);
		Movie movie2 = new Movie("Avatar", date, plot2, director2, avaActors);

		plodao.insert(plot1);
		plodao.insert(plot2);
		actdao.insert(a1);
		actdao.insert(a2);
		actdao.insert(a3);
		actdao.insert(a4);
		actdao.insert(a5);
		dirdao.insert(director1);
		dirdao.insert(director2);
		movdao.insert(movie1);
		movdao.insert(movie2);
		System.out.println("We have inserted the stuff");
	}
}
