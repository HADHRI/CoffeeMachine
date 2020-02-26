package exceptions;

public class CoffeTypeNotMentionnedException extends Exception{

    public CoffeTypeNotMentionnedException(String errorMessage) {
        super(errorMessage);
    }
}
