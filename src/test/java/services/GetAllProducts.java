package services;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.lessThan;

public class GetAllProducts {
    @Test
    public void getProducts(){
        RequestSpecification request = RestAssured.given();
        Response response= request.log().all().
                when()
                .get("https://606365ea0133350017fd3303.mockapi.io/products");
        response.then().statusCode(200).time(lessThan(4000L)).log().all();
    }

}
