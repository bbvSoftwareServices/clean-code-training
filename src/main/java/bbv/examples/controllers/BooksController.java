package bbv.examples.controllers;

import bbv.examples.domain.Book;
import bbv.examples.services.BooksManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/api/books")
public class BooksController {

  private BooksManager booksManager;

  public BooksController(BooksManager booksManager) {
    this.booksManager = booksManager;
  }

  @GetMapping
  public ResponseEntity<Collection<Book>> index() {
    Collection<Book> booksList = booksManager.findAllBooks();

    return ResponseEntity.ok(booksList);
  }

  @GetMapping("{bookId}")
  public ResponseEntity<Book> findBookById(@PathVariable("bookId") Integer bookId) {
    Book book = booksManager.findById(bookId);

    return ResponseEntity.ok(book);
  }

  @GetMapping("/isbn/{isbn}")
  public ResponseEntity<Book> findByIsbn(@PathVariable("isbn") String isbn) {
    Book book = booksManager.findByIsbn(isbn);

    return ResponseEntity.ok(book);
  }

  @PostMapping
  public ResponseEntity<Book> addBookToLibrary(@RequestBody Book book) {
    Book persistedBook = booksManager.addBookToLibrary(book);
    URI location = booksManager.createBookLocationURI(persistedBook);

    return ResponseEntity.created(location).body(book);
  }

  @PutMapping("{bookId}")
  public ResponseEntity<Book> updateBookDetails(@PathVariable Integer bookId, @RequestBody Book book) {
    Book updatedBook = booksManager.updateBookDetails(book);

    return ResponseEntity.ok(updatedBook);
  }

  @DeleteMapping("{bookId}")
  public ResponseEntity<Book> removeBookFromLibrary(@PathVariable Integer bookId) {
    Book book = booksManager.findById(bookId);
    booksManager.removeBookFromLibrary(book);

    return ResponseEntity.noContent().build();
  }
}
