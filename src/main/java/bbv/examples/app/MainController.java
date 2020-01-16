package bbv.examples.app;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/api")
public class MainController {

  private Services services;

  public MainController(Services services) {
    this.services = services;
  }

  @GetMapping("/publishers")
  public ResponseEntity<Collection<Publisher>> retrieveAllPublishers() {
    return ResponseEntity.ok(services.findAllPublishers());
  }

  @GetMapping("/books")
  public ResponseEntity<Collection<Book>> findAllBooks() {
    Collection<Book> books = services.findAllBooks();

    return ResponseEntity.ok(books);
  }

  @GetMapping("/books/{bookId}")
  public ResponseEntity<Book> findBookById(@PathVariable("bookId") Integer bookId) {
    Book book = services.findBookById(bookId);

    return ResponseEntity.ok(book);
  }

  @GetMapping("/publishers/{publisherId}")
  public ResponseEntity<Publisher> findById(@PathVariable UUID publisherId) {
    return services.findPublisherById(publisherId)
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/publishers/{publisherId}/books")
  public ResponseEntity<Collection<Book>> retrieveAllBooksForPublisher(@PathVariable UUID publisherId) {
    List<Book> books = new LinkedList<>();

    services.findPublisherById(publisherId).ifPresent(publisher -> {
      services.findAllBooks().stream()
        .filter(book -> book.getPublisher().equals(publisher))
        .forEach(books::add);
    });

    return ResponseEntity.ok(books);
  }

  @GetMapping("/books/isbn/{isbn}")
  public ResponseEntity<Book> findByIsbn(@PathVariable("isbn") String isbn) {
    Book book = services.findByIsbn(isbn);

    return ResponseEntity.ok(book);
  }

  @PostMapping("/books")
  public ResponseEntity<Book> addBookToLibrary(@RequestBody Book book) {
    services.validateBookDetails(book);
    Book persistedBook = services.addBookToLibrary(book);
    URI location = services.createBookLocationURI(persistedBook);

    return ResponseEntity.created(location).body(book);
  }

  @PutMapping("/books/{bookId}")
  public ResponseEntity<Book> updateBookDetails(@PathVariable Integer bookId, @RequestBody Book book) {
    services.validateBookDetails(book);
    Book updatedBook = services.updateBookDetails(bookId, book);

    return ResponseEntity.ok(updatedBook);
  }

  @DeleteMapping("/books/{bookId}")
  public ResponseEntity<Book> removeBookFromLibrary(@PathVariable Integer bookId) {
    Book book = services.findBookById(bookId);
    services.removeBookFromLibrary(book);

    return ResponseEntity.noContent().build();
  }
}
