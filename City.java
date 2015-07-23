package com.lumiplan.hibernate;

import javax.persistence.*;

/**
 * this class serves as the template for entity City in the database
 * 
 *
 */

@Entity
@Table(name = "City")
public class City {

	 @ManyToOne(cascade=CascadeType.ALL)  
	 @JoinColumn(name="stateID") 
	 	private State state;
	   
	 @Id @GeneratedValue
	 @Column(name = "cityID")
	 	private int cityID;
	   
	 @Column(name = "cityName")
	 	private String cityName;
	
	/**
	 * getter for state object
	 * @return state
	 */
	public State getState()
	{
		return state;
	}
	
	/**
	 * getter for city id
	 * @return cityID
	 */
	
	public int getCityID()
	{
		return cityID;
	}
	
	/**
	 * getter for city name
	 * @return cityName
	 */
	public String getCityName()
	{
		return cityName;
	}
	/**
	 * setter for state object
	 * @param s
	 */
	public void setState(State s)
	{
		state = s;
	}
	/**
	 * setter for city id
	 * @param c_id
	 */
	public void setCityID(int c_id)
	{
		cityID = c_id;
	}
	/**
	 * setter for city name
	 * @param c_name
	 */
	public void setCityName(String c_name)
	{
		cityName = c_name;
	}
}
