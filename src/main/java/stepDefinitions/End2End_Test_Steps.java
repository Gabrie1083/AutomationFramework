package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import pages.model.request.AuthorizationRequest;
import pages.model.response.Book;
import pages.model.response.Books;
import pages.model.response.Token;

public class End2End_Test_Steps  {

    private static final String USER_ID = "9b5f49ab-eea9-45f4-9d66-bcf56a531b85";
    private static final String BASE_URL = "https://bookstore.toolsqa.com";

    private static Response response;
    private static Token tokenResponse;
    private static Book getAllBook;


    @Given("I am an authorized user")
    public void i_am_an_authorized_user() {
        // Write code here that turns the phrase above into concrete actions
        AuthorizationRequest authReq = new AuthorizationRequest("TOOLSQA-Test","Test@@123");

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        response = request.body(authReq).post("/Account/v1/GenerateToken");
        System.out.println(response.statusCode());
        System.out.println(response.asString());
        // Deserializing the Response body into tokenResponse
        tokenResponse = response.getBody().as(Token.class);
        System.out.println("Test..."+tokenResponse.token);
    }
    
    @Given("A list of books are available")
    public void a_list_of_books_are_available() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        response = request.get("/BookStore/v1/Books");
        ResponseBody body = response.getBody();
        // Deserializing the Response body into Books class
        //((RestAssuredResponseImpl) body).response().jsonPath().getList("books")
        Books book = response.getBody().as(Books.class);
        getAllBook = book.books.get(0);
        System.out.println(getAllBook);
    }

    @When("I add a book to my reading list")
    public void i_add_a_book_to_my_reading_list() {
        System.out.print("Add Test1");
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("the book is added")
    public void the_book_is_added() {
        // Write code here that turns the phrase above into concrete actions
   }

    @When("I remove a book from my reading list")
    public void i_remove_a_book_from_my_reading_list() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("the book is removed")
    public void the_book_is_removed() {
        // Write code here that turns the phrase above into concrete actions
    }
}
