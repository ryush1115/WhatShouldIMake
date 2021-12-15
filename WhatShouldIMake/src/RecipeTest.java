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
		System.out.println(Arrays.deepToString(this.recipeResults.get(0).getIngredients()).split(",").length);
		assertEquals(26, Arrays.deepToString(this.recipeResults.get(0).getIngredients()).split(",").length);
		String[] ingredientsList = this.recipeResults.get(0).getIngredients();
		//System.out.println(ingredientsList);
		assertTrue(ingredientsList[0].contains("olive oil"));
		assertTrue(ingredientsList[1].contains("red onions"));
		assertTrue(ingredientsList[2].contains("red pepper"));

		System.out.println(Arrays.deepToString(this.recipeResults.get(1).getIngredients()).split(",").length);
		assertEquals(35, Arrays.deepToString(this.recipeResults.get(1).getIngredients()).split(",").length);
		String[] ingredientsList2 = this.recipeResults.get(1).getIngredients();
		//System.out.println(Arrays.deepToString(ingredientsList2));
		assertTrue(ingredientsList2[0].contains("chicken broth"));
		assertTrue(ingredientsList2[1].contains("olive oil"));
//		assertTrue(ingredientsList2[2].contains("onion"));
//		assertTrue(ingredientsList2[0].contains("chicken broth"));
//		assertTrue(ingredientsList2[1].contains("olive oil"));
//		assertTrue(ingredientsList2[2].contains("onion"));

	}
	
	@Test
	void getDietLabelsTest() {
		System.out.println(Arrays.deepToString(this.recipeResults.get(0).getDietLabels()).split(",").length);
		assertEquals(1, Arrays.deepToString(this.recipeResults.get(0).getDietLabels()).split(",").length);
		System.out.println(Arrays.deepToString(this.recipeResults.get(0).getDietLabels()));
		System.out.println(Arrays.deepToString(this.recipeResults.get(0).getDietLabels()).getClass());
		String[] dietLabel = {"\"High-Fiber\""};
		System.out.println(Arrays.deepToString(this.recipeResults.get(0).getDietLabels()).getClass());
		//assertEquals(dietLabel, this.recipeResults.get(0).getDietLabels());
		
	}
	
	@Test
	void getHealthLabelsTest() {
		
	}

}
