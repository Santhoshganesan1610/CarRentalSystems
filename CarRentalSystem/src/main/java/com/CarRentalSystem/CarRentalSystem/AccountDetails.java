package com.CarRentalSystem.CarRentalSystem;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.Car.CarDetails;
import com.Car.Cars;
import com.Car.Rentals;
import com.Search.Catalog;

public class AccountDetails {
    public static List<Customer> customerList = new LinkedList<>();
    private static List<Cars> carList = new LinkedList<>();
    static Scanner sc=new Scanner(System.in);
    public static void loginAndValidate() {
    	
    	while(true) {
    	  System.out.println("*******************************************");
          System.out.println("*                                         *");
          System.out.println("*          Welcome to RENT AND RIDE       *");
          System.out.println("*                                         *");
          System.out.println("*******************************************");
          System.out.println("*   1. Customer                           *");
          System.out.println("*   2. Admin                              *");
          System.out.println("*   3. Guest                              *");
          System.out.println("*   4. Exit                               *");
          System.out.println("*******************************************");
          try {
	        Scanner sc = new Scanner(System.in);
	        int option = sc.nextInt();
	        LoginPage(option);
	        if (option < 1 || option > 3) {
	            throw new InputMismatchException("Invalid option. Please enter a valid number ");
	        }
	       
	      }
	      catch(InputMismatchException e) {
	        	  System.out.println(e.getMessage());
	          }
    }
    }
    private static void LoginPage(int option) {
    boolean isLoginPage=true;
    while(isLoginPage) {
    switch (option) {
        case 1:
        	boolean flag=true;
        	do {
        		System.out.println("*******************************************");
                System.out.println("*                                         *");
                System.out.println("*      Car Rental - Login/Register        *");
                System.out.println("*                                         *");
                System.out.println("*******************************************");
                System.out.println("*   1. Login                              *");
                System.out.println("*   2. Register                           *");
                System.out.println("*   3. Previous Menu                      *");
                System.out.println("*   4. Logout                             *");
                System.out.println("*******************************************");
                String opt = sc.next();
                switch (opt) {
                    case "1":
                        Login();
                        break;
                    case "2":
                        addingDetails();
                        break;
                    case "3":
                    	loginAndValidate();
                    case "4":
                    	System.exit(0);
                        break;
                    default:
                    	try {
                    		throw new InputMismatchException("Invalid option. Please enter a Valid number ");
                    	}catch(Exception e) {
                    		System.out.println(e.getMessage());
                    		flag=false;
                    	}
                }
        	}while(flag);
        	
            break;
        case 2:
            Loginadmin();
            break;

        case 3:
        	search();
            break;
        case 4:
        	System.exit(0);
    	}
    	}
    }
    public static void search() {
    	Catalog catalog=new Catalog();
    	 boolean flag1 = true;
         do {
             System.out.println("*******************************************");
             System.out.println("*                                         *");
             System.out.println("*       Car Rental - Search Options       *");
             System.out.println("*                                         *");
             System.out.println("*******************************************");
             System.out.println("*   1. Search By Price                    *");
             System.out.println("*   2. Search By Availability             *");
             System.out.println("*   3. Search By Model                    *");
             System.out.println("*   4. Previous Menu                      *");
             System.out.println("*   5. Exit                               *");
             System.out.println("*******************************************");
             String search = sc.next();
             switch (search) {
                 case "1":
                     catalog.searchByPriceRange();
                     //backoption();
                     break;
                 case "2":
                     catalog.showAvailableCars();
                    // backoption();
                     break;
                 case "3":
                     catalog.searchByModel();
                    // backoption();
                     break;
                 case "4":
                	 loginAndValidate();
                     break;
                 default:
                 	try {
                 		throw new InputMismatchException("Invalid option. Please enter a valid number ");
                 	}catch(Exception e) {
                 		System.out.println(e.getMessage());
                 		flag1=false;
                 	}
             }
         } while (flag1);
    }
    public static void editProfileDetails() {
   // boolean flag1=true;
	//do {
	  System.out.println("*******************************************");
      System.out.println("*                                         *");
      System.out.println("*        Edit Profile Options             *");
      System.out.println("*                                         *");
      System.out.println("*******************************************");
      System.out.println("*   1. Edit Password                    *");
      System.out.println("*   2. Edit First Name                    *");
      System.out.println("*   3. Edit Last Name                     *");
      System.out.println("*   4. Edit Gender                        *");
      System.out.println("*   5. Edit Age                           *");
      System.out.println("*   6. Edit Phone Number                  *");
      System.out.println("*   7. Edit Email                         *");
      System.out.println("*   8. Previous Menu                      *");
      System.out.println("*******************************************");
    String opt2 = sc.next();
    switch (opt2) {
    	case "1":
    		System.out.println("Enter the new password");
    		String password=sc.next();
        	DatabaseConnection.modifyPasswordAdmin(password);
        	
            break;
        case "2":
        	DatabaseConnection.modifyAdminFirstName();
        	
            break;
        case "3":
        	DatabaseConnection.modifyAdminLastName();
        	
        	break;
        case "4":
        	DatabaseConnection.modifyAdminGender();
        	
            break;
        case "5":
        	DatabaseConnection.modifyAdminAge();
        	
            break;
        case "6":
        	DatabaseConnection.modifyAdminPhoneNo();
        	
            break;
        case "7":
        	DatabaseConnection.modifyAdminEmail();
        	
            break;
        case "8":
        	showAdminProfileMenu( sc) ;
        	break;
        default: 
        	try {
         		throw new InputMismatchException("Invalid option. Please enter a valid number");
         	}catch(Exception e) {
         		System.out.println(e.getMessage());
         		editProfileDetails();
         	}
    }
	//}while(flag1);
}
    public static void editProfiles(String opt) {
    	boolean flag=true;
    	//do {
    	switch (opt) {
        case "1":
        	DatabaseConnection.getAdminDetails();
        	System.out.println("If you want go back to previous menu: press 1. previous menu 2.exit");
        	int oppt=sc.nextInt();
        	if(oppt==1) {
        		showAdminProfileMenu( sc) ;
        	}
        	break;
        case "2":
        	editProfileDetails();
        	System.out.println("If you want go back to previous menu: press 1. previous menu 2.exit");
        	int opt1=sc.nextInt();
        	if(opt1==1) {
        		showAdminProfileMenu( sc) ;
        	}
            break;
        case "3":
        	showAdminMenu(sc);
        	break;
        default:
        	try {
         		throw new InputMismatchException("Invalid option. Please enter a valid number.");
         	}catch(Exception e) {
         		System.out.println(e.getMessage());
         		showAdminProfileMenu( sc);
         	}
    	}
    	//}while(flag);
    	
    }
    
