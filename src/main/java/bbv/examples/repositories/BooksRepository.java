package bbv.examples.repositories;

import bbv.examples.domain.Book;
import bbv.examples.exceptions.RepositoryException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
@Slf4j
public class BooksRepository {

  private Map<Integer, Book> books = new HashMap<>();

  public Collection<Book> findAll() {
    return books.values();
  }

  public Book findById(Integer id) throws RepositoryException {
    if (books.containsKey(id)) {
      return books.get(id);
    }
    else {
      throw RepositoryException.entryNotFound();
    }
  }

  public Book save(Book book) throws RepositoryException {
    return book.getId() != null ? this.update(book) : this.add(book);
  }

  private Book add(Book book) throws RepositoryException {
    validateExistence(book);

    Date createdAt = new Date();

    book.setId(books.size() + 1);
    book.setCreatedAt(createdAt);
    book.setUpdatedAt(createdAt);

    books.put(book.getId(), book);

    return book;
  }

  private void validateExistence(Book book) throws RepositoryException {
    for (Book b : findAll()) {
      if (b.getIsbn().equals(book.getIsbn())) {
        throw RepositoryException.bookAlreadyExists();
      }
    }
  }

  private Book update(Book book) {
    book.setUpdatedAt(new Date());

    books.put(book.getId(), book);

    return book;
  }

  public void delete(Book book) {
    books.remove(book.getId());
  }
}
