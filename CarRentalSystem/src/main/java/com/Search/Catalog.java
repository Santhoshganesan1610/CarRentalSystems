package com.Search;

import java.util.Scanner;

import com.CarRentalSystem.CarRentalSystem.AccountDetails;
import com.CarRentalSystem.CarRentalSystem.DatabaseConnection;

public class Catalog implements Search {
	static Scanner sc=new Scanner(System.in);
	
	

	@Override
	
	public void searchByModel() {
		System.out.println("Enter the Car_model");
		String carModel=sc.next();
		DatabaseConnection.searchByModel(carModel);
		System.out.println("Do you want to book the car ? If yes enter 1 || else enter 0 to previos menu ");
		int opt=sc.nextInt();
		if(opt==1) {
			AccountDetails.addingDetails();
		}
		else {
			AccountDetails.search();
		}
	}

	@Override
	public void searchByPriceRange() {
		try {
		System.out.println("Enter the min price");
		int minPrice= sc.nextInt();
		System.out.println("Enter the min price");
		int maxPrice= sc.nextInt();
		DatabaseConnection.searchByPriceRange(minPrice,maxPrice);
		System.out.println("Do you want to book the car ? If yes dO REGISTER ");
		System.out.println("Enter 1 FOR Registeration || Else enter 0 to previos menu ");
		int opt=sc.nextInt();
		if(opt==1) {
			AccountDetails.addingDetails();
		}
		else {
			AccountDetails.search();
		}
		}
		catch(Exception e) {
			System.out.println("Kindly enter the Integer values");
		}
	}

	@Override
	public void showAvailableCars() {
	 
		DatabaseConnection.showAvailableCars();
		System.out.println("Do you want to book the car ? If yes enter 1 || else enter 0 to previos menu ");
		int opt=sc.nextInt();
		if(opt==1) {
			AccountDetails.addingDetails();
		}
		else {
			AccountDetails.search();
		}
	}



}
