import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FilterRecipesTest {
	ApiCall obj = new ApiCall();
	String ingredient1 = "chicken";
	String ingredient2 = "bean";
	// initialize recipeResults to be used throughout
    ArrayList<Recipe> recipeResults = obj.sendGet(ingredient1, ingredient2);
		
	FilterRecipes fr;
	
	@BeforeEach
	void setUp() throws Exception {
		this.fr = new FilterRecipes();
	}

	@Test
	void createLabelsListTest() {
		// assert that the size of the labels list are correct
		assertEquals(3, this.fr.createLabelsList(recipeResults, "diet").size());
		assertEquals(3, this.fr.createLabelsList(recipeResults, "health").size());
		// assert that the labels list contains the right values
		assertTrue(this.fr.createLabelsList(recipeResults, "health").get(0).contains("Dairy-Free"));
		assertTrue(this.fr.createLabelsList(recipeResults, "health").get(1).contains("Gluten-Free"));
		assertTrue(this.fr.createLabelsList(recipeResults, "health").get(2).contains("Peanut-Free"));
		assertTrue(this.fr.createLabelsList(recipeResults, "diet").get(0).contains("Low-Carb"));
		assertTrue(this.fr.createLabelsList(recipeResults, "diet").get(1).contains("High-Fiber"));
		assertTrue(this.fr.createLabelsList(recipeResults, "diet").get(2).contains("Balanced"));
	}
	
	@Test
	void filterRecipesTest() {
		// assert that the filtered recipes are the right size
		String[] healthList = {"Dairy-Free", "Gluten-Free"};
		assertEquals(8, fr.filterRecipes(recipeResults, "health", healthList).size());
		String[] healthList2 = {"Dairy-Free"};
		assertEquals(3,fr.filterRecipes(recipeResults, "health", healthList2).size());
		String[] healthList3 = {"Gluten-Free"};
		assertEquals(5,fr.filterRecipes(recipeResults, "health", healthList3).size());
		
		String[] dietList = {"Low-Carb"};
		assertEquals(1, fr.filterRecipes(recipeResults, "diet", dietList).size());
	}
		

}
