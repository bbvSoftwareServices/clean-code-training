package bbv.examples.controllers;

import bbv.examples.domain.Book;
import bbv.examples.services.BooksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/books")
public class BooksController {

  private BooksService booksService;

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

  @PostMapping
  public Book addBookToLibrary(@RequestBody Book book) {
    if (booksService.check(book)) {
      booksService.addBookToLibrary(book);
      return book;
    }
    else {
      return null;
    }
  }

  @PutMapping("{bookId}")
  public Book updateBookDetails(@PathVariable Integer bookId, @RequestBody Book book) {
    if (booksService.check(book)) {
      return booksService.updateBookDetails(bookId, book);
    }
    else {
      return null;
    }
  }

  @DeleteMapping("{bookId}")
  public ResponseEntity<Book> removeBookFromLibrary(@PathVariable Integer bookId) {
    Book book = booksService.findById(bookId);
    booksService.removeBookFromLibrary(book);

    return ResponseEntity.noContent().build();
  }
}
