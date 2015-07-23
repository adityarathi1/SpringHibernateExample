/**
 * 
 */
package com.lumiplan.dao;

/**
 * @author Admin
 *
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.lumiplan.hibernate.*;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session; 
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * ApplicationDAO class is the data access object layer. 
 * It loads the database entities and allows the user to perform CRUD operations.
 * 
 *
 */

@Repository
public class ApplicationDAO { 
	List<Country> countries = new ArrayList<Country>();
	List<State> states = new ArrayList<State>();
	List<City> cities = new ArrayList<City>();
	private static SessionFactory sessionFactory;
	
	/**
	 * It returns the session factory whenever required
	 * 
	 * @return sessionFactory
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * It sets the session factory
	 * @param sf
	 */
	public static void setSessionFactory(SessionFactory sf) {
		sessionFactory = sf;
	}
	
	/**
	 * loads all the tables from the database into the respective lists.
	 */
	public void loadTables()
	{
		org.hibernate.Transaction tx = null;
		Session session = sessionFactory.openSession();
		tx = session.beginTransaction();
		
		Criteria cr = session.createCriteria(Country.class);
		countries = cr.list();
		
		Criteria cr1 = session.createCriteria(City.class);
		cities = cr1.list();
		
		Criteria cr2 = session.createCriteria(State.class);
		states = cr2.list();
		
		tx.commit();
	}
	
	/**
	 * creates the entry in the table country.
	 * @param c_name
	 */
	public void createCountry(String c_name)
	{
		int c_id;
		org.hibernate.Transaction tx = null;
		Session session = sessionFactory.openSession();
		tx = session.beginTransaction();
		
		Country country = new Country(c_name);
		c_id = (Integer) session.save(country);
		System.out.println(c_id);
		
		tx.commit();
		session.close();
	}
	
	/**
	 * prints the data of the entity country in the database.
	 * 
	 * @return list of countries
	 */
	public List<Country> readCountry()
	{
		loadTables();
		for (Iterator iterator =  countries.iterator(); iterator.hasNext();)
		{
			Country co = (Country) iterator.next(); 
			System.out.println(co.getCountryID() + " " + co.getCountryName()); 
		}
		
		return countries;
	}
	
	
	/**
	 * allows the user to update the existing entries in the database.
	 * @param c_id
	 * @param c_name
	 */
	public void updateCountry(int c_id, String c_name)
	{
		org.hibernate.Transaction tx = null;
		Session session = sessionFactory.openSession();
		tx = session.beginTransaction();
		
		Country country = (Country)session.get(Country.class, c_id); 
		country.setCountryName(c_name);
		session.update(country); 
		tx.commit();
	}
	
	/**
	 * creates the entry in the table state.
	 * @param c_name
	 * @param s_name
	 */
	
	public void createState(String c_name, String s_name)
	{
		int s_id;
		org.hibernate.Transaction tx = null;
		Session session = sessionFactory.openSession();
		tx = session.beginTransaction();
		Country c = new Country();
		int i=0;
		
		while(i<countries.size())
		{
			if(countries.get(i).getCountryName().equals(c_name))
			{
				c = countries.get(i);
			}
			i++;
		}
		
		State state = new State();
		
		state.setCountry(c);
		state.setStateName(s_name);
		
		s_id = (Integer) session.save(state);
		System.out.println(s_id);
		
		
		tx.commit();
		readState("*");
	}
	
	/**
	 * prints the data of the entity state in the database.
	 */
	
	
	public void readState(String stateName)
	{
		org.hibernate.Transaction tx = null;
		Session session = sessionFactory.openSession();
		tx = session.beginTransaction();
		
		Criteria cr = session.createCriteria(State.class, "states");
		cr.setFetchMode("states.country", FetchMode.JOIN);
		cr.createAlias("states.country", "sc");
		
		if(stateName != "*")
			cr.add(Restrictions.eq("stateName", stateName));
		
		ProjectionList columns = Projections.projectionList()
				.add(Projections.property("stateID"))
				.add(Projections.property("stateName"))
				.add(Projections.property("sc.countryName"));
   
		cr.setProjection(columns);
	 
		List<Object[]> list = cr.list();
		for(Object[] arr : list){
			System.out.println(Arrays.toString(arr));
		}
	}
	
