package bbv.examples.app;

public class ValidationException extends RuntimeException {

  private static final String MANDATORY_BOOK_TITLE = "Book title can not be empty or null";
  private static final String MANDATORY_ISBN = "ISBN is mandatory";

  public ValidationException(String message) {
    super(message);
  }

  public static ValidationException bookTitleIsMandatory() {
    return new ValidationException(MANDATORY_BOOK_TITLE);
  }

  public static ValidationException isbnIsMandatory() {
    return new ValidationException(MANDATORY_ISBN);
  }
}
