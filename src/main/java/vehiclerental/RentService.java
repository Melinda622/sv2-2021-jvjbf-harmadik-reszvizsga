package vehiclerental;

import java.time.LocalTime;
import java.util.*;

public class RentService {

    Set<User> users = new HashSet<>();
    Set<Rentable> rentables = new HashSet<>();
    Map<Rentable, User> actualRenting = new TreeMap<>();

    public void registerUser(User user) {
        if (users.stream().anyMatch(u -> u.getUserName().equals(user.getUserName()))) {
            throw new UserNameIsAlreadyTakenException("Username is taken!");
        }
        users.add(user);
    }

    public void addRentable(Rentable rentable) {
        rentables.add(rentable);
    }

    public void rent(User user, Rentable rentable, LocalTime time) {

        if (rentable instanceof Bike) {
            if (rentable.getRentingTime() == null && user.getBalance() >= rentable.calculateSumPrice(180)) {
                ((Bike) rentable).setRentingTime(time);
                actualRenting.put(rentable, user);
            } else {
                throw new IllegalStateException("The conditions are not met to rent this vehicle");
            }
        } else {
            if (rentable.getRentingTime() == null && user.getBalance() >= rentable.calculateSumPrice(180)) {
                ((Car) rentable).setRentingTime(time);
                actualRenting.put(rentable, user);
            } else {
                throw new IllegalStateException("The conditions are not met to rent this vehicle");
            }
        }

    }

    public void closeRent(Rentable rentable, int minutes) {

        if (minutes > 180) {
            throw new IllegalStateException("The vehicle cannot be rented for longer than 3 hours");
        }
        actualRenting.get(rentable).minusBalance(rentable.calculateSumPrice(minutes));
        actualRenting.remove(rentable);
        rentable.closeRent();
    }

    public Map<Rentable, User> getActualRenting() {
        return actualRenting;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Set<Rentable> getRentables() {
        return rentables;
    }
}