	/**
	 * allows the user to update the existing entries in the database.
	 * @param s_id
	 * @param s_name
	 */
	
	public void updateState(int s_id, String s_name)
	{
		org.hibernate.Transaction tx = null;
		Session session = sessionFactory.openSession();
		tx = session.beginTransaction();
		
		State state = (State)session.get(State.class, s_id); 
		state.setStateName(s_name);
		session.update(state); 
		tx.commit();
		readState("*");
	}
	
	/**
	 * deletes the data from the database of the given state id.
	 * @param s_id
	 */
	
	public void deleteState(int s_id)
	{
		org.hibernate.Transaction tx = null;
		Session session = sessionFactory.openSession();
		tx = session.beginTransaction();
		
		State state = (State)session.get(State.class, s_id); 
		session.delete(state); 
		tx.commit();
		
		session.close();
	}

	/**
	 * creates the entry in the table city.
	 * @param c_name
	 * @param s_name
	 */
	
	
	
	public void createCity(String s_name, String c_name)
	{
		loadTables();
		int c_id;
		org.hibernate.Transaction tx = null;
		Session session = sessionFactory.openSession();
		tx = session.beginTransaction();
		State s = new State();
		int i=0;
		
		while(i<states.size())
		{
			System.out.println(states.get(i).getStateName());
			if(states.get(i).getStateName().equals(s_name))
			{
				s = states.get(i);
			}
			i++;
		}
		
		City city = new City();
		
		city.setState(s);
		city.setCityName(c_name);
		
		c_id = (Integer) session.save(city);
		System.out.println(c_id);
		
		
		tx.commit();
		readCity("*");
	}
	
	/**
	 * prints the data of the entity city in the database.
	 */
	
	public void readCity(String cityname)
	{
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
	
		Criteria cr = session.createCriteria(City.class, "cities");
		cr.setFetchMode("cities.state.country", FetchMode.JOIN);
		cr.createAlias("cities.state", "cs"); 
		cr.createAlias("state.country", "sc");
		if(cityname != "*")
			cr.add(Restrictions.eq("cityName", cityname));
		
		ProjectionList columns = Projections.projectionList()
				.add(Projections.property("cityName"))
				.add(Projections.property("cs.stateName"))
				.add(Projections.property("sc.countryName"))
				.add(Projections.property("sc.countryID"));
   
		cr.setProjection(columns);
	 
		List<Object[]> list = cr.list();
		for(Object[] arr : list){
			System.out.println(Arrays.toString(arr));
		}
		session.close();
	}
	
	/**
	 * deletes the data from the database, whose city name is given as input.
	 * @param c_name
	 */
	
	public void deleteCity(String c_name)
	{
		loadTables();
		readCity(c_name);
		org.hibernate.Transaction tx = null;
		Session session = sessionFactory.openSession();
		tx = session.beginTransaction();
		int i=0;
		
		while(i<cities.size())
		{
			
			if(cities.get(i).getCityName().equals(c_name))
			{
					session.delete(cities.get(i)); 
			}
			i++;
		}
		tx.commit();
		
	}
	

	/**
	 * allows the user to update the existing entries in the database.
	 * @param c_id
	 * @param c_name
	 */
	
	public void updateCity(int c_id, String c_name)
	{
		org.hibernate.Transaction tx = null;
		Session session = sessionFactory.openSession();
		tx = session.beginTransaction();
		
		City city = (City)session.get(City.class, c_id); 
		city.setCityName(c_name);
		session.update(city); 
		tx.commit();
		readCity("*");
	}

	
	
} 
	 

	
