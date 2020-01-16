package bbv.examples.controllers;

import bbv.examples.domain.Publisher;
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

  public PublishersController(PublishersService publishersService) {
    this.publishersService = publishersService;
  }

  @GetMapping
  public ResponseEntity<Collection<Publisher>> retrieveAllPublishers() {
    return ResponseEntity.ok(publishersService.findAll());
  }

  @GetMapping("/{publisherId}")
  public ResponseEntity<Publisher> findById(@PathVariable UUID publisherId) {
    return publishersService.findById(publisherId)
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
