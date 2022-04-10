package classes;
import enums.CarState;
import enums.CarType;

public class Truck {
    private int id;
    private String model;
    private String driver_name;
    private CarState carState;
    private CarType carType;


    public Truck(int id, String model, String driver_name, CarState carState, CarType carType) {
        this.id = id;
        this.model = model;
        this.driver_name = driver_name;
        this.carState = carState;
        this.carType = carType;
    }

    public Truck() {

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public CarState getCarState() {
        return carState;
    }

    public void setCarState(CarState carState) {
        this.carState = carState;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    @Override
    public String toString() {
        return "N        : " + id + '\n' +
                "Truck      : " + model + '\n' +
                "Driver   : " + driver_name + '\n' +
                "Truck state : " + carState;
    }

    public static Truck addTruck(Integer id, String model, String driver_name, CarState carState, CarType carType) {
        Truck truck = new Truck();
        truck.id = id;
        truck.model = model;
        truck.driver_name = driver_name;
        truck.carState = carState;
        truck.carType = carType;
        return truck;
    }

}
