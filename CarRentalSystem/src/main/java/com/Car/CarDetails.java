package com.Car;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.CarRentalSystem.CarRentalSystem.Customer;
import com.CarRentalSystem.CarRentalSystem.DatabaseConnection;
import com.FeedBack.FeedBackDetails;
import com.Transaction.OfflinePayment;
import com.Transaction.OnlinePayment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;

public class CarDetails {
    private static List<Cars> carList = new LinkedList<>();
    static Scanner sc = new Scanner(System.in);
    public static void addingDetails() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Car Number Plate");
        int carNumberPlate = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the Car Model");
        String carModel = sc.next();
        System.out.println("Enter the Capacity");
        int capacity = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the Car Availability");
        String carAvailability = sc.next();
        System.out.println("Enter the Rent Price");
        double rentPrice = sc.nextDouble();
        sc.nextLine();
        carList.add(new Cars(carNumberPlate, carModel, capacity, carAvailability, rentPrice));
        DatabaseConnection.addingCarDetailsIntoDatabase(carList);
    }
//    public static boolean isValidDate(String inputDate) {
////    	boolean isvalid=true;
////    	while(isvalid) {
//    	
//        String regex = "^\\d{4}-\\d{2}-\\d{2}$";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(inputDate);
//        if (matcher.matches()) {
//            LocalDate currentDate = LocalDate.now();
//            LocalDate parsedDate = LocalDate.parse(inputDate);
//          //  isvalid=false;
//            return !parsedDate.isBefore(currentDate);
//        }
//    	//}
//    	return false;
//    }
//    public static String isValidStartDate() {
//        boolean flag = true;
//        String inputDate = null;
//
//        while (flag) {
//            System.out.println("Enter the Rental Date (YYYY-MM-DD)");
//            inputDate = sc.next();
//
//            if (isValidDate(inputDate)) {
//                flag = false;
//            } else {
//                System.out.println("Invalid date or date is before current date: " + inputDate);
//                return null;
//            }
//        }
//        return inputDate;
//    }
    private static List<Rentals> rentalList = new LinkedList<>();

    public static void addingRentalDetails(int opt2, int userId) {
        Date rentalStartDate = null;
        boolean flag = true;
        while(flag) {
        System.out.println("Enter the Rental Date (YYYY-MM-DD)");
        String startDate = sc.next();
        //String startDate=isValidStartDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            rentalStartDate = dateFormat.parse(startDate);
            Date presentDate = new Date();
            if (rentalStartDate.before(presentDate)) {
                throw new ParseException("Start date should not be before the present date.", 0);
            }
            flag=false;
        } catch (ParseException e) {
            System.out.println("Error parsing the date. Please enter a valid date in the format YYYY-MM-DD.");
            addingRentalDetails(opt2, userId);
        }
        
        sc.nextLine();
        System.out.println("Enter the number of days:");
        int noOfDays = sc.nextInt();
        int payment = DatabaseConnection.getRentalAmount(opt2) * noOfDays;
        int carsId = opt2;
        Date rentalEndDate = new Date(rentalStartDate.getTime() + (noOfDays * 24L * 60 * 60 * 1000));
        String formattedEndDate = dateFormat.format(rentalEndDate);
        System.out.println("*******************************************");
        System.out.println("*                                         *");
        System.out.println("*      Rental Details                     *");
        System.out.println("*                                         *");
        System.out.println("*******************************************");
        System.out.println("The Rent StartDate  : "+startDate);
        System.out.println("The Rent EndDate    : "+formattedEndDate);
        System.out.println("Number of Rent Days : "+noOfDays);
        System.out.println("The Rental amount for "+noOfDays+" Days :"+payment);
        rentalList.add(new Rentals(rentalStartDate, noOfDays, payment, carsId, formattedEndDate));
        //DatabaseConnection.addingRentalDetailsIntoDatabase(rentalList, userId);
//
//            System.out.println("Details Collected successfully");
            System.out.println("*******************************************");
            System.out.println("*                                         *");
            System.out.println("*      Payment Options                    *");
            System.out.println("*                                         *");
            System.out.println("*******************************************");
            System.out.println("*   1. Yes, Add Payment                   *");
            System.out.println("*   2. Exit                               *");
            System.out.println("*******************************************");
            String opt = sc.next();

            switch (opt) {
                case "1":
                    bookingDetails(payment, carsId, userId);
                    flag=false;
                    break;
                case "2":
                    System.exit(0);
                    break;
                default:
                        System.out.println ("Invalid option. Please enter a valid number");
                        break;
            //}
        }
        }
        }
   public static List<BookingDetails> bookingList = new LinkedList<>();
    public static void bookingDetails(int payment, int carsId, int userId) {
        boolean flag = true;
        do {
            try {
                System.out.println("*******************************************");
                System.out.println("*                                         *");
                System.out.println("*      Payment Mode Selection             *");
                System.out.println("*                                         *");
                System.out.println("*******************************************");
                System.out.println("*   Choose mode of payment:               *");
                System.out.println("*   1. G-PAY                              *");
                System.out.println("*   2. OFFLINE                            *");
                System.out.println("*   3. Exit                               *");
                System.out.println("*******************************************");
                String opt1 = sc.next();
                switch (opt1) {
                    case "1":
                    	System.out.println("Enter UPI Id:");
                		//Scanner sc = new Scanner(System.in);
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
                            DatabaseConnection.addingRentalDetailsIntoDatabase(rentalList, userId);
                            System.out.println("The car has been booked");
                            feedback(userId,carsId) ;
                            flag=false;
                            break;
                        }
                        else {
                        	System.out.println("The Amount is Wrong");
                        }
                        break;
                    case "2":
                    	System.out.println("Your Rental Amount : " + payment +"\nEnter the correct rental amount");
                        int rentAmount1 = sc.nextInt();
                        String bookingStatus1 = "Completed";
                        String paymentMode1="Offline";
                        LocalDate bookingDate1 = LocalDate.now();
                       
                        if (rentAmount1 == payment) {
                            //System.out.println("The car has been booked");
                            bookingList.add(new BookingDetails(userId, carsId, paymentMode1, bookingDate1,bookingStatus1));
                            DatabaseConnection.addingBookingDetailsIntoDatabase(bookingList);
                            DatabaseConnection.addingRentalDetailsIntoDatabase(rentalList, userId);
                            System.out.println("The car has been booked");
                           // DatabaseConnection.updateCarAvailability(carsId); 
//                            System.out.println("Do you want to Add ");
                            feedback(userId,carsId);
                            flag=false;
                        }
                        else {
                        	System.out.println("The Amount is Wrong");
                        }
                    	
                        break;
                    case "3":
                    	System.exit(0);
                    	break;
                    default:
                        System.out.println("Invalid option. Please enter a valid option.");
                        bookingDetails( payment,  carsId,  userId);
                        flag = false;
                }
//               
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Invalid input. Please enter a valid number.");
                flag = false;
            }
        } while (flag);
    }
    static List<FeedBackDetails>feedback=new LinkedList<>();
    public static void feedback(int userId,int carsId) {
    	DatabaseConnection.Invoice(userId, carsId);
    	System.out.println("Give Your Ratings for the (RENT AND RIDE)in scale of 5 ");
    	int rating = sc.nextInt();
    	sc.nextLine();
    	System.out.println("Give Your FeedBack about the (RENT AND RIDE) and their Service ");
    	String longText=sc.nextLine();
    	
    	feedback.add(new FeedBackDetails(longText,rating,userId));
    	DatabaseConnection.addingFeedbackDetailsIntoDatabase(feedback);
        DatabaseConnection.updateCarAvailability(carsId);
    }
}