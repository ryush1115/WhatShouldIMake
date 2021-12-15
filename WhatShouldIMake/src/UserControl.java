import java.util.ArrayList;
import java.util.Scanner;

public class UserControl {

	public static void main(String[] args) {
		System.out.println("I'm here to help you decide what to make!");
				
		// initialize the UserControl class
		UserControl uc = new UserControl();
				
		// Run the "run" method while user wants to continue looking for recipes
		while (true) {
		// main method that controls the rounds of the recipe search
			Scanner sc = new Scanner(System.in);
			uc.run(sc);
					
			// ask if the user wants to start the entire process again
			System.out.println("--------------------");
			System.out.println("Do you want to find recipes with different ingredients?");
					
			boolean check = uc.askYesNo(sc);
			if (!check) {
				System.out.println("Good Bye!");
				sc.close();
				break;
			}
		}
	}
	
	public void run(Scanner sc) {
		
		System.out.println("Please input your name: ");
		String humanName = sc.next();
		System.out.println("Welcome " + humanName);
		
		System.out.println("Please enter 2 ingredients you have in the fridge (comma separated list e.g. chicken, bean): ");
		String ingredientInput = sc.next();
		ingredientInput += sc.nextLine();
		String[] ingredients = ingredientInput.split(",");
		String ingredient1 = ingredients[0];
		String ingredient2 = ingredients[1];
		System.out.println(ingredient1 + ingredient2);
		
		ApiCall obj = new ApiCall();
//		String ingredient1 = "chicken";
//		String ingredient2 = "bean";
//		
        ArrayList<Recipe> recipeResults = obj.sendGet(ingredient1, ingredient2);
		
        FilterRecipes fr = new FilterRecipes();
        
        // filter for health preferences
        ArrayList<String> healthLabelsList = fr.createLabelsList(recipeResults, "health");
        System.out.println("Please select all health restrictions out of the following (comma separated list): " + healthLabelsList);
        System.out.println("Type \"None\" for all recipes");
        String healthInput = sc.next();
        healthInput += sc.nextLine();
        System.out.println(healthInput);
        String[] healthPreference = healthInput.split(",");
        ArrayList<Recipe> healthFilteredRecipeList = fr.filterRecipes(recipeResults, "health", healthPreference);
        
        System.out.println("Printing filtered recipe list size");
		System.out.println(healthFilteredRecipeList.size());
		
        
        // filter for dietary preferences
        ArrayList<String> dietLabelsList = fr.createLabelsList(healthFilteredRecipeList, "diet");
        System.out.println("Please select one dietary option out of the following: " + dietLabelsList);
        System.out.println("Type \"None\" for all recipes");
        String dietaryInput = sc.next();
        String[] dietaryPreference = dietaryInput.split(",");
        ArrayList<Recipe> dietFilteredRecipeList = fr.filterRecipes(healthFilteredRecipeList, "diet", dietaryPreference);
        
        System.out.println("Printing filtered recipe list");
        System.out.println(dietFilteredRecipeList.size());
        System.out.println("--------------------");
        System.out.println(dietFilteredRecipeList.get(0).getName());
//        GUI.runGUI(dietFilteredRecipeList.get(0).getName(), dietFilteredRecipeList.get(0).getIngredients(), dietFilteredRecipeList.get(0).getSourceUrl(), dietFilteredRecipeList.get(0).getImageUrl());
		
        // initialize the UserControl class
     	UserControl uc2 = new UserControl();
     		
        int counter = 1;
        // Run the "run" method while user wants to look at different recipes
     	while (true) {
     	// main method that controls the rounds of the recipe search
     		
     		System.out.println("--------------------");
     		System.out.println("Do you want to find another recipe with the same ingredients?");
     					
     		boolean check = uc2.askYesNo(sc);
     		
     		if (counter == dietFilteredRecipeList.size()) {
     			System.out.println("There are no more recipes that meet your criteria");
     			break;
     		}
     		if (!check) {
     			System.out.println("Bon Appetit!");
     			break;
     		}
     		
     		System.out.println(dietFilteredRecipeList.get(counter).getName());
     		counter += 1;
     	}
	}
	
	/**
	 * If the user responds with a string "y" or "Y", the function returns True. 
	 * If the  user responds with a string "n" or "N", the function returns False. 
	 * Any other response will cause the question to be repeated until the user provides an acceptable response. 
	 * @param sc to use for getting user input
	 * @return true if user responds with "y" or "Y"
	 */
	public boolean askYesNo(Scanner sc) {
		// get user input
		String yesNo = sc.next();
		// take the first character and convert to lower case
		char ch1 = yesNo.charAt(0);
		char ch1Lower = Character.toLowerCase(ch1);
		
		if (ch1Lower == 'y') {
			return true;
		} else {
			return false;
		} 
	}	
	
	public UserControl() {
		// TODO Auto-generated constructor stub
	}



}
