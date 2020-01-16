package bbv.examples.services;

import bbv.examples.domain.Book;
import bbv.examples.domain.Publisher;
import bbv.examples.exceptions.RepositoryException;
import bbv.examples.repositories.BooksRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service
public class BooksService {

  private BooksRepository repository;

  public BooksService(BooksRepository repository) {
    this.repository = repository;
  }

  public Collection<Book> findAllBooks() {
    return repository.findAll();
  }

  public Book findById(Integer bookId) throws Exception {
    try {
      return repository.findById(bookId);
    }
    catch (RepositoryException e) {
      throw new Exception(e);
    }
  }

  public Book findByIsbn(String isbn) {
    Book book = null;
    Collection<Book> books = repository.findAll();

    for (Book b : books) {
      if (b.getIsbn().equals(isbn)) {
        book = b;
        break;
      }
    }

    return book;
  }

  public void addBookToLibrary(Book book) throws RepositoryException {
    repository.save(book);
  }

  public Book updateBookDetails(Integer bookId, Book book) throws Exception {
    Book persistedBook = null;

    try {
      persistedBook = repository.findById(bookId);

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
    catch (RepositoryException e) {
      throw new Exception("something went wrong", e);
    }
  }

  public void removeBookFromLibrary(Book book) {
    repository.delete(book);
  }

  public boolean check(Book book) {
    boolean ok = true;

    if (StringUtils.isEmpty(book.getTitle())) {
      ok = false;
    }

    if (StringUtils.isEmpty(book.getIsbn())) {
      ok = false;
    }

    return ok;
  }

  public Collection<Book> findAllBooksForPublisher(Publisher publisher) {
    List<Book> books = null;

    Collection<Book> allBooks = repository.findAll();

    if (!allBooks.isEmpty()) {
      books = new LinkedList<>();

      for (Book book : allBooks) {
        if (book.getPublisher().equals(publisher)) {
          books.add(book);
        }
      }
    }

    return books;
  }
}
