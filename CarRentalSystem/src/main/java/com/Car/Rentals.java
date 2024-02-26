package com.Car;

import java.time.LocalDate;
import java.util.Date;

import com.CarRentalSystem.CarRentalSystem.DatabaseConnection;

public class Rentals {
    //private int rentalId;
    private Date rentalStartDate; // changed from rentalDate
    private int noOfDays; // changed from returnDate
    private int payment;
    private int carsId;
    private String rentalEndDate;

    // other attributes like destination, customerId can be added if needed
    //int rentalId,
    public Rentals( Date rentalStartDate, int noOfDays, int payment, int carsId,String  rentalEndDate ) {
      
        this.rentalStartDate = rentalStartDate;
        this.noOfDays = noOfDays;
        this.payment = payment;
        this.carsId = carsId;
        this.rentalEndDate=rentalEndDate;
    }

    // Getter and setter methods go here...

//    public int getRentalId() {
//        return rentalId;
//    }
//
//    public void setRentalId(int rentalId) {
//        this.rentalId = rentalId;
//    }

    public String getRentalEndDate() {
		return rentalEndDate;
	}

	public void setRentalEndDate(String rentalEndDate) {
		this.rentalEndDate = rentalEndDate;
	}

	public Date getRentalStartDate() {
        return rentalStartDate;
    }

    public void setRentalStartDate(Date rentalStartDate) {
        this.rentalStartDate = rentalStartDate;
    }

    public int getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public int getCarsId() {
        return carsId;
    }

    public void setCarsId(int carsId) {
        this.carsId = carsId;
    }
//    public static void cancellation(String userName) {
//    	System.out.println("The Car that you have been booked So far ");
//    	DatabaseConnection.getBookingHistory(userName);
//    	System.out.println("Enter the carId which you need to Cancel ");
//    	LocalDate presentdate=LocalDate.now(); 
//    	
//    }
}
