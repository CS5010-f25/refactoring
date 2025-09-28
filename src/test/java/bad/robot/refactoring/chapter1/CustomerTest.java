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

  @Test
  public void statementSingleRegularRentalFor1Day() throws IOException {
    customer.addRental(new Rental(jawsMovie, 1));
    assertEquals(getResource("single-regular-1day.txt"), customer.statement());
  }

  @Test
  public void statementSingleRegularRentalFor3Days() throws IOException {
    customer.addRental(new Rental(jawsMovie, 3));
    assertEquals(getResource("single-regular-3days.txt"), customer.statement());
  }

  @Test
  public void statementSingleChildrenRentalFor2Days() throws IOException {
    customer.addRental(new Rental(upMovie, 2));
    assertEquals(getResource("single-children-2days.txt"),
        customer.statement());
  }

  @Test
  public void statementSingleChildrenRentalFor4Days() throws IOException {
    customer.addRental(new Rental(upMovie, 4));
    assertEquals(getResource("single-children-4days.txt"),
        customer.statement());
  }

  @Test
  public void statementSingleNewReleaseRentalFor1Day() throws IOException {
    customer.addRental(new Rental(supermanMovie, 1));
    assertEquals(getResource("single-newrelease-1day.txt"),
        customer.statement());
  }

  @Test
  public void statementSingleNewReleaseRentalFor2Days() throws IOException {
    customer.addRental(new Rental(supermanMovie, 2));
    assertEquals(getResource("single-newrelease-2days.txt"),
        customer.statement());
  }

  @Test
  public void statementMultipleRentals() throws IOException {
    customer.addRental(new Rental(jawsMovie, 2));
    customer.addRental(new Rental(upMovie, 3));
    customer.addRental(new Rental(supermanMovie, 1));
    assertEquals(getResource("multiple-rentals.txt"), customer.statement());
  }

  @Test
  public void statementMultipleRentalsWithBonus() throws IOException {
    customer.addRental(new Rental(jawsMovie, 5));
    customer.addRental(new Rental(upMovie, 4));
    customer.addRental(new Rental(supermanMovie, 3));
    assertEquals(getResource("multiple-rentals-bonus.txt"),
        customer.statement());
  }

  @Test
  public void testPriceCodeChange() throws IOException {
    customer.addRental(new Rental(jawsMovie, 3));
    // This should retroactively change the previous rental.
    jawsMovie.setPriceCode(Movie.NEW_RELEASE);
    customer.addRental(new Rental(jawsMovie, 3));
    assertEquals(getResource("price-code-change.txt"), customer.statement());
  }

  @Test
  public void statementLongRentals() throws IOException {
    customer.addRental(new Rental(jawsMovie, 10));
    customer.addRental(new Rental(upMovie, 10));
    customer.addRental(new Rental(supermanMovie, 10));
    assertEquals(getResource("long-rentals.txt"), customer.statement());
  }

}