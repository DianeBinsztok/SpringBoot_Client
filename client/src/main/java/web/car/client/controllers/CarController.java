package web.car.client.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import web.car.client.cars.Car;

import java.util.List;

//@JsonIgnoreProperties(ignoreUnknown = true) => ça sert à quoi?
// Rest Controller envoie du JSON
// si je veux envoyer un template, j'utilise l'annotation @Controller
@Controller
public class CarController {

    private String serverUrl = "http://localhost:8080";
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value= {"/client", "/client/index"})
    public String getIndex(Model model){
        List<Car> cars =  this.restTemplate.getForObject(this.serverUrl, List.class);
        model.addAttribute("cars", cars);
        model.addAttribute("title", "All our available vehicles");
        return "index";
    }

    @GetMapping("/client/car/{id}")
    public String getCarById(@PathVariable int id, Model model){
        Car car = this.restTemplate.getForObject(this.serverUrl+"/car/"+id, Car.class);
        model.addAttribute("car", car);
        return "carDetail";
    }

    @GetMapping("/client/brand/{brand}")
    public String getCarByBrand(@PathVariable String brand, Model model){
        List<Car> cars = this.restTemplate.getForObject(this.serverUrl+"/brand/"+brand, List.class);
        model.addAttribute("cars", cars);
        model.addAttribute("title", "All our available "+brand);
        return "index";
    }
    // à quoi sert le dernier argument List.class ?

    @GetMapping("/client/color/{color}")
    public String getCarByColor(@PathVariable String color, Model model){
        List<Car> cars = this.restTemplate.getForObject(this.serverUrl+"/color/"+color, List.class);
        model.addAttribute("cars", cars);
        model.addAttribute("title", "All our available "+color+" vehicles");
        return "index";
    }

    // En cours:
    @GetMapping(value = "/client/cars")
    public String addCarForm(Model model){
        model.addAttribute("title", "Add a new vehicle to our park");
        model.addAttribute("car", new Car());
        return "newCarForm";
    }

    // Ne pas mettre de / à la fin de l'url, sinon Postman ne la trouve pas (réponse 404)
    @PostMapping("/client/cars")
    public String postNewCar(@ModelAttribute("Car") Car newCar){
        //HttpEntity<Car> car = new HttpEntity<Car>(newCar);
        int id = newCar.getId();
        String model = newCar.getModel();
        String brand = newCar.getBrand();
        String color = newCar.getColor();
        this.restTemplate.postForObject(this.serverUrl+"/cars", newCar, Car.class);
        //return "index"; => renvoie la vue index, mais sur la même url (/client/cars), donc, sans les données
        return "redirect:/client/";
    }

    @PutMapping("/client/update/{id}")
    public void update(@PathVariable int id, @RequestBody Car newSpecCar){
        HttpEntity<Car> car = new HttpEntity<Car>(newSpecCar);
        this.restTemplate.put(this.serverUrl+"/update/"+id, car, List.class);
    }

    @DeleteMapping ("/client/delete/{id}")
    public void delete(@PathVariable int id){
        this.restTemplate.delete(this.serverUrl+"/delete/"+id, List.class);
    }
}
