import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.Arrays.asList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserControlTest {
	ApiCall obj = new ApiCall();
	String ingredient1 = "chicken";
	String ingredient2 = "bean";
	
    ArrayList<Recipe> recipeResults = obj.sendGet(ingredient1, ingredient2);
		
	UserControl uc;
	
	@BeforeEach
	void setUp() throws Exception {
		this.uc = new UserControl();
	}

	@Test
	void askYesNoTest() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println(this.uc.askYesNo(sc));
		assertEquals("Chicken & bean enchiladas", this.recipeResults.get(0).getName());
		assertEquals("Spicy Chicken & Bean Wrap", this.recipeResults.get(2).getName());
	}
	
	
}
