import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/***
 * Class that makes the API call and formats the json file
 * @author amyry
 *
 */
public class ApiCall {
	/***
	 * constructor
	 */
	public ApiCall() {};
	// httpclient initialization
	private final OkHttpClient httpClient = new OkHttpClient();
	
	/***
	 * The sendGet function makes an API call based on the two ingredients specified
	 * This function also uses the processResults function to reformat the json file
	 * @param ingredient1
	 * @param ingredient2
	 * @return
	 */
	public ArrayList<Recipe> sendGet(String ingredient1, String ingredient2) {
		// call on the constant key values
        String APP_KEY = ConstantKeys.APP_KEY;
        String APP_ID = ConstantKeys.APP_ID;
        // create a string of the ingredients
        String ingredients = (ingredient1 + "," + ingredient2).replaceAll("\\s","");
        // construct a HTTP URL and add in the query parameters
        HttpUrl.Builder urlBuilder = HttpUrl.parse(ConstantKeys.BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(ConstantKeys.QUERY_PARAMETER, ingredients);
        urlBuilder.addQueryParameter(ConstantKeys.APP_QUERY_PARAMETER, APP_ID);
        urlBuilder.addQueryParameter(ConstantKeys.KEY_QUERY_PARAMETER, APP_KEY);
        String url = urlBuilder.build().toString();
        
        // send a request based on the URL parameter
        Request request = new Request.Builder()
                .url(url)
                .build();
        
        // initialize response
        Response response = null;
		try {
			response = httpClient.newCall(request).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// use the processResults function to cleanup the response
        ArrayList<Recipe> recipeResults = processResults(response);
        return recipeResults;
    }

	/***
	 * Cleans up the json file to output a cleaned up recipe ArrayList
	 * @param response
	 * @return
	 */
    public ArrayList<Recipe> processResults(Response response) {
    	// initialize a recipe ArrayList
    	ArrayList<Recipe> recipes = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
            	// initialize the JSON objects
                JSONObject returnJSON = new JSONObject(jsonData);
                JSONArray recipesJSON = returnJSON.getJSONArray("hits");
                for (int i = 0; i < 10; i++) {
                    JSONObject recipeArrayJSON = recipesJSON.getJSONObject(i);
                    JSONObject recipeJSON = recipeArrayJSON.getJSONObject("recipe");
                    // take elements of the JSON object and save to the relevant variables
                    String name = recipeJSON.getString("label");
                    String imageUrl = recipeJSON.getString("image");
                    String sourceUrl = recipeJSON.getString("url");
                    String ingredients = recipeJSON.getJSONArray("ingredientLines").toString();
                    String dietLabels = recipeJSON.getJSONArray("dietLabels").toString();
                    String healthLabels = recipeJSON.getJSONArray("healthLabels").toString();
                    	
                    Recipe recipe = new Recipe (name, imageUrl, sourceUrl, ingredients, dietLabels, healthLabels);
                    // repeat for all recipe values from the response call
                    recipes.add(recipe);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recipes;
    }

}
