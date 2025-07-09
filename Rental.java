import java.time.LocalDate;

class Rental {
    private Customer customer;
    private Vehicle vehicle;
    private int rentalDays;
    private LocalDate rentalDate;

    public Rental(Customer customer, Vehicle vehicle, int rentalDays, LocalDate rentalDate) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.rentalDays = rentalDays;
        this.rentalDate = rentalDate;
    }

    public double getTotalPrice() {
        return vehicle.calculateRentalPrice(rentalDays);
    }

    public Customer getCustomer() { return customer; }
    public Vehicle getVehicle() { return vehicle; }
    public int getRentalDays() { return rentalDays; }
    public LocalDate getRentalDate() { return rentalDate; }

    @Override
    public String toString() {
        return "Rental by " + customer + " of " + vehicle + " for " + rentalDays + " day(s) starting " + rentalDate +
                " | Total: $" + String.format("%.2f", getTotalPrice());
    }
}
