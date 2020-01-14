package bbv.examples.controllers;

import bbv.examples.domain.Book;
import bbv.examples.services.BooksService;
import bbv.examples.services.BooksServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/api/books")
public class BooksController {

  /**
   * Books service.
   */
  private BooksService booksService;

  /**
   * Constructor of books controller.
   * @param booksService service of books
   */
  public BooksController(BooksService booksService) {
    this.booksService = booksService;
  }

  @GetMapping
  public ResponseEntity<Collection<Book>> findAllBooks() {
    Collection<Book> books = booksService.findAllBooks();

    return ResponseEntity.ok(books);
  }

  @GetMapping("{bookId}")
  public ResponseEntity<Book> findBookById(@PathVariable("bookId") Integer bookId) {
    Book book = booksService.findById(bookId);

    return ResponseEntity.ok(book);
  }

  @GetMapping("/isbn/{isbn}")
  public ResponseEntity<Book> findByIsbn(@PathVariable("isbn") String isbn) {
    Book book = booksService.findByIsbn(isbn);

    return ResponseEntity.ok(book);
  }

  /**
   * Retrieves all the books of given publisher.
   * @param publisher searched publisher
   * @return collection of books
   */

  @PostMapping
  public ResponseEntity<Book> addBookToLibrary(@RequestBody Book book) {
    Book persistedBook = booksService.addBookToLibrary(book);
    URI location = booksService.createBookLocationURI(persistedBook);

    return ResponseEntity.created(location).body(book);
  } // end of addBookToLibrary

  @PutMapping("{bookId}")
  public ResponseEntity<Book> updateBookDetails(@PathVariable Integer bookId, @RequestBody Book book) {
    Book updatedBook = booksService.updateBookDetails(book);

    return ResponseEntity.ok(updatedBook);
  } // end of updateBookDetails

  @DeleteMapping("{bookId}")
  public ResponseEntity<Book> removeBookFromLibrary(@PathVariable Integer bookId) {
    Book book = booksService.findById(bookId);
    booksService.removeBookFromLibrary(book);

    return ResponseEntity.noContent().build();
  }
}