    private static void Loginadmin() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the adminName");
            String adminName = sc.next();
            String adminPassword = DatabaseConnection.getPasswordForAdmin(adminName);
            System.out.println("Enter the Password");
            String pass = sc.next();
            if (adminPassword != null && adminPassword.equals(pass)) {
            	  System.out.println("+------------------------------------------------------+");
                  System.out.println("|              Welcome, " + adminName + "!             |");
                  System.out.println("+------------------------------------------------------+");
                showAdminMenu(sc);
            } else {
                System.out.println("Invalid password");
            }
        } catch (Exception e) {
            System.out.println("Kindly enter valid input");
        }
    }
    private static void showAdminMenu(Scanner sc) {
    	 boolean flag=true;
    	// do{
    		
        //while (true) {
            System.out.println("*******************************************");
            System.out.println("*                                         *");
            System.out.println("*            Admin Menu Options           *");
            System.out.println("*                                         *");
            System.out.println("*******************************************");
            System.out.println("*   1. Profile Update                     *");
            System.out.println("*   2. Cars Mangament                     *");
            System.out.println("*   3. UserManagement                     *");
            System.out.println("*   4. Read Feedback                      *");
            System.out.println("*   5. Previous Menu                      *");
            System.out.println("*******************************************");
            String option = sc.next();
            switch (option) {
                case "1":
                    showAdminProfileMenu(sc);
                    break;
                case "2":
                	CarManagementMenu( sc);
                	break;
                case "3":
                    showUpdateUserMenu(sc);
                    break;
                case "4":
                	DatabaseConnection.feedbackRead();
                	break;
                case "5":
                	Loginadmin();
                default:
                	 try {
                    		throw new InputMismatchException("Invalid option. Please enter a valid number ");
                    	}catch(Exception e) {
                    		System.out.println(e.getMessage());
                    		showAdminMenu(sc);
                    		}
            }
       // }while(flag);
    }
    private static void CarManagementMenu(Scanner sc) {
        System.out.println("*******************************************");
        System.out.println("*                                         *");
        System.out.println("*          CarManagement Options          *");
        System.out.println("*                                         *");
        System.out.println("*******************************************");
        System.out.println("*   1. View CarDetails                    *");
        System.out.println("*   2. Update CarDetails                  *");
        System.out.println("*   3. Previous Menu                      *");
        System.out.println("*******************************************");
        String opt = sc.next();
        switch(opt) {
        case "1":
        	DatabaseConnection.getCarDetails();
            System.out.println("Do you want to go to previous menu? If yes press 1 else 0");
            int option = sc.nextInt();
            if(option==1) {
            	CarManagementMenu(sc);
            }
            else {
            	System.exit(0);
            }
            break;
        case "2":
        	showCarManagementMenu(sc);
        	System.out.println("Do you want to go to previous menu? If yes press 1 else 0");
            int option1 = sc.nextInt();
            if(option1==1) {
            	CarManagementMenu( sc);
            }
            else {
            	System.exit(0);
            }
            break;
        case "3":
        	showAdminMenu(sc);
        	break;
        default:
        	 try {
         		throw new InputMismatchException("Invalid option. Please enter a valid number ");
         	}catch(Exception e) {
         		System.out.println(e.getMessage());
         		CarManagementMenu(sc);
         		}
        }
        
    }

    private static void showAdminProfileMenu(Scanner sc) {
        System.out.println("*******************************************");
        System.out.println("*                                         *");
        System.out.println("*          Admin Profile Options          *");
        System.out.println("*                                         *");
        System.out.println("*******************************************");
        System.out.println("*   1. View Profile                       *");
        System.out.println("*   2. Edit Profile                       *");
        System.out.println("*   3. Previous Menu                      *");
        System.out.println("*******************************************");
        try {
        String opt = sc.next();
        editProfiles(opt);
        }
        catch(InputMismatchException e) {
        	showAdminProfileMenu(sc);
        }
    }

    private static void showCarManagementMenu(Scanner sc) {
       // DatabaseConnection.getCarDetails();
        System.out.println("*******************************************");
        System.out.println("*                                         *");
        System.out.println("*        Car Management Menu              *");
        System.out.println("*                                         *");
        System.out.println("*******************************************");
        System.out.println("*   1. Add Car                            *");
        System.out.println("*   2. Delete Car                         *");
        System.out.println("*   3. Update Car                         *");
        System.out.println("*   4. Previous Menu                      *");
        System.out.println("*   5. Exit                               *");
        System.out.println("*******************************************");
        String opt1 = sc.next();
        switch (opt1) {
            case "1":
                CarDetails.addingDetails();
                System.out.println("Do you want to go to previous menu? If yes press 1 else 0");
                int option1 = sc.nextInt();
                if(option1==1) {
                	showCarManagementMenu(sc);
                }
                else {
                	System.exit(0);
                }
                break;
            case "2":
                System.out.println("Enter the car name which is to be deleted");
                String name = sc.next();
                DatabaseConnection.deleteCar(name);
                System.out.println("Do you want to go to previous menu? If yes press 1 else 0");
                int option = sc.nextInt();
                if(option==1) {
                	showCarManagementMenu(sc);
                }
                else {
                	System.exit(0);
                }
                break;
            case "3":
                showUpdateCarDetailsMenu(sc);
                System.out.println("Do you want to go to previous menu? If yes press 1 else 0");
                int option2 = sc.nextInt();
                if(option2==1) {
                	showCarManagementMenu(sc);
                }
                else {
                	System.exit(0);
                }
                break;
            case "4":
            	CarManagementMenu(sc);
                break;
            case "5":
            	System.exit(0);
            default:
           	 try {
            		throw new InputMismatchException("Invalid option. Please enter a valid number ");
            	}catch(Exception e) {
            		System.out.println(e.getMessage());
            		showCarManagementMenu(sc);
            		}
        }
    }
    private static void showUpdateCarDetailsMenu(Scanner sc) {
        System.out.println("*******************************************");
        System.out.println("*                                         *");
        System.out.println("*      Update Car Details Menu            *");
        System.out.println("*                                         *");
        System.out.println("*******************************************");
        System.out.println("*   1. Update Car Rent                    *");
        System.out.println("*   2. Update Car Availability            *");
        System.out.println("*   3. Update Car Capacity                *");
        System.out.println("*   4. Update Car Number Plate            *");
       // System.out.println("*   5. Previous Menu                      *");
        System.out.println("*******************************************");
        String opt2 = sc.next();
        System.out.println("Enter the car_model");
        String carModel = sc.next();
        sc.nextLine();
        switch (opt2) {
            case "1":
                System.out.println("Enter the newRentprice");
                BigDecimal rentprice = sc.nextBigDecimal();
                DatabaseConnection.updateCarRent(carModel, rentprice);
                backOption(sc);
                break;
            case "2":
                System.out.println("Enter the Availability");
                String availability = sc.next();
                DatabaseConnection.updateCarAvailabity(carModel, availability);
                backOption(sc);
                break;
            case "3":
                System.out.println("Enter the capacity");
                int newCapacity = sc.nextInt();
                DatabaseConnection.updateCarCapacity(carModel, newCapacity);
                backOption(sc);
                break;
            case "4":
                System.out.println("Enter the numberplate");
                int numberPlate = sc.nextInt();
                DatabaseConnection.updateCarNumberPlate(carModel, numberPlate);
                backOption(sc);
                break;
//            case "5":
//            	showCarManagementMenu(sc);
//            	break;
            default:
           	 try {
            		throw new InputMismatchException("Invalid option. Please enter a valid number ");
            	}catch(Exception e) {
            		System.out.println(e.getMessage());
            		showUpdateCarDetailsMenu(sc);
            		}
        }
    }
    private static void showUpdateUserMenu(Scanner sc) {
        System.out.println("*******************************************");
        System.out.println("*                                         *");
        System.out.println("*     1.View Customer Details             *");
        System.out.println("*     2.InActive UserId                   *");
        System.out.println("*     3.Booking History of customer       *");
        System.out.println("*     4.Previous Menu                     *");
        System.out.println("*                                         *");
        System.out.println("*******************************************");
        String option1 = sc.next();
        switch (option1) {
            case "1":
                System.out.println("Enter the UserName who's detail to be viewed");
                String userName = sc.next();
                DatabaseConnection.getUserDetails(userName);
                System.out.println("Do you want to go to previous menu? If yes press 1 else 0");
                int option2 = sc.nextInt();
                if(option2==1) {
                	showUpdateUserMenu(sc);
                }
                else {
                	System.exit(0);
                }
                break;
            case "2":
                System.out.println("Enter the UserName who's to be Inactive");
                String userName1 = sc.next();
                DatabaseConnection.modifyActiveStatus(userName1);
                System.out.println("Do you want to go to previous menu? If yes press 1 else 0");
                int option = sc.nextInt();
                if(option==1) {
                	showUpdateUserMenu(sc);
                }
                else {
                	System.exit(0);
                }
                break;
            case "3":
            	 System.out.println("Enter the UserName who's History to be viewed");
            	 String userName2 = sc.next();
            	 DatabaseConnection.getBookingHistory(userName2);
            	 System.out.println("Do you want to go to previous menu? If yes press 1 else 0");
                 int option3 = sc.nextInt();
                 if(option3==1) {
                 	showUpdateUserMenu(sc);
                 }
                 else {
                 	System.exit(0);
                 }
            case "4":
            	showAdminMenu(sc);
            	break;
            default:
              	 try {
               		throw new InputMismatchException("Invalid option. Please enter a valid number ");
               	}catch(Exception e) {
               		System.out.println(e.getMessage());
               		showUpdateUserMenu( sc);
               		}
        }
    }
    private static void backOption(Scanner sc) {
        System.out.println("Press 1 to go back to the Previous Menu");
        int backOption = sc.nextInt();
        if (backOption == 1) {
            return;
        } else {
            System.out.println("Invalid option. Returning to the Previous Menu.");
            backOption(sc);
        }
    }

    public static void ProfileOptions(String option,String userName, int userId, String userPassword1, String pass) throws Exception {
    	 boolean flag=true;
    	 do{
    		 switch (option) {
    		
	         case "1":
	        	 boolean flag1=true;
	        	 do {
	         	System.out.println("*******************************************");
	             System.out.println("*                                         *");
	             System.out.println("*          User Profile Options           *");
	             System.out.println("*                                         *");
	             System.out.println("*******************************************");
	             System.out.println("*   1. View Profile                       *");
	             System.out.println("*   2. Edit Profile                       *");
	             System.out.println("*   3. Previous Menu                      *");
	             System.out.println("*   4. Exit                               *");
	             System.out.println("*******************************************");
	             String opt = sc.next();
	             switch (opt) {
	                 case "1":
	                 	DatabaseConnection.getUserDetails(userName);
	                 	backoption();
	                    break;
	                 case "2":
	                	editProfileOptions(userName);
	                 case "3":
	                	 userOptionMenu(userName,userId,userPassword1,pass);
	                     break;
	                 case "4":
	                 	System.exit(0);
	                     break;
	                 default:
	                	 try {
                       		throw new InputMismatchException("Invalid option. Please enter a valid number .");
                       	}catch(Exception e) {
                       		System.out.println(e.getMessage());
                       		flag1=false;
                       	}
	             }
	             }while(flag1);
	             break;
	         case "2":
	         	CarBooking(userName,userId,userPassword1,pass);
	         	break;
	         case "3":
	         	DatabaseConnection.getBookingHistory(userName);
	         	flag=false;
	         	break;
	         case "4":
	        	 DatabaseConnection.cancellation(userName);
	        	 flag=false;
	        	 break;
	         case "5":
	        	 loginAndValidate();
	        	 break;
	         case "6":
	         	System.exit(0);
	         default:
	        	 try {
	        		 throw new InputMismatchException();
	        	 }catch(Exception e) {
	        		 System.out.println(e.getMessage());
	        		 flag=false;
	        	 }
	     	}
    	 }while(flag);
    }
    
    public static void Login() {
    	
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the userName");
        String userName = sc.next();
        int userId= DatabaseConnection.getUserId(userName);
        String userPassword1 = DatabaseConnection.getPasswordFromDatabase(userName);
        System.out.println("Enter the Password");
        String pass = sc.next();
        if (userPassword1 != null && userPassword1.equals(pass)) {
        	  System.out.println("+-----------------------------------------------+");
              System.out.println("             Welcome, " + userName + "!          ");
              System.out.println("+-----------------------------------------------+");
        userOptionMenu(userName,userId,userPassword1,pass);
        }
        else {
            System.out.println("Invalid password");
        }
    }
    public static void userOptionMenu(String userName, int userId, String userPassword1, String pass) {
        boolean isLogin = true;
        try {
                while (isLogin) {
                    System.out.println("*******************************************");
                    System.out.println("*                                         *");
                    System.out.println("*          User Options Menu               *");
                    System.out.println("*                                         *");
                    System.out.println("*******************************************");
                    System.out.println("*   1. Profile Update                     *");
                    System.out.println("*   2. Show Cars                          *");
                    System.out.println("*   3. Booking History                    *");
                    System.out.println("*   4. Cancellation                       *");
                    System.out.println("*   5. Previous Menu                      *");
                    System.out.println("*   6. EXIT                               *");
                    System.out.println("*******************************************");
                    String option = sc.next();
                    ProfileOptions(option, userName, userId, userPassword1, pass);

                }
            
        } catch (Exception e) {
            System.out.println("Enter a valid option.");
            isLogin = false;
        }
    }
    public static void CarBooking(String userName, int userId, String userPassword1, String pass) {
    	boolean flag=true;
    	do {
        Scanner sc = new Scanner(System.in);
        System.out.println("*******************************************");
        System.out.println("*                                         *");
        System.out.println("*      Car Rental - Search Options         *");
        System.out.println("*                                         *");
        System.out.println("*******************************************");
        System.out.println("*   1. Search By Price                    *");
        System.out.println("*   2. Search By Model                    *");
        System.out.println("*   3. Search By Availability             *");
        System.out.println("*   4. Previous Menu                      *");
        System.out.println("*   5. EXIT                               *");
        System.out.println("*******************************************");
    	String option1=sc.next();
    	switch(option1) {
    	case "1":
    		 System.out.println("Enter the minPrice:");
    		 int minPrice = sc.nextInt();
    	     System.out.println("Enter the maxPrice:");
    		 int maxPrice = sc.nextInt();
    		 DatabaseConnection.searchByPriceRangeDetails(minPrice, maxPrice,userId);
    		 backoption();
             break;  
    	case "2":
       		 System.out.println("Enter the ModelName:");
       		 String modelName = sc.next();
       		 DatabaseConnection.searchByModelDetails(userId,modelName);
       		backoption();
             break; 
    	case "3":
    		DatabaseConnection.showAvailableCarsDetails(userId);
    		backoption();
    		break; 
    	case "4":
    		userOptionMenu(userName,userId,userPassword1,pass);
    		backoption();
    		break;
    	case "5":
    		System.exit(0);
    	default:
    		try {
       		 throw new InputMismatchException();
       	 }catch(Exception e) {
       		 System.out.println(e.getMessage());
       		 flag=false;
       	 }
	    }
    	}while(flag);
    }
    public static boolean passwordvalidate( String password) {
    	while(true) {
        Pattern passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$");

        Matcher passwordMatcher = passwordPattern.matcher(password);

            if (passwordMatcher.matches()) {
                System.out.println("Password is valid.");
                return true;
            } 
            else {
			return false;
            }
    	}
    }
    public static String validate(String userName, String password) {
    	while(true) {
        Pattern gmailPattern = Pattern.compile("[0-9a-zA-Z]+");
        Pattern passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$");

        Matcher emailMatcher = gmailPattern.matcher(userName);
        Matcher passwordMatcher = passwordPattern.matcher(password);

        if (emailMatcher.matches()) {
            System.out.println("Username is valid.");
            if (passwordMatcher.matches()) {
                System.out.println("Password is valid.");
                return "valid";
            } else {
                return "password Invalid";
            }
        } else {
            return "UserName Invalid";
        }
    }
    }
    

    
    public static String validateUserName() {
    	while(true) {
    	System.out.print("Enter the user Name Constraint: Should contain only alphanumeric letter");
        String userName = sc.next();
        Pattern gmailPattern = Pattern.compile("[0-9a-zA-Z]+");
        Matcher emailMatcher = gmailPattern.matcher(userName);

        if (emailMatcher.matches()) {
            System.out.println("Username is valid.");
            return userName;
        }
        else {
        	System.out.println("Usename is not followed above constraint");        }
    	}
    }
    
    

    public static String validatePassword() {
    	while(true) {
    	System.out.print("Enter the password Constraint: should contain alphanumeric and one spl character");
        String password = sc.next();
        Pattern passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$");
        Matcher passwordMatcher = passwordPattern.matcher(password);

        if (passwordMatcher.matches()) {
            System.out.println("Password is valid.");
            return password;
        } 
        else {
           System.out.println("Password is not followed above constraint");
        }
    	}
    }
    
    
