package com.company;
import classes.Driver;
import classes.Service;
import classes.Truck;
import com.google.gson.*;
import exceptions.ChangeDriverException;
import exceptions.TruckStateException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static enums.CarState.*;
import static enums.CarType.*;


public class Main {

    public static final GsonBuilder GSON_BUILDER = new GsonBuilder();
    public static final Gson GSON = GSON_BUILDER.setPrettyPrinting().create();
    public static final Path WRITE_PATH = Paths.get("./car.gson");
    public static final Path WRITE_PATH_DRIVER = Paths.get("./driver.gson");

    public static void main(String[] args) {

        List<Truck> trucks = new ArrayList<>();
        trucks.add(Truck.addTruck(1, "Renault Magnum", "", BASE, TRUCK));
        trucks.add(Truck.addTruck(2, "Volvo FH12", "", BASE, TRUCK));
        trucks.add(Truck.addTruck(3, "DAF XF", "", BASE, TRUCK));

        List<Driver> drivers = new ArrayList<>();
        drivers.add(Driver.addDriver(1,"Sasha",""));
        drivers.add(Driver.addDriver(2,"Petya",""));
        drivers.add(Driver.addDriver(3,"Vasya",""));


        String jsonTruck = GSON.toJson(trucks);
        setWritePath(jsonTruck);
//        System.out.println(readFile());
        printTable(trucks);

        System.out.println();
        System.out.println("********************************************************************************");

        String jsonDriver = GSON.toJson(drivers);
        setWritePathDriver(jsonDriver);
//        System.out.println(readFile());
        printDriverTable(drivers);

        System.out.println();
        System.out.println("********************************************************************************");

        Service service = new Service();
        Scanner scanner = new Scanner(System.in);

        for (;true;) {
            System.out.println("""
                    To change driver press 1
                    To send truck to road press 2
                    To send truck to repair press 3                    
                    If you want to quit press 0""");
            int input = scanner.nextInt();
            if (input == 1) {
                try{
                    System.out.println("Select drivers id");
                    int inputId = scanner.nextInt();
                    service.changeDriver(drivers.get(inputId - 1), trucks.get(inputId - 1));
                    printTable(trucks);
                    printDriverTable(drivers);
                }
                catch (ChangeDriverException e) {
                    System.err.println(e.getMessage());
                }

            }
            if (input == 2) {
                try {
                    System.out.println("Select truck's ID");
                    int inputId = scanner.nextInt();
                    service.startDriving(drivers.get(inputId - 1), trucks.get(inputId - 1));
                    System.out.println(trucks.get(inputId - 1));
                    printTable(trucks);
                    printDriverTable(drivers);
                }
                catch (TruckStateException e) {
                    System.err.println(e.getMessage());
                }
            }
            if (input == 3) {
                try{
                    System.out.println("Select truck's ID");
                    int inputId = scanner.nextInt();
                    service.startRepair(drivers.get(inputId - 1), trucks.get(inputId - 1));
                    System.out.println(trucks.get(inputId - 1));
                    printTable(trucks);
                    printDriverTable(drivers);
                }
                catch (TruckStateException e) {
                    System.err.println(e.getMessage());
                }
            }
            if (input == 0) {
                System.out.println("See You!");
                break;
            }
        }


    }

    public static void printDriverTable(List<Driver> drivers) {
        System.out.println();
        System.out.println("-----------  DRIVERS  -----------");
        System.out.println("   #   |  Driver             |  Bus               ");
        System.out.println("-------+---------------------+--------------------");
        int counter = 1, tabsDriver = 20, tabsBus = 18;
        for(Driver i: drivers) {
            System.out.print("   " + counter + "   | ");
            System.out.print(i.getName());
            for(int j = 0; j < tabsDriver - i.getName().length(); j++) {
                System.out.print(" ");
            }
            System.out.print("|  " + i.getTruck());
            for(int j = 0; j < tabsBus; j++) {
                System.out.print(" ");
            }
            counter++;
            System.out.println();
        }
    }

    public static void printTable(List<Truck> trucks) {
        System.out.println();
        System.out.println("-----------  TRUCKS  -----------");
        System.out.println("   #   |  Bus                |  Driver            |  State         ");
        System.out.println("-------+---------------------+--------------------+----------------");
        int counter = 1, tabsBus = 20, tabsDriver = 18, tabsState = 14;
        for(Truck i: trucks) {
            System.out.print("   " + counter + "   | ");
            System.out.print(i.getModel());
            for(int j = 0; j < tabsBus - i.getModel().length(); j++) {
                System.out.print(" ");
            }
            System.out.print("|  " + i.getDriver_name());
            for(int j = 0; j < tabsDriver; j++) {
                System.out.print(" ");
            }
            System.out.print("|  ");
            System.out.print(i.getCarState());
            for(int j = 0; j < tabsState - i.getCarState().toString().length(); j++) {
                System.out.print(" ");
            }
            counter++;
            System.out.println();
        }
    }

    private static void setWritePath(String object){
        Path setWritePath = Paths.get(String.valueOf(WRITE_PATH));
        try{
            Files.writeString(setWritePath, object, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void setWritePathDriver(String object){
        Path setWritePath = Paths.get(String.valueOf(WRITE_PATH_DRIVER));
        try{
            Files.writeString(setWritePath, object, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static String readFile() {
        String json = "";
        try {
            FileReader fileReader = new FileReader(String.valueOf(WRITE_PATH));
            int temp;
            while ((temp = fileReader.read()) != -1) {
                json += (char) temp;
            }
            return json;
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return json;
    }


}