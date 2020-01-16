package bbv.examples.app;

import java.util.Date;
import java.util.UUID;

public class Publisher {

  private UUID id;
  private String name;
  private Date createdAt;

  public static Publisher create(String name) {
    Publisher publisher = new Publisher();
    publisher.setName(name);

    return publisher;
  }

  public Publisher() {
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
}
