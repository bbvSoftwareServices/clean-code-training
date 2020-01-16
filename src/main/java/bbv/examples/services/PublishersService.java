package bbv.examples.services;

import bbv.examples.domain.Publisher;
import bbv.examples.repositories.PublishersRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
public class PublishersService {

  private PublishersRepository publishersRepository;

  public PublishersService(PublishersRepository publishersRepository) {
    this.publishersRepository = publishersRepository;
  }

  public Collection<Publisher> findAll() {
    return publishersRepository.findAll();
  }

  public Publisher findById(UUID publisherId) {
    return publishersRepository.findById(publisherId);
  }

  public Publisher addPublisher(Publisher publisher) {
    return publishersRepository.save(publisher);
  }
}
