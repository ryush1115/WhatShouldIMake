import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static java.util.Arrays.asList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ApiCallTest {
	ApiCall obj;
	String ingredient1 = "chicken";
	String ingredient2 = "bean";
	String ingredient3 = "tomato";
	String ingredient4 = "apple";
	String ingredient5 = "truffles";
	
//    ArrayList<Recipe> recipeResults = obj.sendGet(ingredient1, ingredient2);
//		
//	Recipe recipe;
//	
	@BeforeEach
	void setUp() throws Exception {
		this.obj = new ApiCall();
	}

	@Test
	void getGetTest() {
		assertEquals(10, this.obj.sendGet(this.ingredient1, this.ingredient2).size());
		assertEquals(10, this.obj.sendGet(this.ingredient1, this.ingredient3).size());
		assertEquals(10, this.obj.sendGet(this.ingredient1, this.ingredient4).size());
		assertEquals(10, this.obj.sendGet(this.ingredient1, this.ingredient5).size());
		assertEquals(10, this.obj.sendGet(this.ingredient2, this.ingredient3).size());
	}
	


}