//    private static boolean validateName(String name) {
//        // Using the regex ^[a-zA-Z\s]+$
//        return name.matches("^[a-zA-Z\\s]+$");
//    }
    private static Date validateDateOfBirth() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        while (true) {
            try {
                String dobString = sc.next();
                return sdf.parse(dobString);
            } catch (ParseException ex) {
                System.out.println("Invalid date format. Please enter the date in dd/MM/yyyy format.");
            }
        }
    }

    private static int calculateAge(Date dob) {
        long ageInMillis = System.currentTimeMillis() - dob.getTime();
        long ageInYears = TimeUnit.MILLISECONDS.toDays(ageInMillis) / 365;
        return (int) ageInYears;
    }
    public static void addingDetails() {
        while (true) {
            try {
            	sc.nextLine();
                String firstName = validateName("Enter the First Name");
                String lastName = validateName("Enter the Last Name");
                System.out.println("Enter the Gender (Male/Female)");
                String gender = sc.next();
                //System.out.println("Enter the Age");
                System.out.println("Enter the Date of Birth (Format: dd/MM/yyyy)");
                Date dob = validateDateOfBirth();
                int age = calculateAge(dob);
                sc.nextLine(); //
                //int age = sc.nextInt();
                //sc.nextLine(); // Consume the newline character
                System.out.println("Enter the Email_Id");
                String eMail = validateEmail();
                System.out.println("Enter the PhoneNo");
                long phoneNo = validatePhoneNumber();
                System.out.println("Enter the Address");
                String address = sc.nextLine();
                String userName = validateUserName();
                String password = validatePassword();

                DatabaseConnection.UsernameAndPassword(userName, password);
                customerList.add(new Customer(firstName, lastName, gender, age, eMail, phoneNo, address));
                DatabaseConnection.addingDetailsIntoDatabase(customerList, userName);
                break;
            } catch (Exception e) {
                System.out.println("Enter the valid inputs");
            }
        }
    }

    private static String validateName(String prompt) {
        String nameRegex = "^[a-zA-Z\\s]+$";
        Pattern pattern = Pattern.compile(nameRegex);
        String input;

        do {
            System.out.println(prompt);
            input = sc.nextLine();
            Matcher matcher = pattern.matcher(input);

            if (!matcher.matches()) {
                System.out.println("Invalid input. Please enter alphabetic characters and spaces only.");
            }

        } while (!input.matches(nameRegex));

        return input;
    }

    // Other validation methods (validateEmail, validatePhoneNumber, validateUserName, validatePassword) can be defined similarly.
