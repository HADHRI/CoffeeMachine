package exceptions;

public class NotEnoughMoneyException extends Exception {

    public static final String  MISSING_MONEY="M:insufficient Money-Missing ";
    public NotEnoughMoneyException(String errorMessage) {
        super(errorMessage);
    }

}
