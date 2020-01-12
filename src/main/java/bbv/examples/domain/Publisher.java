package bbv.examples.domain;

import java.util.UUID;

public class Publisher {

  private UUID id;
  private String name;

  public static Publisher create(String name) {
    return new Publisher(name);
  }

  private Publisher() {
  }

  private Publisher(String name) {
    this.id = UUID.randomUUID();
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public UUID getId() {
    return id;
  }
}
