package bbv.examples.services;

import bbv.examples.domain.Book;
import bbv.examples.exceptions.ServiceException;
import bbv.examples.repositories.BooksRepository;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@Service
public class BooksService {

  private BooksRepository repository;

  public BooksService(BooksRepository repository) {
    this.repository = repository;
  }

  public Collection<Book> findAllBooks() {
    return repository.findAll();
  }

  public Book findById(Integer bookId) {
    return repository.findById(bookId);
  }

  public Book findByIsbn(String isbn) {
    Optional<Book> result = repository.findAll().stream()
      .filter(book -> isbn.equals(book.getIsbn()))
      .findFirst();

    if (result.isPresent()) {
      return result.get();
    }
    else {
      throw new ServiceException(ServiceException.EXCEPTION_NOT_FOUND);
    }
  }

  public Book addBookToLibrary(Book book) {
    return repository.save(book);
  }

  public Book updateBookDetails(Book book) {
    return repository.save(book);
  }

  public void removeBookFromLibrary(Book book) {
    repository.delete(book);
  }

  public URI createBookLocationURI(Book book) {
    return URI.create(String.format("/api/books/%s", book.getId()));
  }
}
