
public class Recipe {
    private String name;
    private String imageUrl;
    private String sourceUrl;
    private String[] ingredients;
    private String[] dietLabels;
    private String[] healthLabels;
    private String pushId;

    public Recipe() {};

    public Recipe(String name, String imageUrl, String sourceUrl, String ingredients, String dietLabels, String healthLabels) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.sourceUrl = sourceUrl;
        this.ingredients = ingredients.replaceAll("\\[","").replaceAll("\\]","").replaceAll("\\\\","").split("\",\"");
        this.dietLabels= dietLabels.replaceAll("\\[","").replaceAll("\\]","").replaceAll("\\\\","").split("\",\"");
        this.healthLabels= healthLabels.replaceAll("\\[","").replaceAll("\\]","").replaceAll("\\\\","").split("\",\"");
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public String[] getDietLabels() {
        return dietLabels;
    }
    
    public String[] getHealthLabels() {
        return healthLabels;
    }
    
    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }


}
