# Console-Based Library Management System in Java

A robust, console-based **Library Management System** developed in Java to demonstrate core **Object-Oriented Programming (OOP)** principles, robust input validation, and in-memory state management.

---

## Features
* **Inventory Management:** Add new books with unique IDs, titles, and authors.
* **Patron Registration:** Register library members into the system.
* **Checkout & Check-in Workflows:** Borrow and return books with real-time availability tracking.
* **Polymorphic Display:** Dynamically format and display inventory items using abstract base classes and method overriding.
* **Robust Error Handling:** Intercepts invalid menu inputs and `NumberFormatException` to prevent application crashes.

---

## Object-Oriented Programming (OOP) Principles Applied
1. **Abstraction:** The `LibraryItem` abstract class defines a common blueprint and enforces polymorphic display methods for all inventory types.
2. **Inheritance:** The `Book` subclass extends `LibraryItem`, utilizing `super()` for clean constructor chaining.
3. **Polymorphism:** Runtime method overriding (`@Override`) of `displayItemDetails()` allows seamless iteration across generalized item collections.
4. **Encapsulation:** Private instance variables across all models (`LibraryItem`, `Book`, `Member`) protect data integrity, accessed exclusively via public getters and setters.

---

## Project Structure
```text
library_management_system/
│
├── LibraryManagementSystem.java   # Contains all system classes, controller, and main entry point
└── README.md                      # Project documentation and setup guide
