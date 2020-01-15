package bbv.examples.services;

import bbv.examples.domain.Book;
import bbv.examples.exceptions.ServiceException;
import bbv.examples.exceptions.ValidationException;
import bbv.examples.repositories.BooksRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

  public Book updateBookDetails(Integer bookId, Book book) {
    Book persistedBook = repository.findById(bookId);

    persistedBook.setTitle(book.getTitle());
    persistedBook.setAdditionalTitle(book.getAdditionalTitle());
    persistedBook.setDescription(book.getDescription());
    persistedBook.getAuthors().clear();
    persistedBook.getAuthors().addAll(book.getAuthors());
    persistedBook.getTags().clear();
    persistedBook.getTags().addAll(book.getTags());
    persistedBook.setPublisher(book.getPublisher());

    return repository.save(book);
  }

  public void removeBookFromLibrary(Book book) {
    repository.delete(book);
  }

  public URI createBookLocationURI(Book book) {
    return URI.create(String.format("/api/books/%s", book.getId()));
  }

  public void validateBookDetails(Book book) {
    if (StringUtils.isEmpty(book.getTitle())) {
      throw ValidationException.bookTitleIsMandatory();
    }
    if (StringUtils.isEmpty(book.getIsbn())) {
      throw ValidationException.isbnIsMandatory();
    }
  }
}
