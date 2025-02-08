package Model.Exceptions;

public class WrongPassword extends Exception {
    public WrongPassword(String message) {
        super(message);
    }
}
