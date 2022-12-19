package com.example.carservice4.core.data;

import com.example.carservice4.core.Car;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends MongoRepository<Car, String> {
    @Query(value = "{carId:  '?0'}")
    public Car findByCarId(String carId);

    @Query(value = "{type: '?0'}")
    public List<Car> findByType(String type);

    @Query(value = "{brand: '?0'}")
    public List<Car> findByBrand(String brand);

}
