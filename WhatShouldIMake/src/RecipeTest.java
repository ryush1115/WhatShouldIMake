import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static java.util.Arrays.asList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecipeTest {
	ApiCall obj = new ApiCall();
	String ingredient1 = "chicken";
	String ingredient2 = "bean";
	
    ArrayList<Recipe> recipeResults = obj.sendGet(ingredient1, ingredient2);
		
	Recipe recipe;
	
	@BeforeEach
	void setUp() throws Exception {
		this.recipe = new Recipe();
	}

	@Test
	void getNameTest() {
		assertEquals("Chicken & bean enchiladas", this.recipeResults.get(0).getName());
		assertEquals("Spicy Chicken & Bean Wrap", this.recipeResults.get(2).getName());
	}
	
	@Test
	void getImageUrlTest() {
		assertEquals("https://www.edamam.com/web-img/6bd/6bde8749b7c9c88c1049696324502701.jpg", this.recipeResults.get(0).getImageUrl());
		assertEquals("https://www.edamam.com/web-img/297/2971cc81ed291d7286679974f4689ae9.JPG", this.recipeResults.get(1).getImageUrl());
	}
	
	@Test
	void getSourceUrlTest() {
		assertEquals("http://www.bbcgoodfood.com/recipes/11259/chicken-and-bean-enchiladas", this.recipeResults.get(0).getSourceUrl());
		assertEquals("https://food52.com/recipes/33757-chicken-bean-kale-soup", this.recipeResults.get(1).getSourceUrl());
	}
	
	@Test
	void getIngredientsTest() {
		assertEquals(26, Arrays.deepToString(this.recipeResults.get(0).getIngredients()).split(",").length);
		String[] ingredientsList = this.recipeResults.get(0).getIngredients();
		assertTrue(ingredientsList[0].contains("olive oil"));
		assertTrue(ingredientsList[1].contains("red onions"));
		assertTrue(ingredientsList[2].contains("red pepper"));

		assertEquals(35, Arrays.deepToString(this.recipeResults.get(1).getIngredients()).split(",").length);
		String[] ingredientsList2 = this.recipeResults.get(1).getIngredients();
		assertTrue(ingredientsList2[0].contains("chicken broth"));
		assertTrue(ingredientsList2[1].contains("olive oil"));
	}
	
	@Test
	void getDietLabelsTest() {
		assertEquals(1, Arrays.deepToString(this.recipeResults.get(0).getDietLabels()).split(",").length);
		assertTrue(this.recipeResults.get(0).getDietLabels()[0].contains("High-Fiber"));
		
		assertEquals(2, Arrays.deepToString(this.recipeResults.get(5).getDietLabels()).split(",").length);
		assertTrue(this.recipeResults.get(5).getDietLabels()[1].contains("Low-Carb"));
		
		assertEquals(1, Arrays.deepToString(this.recipeResults.get(7).getDietLabels()).split(",").length);
		assertTrue(this.recipeResults.get(7).getDietLabels()[0].contains("Balanced"));
		
	}
	
	@Test
	void getHealthLabelsTest() {
		assertEquals(17, Arrays.deepToString(this.recipeResults.get(0).getHealthLabels()).split(",").length);
		assertTrue(this.recipeResults.get(0).getHealthLabels()[0].contains("Gluten-Free"));
		assertTrue(this.recipeResults.get(0).getHealthLabels()[1].contains("Wheat-Free"));
		assertTrue(this.recipeResults.get(0).getHealthLabels()[2].contains("Egg-Free"));
		
		assertEquals(13, Arrays.deepToString(this.recipeResults.get(1).getHealthLabels()).split(",").length);
		assertTrue(this.recipeResults.get(1).getHealthLabels()[0].contains("Egg-Free"));
		assertTrue(this.recipeResults.get(1).getHealthLabels()[1].contains("Peanut-Free"));
		assertTrue(this.recipeResults.get(1).getHealthLabels()[2].contains("Tree-Nut-Free"));
		
	}

}
