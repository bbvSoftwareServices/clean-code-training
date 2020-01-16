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

  public Publisher findById(UUID publisherId) {
    if (publishers.containsKey(publisherId)) {
      return publishers.get(publisherId);
    }
    else {
      return null;
    }
  }

  public Publisher save(Publisher publisher) {
    return publisher.isPersisted() ? this.updatePublisher(publisher) : this.addPublisher(publisher);
  }

  private Publisher addPublisher(Publisher publisher) {
    Date createdAt = new Date();
    publisher.setId(UUID.randomUUID());
    publisher.setCreatedAt(createdAt);
    publisher.setUpdatedAt(createdAt);
    publishers.put(publisher.getId(), publisher);

    return publisher;
  }

  private Publisher updatePublisher(Publisher publisher) {
    publisher.setUpdatedAt(new Date());
    publishers.put(publisher.getId(), publisher);

    return publisher;
  }
}
