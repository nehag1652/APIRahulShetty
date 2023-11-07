package API_RahulShetty.API_RahulShetty;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class PriceValidation {

	@Test
	public void sumOfCourses() {
		int sum = 0;
		JsonPath js = new JsonPath(Payload.coursePrice());
		int count = js.getInt("courses.size()");
		System.out.println("Courses count is:" + count);

		// Verify if Sum of all Course prices matches with Purchase Amount
		for (int i = 0; i < count; i++) {

			int coursePrice = js.getInt("courses[" + i + "].price");
			int copies = js.getInt("courses[" + i + "].copies");
			sum = sum + copies * coursePrice;

		}
		System.out.println(sum);

		int purchaseAmt = js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum, purchaseAmt);

	}

}
