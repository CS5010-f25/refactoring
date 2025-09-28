package bad.robot.refactoring.chapter1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerTest {

  Movie jawsMovie; // starts REGULAR
  Movie upMovie; // starts CHILDREN
  Movie supermanMovie; // starts NEW_RELEASE
  Customer customer;

  @BeforeEach
  public void setup() {
    jawsMovie = new Movie("Jaws", Movie.REGULAR);
    upMovie = new Movie("Up", Movie.CHILDREN);
    supermanMovie = new Movie("Superman", Movie.NEW_RELEASE);
    customer = new Customer("Leslie");
  }

  private String getResource(String fileName) throws IOException {
    return Files.readString(Paths.get("src/test/resources/" + fileName))
        .replace("\r\n", "\n");
  }

  @Test
  public void statementNoRentals() throws IOException {
    assertEquals(getResource("no-rentals.txt"), customer.statement());
  }

}