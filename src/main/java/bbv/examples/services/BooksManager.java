package bbv.examples.services;

import bbv.examples.domain.Book;
import bbv.examples.exceptions.ServiceException;
import bbv.examples.repositories.BooksRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class BooksManager {

  private BooksRepository r;

  public BooksManager(BooksRepository repository) {
    this.r = repository;
  }

  public Collection<Book> search() {
    return r.findAll();
  }

  public Book retrieveById(Integer bookId) {
    return r.findById(bookId);
  }

  public Book fetchByIsbn(String isbn) {
    Optional<Book> result = r.findAll().stream()
      .filter(book -> isbn.equals(book.getIsbn()))
      .findFirst();

    if (result.isPresent()) {
      return result.get();
    }
    else {
      throw new ServiceException(ServiceException.EXCEPTION_NOT_FOUND);
    }
  }

  public Book persist(Book book) {
    return r.save(book);
  }

  public Book updateBookDetails(Book book) {
    return r.save(book);
  }

  public void removeBookFromLibrary(Book book) {
    r.delete(book);
  }

}
