package org.example;


import org.example.DAO.VehicleDAO;
import org.example.DAO.SalesContractDAO;
import org.example.DAO.LeaseContractDAO;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private final VehicleDAO vehicleDAO;
    private final SalesContractDAO salesDAO;
    private final LeaseContractDAO leaseDAO;
    private final Scanner scanner = new Scanner(System.in);

    // Constructor
    public UserInterface(String url, String user, String pass) {
        this.vehicleDAO = new VehicleDAO(url, user, pass);
        this.salesDAO = new SalesContractDAO(url, user, pass);
        this.leaseDAO = new LeaseContractDAO(url, user, pass);
    }

    //Run menu
    public void display() {

        while (true) {
            System.out.println("---MAIN MENU---");
            System.out.println("1) Find vehicles within a price range");
            System.out.println("2) Find vehicles by make/model");
            System.out.println("3) Find vehicles by year range");
            System.out.println("4) Find vehicles by color");
            System.out.println("5) Find vehicles by mileage range");
            System.out.println("6) Find vehicles by type (car, truck, SUV, van)");
            System.out.println("7) List ALL vehicles");
            System.out.println("8) Add a vehicle");
            System.out.println("9) Remove a vehicle");
            System.out.println("10) Sell/Lease a vehicle");
            System.out.println("-----------------");
            System.out.println("99) QUIT");


            switch (scanner.nextLine()) {
                case "1" -> searchPrice();
                case "2" -> searchMakeModel();
                case "3" -> searchYear();
                case "4" -> searchColor();
                case "5" -> searchMileage();
                case "6" -> searchType();
                case "7" -> displayVehicles(vehicleDAO.getAllVehicles());
                case "8" -> addVehicle();
                case "9" -> removeVehicle();
                case "10" -> processContract();
                case "99" -> {System.out.println("Goodbye!"); return;}
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    //Process Methods for each menu option
    private void searchPrice() {
        displayVehicles(vehicleDAO.getByPrice(
                getDouble("Min price: "), getDouble("Max price: ")));
    }

    private void searchMakeModel(){
        displayVehicles(vehicleDAO.getByMakeModel(
                get("Make: "), get("Model: ")
        ));
    }

    private void searchYear(){
        displayVehicles(vehicleDAO.getByYear(
                getInt("Min year:"), getInt("Max year:")
        ));
    }

    private void searchColor(){ displayVehicles(vehicleDAO.getByColor(get("Color: "))); }

    private void searchMileage(){
        displayVehicles(vehicleDAO.getByMileage(
                getInt("Min miles:"), getInt("Max miles:")
        ));
    }

    private void searchType(){ displayVehicles(vehicleDAO.getByType(get("Type: "))); }

    private void addVehicle(){
        Vehicle v = new Vehicle(
                get("VIN:"), getInt("Year:"), get("Make:"), get("Model:"),
                get("Type:"), get("Color:"), getInt("Mileage:"), getDouble("Price:")
        );
        vehicleDAO.insertVehicle(v);
    }

    private void removeVehicle(){ vehicleDAO.deleteVehicle(get("VIN to remove: ")); }

    private void processContract(){
        String vin = get("VIN:");
        Vehicle v = vehicleDAO.getByVin(vin);
        if(v == null){ System.out.println("Vehicle not found."); return;}

        String name = get("Customer name:");
        String email = get("Email:");
        String date = get("Date YYYYMMDD:");

        if(get("SALE or LEASE?").equalsIgnoreCase("SALE")){
            salesDAO.insertSale(new SalesContract(date,name,email,v,false));
        }else{
            leaseDAO.insertLease(new LeaseContract(date,name,email,v,v.getPrice()*0.5,v.getPrice()*0.07));
        }
        vehicleDAO.deleteVehicle(vin);
        System.out.println("Contract saved – vehicle removed.");
    }

    //================ HELPER INPUT ================
    private int getInt(String msg){ System.out.print(msg); return Integer.parseInt(scanner.nextLine()); }
    private double getDouble(String msg){ System.out.print(msg); return Double.parseDouble(scanner.nextLine()); }
    private String get(String msg){ System.out.print(msg); return scanner.nextLine().trim(); }

    //Did a grid style table
    private void displayVehicles(List<Vehicle> list) {

        System.out.println("+----------------+------+------------+------------+----------+------------+------------+--------------+");
        System.out.println("| VIN            | Year | Make       | Model      | Type     | Color      | Mileage    | Price        |");
        System.out.println("+----------------+------+------------+------------+----------+------------+------------+--------------+");

        for (Vehicle v : list) {
            System.out.printf("| %-14s | %-4d | %-10s | %-10s | %-8s | %-10s | %-10d | $%-10.2f |\n",
                    v.getVin(), v.getYear(), v.getMake(), v.getModel(),
                    v.getVehicleType(), v.getColor(), v.getOdometer(), v.getPrice());
        }

        System.out.println("+----------------+------+------------+------------+----------+------------+------------+--------------+");
    }
}


   /* private String getStringInput(String prompt) {
        while(true){
            System.out.println(prompt);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Try again.");
                continue;
            }

            // input.matches [a-zA-Z\\s-]+ (I looked this up for a way to catch symbols as well)
            // a-z allow any lower case char through a-z
            // A-Z allow any cap char through A-Z
            // \\s space character, allows spaces like "Ford Focus"
            // - is hyphens for like "F-150"
            // [] anything inside the brackets are allowed
            // + one or more of the allowed characters

            //If the prompt mentions "email", allow @ and .
             if (prompt.toLowerCase().contains("email")) {
                //email validation that I looked up
                if (!input.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                    System.out.println("Invalid email format. Please enter a valid email address.");
                    continue;
                }
                return input;
            }

            if (prompt.toLowerCase().contains("date")) {
                //need 8 digits for YYYMMDD
                if (!input.matches("\\d{8}")) {
                    System.out.println("Invalid date. Please enter 8 digits as YYYYMMDD.");
                    continue;
                }
                return input; //store as YYYYMMDD
            }

            if (!input.matches("[a-zA-Z\\s-]+")) {
                System.out.println("Please enter letters only (no numbers or symbols).");
                continue;
            }
            return input;
        } */


