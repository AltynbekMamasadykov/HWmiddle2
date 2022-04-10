package interfaces;


import classes.Driver;
import classes.Truck;

public interface Service {
    void changeDriver(Driver driver, Truck truck);
    void startDriving(Driver driver, Truck truck);
    void startRepair(Driver driver, Truck truck);
}
