package web.car.client.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.List;

//@JsonIgnoreProperties(ignoreUnknown = true) => ça sert à quoi?
@RestController
public class CarController {

    private String url = "http://localhost:8080";

    @GetMapping("/")
    public Object getIndex(){
        RestTemplate restTemplate = new RestTemplate();
        Object index = restTemplate.getForObject(this.url, String.class);
        System.out.println("La réponse à getIndex =>" + index);
        return index;
    }

    @GetMapping("/car/{id}")
    public Object getCarById(@PathVariable int id){
        RestTemplate restTemplate = new RestTemplate();
        Object targetCar = restTemplate.getForObject(this.url+"/car/"+id, String.class);
        System.out.println("La réponse à getCarById =>" + targetCar);
        return targetCar;
    }

    @GetMapping("/brand/{brand}")
    public Object getCarByBrand(@PathVariable String brand){
        RestTemplate restTemplate = new RestTemplate();
        Object carsByBrand = restTemplate.getForObject(this.url+"/brand/"+brand, String.class);
        System.out.println("La réponse à getCarByBrand =>" + carsByBrand);
        return carsByBrand;
    }

    @GetMapping("/color/{color}")
    public Object getCarByColor(@PathVariable String color){
        RestTemplate restTemplate = new RestTemplate();
        Object carsByColor = restTemplate.getForObject(this.url+"/color/"+color, String.class);
        System.out.println("La réponse à getCarByColor =>" + carsByColor);
        return carsByColor;
    }
}
