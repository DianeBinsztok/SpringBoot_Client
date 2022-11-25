package web.car.client.cars;


public class Car {

    private  int id;
    private  String model;
    private  String brand;
    private  String color;

    public Car(){}
    public int getId() {
        return id;
    }
    public String getModel() {
        return model;
    }
    public String getBrand() {
        return brand;
    }
    public String getColor() {
        return color;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Car(int id, String model, String brand, String color) {
        this.id = id;
        this.model = model;
        this.brand = brand;
        this.color = color;
    }




}
