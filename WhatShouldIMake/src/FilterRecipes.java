import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FilterRecipes {

	public FilterRecipes() {};
	
	public ArrayList<String> createLabelsList(ArrayList<Recipe> recipes, String labelType) {
		ArrayList<String> labelsList = new ArrayList<String>();
		String[] labelsStringList;
		List<String> relevantHealthLabels = new ArrayList<String>(Arrays.asList("Dairy-Free", "Gluten-Free", "Keto-Friendly", "Peanut-Free", "Vegan", "Vegetarian"));
		for(int i=0; i< recipes.size(); i++) {
			if(labelType.equals("diet")) {
				labelsStringList = recipes.get(i).getDietLabels();
				for(int j =0; j<labelsStringList.length; j++) {
	        		labelsList.add(labelsStringList[j].replace("\"", ""));        		
	        	}
			}
			else {
				labelsStringList = recipes.get(i).getHealthLabels();
				
				for(int j =0; j<labelsStringList.length; j++) {
	        		if (relevantHealthLabels.contains(labelsStringList[j])){
	            		labelsList.add(labelsStringList[j].replace("\"", ""));
	        		}        		
	        	}
			}
		}
		
		Set<String> set = new HashSet<>(labelsList);
    	labelsList.clear();
    	labelsList.addAll(set);
    	labelsList.removeAll(Arrays.asList("", null));
    	
		return labelsList;
	}
	
	public ArrayList<Recipe> filterRecipes(ArrayList<Recipe> recipes, String labelType, String[] selectedLabels){
		ArrayList<Recipe> filteredRecipeList = new ArrayList<Recipe>();
		String[] recipeLabelsStringList;
		if (selectedLabels[0].equals("None")) {
			filteredRecipeList = recipes;
		}
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
