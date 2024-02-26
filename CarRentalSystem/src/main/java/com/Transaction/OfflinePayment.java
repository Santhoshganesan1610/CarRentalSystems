package com.Transaction;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.Car.BookingDetails;
import com.Car.CarDetails;
import com.Car.Rentals;
import com.CarRentalSystem.CarRentalSystem.DatabaseConnection;

public class OfflinePayment implements Payment {
	public void payment(int payment, int carsId, int userId, List<Rentals> rentalList) {
		List<BookingDetails> bookingList = new LinkedList<>();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Your Rental Amount : " + payment +"\nEnter the correct rental amount");
        int rentAmount = sc.nextInt();
        String bookingStatus = "Completed";
        String paymentMode="Gpay";
        LocalDate bookingDate = LocalDate.now();
       
        if (rentAmount == payment) {
            System.out.println("The car has been booked");
            DatabaseConnection.updateCarAvailability(carsId); 
            bookingList.add(new BookingDetails(userId, carsId, paymentMode, bookingDate,bookingStatus));
            DatabaseConnection.addingBookingDetailsIntoDatabase(bookingList);
            //DatabaseConnection.addingRentalDetailsIntoDatabase(rentalList, userId);
            System.out.println("The car has been booked");
           // DatabaseConnection.updateCarAvailability(carsId); 
        }
        else {
        	System.out.println("the amount is wrong");
        }
	}

}
