import Files.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Example1 {

    public static void main(String arg[]){
        RestAssured.baseURI = "https://reqres.in/";
        String response = given()
                .header("Content-Type","application/json")
                .body(Payload.addPlace())
                .when().post("api/users")
                .then()
                .assertThat()
                .statusCode(201)
                .body("name", equalTo("Shivam Singh"))
                .header("Server","cloudflare")
                .extract().response().asString();

        System.out.println(response);

       // JsonPath js = new JsonPath(response);   //Converting the string response in json so that , we can fetch each value
        JsonPath js = ReusableMethods.rawToJson(response);

        String id =  js.getString("id"); //Fetch the id
        System.out.println("id = "+id);

        //Update job on fetched id.
        given()
                .header("Content-Type","application/json")
                .body("{\n" +
                        "    \"name\": \"Shivam\",\n" +
                        "    \"job\": \"JavaDeveloper\"\n" +
                        "}")
                .when().put("api/users/"+id)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }
}
