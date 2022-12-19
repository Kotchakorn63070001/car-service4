package com.example.carservice4.core.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarCreatedEvent {
    private String carId;
    private String type;
    private String brand;
    private String model;
    private int numOfSeat;
    private int price;
    private int quantity;
}
