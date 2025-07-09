class Car extends Vehicle {
    private String carType; // e.g., Sedan, SUV

    public Car(String licensePlate, String model, double rentalPricePerDay, String carType) {
        super(licensePlate, model, rentalPricePerDay);
        this.carType = carType;
    }

    public String getCarType() { return carType; }

    @Override
    public double calculateRentalPrice(int days) {
        // Different pricing logic if needed (e.g., SUV is 10% more expensive)
        double basePrice = getRentalPricePerDay() * days;
        if (carType.equalsIgnoreCase("SUV")) {
            basePrice *= 1.10;
        }
        return basePrice;
    }

    @Override
    public String toString() {
        return super.toString() + " - Type: " + carType;
    }
}
