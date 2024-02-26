package com.Car;
import java.time.LocalDate;
import java.util.Date;
public class BookingDetails {
    private int bookingId;
    private int customerId;
    private int carsId;
    private String modeOfPayment;
    private LocalDate bookingDate;
    private String BookingStatus;
    public BookingDetails(int customerId, int carsId,  String modeOfPayment, LocalDate bookingDate, String bookingStatus) {
        //this.bookingId = bookingId;
        this.customerId = customerId;
        this.carsId = carsId;
        this.modeOfPayment = modeOfPayment;
        this.bookingDate = bookingDate;
        this.BookingStatus=bookingStatus;
        //this.payment=payment;
    }
	public String getBookingStatus() {
		return BookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		BookingStatus = bookingStatus;
	}

	public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCarsId() {
        return carsId;
    }

    public void setCarsId(int carsId) {
        this.carsId = carsId;
    }


    public String getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(String modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }
    
    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }
}
