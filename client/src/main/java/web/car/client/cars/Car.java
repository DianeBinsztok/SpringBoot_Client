package web.car.client.cars;


public class Car {

    private final long id;
    private final String model;
    private final String brand;
    private final String color;

    public Car(long id, String model, String brand, String color) {
        this.id = id;
        this.model = model;
        this.brand = brand;
        this.color = color;
    }

    public long getId() {
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
}
