package com.example.carservice4.core;

//import jakarta.persistence.Column;
//import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

@Data
@Document("Car")
public class Car implements Serializable {

    @Serial
    private static final long serialVersionUID = -2015403071869763719L;
    @Id
//    @Column(unique = true)
    private String carId;
    private String type;
    private String brand;
    private String model;
    private int numOfSeat;
    private int price;
    private int quantity;
}
