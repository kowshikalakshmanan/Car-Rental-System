import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarRentalApp {
    private static List<Vehicle> vehicles = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();
    private static List<Rental> rentals = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Add some sample cars
        vehicles.add(new Car("ABC123", "Toyota Camry", 50.0, "Sedan"));
        vehicles.add(new Car("XYZ789", "Honda CRV", 70.0, "SUV"));

        boolean exit = false;
        while (!exit) {
            System.out.println("\n=== Car Rental System Menu ===");
            System.out.println("1. View Available Cars");
            System.out.println("2. Register Customer");
            System.out.println("3. Rent a Car");
            System.out.println("4. Return a Car");
            System.out.println("5. View Rental History");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    viewAvailableCars();
                    break;
                case 2:
                    registerCustomer(scanner);
                    break;
                case 3:
                    rentCar(scanner);
                    break;
                case 4:
                    returnCar(scanner);
                    break;
                case 5:
                    viewRentalHistory();
                    break;
                case 6:
                    exit = true;
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }

    private static void viewAvailableCars() {
        System.out.println("\nAvailable Cars:");
        for (Vehicle v : vehicles) {
            if (v.isAvailable()) {
                System.out.println(v);
            }
        }
    }

    private static void registerCustomer(Scanner scanner) {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter driver license number: ");
        String dl = scanner.nextLine();

        customers.add(new Customer(name, dl));
        System.out.println("Customer registered successfully!");
    }

    private static void rentCar(Scanner scanner) {
        if (customers.isEmpty()) {
            System.out.println("No customers registered. Please register first.");
            return;
        }
        System.out.print("Enter customer name: ");
        String custName = scanner.nextLine();

        Customer customer = null;
        for (Customer c : customers) {
            if (c.getName().equalsIgnoreCase(custName)) {
                customer = c;
                break;
            }
        }
        if (customer == null) {
            System.out.println("Customer not found! Register first.");
            return;
        }

        System.out.println("Available cars:");
        List<Vehicle> availableCars = new ArrayList<>();
        for (Vehicle v : vehicles) {
            if (v.isAvailable()) {
                System.out.println(v);
                availableCars.add(v);
            }
        }
        if (availableCars.isEmpty()) {
            System.out.println("No cars available right now.");
            return;
        }

        System.out.print("Enter license plate of car to rent: ");
        String license = scanner.nextLine();

        Vehicle vehicleToRent = null;
        for (Vehicle v : availableCars) {
            if (v.getLicensePlate().equalsIgnoreCase(license)) {
                vehicleToRent = v;
                break;
            }
        }
        if (vehicleToRent == null) {
            System.out.println("Car not found or unavailable.");
            return;
        }

        System.out.print("Enter number of rental days: ");
        int days = scanner.nextInt();
        scanner.nextLine();

        vehicleToRent.setAvailable(false);
        Rental rental = new Rental(customer, vehicleToRent, days, LocalDate.now());
        rentals.add(rental);

        System.out.println("Car rented successfully! Total cost: $" + String.format("%.2f", rental.getTotalPrice()));
    }

    private static void returnCar(Scanner scanner) {
        System.out.print("Enter license plate of car to return: ");
        String license = scanner.nextLine();

        Rental rentalToReturn = null;
        for (Rental r : rentals) {
            if (r.getVehicle().getLicensePlate().equalsIgnoreCase(license) && !r.getVehicle().isAvailable()) {
                rentalToReturn = r;
                break;
            }
        }

        if (rentalToReturn == null) {
            System.out.println("No active rental found for this car.");
            return;
        }

        rentalToReturn.getVehicle().setAvailable(true);
        System.out.println("Car returned successfully!");
    }

    private static void viewRentalHistory() {
        if (rentals.isEmpty()) {
            System.out.println("No rentals yet.");
            return;
        }
        System.out.println("\nRental History:");
        for (Rental r : rentals) {
            System.out.println(r);
        }
    }
}
