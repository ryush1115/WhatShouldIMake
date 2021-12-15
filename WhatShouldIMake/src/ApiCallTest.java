import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/***
 * Test for the ApiCall class
 * @author amyry
 *
 */
class ApiCallTest {
	// initialize variables needed
	ApiCall obj;
	String ingredient1 = "chicken";
	String ingredient2 = "bean";
	String ingredient3 = "tomato";
	String ingredient4 = "apple";
	String ingredient5 = "truffles";
	
	@BeforeEach
	void setUp() throws Exception {
		this.obj = new ApiCall();
	}

	@Test
	void getGetTest() {
		// assert that the recipe call returns 10 recipes each
		assertEquals(10, this.obj.sendGet(this.ingredient1, this.ingredient2).size());
		assertEquals(10, this.obj.sendGet(this.ingredient1, this.ingredient3).size());
		assertEquals(10, this.obj.sendGet(this.ingredient1, this.ingredient4).size());
		assertEquals(10, this.obj.sendGet(this.ingredient1, this.ingredient5).size());
		assertEquals(10, this.obj.sendGet(this.ingredient2, this.ingredient3).size());
	}
}
