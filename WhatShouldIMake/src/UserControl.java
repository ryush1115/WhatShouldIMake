import java.util.ArrayList;

import java.util.Scanner;

public class UserControl {
/***
 * The main class that runs the recipe API
 * @author Seung Hyun (Amy) Ryu and Lisa Friedmann
 * Please make sure you install the external Jars: java-json.jar, okhttp-3.9.0.jar, okio-1.13.0.jar 
 */

	public static void main(String[] args) {
		System.out.println("I'm here to help you decide what to make!");
				
		// initialize the UserControl class
		UserControl uc = new UserControl();
		// initialize scanner
		Scanner sc = new Scanner(System.in);
		// ask for user name to welcome
		System.out.println("Please input your name: ");
		String humanName = sc.next();
		System.out.println("Welcome " + humanName);
		
		// Run the "run" method while user wants to continue looking for recipes
		while (true) {
		// main method that controls the rounds of the recipe search
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
		// ask user input on ingredients
		System.out.println("Please enter 2 ingredients you have in the fridge (comma separated list e.g. chicken, bean): ");
		String ingredientInput = sc.next();
		ingredientInput += sc.nextLine();
		String[] ingredients = ingredientInput.split(",");
		String ingredient1 = ingredients[0];
		String ingredient2 = ingredients[1];
		
		// make the API call
		ApiCall obj = new ApiCall();
        ArrayList<Recipe> recipeResults = obj.sendGet(ingredient1, ingredient2);
		if (recipeResults.size() == 0) {
			System.out.println("There are no recipes that meet your ingredient criteria");
		}
		
		else {
	        // initialize FilterRecipes
	        FilterRecipes fr = new FilterRecipes();
	        
	        // filter for health preferences
	        ArrayList<String> healthLabelsList = fr.createLabelsList(recipeResults, "health");
	        System.out.println("Please select all health restrictions out of the following (comma separated list): " + healthLabelsList);
	        System.out.println("Type \"None\" for all recipes");
	        String healthInput = sc.next();
	        healthInput += sc.nextLine();
	        String[] healthPreference = healthInput.split(",");
	        ArrayList<Recipe> healthFilteredRecipeList = fr.filterRecipes(recipeResults, "health", healthPreference);
	        
	        
	        // filter for dietary preferences
	        ArrayList<String> dietLabelsList = fr.createLabelsList(healthFilteredRecipeList, "diet");
	        System.out.println("Please select one dietary option out of the following: " + dietLabelsList);
	        System.out.println("Type \"None\" for all recipes");
	        String dietaryInput = sc.next();
	        String[] dietaryPreference = dietaryInput.split(",");
	        ArrayList<Recipe> dietFilteredRecipeList = fr.filterRecipes(healthFilteredRecipeList, "diet", dietaryPreference);
	        
	        // printing the final recipe result
	        System.out.println("Printing your recipe suggestion. Check your computer for a UI tab that has opened!");
	        System.out.println("--------------------");
	        System.out.println(dietFilteredRecipeList.get(0).getName());
	        // call the GUI class to publish UI
	        GUI gui = new GUI(dietFilteredRecipeList.get(0).getSourceUrl());
	        gui.runGUI(dietFilteredRecipeList.get(0).getName(), dietFilteredRecipeList.get(0).getIngredients(), dietFilteredRecipeList.get(0).getImageUrl());
			
	        // initialize the UserControl class
	     	UserControl uc2 = new UserControl();
	     	
	     	// ask if the user wants to see more recipes with the same ingredients/ criteria
	        int counter = 1;
	        while (true) {
	     	// continue running until the user says no or we run out of recipes with the same ingredients/ criteria
	     		
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
	     		GUI gui1 = new GUI(dietFilteredRecipeList.get(counter).getSourceUrl());
	            gui1.runGUI(dietFilteredRecipeList.get(counter).getName(), dietFilteredRecipeList.get(counter).getIngredients(), dietFilteredRecipeList.get(counter).getImageUrl());
	     		counter += 1;
	     		
	     	}
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
	}



}
