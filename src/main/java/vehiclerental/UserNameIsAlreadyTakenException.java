package vehiclerental;

public class UserNameIsAlreadyTakenException extends RuntimeException {

    private User user;

    public UserNameIsAlreadyTakenException(String message) {
        super(message);
    }
}
