package com.mthree;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="address")
public class Address {
	@Id
	@SequenceGenerator(
			name="address_sequence",
			sequenceName = "address_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "address_sequence"
	)
	private int id;
	@Column
	private String street;
	@Column
	private String city;
	@Column
	private int postCode;
	@OneToMany(mappedBy="address", orphanRemoval=true, targetEntity=Student.class, cascade = CascadeType.ALL)
	private List<Student> residents;
	
	public Address() {}
	
	public Address(String street, String city, int postCode) {
		super();
		this.street = street;
		this.city = city;
		this.postCode = postCode;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public int getPostCode() {
		return postCode;
	}
	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(city, postCode, street);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(city, other.city) && postCode == other.postCode && Objects.equals(street, other.street);
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", city=" + city + ", postCode=" + postCode
				+ "]";
	}
}
