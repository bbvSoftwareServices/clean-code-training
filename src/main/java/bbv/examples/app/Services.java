package bbv.examples.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class Services {

  private Items items;

  public Collection<Book> findAllBooks() {
    return items.findAllBooks();
  }

  public Book findBookById(Integer bookId) {
    return items.findBookById(bookId);
  }

  public Book findByIsbn(String isbn) {
    Optional<Book> result = items.findAllBooks().stream()
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
    return items.saveBook(book);
  }

  public Book updateBookDetails(Integer bookId, Book book) {
    Book persistedBook = items.findBookById(bookId);

    persistedBook.setTitle(book.getTitle());
    persistedBook.setAdditionalTitle(book.getAdditionalTitle());
    persistedBook.setDescription(book.getDescription());
    persistedBook.setAuthors(book.getAuthors());
    persistedBook.setTags(book.getTags());
    persistedBook.setPublisher(book.getPublisher());

    return items.saveBook(book);
  }

  public void removeBookFromLibrary(Book book) {
    items.deleteBook(book);
  }

  @Autowired
  public void setItems(Items items) {
    this.items = items;
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

  public Collection<Publisher> findAllPublishers() {
    return items.findAll();
  }

  public Optional<Publisher> findPublisherById(UUID publisherId) {
    return items.findById(publisherId);
  }

  public Publisher addPublisher(Publisher publisher) {
    return items.save(publisher);
  }
}
