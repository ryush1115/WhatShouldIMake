import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FilterRecipes {

	public FilterRecipes() {};
	
	public ArrayList<String> createLabelsList(ArrayList<Recipe> recipes, String labelType) {
		ArrayList<String> labelsList = new ArrayList<String>();
		String[] labelsStringList;
		for(int i=0; i< recipes.size(); i++) {
			if(labelType.equals("diet")) {
				labelsStringList = recipes.get(i).getDietLabels();
			}
			else {
				labelsStringList = recipes.get(i).getHealthLabels();
			}
        	for(int j =0; j<labelsStringList.length; j++) {
        		labelsList.add(labelsStringList[j].replace("\"", ""));
        		
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
		return filteredRecipeList;
		
	}
		
}
