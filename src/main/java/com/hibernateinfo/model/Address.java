package com.hibernateinfo.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Pasha
 * Remember the golden rule: readable code is often faster code. 
 * Produce readable code first and only change it if it proves to be too slow.
 */
//https://stackoverflow.com/questions/35174981/when-to-use-embedded-and-embeddable
//https://stackoverflow.com/questions/7664523/what-impact-does-the-embedded-annotation-mean
@Embeddable
public class Address 
{
	@Column(name = "street_name",length=50)
    private String street;
	
    @Column(name = "city_name",length=50)
    private String city;
    
    @Column(name = "state_name")
    private String state;
    
    @Column(name = "postal_code")
    private Long postalcode;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(Long postalcode) {
		this.postalcode = postalcode;
	}

	@Override
	public String toString() {
		return "Address [street=" + street + ", city=" + city + ", state=" + state + ", postalcode=" + postalcode + "]";
	}
    
    
}
