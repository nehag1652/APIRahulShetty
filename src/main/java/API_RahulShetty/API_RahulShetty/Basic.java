package API_RahulShetty.API_RahulShetty;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.Payload;

public class Basic extends ReusableCode {

	public static void main(String[] args) {

		// post method
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String res = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(Payload.addPlace()).when().post("maps/api/place/add/json").then().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)").extract().response()
				.asString();

		System.out.println(res);

		// parsing the response
		JsonPath js = new JsonPath(res);
		String placeID = js.getString("place_id");
		System.out.println(placeID);

		// put method(update a place)
		String newPlace = "70 Summer walk, US";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + placeID + "\",\r\n" + "\"address\":\"" + newPlace + "\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}\r\n" + "")
				.when().put("maps/api/place/update/json").then().assertThat().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

		// Get Place & validate response

		String placeRes = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID)

				.when().get("/maps/api/place/get/json").then().assertThat().statusCode(200).extract().response()
				.asString();

		JsonPath js1 = ReusableCode.rawToJson(placeRes);
		String actualAddress = js1.getString("address");
		System.out.println(actualAddress);
		Assert.assertEquals(actualAddress, newPlace);

	}

}
