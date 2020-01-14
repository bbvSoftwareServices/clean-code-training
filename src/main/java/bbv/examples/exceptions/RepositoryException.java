package bbv.examples.exceptions;

public class RepositoryException extends RuntimeException {

  private static final String BOOK_ALREADY_EXISTS = "A book with given ISBN already exists";

  public RepositoryException(String message) {
    super(message);
  }

  public static RepositoryException bookAlreadyExists() {
    return new RepositoryException(BOOK_ALREADY_EXISTS);
  }
}
