# 🚗 Vehicle Dealership Management System

Welcome to the **Vehicle Dealership Management System**!  
This Java application allows a dealership to manage inventory, process sales and leases, and track contracts. Perfect for learning **OOP concepts**, **file handling**, and **user interfaces** in Java.

---

## 📝 Features

- **Inventory Management**
  - Add new vehicles to the dealership
  - Remove vehicles from inventory
  - Search vehicles by:
    - Price range 💰
    - Make/Model 🔧
    - Year range 📅
    - Color 🎨
    - Mileage 🛣️
    - Vehicle type (Car, Truck, SUV, Van) 🚙

- **Sales & Lease Processing**
  - Sell a vehicle
  - Lease a vehicle (if eligible) 📝
  - Generate and save contracts to `contracts.csv`  

- **File Management**
  - Load and save dealership inventory (`inventory.csv`)
  - Append and save contracts (`contracts.csv`)
  - Supports headers and CSV parsing

- **User-Friendly Interface**
  - Menu-driven system
  - Input validation for numbers, text, and emails ✉️
  - Displays vehicle inventory in a readable table format

---

## 💻 How It Works

1. **Load Dealership**
   - Reads `inventory.csv` to populate the dealership inventory
2. **Display Menu**
   - Options 1–10 to search, add, remove, or sell/lease vehicles
3. **Process Sale or Lease**
   - Sale:
     - Optional financing
     - Calculates total price and monthly payments
   - Lease:
     - Vehicle must be ≤ 3 years old
     - Calculates lease fee, expected ending value, and monthly payments
4. **Save Contracts**
   - Each contract is appended to `contracts.csv`  
   - Removes vehicle from inventory after sale/lease
5. **Exit Program**
   - Updates all files and exits gracefully

---

## 🛠️ Classes & Structure

- **Dealership**
  - Stores dealership info and inventory
  - Provides methods to search, add, remove, and get vehicles by VIN

- **Vehicle**
  - Holds vehicle data (VIN, year, make, model, type, color, odometer, price)

- **Contract (Abstract)**
  - Base class for `SalesContract` and `LeaseContract`
  - Abstract methods:
    - `getTotalPrice()`
    - `getMonthlyPayment()`
    - `getType()`

- **SalesContract**
  - Extends `Contract`
  - Calculates total price and monthly payment (optional finance)

- **LeaseContract**
  - Extends `Contract`
  - Calculates lease fee, expected ending value, and monthly payment

- **File Managers**
  - `DealershipFileManager` → Handles inventory CSV
  - `ContractFileManager` → Handles contracts CSV (append & load)

- **UserInterface**
  - Menu-driven interface
  - Input validation and display formatting
  - Handles user interactions for all menu options

---

## ⚡ Input Guidelines

- **Numbers**: Only integers or doubles for VIN, year, price, mileage
- **Text**: Letters, spaces, hyphens allowed for names and vehicle make/model
- **Emails**: Standard format (`example@domain.com`)
- **Date**: YYYYMMDD (converted internally to `YYYY/MM/DD`)

---

## 📂 CSV File Format

### Inventory (`inventory.csv`)
VIN|Year|Make|Model|Type|Color|Odometer|Price
12345|2022|Toyota|Camry|Car|Blue|15000|25000
...

### Contracts (`contracts.csv`)
CONTRACT_TYPE|DATE|CUSTOMER_NAME|CUSTOMER_EMAIL|VIN|YEAR|MAKE|MODEL|VEHICLE_TYPE|COLOR|ODOMETER|VEHICLE_PRICE|CONTRACT_FIELDS|TOTAL_PRICE|MONTHLY_PAYMENT

SALE|2025/11/02|John Doe|john@example.com|12345|2022|Toyota|Camry|Car|Blue|15000|25000|FINANCE:YES|25500|425.00
LEASE|2025/11/02|Jane Smith|jane@example.com|54321|2023|Honda|Civic|Car|Red|5000|22000|LEASE_FEE:1540|13500|410.00

---

## 🚀 How to Run

1. Clone or download the repository  
2. Ensure you have Java 8 or higher installed  
3. Open the project in your IDE (IntelliJ, VS Code, etc.)  
4. Run `UserInterface` class  
5. Follow menu prompts to interact with the dealership

---

## 🎯 Notes

- Lease eligibility: Only vehicles ≤ 3 years old  
- Contracts are **appended** to `contracts.csv` to avoid overwriting  
- Input validation ensures smooth user experience  
- Displays all vehicles in a formatted table  

👤 Author
Kayla Sanders
