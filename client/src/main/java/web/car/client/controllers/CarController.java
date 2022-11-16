package web.car.client.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import web.car.client.cars.Car;

import java.util.List;

//@JsonIgnoreProperties(ignoreUnknown = true) => ça sert à quoi?
@RestController
public class CarController {

    private String serverUrl = "http://localhost:8080";
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/client")
    public List<Car> getIndex(){
        return this.restTemplate.getForObject(this.serverUrl, List.class);
    }

    @GetMapping("/client/car/{id}")
    public Car getCarById(@PathVariable int id){
        return this.restTemplate.getForObject(this.serverUrl+"/car/"+id, Car.class);
    }

    @GetMapping("/client/brand/{brand}")
    public List<Car> getCarByBrand(@PathVariable String brand){
        return this.restTemplate.getForObject(this.serverUrl+"/brand/"+brand, List.class);
    }

    @GetMapping("/client/color/{color}")
    public List<Car> getCarByColor(@PathVariable String color){
        return this.restTemplate.getForObject(this.serverUrl+"/color/"+color, List.class);
    }

    // Ne pas mettre de / à la fin de l'url, sinon Postman ne la trouve pas (réponse 404)
    @PostMapping("/client/cars")
    public void postNewCar(@RequestBody Car newCar){
        HttpEntity<Car> car = new HttpEntity<Car>(newCar);
        this.restTemplate.postForObject(this.serverUrl+"/cars/", car, List.class);
    }
}
