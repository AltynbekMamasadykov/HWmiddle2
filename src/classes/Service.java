package classes;
import exceptions.ChangeDriverException;
import exceptions.TruckStateException;
import java.util.Random;
import static enums.CarState.*;

public class Service implements interfaces.Service {
    @Override
    public void changeDriver(Driver driver,Truck truck) {
        if(truck.getCarState().equals(BASE)){
            truck.setDriver_name(driver.getName());
            driver.setTruck(truck.getModel());
            System.out.println("Done... The truck " + truck.getModel() + "'s driver is: " + driver.getName());
        }
        if (truck.getCarState().equals(ROUTE)) {
            throw new ChangeDriverException("The driver on the road. You cannot change this driver");
        }
        if(truck.getCarState().equals(REPAIR)) {
            throw new ChangeDriverException("The truck in repair. You cannot change the driver");
        }
    }


    @Override
    public void startDriving(Driver driver, Truck truck) {
        if(truck.getCarState().equals(BASE) && truck.getDriver_name() != null) {
            truck.setCarState(ROUTE);
            truck.setDriver_name(driver.getName());
            System.out.println("The truck is on the road");
        }
        else if(truck.getCarState().equals(ROUTE)) {
            throw new TruckStateException("The truck is on the road!");
        }
        else if(truck.getCarState().equals(REPAIR)) {
            Random random = new Random();
            int a = random.nextInt(1,3);
            if(a == 1)
                truck.setCarState(ROUTE);
            else
                truck.setCarState(BASE);
            System.out.println("The trucks state is: " + truck.getCarState());
        }
    }

    @Override
    public void startRepair(Driver driver, Truck truck) {
        if(truck.getCarState().equals(BASE) || truck.getCarState().equals(ROUTE)) {
            truck.setCarState(REPAIR);
            System.out.println("The truck is in repair");
        }
        else if(truck.getCarState().equals(REPAIR))
            throw new TruckStateException("This truck is already in repair!");
    }

}
