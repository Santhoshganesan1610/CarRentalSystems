package com.Transaction;

import java.util.List;

import com.Car.Rentals;

interface Payment {
	void payment(int payment, int carsId, int userId,List<Rentals> rentalList);
}
