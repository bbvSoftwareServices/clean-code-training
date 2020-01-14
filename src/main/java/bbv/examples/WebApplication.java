package bbv.examples;

import bbv.examples.domain.Book;
import bbv.examples.domain.Publisher;
import bbv.examples.repositories.BooksRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class WebApplication {

  public static void main(String[] args) {
    SpringApplication.run(WebApplication.class, args);
  }

  @Bean
  CommandLineRunner initializeBooks(BooksRepository repository) {
    return (args) -> {
      Publisher manning = Publisher.create("Manning");
      Publisher prenticeHall = Publisher.create("Prentice Hall");

      Book b1 = new Book();
      b1.setIsbn("978-1-61729-494-5");
      b1.setTitle("Spring In Action");
      b1.setAdditionalTitle("Fifth Edition");
      b1.setAuthors(Collections.singletonList("Craig Walls"));
      b1.setPublisher(manning);
      repository.add(b1);

      Book b2 = new Book();
      b2.setIsbn("978-0-13-235088-4");
      b2.setTitle("Clean Code");
      b2.setAdditionalTitle("A Handbook of Agile Software Craftsmanship");
      b2.setAuthors(Collections.singletonList("Robert C. Martin"));
      b2.setPublisher(prenticeHall);
      repository.add(b2);

      Book b3 = new Book();
      b3.setIsbn("978-1-61729-160-9");
      b3.setTitle("MongoDB In Action");
      b3.setAdditionalTitle("Second Edition");
      b3.setAuthors(Arrays.asList("Kyle Banker", "Peter Bakkum", "Shaun Verch", "Douglas Garrett", "Tim Hawkins"));
      b3.setPublisher(manning);
      repository.add(b3);
    };
  }
}
