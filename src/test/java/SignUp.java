import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SignUp{

@Test
        public void signUp(){
//    Token token=new Token();
//    RestAssured.baseURI="http://dev.api.acurovet.com";
    given().
            body("{"+"\"email\":\"ejustin15@bosleo.com\"," +
            "\"password\": \"bosleo\" }").
            when().post("/api/owner-app/v1/login").
            then().assertThat().statusCode(200);






}

}
