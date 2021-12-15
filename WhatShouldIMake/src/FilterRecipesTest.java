import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static java.util.Arrays.asList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertArrayEquals;

class FilterRecipesTest {
	ApiCall obj = new ApiCall();
	String ingredient1 = "chicken";
	String ingredient2 = "bean";
	
    ArrayList<Recipe> recipeResults = obj.sendGet(ingredient1, ingredient2);
		
	FilterRecipes fr;
	
	@BeforeEach
	void setUp() throws Exception {
		this.fr = new FilterRecipes();
	}

	@Test
	void createLabelsListTest() {
		assertEquals(3, this.fr.createLabelsList(recipeResults, "diet").size());
		assertEquals(3, this.fr.createLabelsList(recipeResults, "health").size());
		ArrayList<String> categories = new ArrayList<String>(Arrays.asList("Dairy-Free, Gluten-Free, Peanut-Free"));
		System.out.println(categories);
		System.out.println(this.fr.createLabelsList(recipeResults, "health"));
		assertTrue(categories.equals(this.fr.createLabelsList(recipeResults, "health")));
	}
	
	@Test
	void filterRecipesTest() {
		String[] healthList = {"Dairy-Free", "Gluten-Free"};
		assertEquals(8, fr.filterRecipes(recipeResults, "health", healthList).size());
		String[] dietList = {"Low-Carb"};
		assertEquals(1, fr.filterRecipes(recipeResults, "diet", dietList).size());
	}
		

}