//}
//    public static void addingDetails() {
//    	while(true) {
//    	try {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter the FirstName");
//        String firstName = sc.nextLine();
//        System.out.println("Enter the LastName");
//        String lastName = sc.nextLine();
//        System.out.println("Enter the Gender(Male/Female)");
//        String gender = sc.next();
//        System.out.println("Enter the Age");
//        int age = sc.nextInt();
//        sc.nextLine();
//        System.out.println("Enter the Email_Id");
//        String eMail=validateEmail();
//        System.out.println("Enter the PhoneNo");
//        long phoneNo = validatePhoneNumber();
//        //sc.nextLine();
//        System.out.println("Enter the Address");
//        String address = sc.nextLine();
//        //System.out.println("Details Collected successfully");
//        String userName=validateUserName() ;
//       // System.out.print("Enter the password Constraint: should contain alphanumeric and one spl character");
//        String password = validatePassword();
//       // if (validateUserName(userName).equalsIgnoreCase("valid")&validatePassword(password).equalsIgnoreCase("valid")) {
//            DatabaseConnection.UsernameAndPassword(userName, password);
//            customerList.add(new Customer( firstName, lastName, gender, age, eMail, phoneNo, address));
//            DatabaseConnection.addingDetailsIntoDatabase(customerList, userName);
//     //   } else if (validatePassword(password).equalsIgnoreCase(" password is invalid")){
//         //   System.out.println("Invalid password. It should contain at least one capital and small letters, numbers, and special characters ");
//        //    backoption();
//       // } else if (validateUserName(userName).equalsIgnoreCase(" Username is invalid")) {
//        //    System.out.println("Invalid username. It should contain at least one capital and small letters, numbers and doesnt contain Spl characters");
//          //  backoption();
//    //    }
//    	}
//    	catch (Exception e) {
//    		System.out.println("Enter the valid inputs");
//    	}
//    	break;
//    	}
//    }
    public static String validateEmail() {
        Scanner input = new Scanner(System.in);
        
        while (true) {
            String email = input.next();
            if (email.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {
                return email;
            } else {
                System.out.println("Invalid email address. Please enter a valid Gmail address.");
            }
        }
    }
    public static Long validatePhoneNumber() {
        Scanner input = new Scanner(System.in);
        while (true) {
            try {
                Long phoneNumber = Long.parseLong(input.next());
                String phoneNumberString = String.valueOf(phoneNumber);

                if (phoneNumberString.matches("[789]\\d{9}")) {
                    return phoneNumber;
                } else {
                    System.out.println("Invalid Phone Number. Please enter a valid 10-digit number starting with 7, 8, or 9.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                //input.next();
            }
        }
    }
    public static void backoption() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Do you want to continue or exit?if yes  Enter 1 Or else  Enter 2 to exit");
    	int back = scanner.nextInt();
    	if(back==2)
    		System.exit(0);
	}
    public static void editProfileOptions(String userName) {
    	boolean flag=true;
    	 do {
        System.out.println("*******************************************");
        System.out.println("*                                         *");
        System.out.println("*        Edit Profile Options             *");
        System.out.println("*                                         *");
        System.out.println("*******************************************");
        System.out.println("*   1. Edit Password                      *");
        System.out.println("*   2. Edit First Name                    *");
        System.out.println("*   3. Edit Last Name                     *");
        System.out.println("*   4. Edit Gender                        *");
        System.out.println("*   5. Edit Age                           *");
        System.out.println("*   6. Edit Address                       *");
        System.out.println("*   7. Edit Phone Number                  *");
        System.out.println("*   8. Edit Email                         *");
        System.out.println("*   9. ViewProfile                        *");
        System.out.println("*   10. Exit                              *");
        System.out.println("*******************************************");
        String opt2 = sc.next();
        switch (opt2) {
            case "1":
                System.out.println("Enter the Password");
                String password = sc.next();
                sc.nextLine();
                if(passwordvalidate(  password)) {
                
                DatabaseConnection.modifyPasswordInDatabase(userName, password);
                }
                else {
                	System.out.println("Follow the correct ruless");
                }
                backoption();
                break;
            case "2":
                DatabaseConnection.modifyFirstName(userName);
                backoption();
                break;
            case "3":
                DatabaseConnection.modifyLastName(userName);
                backoption();
                break;
            case "4":
                DatabaseConnection.modifyGender(userName);
                backoption();
                break;
            case "5":
                DatabaseConnection.modifyAge(userName);
                backoption();
                break;
            case "6":
                DatabaseConnection.modifyAddress(userName);
                backoption();
                break;
            case "7":
                DatabaseConnection.modifyPhoneNo(userName);
                backoption();
                break;
            case "8":
                DatabaseConnection.modifyEmail(userName);
                backoption();
                break;
            case "9":
             	DatabaseConnection.getUserDetails(userName);
            case "10":
            	System.exit(0);
            default:
           	 try {
             		throw new InputMismatchException("Invalid option. Please enter a Valid number");
             	}catch(Exception e) {
             		System.out.println(e.getMessage());
             		flag=false;
             	}
        }
        }while(flag);
    }
}
