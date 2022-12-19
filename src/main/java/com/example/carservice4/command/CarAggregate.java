package com.example.carservice4.command;

import com.example.carservice4.command.commands.CreateCarCommand;
import com.example.carservice4.core.event.CarCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class CarAggregate {
    @AggregateIdentifier
    private String carId;
    private String type;
    private String brand;
    private String model;
    private int numOfSeat;
    private int price;
    private int quantity;

    public CarAggregate() {
    }

    @CommandHandler
    public CarAggregate(CreateCarCommand createCarCommand){
        // Validation
        if (createCarCommand.getType() == null || createCarCommand.getType().isBlank()){
            throw new IllegalArgumentException("Type Car cannot be empty");
        }
        if (createCarCommand.getBrand() == null || createCarCommand.getBrand().isBlank()){
            throw new IllegalArgumentException("Brand cannot be empty");
        }
        if (createCarCommand.getModel() == null || createCarCommand.getModel().isBlank()){
            throw new IllegalArgumentException("Model cannot be empty");
        }

        CarCreatedEvent carCreatedEvent = new CarCreatedEvent();
        BeanUtils.copyProperties(createCarCommand, carCreatedEvent);
        AggregateLifecycle.apply(carCreatedEvent);
    }


    @EventSourcingHandler
    public void on(CarCreatedEvent carCreatedEvent) {
        this.carId = carCreatedEvent.getCarId();
        this.type = carCreatedEvent.getType();
        this.brand = carCreatedEvent.getBrand();
        this.model = carCreatedEvent.getModel();
        this.numOfSeat = carCreatedEvent.getNumOfSeat();
        this.price = carCreatedEvent.getPrice();
        this.quantity = carCreatedEvent.getQuantity();
        System.out.println("On Car Created : " + this.carId);
    }
}
