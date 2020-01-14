package bbv.examples.repositories;

import bbv.examples.domain.Book;
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
    return books.get(id);
  }

  public Book add(Book book) {
    Date createdAt = new Date();

    book.setId(books.size() + 1);
    book.setCreatedAt(createdAt);
    book.setUpdatedAt(createdAt);

    books.put(book.getId(), book);

    return book;
  }

  public Book update(Book book) {
    book.setUpdatedAt(new Date());

    books.put(book.getId(), book);

    return book;
  }

  public void delete(Book book) {
    books.remove(book.getId());
  }
}
