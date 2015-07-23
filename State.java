package com.lumiplan.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * this class serves as the template for the state entity in the database
 * 
 *
 */
@Entity
@Table(name = "State")
public class State {
	
	  @ManyToOne(cascade=CascadeType.ALL)  
	    @JoinColumn(name="countryID") 
	private Country country;
	
	@Id @GeneratedValue
	@Column(name = "stateID")
	private int stateID;
	
	@Column(name = "stateName")
	private String stateName;
	
	@OneToMany(mappedBy = "state")
	private List<City> city = new ArrayList<City>();

	/**
	 * setter for state name
	 * @param s_name
	 */
public State(String s_name)
{
	stateName = s_name;
}

public State()
{}
/**
 * getter for country object
 * @return country
 */

public Country getCountry()
{
	return country;
}

/**
 * getter for state id
 * @return stateID
 */

public int getStateID()
{
	return stateID;
}

/**
 * getter for state name
 * @return stateName
 */

public String getStateName()
{
	return stateName;
}

/**
 * setter for country object
 * @param c
 */

public void setCountry(Country c)
{
	country = c;
}

/**
 * setter for state id
 * @param s_id
 */
public void setStateID(int s_id)
{
	stateID = s_id;
}
/**
 * setter for state name
 * @param s_name
 */
public void setStateName(String s_name)
{
	stateName = s_name;
}
/** 
 * getter for list of cities
 * @return city
 */

public List<City> getCity() {
	return city;
}

/**
 * setter for list of cities
 * @param city
 */
public void setCity(List<City> city) {
	this.city = city;
}
}