package com.Transaction;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.Car.BookingDetails;
import com.Car.CarDetails;
import com.Car.Rentals;
import com.CarRentalSystem.CarRentalSystem.DatabaseConnection;

public class OnlinePayment implements Payment {
	@Override
	public void payment(int payment, int carsId, int userId, List<Rentals> rentalList) {
		List<BookingDetails> bookingList = new LinkedList<>();
		System.out.println("Enter UPI Id:");
		Scanner sc = new Scanner(System.in);
		String upi=sc.next();
		System.out.println("Your Rental Amount : " + payment +"\nEnter the correct rental amount");
        int rentAmount = sc.nextInt();
        System.out.println("Enter the PIN");
        String bookingStatus = "Completed";
        String paymentMode="Gpay";
        LocalDate bookingDate = LocalDate.now();
        int pin = sc.nextInt();
        if (rentAmount == payment) {
            bookingList.add(new BookingDetails(userId, carsId, paymentMode, bookingDate,bookingStatus));
            DatabaseConnection.addingBookingDetailsIntoDatabase(bookingList);
//            DatabaseConnection.addingRentalDetailsIntoDatabase(rentalList, userId);
            System.out.println("The car has been booked");

        }
        else {
        	System.out.println("the amount is wrong");
        }
	}

}
