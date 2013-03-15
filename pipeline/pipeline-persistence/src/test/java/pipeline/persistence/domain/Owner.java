package pipeline.persistence.domain;

import java.util.List;

public class Owner {
	private Integer id;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String telephone;
	private List<Pet> pets;

	public Owner() {
	}

	public List<Pet> getPets() {
		return pets;
	}

	public Owner setPets(List<Pet> pets) {
		this.pets = pets;
		return this;
	}

	public Integer getId() {
		return id;
	}

	public Owner setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public Owner withFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public Owner withLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public Owner setAddress(String address) {
		this.address = address;
		return this;
	}

	public String getCity() {
		return city;
	}

	public Owner setCity(String city) {
		this.city = city;
		return this;
	}

	public String getTelephone() {
		return telephone;
	}

	public Owner setTelephone(String telephone) {
		this.telephone = telephone;
		return this;
	}

}
