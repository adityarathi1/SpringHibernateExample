/**
 * 
 */
package com.lumiplan.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
/**
 * this class serves as the template for the entity Country in the database
 *
 */
@Entity
@Table(name = "Country")
public class Country {
	
	@Id @GeneratedValue
	@Column(name = "countryID")
	private int countryID;
	
	@Column(name = "countryName")
	private String countryName;
	
	@OneToMany(cascade=CascadeType.ALL)  
	//@JoinColumn(name="stateID") 
	private List<State> state = new ArrayList<State>();

/**
 * constructor for country, takes country name as the argument
 * @param c_name
 */
	public Country(String c_name)
	{
		countryName = c_name;
	}
	
	public Country()
	{}
/**
 * getter for country id
 * @return countryID
 */

	public int getCountryID()
	{
		return countryID;
	}
	
	/**
	 * getter for country name
	 * @return countryName
	 */
	public String getCountryName()
	{
		return countryName;
	}
	
	/**
	 * setter for country id
	 * @param c_id
	 */
	public void setCountryID(int c_id)
	{
		this.countryID = c_id;
	}
	/**
	 * setter for country name
	 * @param c_name
	 */
	public void setCountryName(String c_name)
	{
		this.countryName = c_name;
	}

/**
 * getter for state object
 * @return
 */
	public List<State> getState() {
		return state;
	}

/**
 * setter for state object
 * @param state
 */
	public void setState(List<State> state) {
		this.state = state;
	}
}