package API_RahulShetty.API_RahulShetty;

import io.restassured.path.json.JsonPath;

public class ReusableCode {
	
	public static JsonPath rawToJson(String response) {
		JsonPath js1=new JsonPath(response);
		return js1;

	}

}
