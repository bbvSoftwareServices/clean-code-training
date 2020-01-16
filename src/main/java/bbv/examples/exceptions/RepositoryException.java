package bbv.examples.exceptions;

public class RepositoryException extends Exception {

  private static final String BOOK_ALREADY_EXISTS = "A book with given ISBN already exists";
  private static final String BOOK_NO_ENTRY_FOUND = "No entry found";

  public RepositoryException(String message) {
    super(message);
  }

  public static RepositoryException bookAlreadyExists() {
    return new RepositoryException(BOOK_ALREADY_EXISTS);
  }

  public static RepositoryException entryNotFound() {
    return new RepositoryException(BOOK_NO_ENTRY_FOUND);
  }
}
