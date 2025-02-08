package Model.Exceptions;

public class WrongUsername extends RuntimeException {
    public WrongUsername(String message) {

        super(message);
    }
}
