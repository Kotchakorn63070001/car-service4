package com.example.carservice4.core.data;

import com.example.carservice4.core.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars(){
        try{
            List<Car> cars = carRepository.findAll();
            return cars;
        } catch (Exception e){
            return null;
        }

    }

    public List<Car> getCarByType(String type){
        try {
            return carRepository.findByType(type);
        } catch (Exception e){
            return null;
        }
    }

    public List<Car> getCarByBrand(String brand){
        try {
            return carRepository.findByBrand(brand);
        } catch (Exception e){
            return null;
        }
    }

    public boolean addCar(Car car){
        try {
            carRepository.save(car);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public boolean updateCar(Car car){
        try {
            carRepository.save(car);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public boolean deleteCar(Car car){
        try {
            carRepository.delete(car);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
