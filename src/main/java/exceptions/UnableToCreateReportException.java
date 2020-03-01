package exceptions;

public class UnableToCreateReportException extends Exception{

    public UnableToCreateReportException(String errorMessage) {
        super(errorMessage);
    }
}
