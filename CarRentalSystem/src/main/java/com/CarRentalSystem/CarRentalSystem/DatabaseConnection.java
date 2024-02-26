package com.CarRentalSystem.CarRentalSystem;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

import com.Car.BookingDetails;
import com.Car.CarDetails;
import com.Car.Cars;
import com.Car.Rentals;
import com.FeedBack.FeedBackDetails;
import com.Search.Catalog;
public class DatabaseConnection {
    private static Connection con;
    static Scanner sc= new Scanner(System.in);
    static {
        String URL = "jdbc:oracle:thin:@localhost:1521:xe";
        String userName = "SANTHOSH";
        String password = "thor";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(URL, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getPasswordFromDatabase(String userName) {
        String password = null;
        final String selectQuery = "SELECT password FROM SANTHOSH.Profile_Management WHERE USER_NAME = ? AND (ACTIVE_STATUS='ACTIVE' OR ACTIVE_STATUS='Active')";

        try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, userName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    password = resultSet.getString("password");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return password;
    }

    public static void modifyActiveStatus(String userName) {
        final String updateQuery = "UPDATE SANTHOSH.Profile_Management SET ACTIVE_STATUS = 'INACTIVE' WHERE USER_NAME = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
           // preparedStatement.setString(1, newPassword);
            preparedStatement.setString(1, userName);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Inactived the User");
            } else {
                System.out.println("Check the UserName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void modifyPasswordInDatabase(String userName, String newPassword) {
        final String updateQuery = "UPDATE SANTHOSH.Profile_Management SET password = ? WHERE USER_NAME = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, userName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void modifyPasswordAdmin(String newPassword) {
        final String updateQuery = "UPDATE SANTHOSH.Profile_Management_admin SET password = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, newPassword);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void UsernameAndPassword(String userName,String password) {
    	 final String insertQuery = "INSERT INTO SANTHOSH.Profile_Management(USER_NAME,PASSWORD) VALUES (?, ?)";
    	 try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
    		 preparedStatement.setString(1, userName);
             preparedStatement.setString(2,password);
             int rowsAffected = preparedStatement.executeUpdate();

             if (rowsAffected > 0) {
                 System.out.println("Inserted");
             } else {
                 System.out.println("Not inserted");
             }
    	 }
    	 catch (SQLException e) {
             e.printStackTrace();
         }
    }
    public static void addingDetailsIntoDatabase(List<Customer> customerList, String userName) {
        final String insertQuery = "INSERT INTO SANTHOSH.CUSTOMER (FIRST_NAME,LAST_NAME,GENDER,AGE,CUST_ADDRESS,PHONE_NO,EMAIL,USER_NAME) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
            for (Customer customer : customerList) {
               // preparedStatement.setInt(1, customer.getCustomerId());
                preparedStatement.setString(1, customer.getFirstName());
                preparedStatement.setString(2, customer.getLastName());
                preparedStatement.setString(3, customer.getGender());
                preparedStatement.setInt(4, customer.getAge());
                preparedStatement.setString(5, customer.getAddress());
                preparedStatement.setLong(6, customer.getPhoneNo());
                preparedStatement.setString(7, customer.geteMail());
                preparedStatement.setString(8, userName);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Inserted");
                } else {
                    System.out.println("Not inserted");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	public static String getPasswordForAdmin(String adminName) {
		 String password = null;
	        final String selectQuery = "SELECT password FROM SANTHOSH.Profile_Management_admin WHERE USER_NAME = ?";
	        try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
	            preparedStatement.setString(1, adminName);
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    password = resultSet.getString("password");
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return password;
	}
	
	public static void modifyFirstName(String userName) {
		System.out.println("Enter the firstName");
	    String newName=sc.next();
	    final String selectQuery = "UPDATE SANTHOSH.CUSTOMER SET First_Name = ? WHERE user_name = ?";
	    try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
	    	preparedStatement.setString(1, newName);
	        preparedStatement.setString(2, userName);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User details updated successfully.");
            } else {
                System.out.println("User not found or no changes made.");
            }
	    }
	    catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void modifyLastName(String userName) {
		System.out.println("Enter the lastName");
	    String newName=sc.next();
	    final String selectQuery = "UPDATE SANTHOSH.CUSTOMER SET Last_Name = ? WHERE user_name = ?";
	    try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
	    	preparedStatement.setString(1, newName);
	        preparedStatement.setString(2, userName);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User details updated successfully.");
            } else {
                System.out.println("User not found or no changes made.");
            }
	    }
	    catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void modifyGender(String userName) {
		System.out.println("Enter the gender");
	    String newName=sc.next();
	    final String selectQuery = "UPDATE SANTHOSH.CUSTOMER SET gender = ? WHERE user_name = ?";
	    try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
	    	preparedStatement.setString(1, newName);
	        preparedStatement.setString(2, userName);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User details updated successfully.");
            } else {
                System.out.println("User not found or no changes made.");
            }
	    }
	    catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void modifyAge(String userName) {
		System.out.println("Enter the Age");
	    int newName=sc.nextInt();
	    final String selectQuery = "UPDATE SANTHOSH.CUSTOMER SET Age = ? WHERE user_name = ?";
	    try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
	    	preparedStatement.setInt(1, newName);
	        preparedStatement.setString(2, userName);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User details updated successfully.");
            } else {
                System.out.println("User not found or no changes made.");
            }
	    }
	    catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void modifyAddress(String userName) {
		System.out.println("Enter the Address");
	    String newName=sc.next();
	    final String selectQuery = "UPDATE SANTHOSH.CUSTOMER SET  Cust_Address = ? WHERE user_name = ?";
	    try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
	    	preparedStatement.setString(1, newName);
	        preparedStatement.setString(2, userName);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User details updated successfully.");
            } else {
                System.out.println("User not found or no changes made.");
            }
	    }
	    catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void modifyPhoneNo(String userName) {
		System.out.println("Enter the PhoneNO");
	    long newName=sc.nextLong();  
	    final String selectQuery = "UPDATE SANTHOSH.CUSTOMER SET  Phone_No = ? WHERE user_name = ?";
	    try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
	    	preparedStatement.setString(1, Long.toString(newName));
	        preparedStatement.setString(2, userName);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User details updated successfully.");
            } else {
                System.out.println("User not found or no changes made.");
            }
	    }
	    catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void modifyEmail(String userName) {
		System.out.println("Enter the Email");
	    String newName=sc.next();
	    final String selectQuery = "UPDATE SANTHOSH.CUSTOMER SET Email = ? WHERE user_name = ?";
	    try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
	    	preparedStatement.setString(1, newName);
	        preparedStatement.setString(2, userName);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User details updated successfully.");
            } else {
                System.out.println("User not found or no changes made.");
            }
	    }
	    catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public static void getUserDetails(String userName) {
	    final String selectQuery = "SELECT First_Name, Last_Name, Gender, Age, Cust_Address, Phone_No, Email FROM SANTHOSH.CUSTOMER WHERE user_name= ?";

	    try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
	        preparedStatement.setString(1, userName);
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            while (resultSet.next()) {
	                String firstName = resultSet.getString("FIRST_NAME");
	                String lastName = resultSet.getString("LAST_NAME");
	                String gender = resultSet.getString("GENDER");
	                int age = resultSet.getInt("AGE");
	                String address = resultSet.getString("cust_ADDRESS");
	                String phoneNo = resultSet.getString("PHONE_NO");
	                String email = resultSet.getString("EMAIL");
	                long phoneNumber = Long.parseLong(phoneNo);	                
	                
	                System.out.println("***************************************");
	                System.out.println("*            User Details             *");
	                System.out.println("***************************************");
	                System.out.printf("Name: %s %s\nGender: %s\nAge: %d\nEmail: %s\nPhone Number: %s\nAddress: %s\n",
	                                  firstName, lastName, gender, age, email, phoneNumber, address);
	                System.out.println("***************************************");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void getAdminDetails() {
	    final String selectQuery = "SELECT First_Name, Last_Name, Gender, Age , Phone_No, Email FROM SANTHOSH.admindetails";
	    try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            while (resultSet.next()) {
	                String firstName = resultSet.getString("FIRST_NAME");
	                String lastName = resultSet.getString("LAST_NAME");
	                String gender = resultSet.getString("GENDER");
	                int age = resultSet.getInt("AGE");
	              //  String address = resultSet.getString("cust_ADDRESS");
	                String phoneNo = resultSet.getString("PHONE_NO");
	                String email = resultSet.getString("EMAIL");
	                long phoneNumber = Long.parseLong(phoneNo);	                
	               //+ " " +  address 
	                System.out.println(firstName + " " + lastName + " " + gender + " " + age+ " " + email + " "+ phoneNumber );
	            }
	        }
	    } 
	    catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void modifyAdminFirstName() {
		System.out.println("Enter the firstName");
	    String newName=sc.next();
	    final String selectQuery = "UPDATE SANTHOSH.AdminDetails SET First_Name = ?";
	    try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
	    	preparedStatement.setString(1, newName);
	        int rowsAffected = preparedStatement.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("User details updated successfully.");
	        } else {
	            System.out.println("User not found or no changes made.");
	        }
	    }
	    catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void modifyAdminLastName() {
		System.out.println("Enter the lastName");
	    String newName=sc.next();
	    final String selectQuery = "UPDATE SANTHOSH.AdminDetails SET Last_Name = ? ";
	    try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
	    	preparedStatement.setString(1, newName);
	        int rowsAffected = preparedStatement.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("User details updated successfully.");
	        } else {
	            System.out.println("User not found or no changes made.");
	        }
	    }
	    catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void modifyAdminGender() {
		System.out.println("Enter the gender");
	    String newName=sc.next();
	    final String selectQuery = "UPDATE SANTHOSH.AdminDetails SET gender = ?";
	    try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
	    	preparedStatement.setString(1, newName);
	        int rowsAffected = preparedStatement.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("User details updated successfully.");
	        } else {
	            System.out.println("User not found or no changes made.");
	        }
	    }
	    catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void modifyAdminAge() {
		System.out.println("Enter the Age");
	    int newName=sc.nextInt();
	    final String selectQuery = "UPDATE SANTHOSH.AdminDetails SET Age = ?";
	    try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
	    	preparedStatement.setInt(1, newName);
	        //preparedStatement.setString(2, userName);
	        int rowsAffected = preparedStatement.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("User details updated successfully.");
	        } else {
	            System.out.println("User not found or no changes made.");
	        }
	    }
	    catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void modifyAdminEmail() {
		System.out.println("Enter the Email");
	    String newName=sc.next();
	    final String selectQuery = "UPDATE SANTHOSH.AdminDetails SET  email = ? ";
	    try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
	    	preparedStatement.setString(1, newName);
	        //preparedStatement.setString(2, userName);
	        int rowsAffected = preparedStatement.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("User details updated successfully.");
	        } else {
	            System.out.println("User not found or no changes made.");
	        }
	    }
	    catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void modifyAdminPhoneNo() {
		System.out.println("Enter the PhoneNO");
	    long newName=sc.nextLong();  
	    final String selectQuery = "UPDATE SANTHOSH.AdminDetails SET  Phone_No = ?";
	    try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
	    	preparedStatement.setString(1, Long.toString(newName));
	       // preparedStatement.setString(2, userName);
	        int rowsAffected = preparedStatement.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("User details updated successfully.");
	        } else {
	            System.out.println("User not found or no changes made.");
	        }
	    }
	    catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void deleteCar(String carModel) {
	    final String deleteQuery = "DELETE FROM SANTHOSH.CARS WHERE CAR_MODEL=?";

	    try (PreparedStatement preparedStatement = con.prepareStatement(deleteQuery)) {
	        preparedStatement.setString(1, carModel);
	        int rowsAffected = preparedStatement.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("Car with model " + carModel + " deleted successfully.");
	        } else {
	            System.out.println("Car with model " + carModel + " not found.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void updateCarCapacity(String carModel, int newCapacity) {
	    final String updateQuery = "UPDATE SANTHOSH.CARS SET Capacity=? WHERE Car_Model = ?";

	    try (PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
	        preparedStatement.setInt(1, newCapacity);
	        preparedStatement.setString(2, carModel);

	        int rowsAffected = preparedStatement.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("Car details for model " + carModel + " updated successfully.");
	        } else {
	            System.out.println("Car with model " + carModel + " not found.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void updateCarNumberPlate(String carModel ,int numberPlate) {
	    final String updateQuery = "UPDATE SANTHOSH.CARS SET   	CAR_NUMBERPLATE =? WHERE Car_Model = ?";

	    try (PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
	       
	        preparedStatement.setInt(1, numberPlate);
	        preparedStatement.setString(2, carModel);

	        int rowsAffected = preparedStatement.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("Car details for model " + carModel + " updated successfully.");
	        } else {
	            System.out.println("Car with model " + carModel + " not found.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void updateCarAvailabity(String carModel ,String newAvailability) {
	    final String updateQuery = "UPDATE SANTHOSH.CARS SET  Car_Availability=? WHERE Car_Model = ?";

	    try (PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
	       
	        preparedStatement.setString(1, newAvailability);
	        preparedStatement.setString(2, carModel);

	        int rowsAffected = preparedStatement.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("Car details for model " + carModel + " updated successfully.");
	        } else {
	            System.out.println("Car with model " + carModel + " not found.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void updateCarRent(String carModel, BigDecimal newRentPrice) {
	    final String updateQuery = "UPDATE SANTHOSH.CARS SET  Rent_Price=? WHERE Car_Model = ?";
	    try (PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
	        preparedStatement.setBigDecimal(1, newRentPrice);
	        preparedStatement.setString(2, carModel);
	        int rowsAffected = preparedStatement.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Car details for model " + carModel + " updated successfully.");
	        } else {
	            System.out.println("Car with model " + carModel + " not found.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void getCarDetails() {
	    final String selectQuery = "SELECT * FROM SANTHOSH.CARS";

	    try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            while (resultSet.next()) {
	                int carId = resultSet.getInt("Cars_Id");
	                int numberPlate = resultSet.getInt("car_numberPlate");
	                String carModel = resultSet.getString("Car_Model");
	                int capacity = resultSet.getInt("Capacity");
	                String availability = resultSet.getString("Car_Availability");
	                BigDecimal rentPrice = resultSet.getBigDecimal("Rent_Price");
	                System.out.println("Car ID       : " + carId);
	                System.out.println("Car Model    : " + carModel);
	                System.out.println("Number Plate : " + numberPlate);
	                System.out.println("Capacity     : " + capacity);
	                System.out.println("Availability : " + availability);
	                System.out.println("Rent Price   : " + rentPrice);
	                System.out.println("----------------------");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void addingCarDetailsIntoDatabase(List<Cars> carList) {
	    final String insertQuery = "INSERT INTO SANTHOSH.Cars(CAR_NUMBERPLATE,CAR_MODEL,CAPACITY,CAR_AVAILABILITY,RENT_PRICE) VALUES ( ?, ?, ?, ?, ?)";	    
	    try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
	        for (Cars car : carList) {
	            preparedStatement.setInt(1, car.getCarNumberPlate());
	            preparedStatement.setString(2, car.getCarModel());
	            preparedStatement.setInt(3, car.getCapacity());
	            preparedStatement.setString(4, car.getCarAvailability());
	            preparedStatement.setDouble(5, car.getRentPrice());  
	            int rowsAffected = preparedStatement.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("Inserted");
	            } else {
	                System.out.println("Not inserted");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	 public static void addingRentalDetailsIntoDatabase(List<Rentals> rentalList,int userId) {
	        final String insertQuery = "INSERT INTO SANTHOSH.RENTALS (RENTAL_START_DATE, NO_OF_DAYS, PAYMENT, CARS_ID,cust_id,Rental_EndDate) VALUES (?, ?, ?, ?,?,?)";
	        
	        try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
	            for (Rentals rental : rentalList) {
	                java.util.Date utilDate = rental.getRentalStartDate();
	                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	                preparedStatement.setDate(1, sqlDate);
	                preparedStatement.setInt(2, rental.getNoOfDays());
	                preparedStatement.setInt(3, rental.getPayment());
	                preparedStatement.setInt(4, rental.getCarsId());
	                preparedStatement.setInt(5, userId);
	                preparedStatement.setString(6, rental.getRentalEndDate());
	                int rowsAffected = preparedStatement.executeUpdate();
	                if (rowsAffected > 0) {
	                    System.out.println("Inserted");
	                } else {
	                    System.out.println("Not inserted");
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	 }

	public static int getRentalAmount(int opt2) {
		  	int rentPrice=0;
	        final String selectQuery = "SELECT RENT_PRICE FROM SANTHOSH.CARS WHERE CARS_ID= ?";
	        try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
	            preparedStatement.setInt(1,opt2 );
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                     rentPrice = resultSet.getInt("RENT_PRICE");
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return rentPrice;
	}
static List<Cars> carsInRange = new LinkedList<>();
public static void searchByPriceRange(int minPrice, int maxPrice) {
    try {
        String selectQuery = "SELECT * FROM SANTHOSH.Cars WHERE Rent_Price >= ? AND Rent_Price <= ? AND Car_Availability='yes' ";
        PreparedStatement preparedStatement = con.prepareStatement(selectQuery);
        preparedStatement.setInt(1, minPrice);
        preparedStatement.setInt(2, maxPrice);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int carId = resultSet.getInt("Cars_Id");
            int numberPlate = resultSet.getInt("car_numberPlate");
            String carModel = resultSet.getString("Car_Model");
            int capacity = resultSet.getInt("Capacity");
            String availability = resultSet.getString("Car_Availability");
            double rentPrice = resultSet.getDouble("Rent_Price");

            Cars car = new Cars(carId, numberPlate, carModel, capacity, availability, rentPrice);
            carsInRange.add(car);
            System.out.println("Car ID       : " + carId);
            System.out.println("Number Plate : " + numberPlate);
            System.out.println("Car Model    : " + carModel);
            System.out.println("Capacity     : " + capacity);
            System.out.println("Availability : " + availability);
            System.out.println("Rent Price   : " + rentPrice);
            System.out.println("----------------------");
        }
//        System.out.println("Do you want to book Car ? If yes 1. Do register 2.Exit");
//        String opt=sc.next();
//        
//        if(opt.equals("1")) {
//        	AccountDetails.addingDetails();
//        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
private static boolean isValidCarId2(final int carId) {
    return carsInRange.stream().anyMatch(new Predicate<Cars>() {
		@Override
		public boolean test(Cars car) {
			return car.getCarsId() == carId;
		}
	});
}
	public static void searchByPriceRangeDetails(int minPrice, int maxPrice,int userId) {
		searchByPriceRange(minPrice, maxPrice);
		 	System.out.println("*******************************************");
	        System.out.println("*                                         *");
	        System.out.println("*        Car Booking Options              *");
	        System.out.println("*                                         *");
	        System.out.println("*******************************************");
	        System.out.println("*   Do you want to book a car?            *");
	        System.out.println("*   If yes, choose any of the car IDs.    *");
	        System.out.println("*   If no, press 0 to exit.               *");
	        System.out.println("*******************************************");
		int opt = sc.nextInt();
		if (opt != 0) {
		    boolean isValidCarId2 = false;
		    for (Cars car : carsInRange) {
		        if (car.getCarsId() == opt) {
		            isValidCarId2 = true;
		            break;
		        }
		    }
		    if (isValidCarId2) {
		        CarDetails.addingRentalDetails(opt,userId);
		    } else {
		        System.out.println("Invalid car ID. Exiting...");
		    }
		} else {
		    System.exit(0);
		}
	}
    static List<Cars> carsWithModel = new LinkedList<>();
	public static void searchByModel(String carModel) {
	    try {
	        String selectQuery = "SELECT * FROM SANTHOSH.Cars WHERE Car_Model LIKE ? AND Car_Availability='yes'";
	        PreparedStatement preparedStatement = con.prepareStatement(selectQuery);
	        preparedStatement.setString(1, "%" + carModel + "%");
	        ResultSet resultSet = preparedStatement.executeQuery();
	        while (resultSet.next()) {
	            int carId = resultSet.getInt("Cars_Id");
	            int numberPlate = resultSet.getInt("car_numberPlate");
	            String model = resultSet.getString("Car_Model");
	            int capacity = resultSet.getInt("Capacity");
	            String availability = resultSet.getString("Car_Availability");
	            double rentPrice = resultSet.getDouble("Rent_Price");
	            Cars car = new Cars(carId, numberPlate, model, capacity, availability, rentPrice);
	            carsWithModel.add(car);
	            System.out.println("Car ID        : " + carId);
	            System.out.println("Number Plate  : " + numberPlate);
	            System.out.println("Car Model     : " + model);
	            System.out.println("Capacity      : " + capacity);
	            System.out.println("Availability  : " + availability);
	            System.out.println("Rent Price    : " + rentPrice);
	            System.out.println("----------------------");
	        }
//	        System.out.println("Do you want to book Car ? If yes 1. Do register 2.Exit");
//	        String opt=sc.next();
//	        
//	        if(opt.equals("1")) {
//	        	AccountDetails.addingDetails();
//	        }
	    	}
	        catch (SQLException e) {
		        e.printStackTrace();
		    }
	    }
	@SuppressWarnings("unused")
	private static boolean isValidCarId1(final int carId) {
	    return carsWithModel.stream().anyMatch(new Predicate<Cars>() {
			@Override
			public boolean test(Cars car) {
				return car.getCarsId() == carId;
			}
		});
	}
	public static void searchByModelDetails(int userId,String carModel) {
			searchByModel(carModel);
			System.out.println("*******************************************");
	        System.out.println("*                                         *");
	        System.out.println("*        Car Booking Options              *");
	        System.out.println("*                                         *");
	        System.out.println("*******************************************");
	        System.out.println("*   Do you want to book a car?            *");
	        System.out.println("*   If yes, choose any of the car IDs.    *");
	        System.out.println("*   If no, press 0 to exit.               *");
	        System.out.println("*******************************************");
	        int opt = sc.nextInt();
	        if (opt != 0) {
	            boolean isValidCarId1 = false;
	            for (Cars car : carsWithModel) {
	                if (car.getCarsId() == opt) {
	                    isValidCarId1 = true;
	                    break;
	                }
	            }

	            if (isValidCarId1) {
	                CarDetails.addingRentalDetails(opt,userId);
	            } else {
	                System.out.println("Invalid car ID. Exiting...");
	            }
	        } else {
	            System.exit(0);
	        }
	    }
	
	public static void showAvailableCarsDetails(int userId) {
	    showAvailableCars();
	    System.out.println("*******************************************");
        System.out.println("*                                         *");
        System.out.println("*        Car Booking Options              *");
        System.out.println("*                                         *");
        System.out.println("*******************************************");
        System.out.println("*   Do you want to book a car?            *");
        System.out.println("*   If yes, choose any of the car IDs.    *");
        System.out.println("*   If no, press 0 to exit.               *");
        System.out.println("*******************************************");
	    try {
	        int opt = sc.nextInt();
	        if (opt != 0) {
	            boolean isValidCarId = isValidCarId(opt);
	            if (isValidCarId) {
	                CarDetails.addingRentalDetails(opt, userId);
	            } else {
	                System.out.println("Invalid car ID. Exiting...");
	            }
	        } else {
	            System.out.println("Exiting...");
	        }
	    } catch (InputMismatchException e) {
	        System.out.println("Invalid input. Please enter a valid number. Exiting...");
	        sc.nextLine(); // Consume the invalid input to avoid an infinite loop.
	    }
	}

	private static boolean isValidCarId(final int carId) {
	    return availableCars.stream().anyMatch(new Predicate<Cars>() {
			@Override
			public boolean test(Cars car) {
				return car.getCarsId() == carId;
			}
		});
	}
	static List<Cars> availableCars = new LinkedList<>();
	public static void showAvailableCars() {
	    
	    try {
	        String selectQuery = "SELECT * FROM SANTHOSH.Cars WHERE Car_Availability = 'yes'";
	        PreparedStatement preparedStatement = con.prepareStatement(selectQuery);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        while (resultSet.next()) {
	            int carId = resultSet.getInt("Cars_Id");
	            int numberPlate = resultSet.getInt("car_numberPlate");
	            String model = resultSet.getString("Car_Model");
	            int capacity = resultSet.getInt("Capacity");
	            String availability = resultSet.getString("Car_Availability");
	            double rentPrice = resultSet.getDouble("Rent_Price");
	            Cars car = new Cars(carId, numberPlate, model, capacity, availability, rentPrice);
	            availableCars.add(car);
	            System.out.println("Car ID       : " + carId);
	            System.out.println("Number Plate : " + numberPlate);
	            System.out.println("Car Model    : " + model);
	            System.out.println("Capacity     : " + capacity);
	            System.out.println("Availability : " + availability);
	            System.out.println("Rent Price   : " + rentPrice);
	            System.out.println("----------------------");
	        }
//	        System.out.println("Do you want to book Car ? If yes 1. Do register 2.Exit");
//	        String opt=sc.next();
//	        
//	        if(opt.equals("1")) {
//	        	AccountDetails.addingDetails();
//	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static int getUserId(String userName) {
	  	int customerId=0;
        final String selectQuery = "SELECT cust_id FROM SANTHOSH.customer WHERE user_name= ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
            preparedStatement.setString(1,userName );
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                	customerId = resultSet.getInt("cust_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerId;
	}
	public static void addingBookingDetailsIntoDatabase(List<BookingDetails> booking) {
	    final String insertQuery = "INSERT INTO SANTHOSH.BOOKING (Cust_id, Cars_id, ModeOfPayment, Booking_Date,BOOKING_STATUS) VALUES (?,?,?,?,?)";
	    try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
	        for (BookingDetails books : booking) {
	            preparedStatement.setInt(1, books.getCustomerId()); 
	            preparedStatement.setInt(2, books.getCarsId());
	            preparedStatement.setString(3, books.getModeOfPayment());
	            LocalDate utilBookingDate = books.getBookingDate();
	            java.sql.Date sqlBookingDate = new java.sql.Date(utilBookingDate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond() * 1000);
	            preparedStatement.setDate(4, sqlBookingDate);
	            preparedStatement.setString(5, books.getBookingStatus());
	            int rowsAffected = preparedStatement.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("Inserted");
	            } else {
	                System.out.println("Not inserted");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void getBookingHistory(String userName) {
	    int customerId = getUserId(userName);
	    final String selectQuery = "SELECT R.RENTAL_START_DATE, R.NO_OF_DAYS, R.PAYMENT, R.CARS_ID, R.RENTAL_ENDDATE, C.CAR_MODEL FROM rentals R JOIN cars C ON R.CARS_ID = C.CARS_ID WHERE R.CUST_ID = ?";
	    try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
	        preparedStatement.setInt(1, customerId);
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	        	boolean bookingsExist = false;
	            while (resultSet.next()) {
	            	bookingsExist = true;
	                int carsId = resultSet.getInt("CARS_ID");
	                String rentalStartDate = resultSet.getString("RENTAL_START_DATE");
	                int noOfDays = resultSet.getInt("NO_OF_DAYS");
	                String rentalEndDate = resultSet.getString("RENTAL_ENDDATE");
	                int payment = resultSet.getInt("PAYMENT");
	                String carModel = resultSet.getString("CAR_MODEL");
	                System.out.println("Car Rental Information:");
	                System.out.println("Car Model         : " + carModel);
	                System.out.println("Car ID            : " + carsId);
	                System.out.println("Rental Start Date : " + rentalStartDate);
	                System.out.println("Number of Days    : " + noOfDays);
	                System.out.println("Rental End Date   : " + rentalEndDate);
	                System.out.println("Payment           : " + payment);
	                System.out.println("-------------------------------------------------");
	            }
	            if (!bookingsExist) {
	                System.out.println("No booking history available for the user.");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void cancellation(String userName) {
	    Date bookedDate = null;
	    System.out.println("The Car that you have been booked So far ");
	    getBookingHistory(userName);
	    System.out.println("Enter the carId which you need to Cancel ");
	    int carId = sc.nextInt();
	    LocalDate presentDate = LocalDate.now();
	    int totalprice = 0;
	    final String selectQuery = "SELECT BOOKING_DATE FROM SANTHOSH.booking WHERE cars_id=?";
	    final String selectPayment = "SELECT payment FROM SANTHOSH.rentals WHERE cars_id=?";
	    try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
	        preparedStatement.setInt(1, carId);
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                bookedDate = resultSet.getDate("BOOKING_DATE");
	                LocalDate startDate = bookedDate.toLocalDate();
	                LocalDate endDate = startDate.plusDays(7);

	                if (presentDate.isAfter(endDate)) {
	                    System.out.println("Refund not permitted as more than 7 days have passed.");
	                } else {
	                    try (PreparedStatement preparedStatements = con.prepareStatement(selectPayment)) {
	                        preparedStatements.setInt(1, carId);
	                        try (ResultSet price = preparedStatements.executeQuery()) {
	                            if (price.next()) {
	                                totalprice = price.getInt(1);
	                                System.out.println("Refunded amount : " + totalprice);
	                                System.out.println("Your car is cancelled successfully :)");
	                                updateCarAvailability(carId);
	                            }
	                        }
	                    }
	                }
	            } else {
	                System.out.println("Booking not found for the specified carId.");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	//FEEDBACKID	LONGTEXT	CUST_ID	RATINGS
	public static void feedbackRead() {
		final String feedback="SELECT * FROM SANTHOSH.FEEDBACK";
		try(PreparedStatement preparedStatement=con.prepareStatement(feedback)){
			try (ResultSet details = preparedStatement.executeQuery()) {
				System.out.println("**********************************************");
				System.out.printf("| %-10s | %-30s | %-8s | %-8s |%n", "FEEDBACKID", "LONGTEXT", "CUST_ID", "RATINGS");
				while(details.next()) {
					System.out.println("|------------|--------------------------------|----------|----------|");
					System.out.printf("| %-10s | %-30s | %-8s | %-8s |%n", details.getInt("FEEDBACKID"), details.getString("LONGTEXT"), details.getInt("CUST_ID"), details.getInt("RATINGS"));
					

				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void updateCarAvailability(int carId) {
	    final String updateQuery = "UPDATE SANTHOSH.CARS SET  CAR_AVAILABILITY='no' WHERE CARS_ID = ?";
	    try (PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
	        
	        preparedStatement.setInt(1, carId);
	        int rowsAffected = preparedStatement.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Updated successfully.");
	        } else {
	            System.out.println("Car not found.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void addingFeedbackDetailsIntoDatabase(List<FeedBackDetails>feedback) {
	    final String insertQuery = "INSERT INTO SANTHOSH.FEEDBACK (LONGTEXT, cust_id, ratings) VALUES (?,?,?)";
	    try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
	        for (FeedBackDetails feedbacks : feedback) {
	             preparedStatement.setString(1, feedbacks.getLongText()); 
	            preparedStatement.setInt(2, feedbacks.getCustomerId());
	            preparedStatement.setInt(3, feedbacks.getRatings());
	            int rowsAffected = preparedStatement.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("Inserted");
	            } else {
	                System.out.println("Not inserted");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void Invoice(int custId, int carsId) {
	  final String feedbackQuery = "SELECT " +
              "R.RENTAL_ID, " +
              "R.RENTAL_START_DATE, " +
              "R.NO_OF_DAYS, " +
              "R.PAYMENT, " +
              "R.CARS_ID AS RENTED_CAR_ID, " +
              "R.CUST_ID AS RENTED_BY_CUSTOMER_ID, " +
              "R.RENTAL_ENDDATE, " +
              "B.BOOKING_ID, " +
              "B.MODEOFPAYMENT, " +
              "B.BOOKING_DATE, " +
              "B.BOOKING_STATUS " +
              "FROM RENTALS R " +
              "JOIN BOOKING B ON R.CUST_ID = B.CUST_ID AND R.CARS_ID = B.CARS_ID " +
              "WHERE R.CUST_ID = ? AND R.CARS_ID = ?";

      try (PreparedStatement preparedStatement = con.prepareStatement(feedbackQuery)) {
          preparedStatement.setInt(1, custId);
          preparedStatement.setInt(2, carsId);

          try (ResultSet details = preparedStatement.executeQuery()) {
        	  System.out.println("**********************************************");

              while (details.next()) {
                  System.out.println("RENTAL DETAILS:");
                  System.out.println("---------------");
                  System.out.println("RENTAL ID: " + details.getInt("RENTAL_ID"));
                  System.out.println("RENTAL START DATE: " + details.getString("RENTAL_START_DATE"));
                  System.out.println("NO OF DAYS: " + details.getInt("NO_OF_DAYS"));
                  System.out.println("PAYMENT: " + details.getInt("PAYMENT"));
                  System.out.println("RENTED CAR ID: " + details.getInt("RENTED_CAR_ID"));
                  System.out.println("RENTED BY CUSTOMER ID: " + details.getInt("RENTED_BY_CUSTOMER_ID"));
                  System.out.println("RENTAL END DATE: " + details.getString("RENTAL_ENDDATE"));

                  System.out.println("\nBOOKING DETAILS:");
                  System.out.println("----------------");
                  System.out.println("BOOKING ID: " + details.getInt("BOOKING_ID"));
                  System.out.println("MODE OF PAYMENT: " + details.getString("MODEOFPAYMENT"));
                  System.out.println("BOOKING DATE: " + details.getString("BOOKING_DATE"));
                  System.out.println("BOOKING STATUS: " + details.getString("BOOKING_STATUS"));

                  System.out.println("**********************************************");
          }
      }
      }catch (SQLException e) {
          e.printStackTrace();
      }
	}
}