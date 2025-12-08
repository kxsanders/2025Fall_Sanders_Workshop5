# 🚗 Car Dealership Management System  
Database-Driven Java CLI Application  

A full dealership inventory system rebuilt from CSV-files into a persistent MySQL-backed application using JDBC and DAO architecture.  
Users may search, add, remove, sell, or lease vehicles. All data now lives in SQL tables instead of text files.

---

## 📌 Features

| Functionality | Status | Storage |
|---|---|---|
| View vehicles (all or filtered) | ✔ | MySQL `vehicles` table |
| Add a vehicle to inventory | ✔ | DAO → INSERT |
| Remove vehicle by VIN | ✔ | DAO → DELETE |
| Sell vehicle | ✔ | Saves to `sales_contracts` |
| Lease vehicle | ✔ | Saves to `lease_contracts` |
| VIN now stored as `VARCHAR(20)` not int | ✔ | + fixed all DAO mappings |
| Fully formatted table UI | ✔ | Clean CLI output |

---

## 🏗 Tech Stack

| Component | Technology |
|---|---|
| Language | Java 17 |
| DB | MySQL 8+ |
| Build System | Maven |
| DB Access | JDBC + DAO pattern |
| UI | Text-Based Console Application |

---

## 📂 Project Structure

src/main/java/org/example
│ Program.java → Runs UI with DB credentials
│ UserInterface.java → Calls DAO instead of CSV
│
├── DAO/
│ VehicleDAO.java
│ SalesContractDAO.java
│ LeaseContractDAO.java
│
├── Vehicle.java
├── SalesContract.java
├── LeaseContract.java
└── Contract.java (parent)

---

## 🔌 Required Database Setup

```sql
CREATE DATABASE car_dealership;
USE car_dealership;

CREATE TABLE vehicles (
    VIN VARCHAR(20) PRIMARY KEY,
    Make VARCHAR(30),
    Model VARCHAR(30),
    Year INT,
    Color VARCHAR(20),
    Type VARCHAR(20),
    Price DECIMAL(10,2),
    Mileage INT,
    SOLD TINYINT(1)
);

CREATE TABLE sales_contracts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    VIN VARCHAR(20),
    Buyer_Name VARCHAR(50),
    Sale_Price DECIMAL(10,2),
    Sale_Date VARCHAR(10),
    Dealer_id INT
);

CREATE TABLE lease_contracts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    VIN VARCHAR(20),
    Leasee_Name VARCHAR(50),
    Monthly_Payment DECIMAL(10,2),
    Months INT,
    Start_Date VARCHAR(10),
    Dealer_id INT
);
▶ Run Instructions
Start MySQL server

Make sure DB exists → car_dealership

Run via IntelliJ command-line args:

Menu opens:

pgsql
Copy code
--- MAIN MENU ---
1) Search by price range
2) Filter by make/model
3) Filter by year
4) Filter by color
5) Filter by mileage
6) Filter by type
7) List ALL vehicles
8) Add vehicle
9) Remove vehicle
10) Sell/Lease vehicle
99) Quit
📊 Example Console Output
markdown
Copy code
+----------------- VEHICLE INVENTORY -----------------+
 VIN         YEAR    MAKE       MODEL      COLOR     PRICE
-----------------------------------------------------------
 1HGBH41     2019    Honda      Civic      Blue      $17,995
 3ABC92X     2021    Toyota     Camry      Silver    $22,400
 JDM4467     2016    Jeep       Wrangler   Red       $29,150
-----------------------------------------------------------
Total Vehicles: 3
🔥 What I Learned
✔ Switching from CSV → SQL persistence
✔ JDBC connection handling
✔ Using DAO classes for CRUD separation
✔ Data validation, VIN handling, SQL schema design
✔ Full app migration without losing functionality

👤 Author
Kayla Sanders
