package bbv.examples.repositories;

import bbv.examples.domain.Publisher;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PublishersRepository {

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
}
