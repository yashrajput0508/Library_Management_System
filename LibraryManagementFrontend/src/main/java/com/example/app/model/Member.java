package com.example.app.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Member {
	
	@Min(value = 2,message = "Member Id value should be greator than 1")
	private int memberId;
	
	@NotBlank(message = "First Name Should not be empty")
	private String firstName;

	@NotBlank(message = "Last Name Should not be empty")
	private String lastName;
	
	@NotBlank(message = "Email Should not be empty")
	private String email;
	
	@Size(min = 10, max = 10, message = "Contact Number size should be 10")
	private String contactNumber;
	
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Member(int memberId, String firstName, String lastName, String email, String contactNumber) {
		super();
		this.memberId = memberId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.contactNumber = contactNumber;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", contactNumber=" + contactNumber + "]";
	}
}
