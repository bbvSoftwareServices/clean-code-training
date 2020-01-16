package bbv.examples.controllers;

import bbv.examples.domain.Book;
import bbv.examples.domain.Publisher;
import bbv.examples.services.BooksService;
import bbv.examples.services.PublishersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/publishers")
public class PublishersController {

  private PublishersService publishersService;
  private BooksService booksService;

  public PublishersController(PublishersService publishersService, BooksService booksService) {
    this.publishersService = publishersService;
    this.booksService = booksService;
  }

  @GetMapping
  public ResponseEntity<Collection<Publisher>> findAllPublishers() {
    return ResponseEntity.ok(publishersService.findAll());
  }

  @GetMapping("/{publisherId}/books")
  public ResponseEntity<Collection<Book>> findAllBooksOfPublisher(@PathVariable UUID publisherId) {
    Collection<Book> books = null;
    Publisher publisher = publishersService.findById(publisherId);

    if (publisher != null) {
      books = booksService.findAllBooksForPublisher(publisher);

      if (books != null) {
        return ResponseEntity.ok(books);
      }
      else {
        return ResponseEntity.noContent().build();
      }
    }

    return ResponseEntity.ok().build();
  }
}
