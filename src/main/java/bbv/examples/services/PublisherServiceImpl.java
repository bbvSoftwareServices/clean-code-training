package bbv.examples.services;

import bbv.examples.domain.Publisher;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
public class PublisherServiceImpl implements PublisherService {

  @Override
  public Collection<Publisher> findAllPublishers() {
    return null;
  }

  @Override
  public Publisher findById(UUID uuid) {
    return null;
  }

  @Override
  public void addPublisher(Publisher publisher) {

  }

  @Override
  public void updatePublisher(Publisher publisher) {

  }

  @Override
  public void deletePublisher(Publisher publisher) {

  }
}
