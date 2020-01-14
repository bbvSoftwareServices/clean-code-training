package bbv.examples.controllers;

import bbv.examples.domain.Book;
import bbv.examples.exceptions.ServiceException;
import bbv.examples.repositories.BooksRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BooksController {

  private BooksRepository booksRepository;

  public BooksController(BooksRepository booksRepository) {
    this.booksRepository = booksRepository;
  }

  @GetMapping
  public Collection<Book> getAllBooks() {
    return booksRepository.findAll();
  }

  @GetMapping("{bookId}")
  public ResponseEntity<Book> retrieveBook(@PathVariable("bookId") Integer bookId) {
    Book book = booksRepository.findById(bookId);

    return ResponseEntity.ok(book);
  }

  @GetMapping("/isbn/{isbn}")
  public Book fetchByIsbn(@PathVariable("isbn") String isbn) {
    Book book = findByIsbn(isbn);

    if (book != null) {
      return book;
    }
    else {
      return null;
    }
  }

  @PostMapping
  public ResponseEntity<Book> post(@RequestBody Book book) {
    Book persistedBook = booksRepository.add(book);
    String path = String.format("/api/books/%s", persistedBook.getId());
    URI location = URI.create(path);

    return ResponseEntity.created(location).body(book);
  }

  @PutMapping("{bookId}")
  public ResponseEntity<Book> put(@PathVariable Integer bookId, @RequestBody Book book) {
    Book updatedBook = booksRepository.update(book);

    return ResponseEntity.ok(updatedBook);
  }

  @DeleteMapping("{bookId}")
  public void removeBookFromLibrary(@PathVariable Integer bookId) {
    Book book = booksRepository.findById(bookId);
    booksRepository.delete(book);
  }

  public Book findByIsbn(String isbn) {
    if (isbn == null) {
      return null;
    }

    Collection<Book> books = booksRepository.findAll();
    Book a = null;

    for (Book book : books) {
      if (book.getIsbn().equals(isbn)) {
        a = book;
        break;
      }
    }

    return a;
  }
}
