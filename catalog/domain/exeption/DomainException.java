package catalog.domain.exeption;

/**
 * Created by user on 31.05.2016.
 */
public class DomainException extends Exception{
    public DomainException() {
    }

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
