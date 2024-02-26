package com.Car;

import java.util.List;
//implements Search 
public class Cars {
    private int carsId;
    private int carNumberPlate;
    private String carModel;
    private int capacity;
    private String carAvailability;
    private double rentPrice;
    public Cars(int carNumberPlate, String carModel, int capacity, String carAvailability, double rentPrice) {

        //this.carsId = carsId;
        this.carNumberPlate = carNumberPlate;
        this.carModel = carModel;
        this.capacity = capacity;
        this.carAvailability = carAvailability;
        this.rentPrice = rentPrice;
    }
    
    public Cars(int carId, int numberPlate, String carModel2, int capacity2, String availability, double rentPrice2) {
    	this.carsId=carId;
    	this.carNumberPlate = carNumberPlate;
        this.carModel = carModel;
        this.capacity = capacity;
        this.carAvailability = carAvailability;
        this.rentPrice = rentPrice;
	}

	public int getCarsId() {
        return carsId;
    }

    public void setCarsId(int carsId) {
        this.carsId = carsId;
    }

    public int getCarNumberPlate() {
        return carNumberPlate;
    }

    public void setCarNumberPlate(int carNumberPlate) {
        this.carNumberPlate = carNumberPlate;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public String getCarAvailability() {
        return carAvailability;
    }
    public void setCarAvailability(String carAvailability) {
        this.carAvailability = carAvailability;
    }
    public double getRentPrice() {
        return rentPrice;
    }
    public void setRentPrice(double rentPrice) {
        this.rentPrice = rentPrice;
    }
//
//	@Override
//	public List<Cars> searchByModel(String model) {
//		return null;
//	}
//
//	@Override
//	public List<Cars> searchByAvailability(String availability) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Cars> searchByPriceRange(double minPrice, double maxPrice) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
