import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ApiCall {
	
	public ApiCall() {};
	
	//public static final String TAG = ApiCall.class.getSimpleName();
	private final OkHttpClient httpClient = new OkHttpClient();
	
//	public static void main(String[] args) throws Exception {
//		ApiCall obj = new ApiCall();
//
//        System.out.println("Testing 1 - Send Http GET request");
//        obj.sendGet("chicken", "bean");
//	}
//	
	
	public ArrayList<Recipe> sendGet(String ingredient1, String ingredient2) {
        
        String APP_KEY = ConstantKeys.APP_KEY;
        String APP_ID = ConstantKeys.APP_ID;
        String ingredients = (ingredient1 + "," + ingredient2).replaceAll("\\s","");
        HttpUrl.Builder urlBuilder = HttpUrl.parse(ConstantKeys.BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(ConstantKeys.QUERY_PARAMETER, ingredients);
        urlBuilder.addQueryParameter(ConstantKeys.APP_QUERY_PARAMETER, APP_ID);
        urlBuilder.addQueryParameter(ConstantKeys.KEY_QUERY_PARAMETER, APP_KEY);
        String url = urlBuilder.build().toString();
        
        Request request = new Request.Builder()
                .url(url)
                .build();
        
        Response response = null;
		try {
			response = httpClient.newCall(request).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ArrayList<Recipe> recipeResults = processResults(response);
        return recipeResults;
    }

    public ArrayList<Recipe> processResults(Response response) {
        ArrayList<Recipe> recipes = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject returnJSON = new JSONObject(jsonData);
                JSONArray recipesJSON = returnJSON.getJSONArray("hits");
                for (int i = 0; i < 10; i++) {
                    JSONObject recipeArrayJSON = recipesJSON.getJSONObject(i);
                    JSONObject recipeJSON = recipeArrayJSON.getJSONObject("recipe");
                    String name = recipeJSON.getString("label");
                    String imageUrl = recipeJSON.getString("image");
                    String sourceUrl = recipeJSON.getString("url");
                    String ingredients = recipeJSON.getJSONArray("ingredientLines").toString();
                    String dietLabels = recipeJSON.getJSONArray("dietLabels").toString();
                    String healthLabels = recipeJSON.getJSONArray("healthLabels").toString();

                    Recipe recipe = new Recipe (name, imageUrl, sourceUrl, ingredients, dietLabels, healthLabels);
                    recipes.add(recipe);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        
//        System.out.println(recipes);
//        
//        System.out.println(recipes.get(0).getName());
//        System.out.println(recipes.get(0).getSourceUrl());
//        System.out.println(Arrays.deepToString(recipes.get(0).getIngredients()));
//        System.out.println(Arrays.deepToString(recipes.get(0).getDietLabels()));
//        System.out.println(Arrays.deepToString(recipes.get(0).getHealthLabels()));
//        
        return recipes;
    }

}
