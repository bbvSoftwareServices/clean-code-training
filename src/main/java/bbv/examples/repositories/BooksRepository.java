package bbv.examples.repositories;

import bbv.examples.domain.Book;
import bbv.examples.exceptions.RepositoryException;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class BooksRepository {

  private Map<Integer, Book> books = new HashMap<>();

  public Collection<Book> findAll() {
    return books.values();
  }

  public Book findById(Integer id) {
    if (books.containsKey(id)) {
      return books.get(id);
    }
    else {
      throw RepositoryException.entryNotFound();
    }
  }

  public Book save(Book book) {
    return book.getId() != null ? this.update(book) : this.add(book);
  }

  private Book add(Book book) {
    validateExistence(book);

    Date createdAt = new Date();

    book.setId(books.size() + 1);
    book.setCreatedAt(createdAt);
    book.setUpdatedAt(createdAt);

    books.put(book.getId(), book);

    return book;
  }

  private void validateExistence(Book book) {
    findAll()
      .stream()
      .filter(b -> b.getIsbn().equals(book.getIsbn()))
      .findFirst()
      .ifPresent((b) -> {
        throw RepositoryException.bookAlreadyExists();
      });
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
