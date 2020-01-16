package bbv.examples.app;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class Items {

  private Map<Integer, Book> books = new HashMap<>();

  public Collection<Book> findAllBooks() {
    return books.values();
  }

  public Book findBookById(Integer id) {
    if (books.containsKey(id)) {
      return books.get(id);
    }
    else {
      throw RepositoryException.entryNotFound();
    }
  }

  public Book saveBook(Book book) {
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
    findAllBooks()
      .stream()
      .filter(b -> b.getIsbn().equals(book.getIsbn()))
      .findFirst()
      .ifPresent((b) -> {
        throw RepositoryException.bookAlreadyExists();
      });
  }

  private Map<UUID, Publisher> publishers = new HashMap<>();

  public Collection<Publisher> findAll() {
    return publishers.values();
  }

  public Publisher save(Publisher publisher) {
    publisher.setId(UUID.randomUUID());
    publisher.setCreatedAt(new Date());

    publishers.put(publisher.getId(), publisher);

    return publisher;
  }

  public Optional<Publisher> findById(UUID publisherId) {
    return Optional.ofNullable(publishers.get(publisherId));
  }

  private Book update(Book book) {
    book.setUpdatedAt(new Date());

    books.put(book.getId(), book);

    return book;
  }

  public void deleteBook(Book book) {
    books.remove(book.getId());
  }
}
