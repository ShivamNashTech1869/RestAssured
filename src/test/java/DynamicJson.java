import Files.Payload;
import io.restassured.RestAssured;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DynamicJson {

    @Test(dataProvider = "UserDetail")
    public void addUser(String userName , String userJob){

        RestAssured.baseURI = "https://reqres.in/";
        String response = given().header("Content-Type","application/json").body(Payload.addThisPlace(userName,userJob))
                .when().post("api/users")
                .then()
                .assertThat()
                .statusCode(201)
                .body("name", equalTo(userName))
                .header("Server","cloudflare")
                .extract().response().asString();

        System.out.println(response);
    }

    @DataProvider(name = "UserDetail")
    public Object[][] getData(){
       return new Object[][] {{"Shivam","Knoldus Intern"},{"Shivam Singh","Tester"},{"Shivam","Automation Studio"}};
    }
}
