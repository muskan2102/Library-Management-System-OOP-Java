import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 1. Abstraction: Abstract class defining the blueprint for library items
abstract class LibraryItem {
    private String id;
    private String title;
    private boolean isAvailable;

    public LibraryItem(String id, String title) {
        this.id = id;
        this.title = title;
        this.isAvailable = true;
    }

    // 2. Encapsulation: Private fields accessed via public getters and setters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    // Abstract method to be implemented by child classes
    public abstract void displayItemDetails();
}

// 3. Inheritance: Book inherits from LibraryItem
class Book extends LibraryItem {
    private String author;

    public Book(String id, String title, String author) {
        super(id, title);
        this.author = author;
    }

    // 4. Polymorphism: Overriding the abstract method to provide specific implementation
    @Override
    public void displayItemDetails() {
        System.out.println("Book ID: " + getId() + " | Title: " + getTitle() +
                           " | Author: " + author + " | Available: " + isAvailable());
    }
}

// Encapsulated Member class
class Member {
    private String memberId;
    private String name;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public String getMemberId() { return memberId; }
    public String getName() { return name; }
}

// System controller managing collections and operations
class LibraryManager {
    private List<LibraryItem> items;
    private List<Member> members;

    public LibraryManager() {
        this.items = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public void addItem(LibraryItem item) {
        items.add(item);
        System.out.println("Success: Item added to the library.");
    }

    public void registerMember(Member member) {
        members.add(member);
        System.out.println("Success: Member registered.");
    }

    public void borrowItem(String itemId, String memberId) {
        for (LibraryItem item : items) {
            if (item.getId().equalsIgnoreCase(itemId)) {
                if (item.isAvailable()) {
                    item.setAvailable(false);
                    System.out.println("Success: Item borrowed by member ID " + memberId);
                } else {
                    System.out.println("Error: Item is currently checked out.");
                }
                return;
            }
        }
        System.out.println("Error: Item not found.");
    }

    public void returnItem(String itemId) {
        for (LibraryItem item : items) {
            if (item.getId().equalsIgnoreCase(itemId)) {
                if (!item.isAvailable()) {
                    item.setAvailable(true);
                    System.out.println("Success: Item returned.");
                } else {
                    System.out.println("Notice: This item was not checked out.");
                }
                return;
            }
        }
        System.out.println("Error: Item not found.");
    }

    public void displayAllItems() {
        if (items.isEmpty()) {
            System.out.println("The library inventory is empty.");
            return;
        }
        System.out.println("\n--- Library Inventory ---");
        for (LibraryItem item : items) {
            item.displayItemDetails(); // Polymorphic behavior in action
        }
        System.out.println("-------------------------");
    }
}

// Main class to run the console interface
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryManager manager = new LibraryManager();

        // Seed initial data
        manager.addItem(new Book("B001", "Clean Code", "Robert C. Martin"));
        manager.addItem(new Book("B002", "Design Patterns", "Erich Gamma"));
        manager.registerMember(new Member("M001", "Jane Doe"));

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n Library Management System ");
            System.out.println("1. Add New Book");
            System.out.println("2. View All Items");
            System.out.println("3. Borrow a Book");
            System.out.println("4. Return a Book");
            System.out.println("5. Exit System");
            System.out.print("Enter your choice: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Book Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author Name: ");
                    String author = scanner.nextLine();
                    manager.addItem(new Book(id, title, author));
                    break;
                case 2:
                    manager.displayAllItems();
                    break;
                case 3:
                    System.out.print("Enter Book ID to borrow: ");
                    String borrowId = scanner.nextLine();
                    System.out.print("Enter Member ID: ");
                    String memberId = scanner.nextLine();
                    manager.borrowItem(borrowId, memberId);
                    break;
                case 4:
                    System.out.print("Enter Book ID to return: ");
                    String returnId = scanner.nextLine();
                    manager.returnItem(returnId);
                    break;
                case 5:
                    isRunning = false;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
            }
        }
        scanner.close();
    }
}
