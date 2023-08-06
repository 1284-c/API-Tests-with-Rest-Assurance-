package services;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import modals.ProductRequest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.startsWith;

public class CreateNewProduct {
    String product_id;


    @BeforeTest
    public String postCreateProduct(){
        ProductRequest productRequest = new ProductRequest("çikolata",12.3,3);
        String requestbody = new Gson().toJson(productRequest);
        Response response = given().body(requestbody).
                contentType(ContentType.JSON).
                log().all().
                when().
                post("https://606365ea0133350017fd3303.mockapi.io/products");

        response.then().statusCode(201).
                body("name", startsWith("çiko")).
                body("stock",lessThan(10)).
                log().all();
        String jsonString = response.getBody().asString();
        String name = JsonPath.from(jsonString).getString("name");
        System.out.println("product name: " + name);

        product_id = response.jsonPath().getString("id");
        System.out.println("product_id: " + product_id);

        return name;
    }

    @Test
    public void deleteCreatedProduct(){
       // RestAssured.delete(name);
      given().
                log().all().
                when().
                delete("https://606365ea0133350017fd3303.mockapi.io/products/" + product_id).
                then().
                statusCode(200);


    }
}
