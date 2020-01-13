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

  private BooksManager manager;

  public BooksController(BooksManager manager) {
    this.manager = manager;
  }

  @GetMapping
  public ResponseEntity<Collection<Book>> index() {
    Collection<Book> booksList = manager.search();

    return ResponseEntity.ok(booksList);
  }

  @GetMapping("{bookId}")
  public ResponseEntity<Book> getById(@PathVariable("bookId") Integer bookId) {
    Book book = manager.retrieveById(bookId);

    return ResponseEntity.ok(book);
  }

  @GetMapping("/isbn/{isbn}")
  public ResponseEntity<Book> retrieveByIsbn(@PathVariable("isbn") String isbn) {
    Book book = manager.fetchByIsbn(isbn);

    return ResponseEntity.ok(book);
  }

  @PostMapping
  public ResponseEntity<Book> post(@RequestBody Book book) {
    Book persistedBook = manager.persist(book);
    URI location = URI.create(String.format("/api/books/%s", persistedBook.getId()));

    return ResponseEntity.created(location).body(book);
  }

  @PutMapping("{bookId}")
  public ResponseEntity<Book> put(@PathVariable Integer bookId, @RequestBody Book book) {
    Book updatedBook = manager.updateBookDetails(book);

    return ResponseEntity.ok(updatedBook);
  }

  @DeleteMapping("{bookId}")
  public ResponseEntity<Book> delete(@PathVariable Integer bookId) {
    Book book = manager.retrieveById(bookId);
    manager.removeBookFromLibrary(book);

    return ResponseEntity.noContent().build();
  }
}
