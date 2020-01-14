package bbv.examples.services;

import bbv.examples.domain.Publisher;

import java.util.Collection;
import java.util.UUID;

public interface PublisherService {
  /**
   * Retrieves all publishers.
   * @return list of publishers
   */
  Collection<Publisher> findAllPublishers();
  /**
   * Delivers publisher for given ID.
   * @param uuid ID of publisher
   * @return publisher
   */
  Publisher findById(UUID uuid);
  /**
   * Adds new publisher.
   * @param publisher publisher
   */
  void addPublisher(Publisher publisher);
  void updatePublisher(Publisher publisher);
  void deletePublisher(Publisher publisher);






}





