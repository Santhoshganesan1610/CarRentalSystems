package com.CarRentalSystem.CarRentalSystem;
public class Customer extends Person {
	private int customerId;
	public Customer(String firstName, String lastName, String gender, int age, String eMail, long phoneNo,
			String address) {
		super(firstName, lastName, gender, age, eMail, phoneNo, address);
		//this.customerId=customerId;
	}
	@Override
	public   String toString() {
		return "getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName() + ", getGender()="
				+ getGender() + ", getAge()=" + getAge() + ", geteMail()=" + geteMail() + ", getPhoneNo()="
				+ getPhoneNo() + ", getAddress()=" + getAddress();
	}

	public int getCustomerId() {
		return customerId;
	}	
	public void print() {
		System.out.println(toString());
	}

}
