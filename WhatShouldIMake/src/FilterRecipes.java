import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***
 * The FilterRecipes class filters down the returned recipe list based on
 * the health and dietary preferences of the user
 * @author amyry
 *
 */
public class FilterRecipes {
	/***
	 * initialize constructor
	 */
	public FilterRecipes() {};
	/***
	 * creates an arraylist of the relevant health and dietary labels to ask the user
	 * @param recipes
	 * @param labelType
	 * @return
	 */
	public ArrayList<String> createLabelsList(ArrayList<Recipe> recipes, String labelType) {
		// initialize variables
		ArrayList<String> labelsList = new ArrayList<String>();
		String[] labelsStringList;
		// only include health labels that are part of this list (there are a lot more health labels that are super niche/ not relevant
		List<String> relevantHealthLabels = new ArrayList<String>(Arrays.asList("Dairy-Free", "Gluten-Free", "Keto-Friendly", "Peanut-Free", "Vegan", "Vegetarian"));
		// loop through the entire recipes list
		for(int i=0; i< recipes.size(); i++) {
			// if its a diet label, just add to the list of the diet labels
			if(labelType.equals("diet")) {
				labelsStringList = recipes.get(i).getDietLabels();
				for(int j =0; j<labelsStringList.length; j++) {
	        		labelsList.add(labelsStringList[j].replace("\"", ""));        		
	        	}
			}
			// if its a health label, check that its relevant then add to the list of health labels
			else {
				labelsStringList = recipes.get(i).getHealthLabels();
				for(int j =0; j<labelsStringList.length; j++) {
	        		if (relevantHealthLabels.contains(labelsStringList[j])){
	            		labelsList.add(labelsStringList[j].replace("\"", ""));
	        		}        		
	        	}
			}
		}
		// change to set to remove duplicates
		Set<String> set = new HashSet<>(labelsList);
    	labelsList.clear();
    	labelsList.addAll(set);
    	// remove blank entries
    	labelsList.removeAll(Arrays.asList("", null));
    	
		return labelsList;
	}
	
	/***
	 * Filter the recipes down based on the user selected health or dietary labels
	 * @param recipes
	 * @param labelType health or diet
	 * @param selectedLabels user inputted
	 * @return
	 */
	public ArrayList<Recipe> filterRecipes(ArrayList<Recipe> recipes, String labelType, String[] selectedLabels){
		// initialize variables
		ArrayList<Recipe> filteredRecipeList = new ArrayList<Recipe>();
		String[] recipeLabelsStringList;
		// if the user has no dietary preferences, return all recipes
		if (selectedLabels[0].equals("None")) {
			filteredRecipeList = recipes;
		}
		// otherwise check if the recipe contains the diet/ health label that the user has specified
		else {
			for(int i=0; i< recipes.size(); i++) {
				if(labelType.equals("diet")) {
					recipeLabelsStringList = recipes.get(i).getDietLabels();
				}
				else {
					recipeLabelsStringList = recipes.get(i).getHealthLabels();
				}
				
	        	for(int j =0; j<recipeLabelsStringList.length; j++) {
	        		for(int k=0; k<selectedLabels.length; k++) {
	        			// if the kth element of the user inputted list is the same as the jth element of the given recipe's element list, then add to filtered recipes
	        			if(selectedLabels[k].replace("\"", "").equals(recipeLabelsStringList[j].replace("\"", ""))) {
	            			filteredRecipeList.add(recipes.get(i));
	            		};
	        		}
	        	}
			}
		}
		return filteredRecipeList;
		
	}
		
}
