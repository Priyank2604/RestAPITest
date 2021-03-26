
import Files.Resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Token {
    public static Response response;
    public static String jsonAsString;
    public static Properties prop;

    @BeforeTest
    public void getData() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream("G:\\Automation_Projects\\RestAssuredAPITest\\src\\test\\java\\Files\\env.properties");
        prop.load(fis);

    }


    @Test
    public String Test1() {
//        RestAssured.baseURI="http://acuvet-api-dev.us-west-2.elasticbeanstalk.com";
        RestAssured.baseURI = prop.getProperty("HOST");
        response = given().param("client_id", 1).
                param("client_secret", prop.getProperty("CLIENT_SECRET")).
                param("grant_type", "client_credentials").
                when().post(Resources.passTokenResource()).
                /* Assert the response code 200 and expect the JSON response */
                        then().assertThat().statusCode(200).contentType(ContentType.JSON).
                /* put Assertion in the body response with the help
                of JSON Path travel and you can compare it with equalTo method */
                        body("token_type", equalTo("Bearer")).
                /* Validate from Header response*/
                        header("Server", "Apache")
                .extract().response();
        jsonAsString = response.asString();
//        System.out.println(jsonAsString);
        JsonPath jp = new JsonPath(jsonAsString);
        String accessToken = jp.getString("access_token");
        System.out.println(accessToken);
        return accessToken;
    }

}
