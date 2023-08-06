package services;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class GetProduct {
    @Test
    public void getSpecificProduct(String productName){
        String product= "products";
        RequestSpecification request = RestAssured.given();
        Response response= request.log().all().
                when()
                .queryParam("name", productName)
                .get("https://606365ea0133350017fd3303.mockapi.io/"+ product);
        response.then().statusCode(200).time(lessThan(4000L)).
                body("name",contains(productName)).
                body("stock", contains(3)).
                log().all();

    }


    @DataProvider(name ="dataProvider")
    public Object[][] dataProvider(){
            return new Object[][]{
            {"çilek","products",200},
            {"Cerenay Coşkun","products",200},
            {"Refined Wooden Fish","uyııuhy",404}

        };
    }
        @Test(dataProvider = "dataProvider")
        public void getSpecificProductWithDifferentTestDaatas(String productName, String product, int statuscode){
           // String productName="Refined Wooden Fish";
          //  String product= "products";
            RequestSpecification request = RestAssured.given();
            Response response= request.log().all().
                    when()
                    .queryParam("name", productName)
                    .get("https://606365ea0133350017fd3303.mockapi.io/"+ product);
            response.then().statusCode(statuscode).time(lessThan(4000L)).
                    log().all();

        }

    }
