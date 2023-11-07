package API_RahulShetty.API_RahulShetty;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class ParseComplexJSON {

	public static void main(String[] args) {
		JsonPath js = new JsonPath(Payload.coursePrice());

		// 1. Print No of courses returned by API
		int count = js.getInt("courses.size()");
		System.out.println("Courses count is:" + count);

		// 2.Print Purchase Amount

		int totalAmt = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmt);

		// 3. Print Title of the first course

		String firstCourseTitle = js.getString("courses[0].title");
		System.out.println(firstCourseTitle);

		// 4.Print All course titles and their respective Prices
		for (int i = 0; i < count; i++) {
			System.out.println(js.getString("courses[" + i + "].title"));
			System.out.println(js.getInt("courses[" + i + "].price"));
		}

		// 5. Print no of copies sold by RPA Course
		System.out.println("Print no of copies sold by RPA Course");
		for (int i = 0; i < count; i++) {
			String courseTitle = js.getString("courses[" + i + "].title");
			if (courseTitle.equalsIgnoreCase("RPA")) {
				int copies = js.getInt("courses[" + i + "].copies");
				System.out.println(copies);
				break;
			}

		}

		
	}

}
