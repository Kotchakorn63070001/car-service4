    package com.example.carservice4.command.rest;

    import com.example.carservice4.command.commands.CreateCarCommand;
    import com.example.carservice4.core.Car;
    import com.example.carservice4.core.data.CarService;
    import org.axonframework.commandhandling.gateway.CommandGateway;
    import org.springframework.amqp.rabbit.core.RabbitTemplate;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;
    import java.util.UUID;

    @RestController
    @RequestMapping("/cars")
    public class CarPublisher {


//    private final CommandGateway commandGateway;
//        @Autowired
//        public CarPublisher(CommandGateway commandGateway) {
//            this.commandGateway = commandGateway;
//        }

        @Autowired
        private RabbitTemplate rabbitTemplate;





        @Autowired
        public CarService carService;

        @GetMapping("/test")
        public List<Car> test(){
            return carService.getAllCars();
//            return "test";
        }

        @GetMapping("/getAllCars")
        public ResponseEntity<?> getAllCars(){
            List<Car> cars = (List<Car>) rabbitTemplate.convertSendAndReceive("CarDirectExchange", "getAllCars", "get all");
            return ResponseEntity.ok(cars);
//            return carService.getAllCars();
        }

        @PostMapping("/addCar")
        public ResponseEntity<?> addCar(@RequestBody Car car){
            boolean status = (boolean) rabbitTemplate.convertSendAndReceive("CarDirectExchange", "addCar", car);
            System.out.println(status);
            return ResponseEntity.ok(status);
        }
//        @PostMapping("/addCar")
//        public boolean addCar(@RequestBody Car car){
//            CreateCarCommand command = CreateCarCommand.builder()
//                    .carId(UUID.randomUUID().toString())
//                    .type(car.getType())
//                    .brand(car.getBrand())
//                    .model(car.getModel())
//                    .numOfSeat(car.getNumOfSeat())
//                    .price(car.getPrice())
//                    .quantity(car.getQuantity())
//                    .build();
//            String result;
//            try{
//                result = commandGateway.sendAndWait(command);
//                System.out.println(result);
//                return true;
//            } catch (Exception e){
//                result = e.getLocalizedMessage();
//                System.out.println(result);
//                return false;
//            }
////        return "car added : " + model.getBrand() + " " + model.getModel();
//        }

        @GetMapping("/getCarByType/{type}")
        public List<Car> getCarByType(@PathVariable("type") String type){
            List<Car> cars = (List<Car>) rabbitTemplate.convertSendAndReceive("CarDirectExchange", "getCarByType", type);
            return cars;
        }

        @GetMapping("/getCarByBrand/{brand}")
        public List<Car> getCarByBrand(@PathVariable("brand") String brand){
            List<Car> cars = (List<Car>) rabbitTemplate.convertSendAndReceive("CarDirectExchange", "getCarByBrand", brand);
            return cars;
        }



//        @DeleteMapping("/delCar")
//        public ResponseEntity<?> deleteCar(@RequestBody Car car){
//            boolean status = (boolean) rabbitTemplate.convertSendAndReceive("CarDirectExchange", "delCar", car);
//            return ResponseEntity.ok(status);
//        }
    }
