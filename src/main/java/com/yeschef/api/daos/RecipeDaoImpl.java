package com.yeschef.api.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.yeschef.api.models.Recipe;

public class RecipeDaoImpl implements RecipeDao {

	private Connection connection = MariaDbUtil.getConnection();
	

	private static String selectAllRecipes = 
			"SELECT * "
			+ "FROM recipes";

	private static String selectRecipesById = 
			"SELECT * " + 
			"FROM recipes "
			+ "WHERE id = ?";
	
	private static String deleteRecipeById = 
			"DELETE FROM recipes " 
			+ "WHERE id = ?";
	
	private static String selectRecipesByName = 
			"SELECT * "
			+ "FROM recipes "
			+ "WHERE name LIKE ?";
	
	//convenience method to make a list of recipes for each method
	private List<Recipe> makeRecipe(ResultSet result) throws SQLException {
		List<Recipe> myRecipes = new ArrayList<Recipe>();

		while (result.next()) {

			Recipe recipe = new Recipe();
			recipe.setId(result.getInt("id"));
			recipe.setName(result.getString("name"));
			recipe.setSpoonacularRecipeId(result.getInt("spoonacularRecipeId"));
			recipe.setImageUrl(result.getString("imageUrl"));
			recipe.setUpdateDateTime(result.getObject("updateDateTime", LocalDateTime.class));
			recipe.setCreateDateTime(result.getObject("createDateTime", LocalDateTime.class));

			myRecipes.add(recipe);
		}

		return myRecipes;
	}
	
	@Override
	public List<Recipe> getRecipes() {
		List<Recipe> myRecipes = new ArrayList<Recipe>();
		ResultSet result = null;
		Statement statement = null;

		try {
			statement = connection.createStatement();
			result = statement.executeQuery(selectAllRecipes);
			myRecipes = makeRecipe(result);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return myRecipes;
	}

	@Override
	public List<Recipe> getRecipesById(Integer id) {
		List<Recipe> myRecipes = new ArrayList<Recipe>();
		ResultSet result = null;
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(selectRecipesById);
			statement.setInt(1, id);

			result = statement.executeQuery();
			myRecipes = makeRecipe(result);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return myRecipes;
	}

	@Override
	public Recipe deleteRecipeById(Integer id) {
				
		List<Recipe> recipes = this.getRecipesById(id); 
		Recipe recipeToDelete = null;
		
		if (recipes.size() > 0) {
			recipeToDelete = recipes.get(0);
			int updateRowCount = 0;
			PreparedStatement ps = null;	
			try {
				ps = connection.prepareStatement(deleteRecipeById);
				ps.setInt(1, id);
				updateRowCount = ps.executeUpdate();
				System.out.println("rows deleted: " + updateRowCount);
			} catch (SQLException e) {
				e.printStackTrace(); 
			}
		}
		
		return recipeToDelete;
	}

	/*
	 * Need to figure out how to pass this method the searchTerm taken from the JS entered into a search bar or a drop down menu
	 */
	@Override
	public List<Recipe> searchRecipesByName(String searchTerm) {
		List<Recipe> myRecipes = new ArrayList<>();
		ResultSet result = null;
		PreparedStatement statement = null;
		
		try { 
			statement = connection.prepareStatement(selectRecipesByName); 
			statement.setString(1, "%" + searchTerm + "%");
			
			result = statement.executeQuery(); 
			myRecipes = makeRecipe(result);
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return myRecipes;
	}

	//this method will need to be fed data from spoonacular 
	@Override
	public Recipe createRecipe(Recipe recipe) {
		try (PreparedStatement preparedStatement = connection.prepareStatement(
				"INSERT INTO recipes (name, spoonacularRecipeId, imageUrl) VALUES (?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setString(1, recipe.getName());
			preparedStatement.setInt(2, recipe.getSpoonacularRecipeId());
			preparedStatement.setString(3, recipe.getImageUrl());
			preparedStatement.executeUpdate();

			// Retrieve the auto-generated ID
			try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					recipe.setId(generatedKeys.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
		return recipe;
	}

//	 @Override
//	    public Recipe createRecipe(Recipe recipe) {
//	        // First, call the Spoonacular API to get recipe data
//	        SpoonacularApiData apiData = callSpoonacularApi(recipe.getSpoonacularRecipeId());
//
//	        // Update the recipe object with data from the API response
//	        recipe.setName(apiData.getName());
//	        recipe.setImageUrl(apiData.getImageUrl());
//
//	        // Now, insert the recipe into the database
//	        try (PreparedStatement preparedStatement = connection.prepareStatement(
//	                "INSERT INTO recipes (name, spoonacularRecipeId, imageUrl) VALUES (?, ?, ?)",
//	                Statement.RETURN_GENERATED_KEYS)) {
//	            preparedStatement.setString(1, recipe.getName());
//	            preparedStatement.setInt(2, recipe.getSpoonacularRecipeId());
//	            preparedStatement.setString(3, recipe.getImageUrl());
//	            preparedStatement.executeUpdate();
//
//	            // Retrieve the auto-generated ID
//	            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
//	                if (generatedKeys.next()) {
//	                    recipe.setId(generatedKeys.getInt(1));
//	                }
//	            }
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
//
//	        return recipe;
//	    }
//
//	    private SpoonacularApiData callSpoonacularApi(int spoonacularRecipeId) {
//	        // Implement logic to make HTTP request to Spoonacular API
//	        // Parse the API response and return relevant data
//	        // You might use a library like HttpClient or HttpURLConnection for this
//
//	        // For example, you might create a class SpoonacularApiService that handles API requests
//	        // SpoonacularApiService apiService = new SpoonacularApiService();
//	        // return apiService.getRecipeData(spoonacularRecipeId);
//
//	        // For simplicity, let's assume a placeholder implementation
//	        return new SpoonacularApiData("Spaghetti Bolognese", "https://example.com/spaghetti.jpg");
//	    }
//	}
	
	
	
	
}

   
