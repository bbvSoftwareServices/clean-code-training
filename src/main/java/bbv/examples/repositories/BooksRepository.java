package bbv.examples.repositories;

import bbv.examples.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class BooksRepository {

  private Map<Integer, Book> booksMap = new HashMap<>();

  public Collection<Book> findAll() {
    return booksMap.values();
  }

  public Book findById(Integer id) {
    return booksMap.get(id);
  }

  public Book save(Book book) {
    return book.getId() != null ? this.update(book) : this.add(book);
  }

  private Book add(Book book) {
    Date createdAt = new Date();

    book.setId(booksMap.size() + 1);
    book.setCreatedAt(createdAt);
    book.setUpdatedAt(createdAt);

    booksMap.put(book.getId(), book);

    return book;
  }

  private Book update(Book book) {
    book.setUpdatedAt(new Date());

    booksMap.put(book.getId(), book);

    return book;
  }

  public void delete(Book book) {
    booksMap.remove(book.getId());
  }
}
