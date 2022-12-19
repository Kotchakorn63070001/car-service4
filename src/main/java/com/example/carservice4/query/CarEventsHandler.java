package com.example.carservice4.query;

import com.example.carservice4.core.Car;
import com.example.carservice4.core.data.CarService;
import com.example.carservice4.core.event.CarCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// wait when new car create
@Component
public class CarEventsHandler {
//    private final CarRepository carRepository;
//
//    public CarEventsHandler(CarRepository carRepository) {
//        this.carRepository = carRepository;
//    }
    @Autowired
    private CarService carService;

    @EventHandler
    public void on(CarCreatedEvent event){
        Car car = new Car();
        BeanUtils.copyProperties(event, car); //copy from event to paste in CarEntity
        carService.addCar(car);
    }
}
