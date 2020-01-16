package bbv.examples.app;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Book {

  private Integer id;
  private String isbn;
  private String title;
  private String additionalTitle;
  private String description;
  private Publisher publisher;
  private List<String> authors = new LinkedList<>();
  private List<Tag> tags = new LinkedList<>();
  private Date createdAt;
  private Date updatedAt;

  public Book() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAdditionalTitle() {
    return additionalTitle;
  }

  public void setAdditionalTitle(String additionalTitle) {
    this.additionalTitle = additionalTitle;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Publisher getPublisher() {
    return publisher;
  }

  public void setPublisher(Publisher publisher) {
    this.publisher = publisher;
  }

  public List<String> getAuthors() {
    return authors;
  }

  public void setAuthors(List<String> authors) {
    this.authors = authors;
  }

  public List<Tag> getTags() {
    return tags;
  }

  public void setTags(List<Tag> tags) {
    this.tags = tags;
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
}
