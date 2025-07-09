import java.time.LocalDate;

abstract class Vehicle {
    private String licensePlate;
    private String model;
    private double rentalPricePerDay;
    private boolean isAvailable;

    public Vehicle(String licensePlate, String model, double rentalPricePerDay) {
        this.licensePlate = licensePlate;
        this.model = model;
        this.rentalPricePerDay = rentalPricePerDay;
        this.isAvailable = true;
    }

    // Getters and setters
    public String getLicensePlate() { return licensePlate; }
    public String getModel() { return model; }
    public double getRentalPricePerDay() { return rentalPricePerDay; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    // Abstract method for price calculation
    public abstract double calculateRentalPrice(int days);

    @Override
    public String toString() {
        return model + " (" + licensePlate + ") - $" + rentalPricePerDay + "/day - " + (isAvailable ? "Available" : "Rented");
    }
}
