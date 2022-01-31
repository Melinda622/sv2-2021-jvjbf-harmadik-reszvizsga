package vehiclerental;

import java.time.LocalTime;

public class Bike implements Rentable{

    private String id;
    private LocalTime rentingTime;

    public Bike(String id) {
        this.id = id;
    }

    @Override
    public int calculateSumPrice(long minutes) {
        return (int) (15 * minutes);
    }

    @Override
    public LocalTime getRentingTime() {
        return rentingTime;
    }

    public void setRentingTime(LocalTime rentingTime) {
        this.rentingTime = rentingTime;
    }

    @Override
    public void rent(LocalTime time) {
        this.rentingTime = time;
    }

    @Override
    public void closeRent() {
        this.rentingTime = null;
    }
}
