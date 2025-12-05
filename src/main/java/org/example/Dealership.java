package org.example;

import java.util.ArrayList;
import java.util.List;

public class Dealership {

    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    //================= SEARCH METHODS =================//

    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        List<Vehicle> results = new ArrayList<>();
        for (Vehicle v : inventory)
            if (v.getPrice() >= min && v.getPrice() <= max) results.add(v);
        return results;
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        List<Vehicle> results = new ArrayList<>();
        for (Vehicle v : inventory)
            if (v.getMake().equalsIgnoreCase(make) && v.getModel().equalsIgnoreCase(model)) results.add(v);
        return results;
    }

    public List<Vehicle> getVehiclesByYear(int min, int max){
        List<Vehicle> results = new ArrayList<>();
        for (Vehicle v : inventory)
            if (v.getYear() >= min && v.getYear() <= max) results.add(v);
        return results;
    }

    public List<Vehicle> getVehiclesByColor(String color){
        List<Vehicle> results = new ArrayList<>();
        for (Vehicle v : inventory)
            if (v.getColor().equalsIgnoreCase(color)) results.add(v);
        return results;
    }

    public List<Vehicle> getVehiclesByMileage(int min, int max){
        List<Vehicle> results = new ArrayList<>();
        for (Vehicle v : inventory)
            if (v.getOdometer() >= min && v.getOdometer() <= max) results.add(v);
        return results;
    }

    public List<Vehicle> getVehiclesByType(String type){
        List<Vehicle> results = new ArrayList<>();
        for (Vehicle v : inventory)
            if (v.getVehicleType().equalsIgnoreCase(type)) results.add(v);
        return results;
    }

    public List<Vehicle> getAllVehicles(){
        return new ArrayList<>(inventory);
    }

    public void addVehicle(Vehicle v){ inventory.add(v); }

    //================= FIXED VIN and made it a String instead of int =================//

    public Vehicle getVehicleByVin(String vin) {
        for (Vehicle v : inventory)
            if (v.getVin().equals(vin)) return v;
        return null;
    }

    public boolean removeVehicle(String vin) {
        for (Vehicle v : inventory)
            if (v.getVin().equals(vin)) {
                inventory.remove(v);
                return true;
            }
        return false;
    }

    //=============== GETTERS ===============//

    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
}

