package bbv.examples.services;

import bbv.examples.domain.Book;

import java.net.URI;
import java.util.Collection;

public interface BooksService {

  Collection<Book> findAllBooks();

  Book findById(Integer bookId);

  Book findByIsbn(String isbn);

  Book addBookToLibrary(Book book);

  Book updateBookDetails(Book book);

  void removeBookFromLibrary(Book book);

  URI createBookLocationURI(Book book);
}
