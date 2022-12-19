package com.example.carservice4.command.rest;

import com.example.carservice4.command.commands.CreateCarCommand;
import com.example.carservice4.core.Car;
import com.example.carservice4.core.data.CarService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Component
public class CarConsumer {

    private final CommandGateway commandGateway;
    @Autowired
    public CarConsumer(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Autowired
    private CarService carService;

    @RabbitListener(queues = "GetAllCarsQueue")
    public List<Car> getAllCars(String getAll){
        System.out.println(getAll);
        return carService.getAllCars();
    }

    @RabbitListener(queues = "AddCarQueue")
    public boolean addCar(Car car){
        System.out.println("CarConsumer here");
        CreateCarCommand command = CreateCarCommand.builder()
                .carId(UUID.randomUUID().toString())
                .type(car.getType())
                .brand(car.getBrand())
                .model(car.getModel())
                .numOfSeat(car.getNumOfSeat())
                .price(car.getPrice())
                .quantity(car.getQuantity())
                .build();
        String result;
        try{
            result = commandGateway.sendAndWait(command);
            System.out.println(result);
            return true;
        } catch (Exception e){
            result = e.getLocalizedMessage();
            System.out.println(result);
            return false;
        }
//        return "car added : " + model.getBrand() + " " + model.getModel();
    }

    @RabbitListener(queues = "GetCarByTypeQueue")
    public List<Car> getCarByType(String type){
        return (List<Car>) carService.getCarByType(type);
    }

    @RabbitListener(queues = "GetCarByBrandQueue")
    public List<Car> getCarByBrand(String brand){
        return (List<Car>) carService.getCarByBrand(brand);
    }

//    @RabbitListener(queues = "DelCarQueue")
//    public boolean deleteCar(Car car){
//        System.out.println(car);
//        DeleteCarCommand deleteCarCommand = DeleteCarCommand.builder()
//                .type(car.getType())
//                .brand(car.getBrand())
//                .model(car.getModel())
//                .numOfSeat(car.getNumOfSeat())
//                .price(car.getPrice())
//                .quantity(car.getQuantity())
//                .build();
//        String result;
//        try {
//            result = commandGateway.sendAndWait(deleteCarCommand);
//            System.out.println(result);
//            return true;
//        } catch (Exception e){
//            result = e.getLocalizedMessage();
//            System.out.println(result);
//            return false;
//        }
//    }


}
