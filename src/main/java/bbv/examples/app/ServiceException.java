package bbv.examples.app;

public class ServiceException extends RuntimeException {

  public static final String EXCEPTION_NOT_FOUND = "Doesn't exist";

  public ServiceException(String message) {
    super(message);
  }
}
