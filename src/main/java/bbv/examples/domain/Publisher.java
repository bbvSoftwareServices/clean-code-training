package bbv.examples.domain;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Publisher {

  private UUID id;
  private String name;
  private Date createdAt;
  private Date updatedAt;

  public Publisher() {
  }

  public static Publisher create(String name) {
    Publisher publisher = new Publisher();
    publisher.setName(name);

    return publisher;
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

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public boolean isPersisted() {
    return this.id != null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Publisher publisher = (Publisher) o;
    return id.equals(publisher.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
