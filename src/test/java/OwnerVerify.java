import Files.payLoad;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class OwnerVerify extends Token {


    @Test
    public void OwnerVerify() {

        RestAssured.baseURI = "http://dev.api.acurovet.com";

        response = given().headers("Authorization", Test1(), "Accept", ContentType.JSON, "Content-Type", "application/json").
                body(payLoad.postData()).
                when().post("/api/owner-app/v1/owner-verification").
                then().assertThat().statusCode(200).extract().response();
        String body = response.asString();
        System.out.println(body);
        JsonPath jp = new JsonPath(body);
        String OwnerId = jp.getString("owner[0].id");
        System.out.println(OwnerId);


    }

}
