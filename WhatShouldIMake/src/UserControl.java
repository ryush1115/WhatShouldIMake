import java.util.ArrayList;
import java.util.Scanner;

public class UserControl {

	public static void main(String[] args) {
		System.out.println("I'm here to help you decide what to make!");
				
		// initialize the UserControl class
		UserControl uc = new UserControl();
		Scanner sc = new Scanner(System.in);
		
		// ask the user for their name to welcome them
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
		// ask for user input on ingredients
		System.out.println("Please enter 2 ingredients you have in the fridge (comma separated list e.g. chicken, bean): ");
		String ingredientInput = sc.next();
		ingredientInput += sc.nextLine();
		String[] ingredients = ingredientInput.split(",");
		String ingredient1 = ingredients[0];
		String ingredient2 = ingredients[1];
		
		// start a new API call
		ApiCall obj = new ApiCall();
		ArrayList<Recipe> recipeResults = obj.sendGet(ingredient1, ingredient2);
		
		// if the API call returns blank, tell the user that
		if(recipeResults.size() ==0) {
			System.out.println("There are no recipes that meet your criteria");
		}
		// otherwise continue forward
		else {
	        FilterRecipes fr = new FilterRecipes();
	        
	        // create a list of health labels
	        ArrayList<String> healthLabelsList = fr.createLabelsList(recipeResults, "health");
	        // ask the user for their health preferences
	        System.out.println("Please select all health restrictions out of the following (comma separated list): " + healthLabelsList);
	        System.out.println("Type \"None\" for all recipes");
	        String healthInput = sc.next();
	        healthInput += sc.nextLine();
	        String[] healthPreference = healthInput.split(",");
	        // filter the recipe list based on user's health preferences
	        ArrayList<Recipe> healthFilteredRecipeList = fr.filterRecipes(recipeResults, "health", healthPreference);
	       
	        // create a list of dietary labels
	        ArrayList<String> dietLabelsList = fr.createLabelsList(healthFilteredRecipeList, "diet");
	        // ask the user for their dietary preferences
	        System.out.println("Please select one dietary option out of the following: " + dietLabelsList);
	        System.out.println("Type \"None\" for all recipes");
	        String dietaryInput = sc.next();
	        String[] dietaryPreference = dietaryInput.split(",");
	        // filter the recipe list based on user's dietary preferences
	        ArrayList<Recipe> dietFilteredRecipeList = fr.filterRecipes(healthFilteredRecipeList, "diet", dietaryPreference);
	        
	        System.out.println("Printing your suggested recipe");
	        System.out.println("--------------------");
	        if (dietFilteredRecipeList.size() == 0) {
	        	System.out.println("There are no recipes that match your criteria");
	        }
	        else {
	        	System.out.println(dietFilteredRecipeList.get(0).getName());
	    		
	            // initialize the UserControl class
	         	UserControl uc2 = new UserControl();
	         		
	            int counter = 1;
	            // run while the user wants to keep looking at recipes with the same ingredients
	         	while (true) {
	         		System.out.println("--------------------");
	         		System.out.println("Do you want to find another recipe with the same ingredients?");
	         		// does the user want to see more recipes?
	         		boolean check = uc2.askYesNo(sc);
	         		// check for cases where there are no more recipes with the relevant criteria
	         		if (counter == dietFilteredRecipeList.size()) {
	         			System.out.println("There are no more recipes that meet your criteria");
	         			break;
	         		}
	         		// if the user doesn't want to see more recipes, tell them to enjoy the recipe!
	         		if (!check) {
	         			System.out.println("Bon Appetit!");
	         			break;
	         		}
	         		// this is the new recipe
	         		System.out.println(dietFilteredRecipeList.get(counter).getName());
	         		counter += 1;
	         	}
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
