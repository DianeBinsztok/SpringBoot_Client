package web.car.client.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import web.car.client.cars.Car;

import java.util.List;

//@JsonIgnoreProperties(ignoreUnknown = true) => ça sert à quoi?
@RestController
public class CarController {

    private String url = "http://localhost:8080";
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/client/")
    public List<Car> getIndex(){
        List index = this.restTemplate.getForObject(this.url, List.class);
        System.out.println("La réponse à getIndex =>" + index);
        return index;
    }

    @GetMapping("/client/car/{id}")
    public Car getCarById(@PathVariable int id){
        Car targetCar = this.restTemplate.getForObject(this.url+"/car/"+id, Car.class);
        System.out.println("La réponse à getCarById =>" + targetCar);
        return targetCar;
    }

    @GetMapping("/client/brand/{brand}")
    public List<Car> getCarByBrand(@PathVariable String brand){
        List carsByBrand = this.restTemplate.getForObject(this.url+"/brand/"+brand, List.class);
        System.out.println("La réponse à getCarByBrand =>" + carsByBrand);
        return carsByBrand;
    }

    @GetMapping("/client/color/{color}")
    public List<Car> getCarByColor(@PathVariable String color){
        List carsByColor = this.restTemplate.getForObject(this.url+"/color/"+color, List.class);
        System.out.println("La réponse à getCarByColor =>" + carsByColor);
        return carsByColor;
    }
}
