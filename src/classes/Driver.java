package classes;

public class Driver {
    private Integer id;
    private String name;
    private String truck;

    public Driver(Integer id, String name, String truck) {
        this.id = id;
        this.name = name;
        this.truck =truck;
    }

    public Driver() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTruck() {
        return truck;
    }

    public void setTruck(String truck) {
        this.truck = truck;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", truck=" + truck +
                '}';
    }

    public static Driver addDriver(int id, String name, String truck) {
        Driver driver = new Driver();
        driver.id = id;
        driver.name = name;
        driver.truck = truck;
        return driver;
    }
}
