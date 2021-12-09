package hu.elte.teamfinder.exceptions;

public class UserAlreadyExists extends Throwable {
    public UserAlreadyExists(String message) {
        super(message);
    }
}
