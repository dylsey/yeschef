package com.yeschef.api.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.yeschef.api.models.MealType;
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
	
	private static String selectRecipesByMealType = 
			"SELECT * " +
			"FROM recipes " +
		    "WHERE mealType = ?";		
	
	
	private static String deleteRecipeById = 
			"DELETE FROM recipes " 
			+ "WHERE id = ?";
	
	private static String selectRecipesByName = 
			"SELECT * "
			+ "FROM recipes "
			+ "WHERE name LIKE ?";
	
	private static String createRecipe =			
			"INSERT INTO recipes (name, recipeUrl, imageUrl) "
			+ "VALUES "
			+ "(?, ?, ?)";
	
	private static String selectNewRecipeId =
			"SELECT LAST_INSERT_ID() "
			+ "AS id";
			
	
	//convenience method to make a list of recipes for each method
	private List<Recipe> makeRecipe(ResultSet result) throws SQLException {
		List<Recipe> myRecipes = new ArrayList<Recipe>();
		while (result.next()) {

			Recipe recipe = new Recipe();
			recipe.setId(result.getInt("id"));
			recipe.setName(result.getString("name"));
			recipe.setRecipeUrl(result.getString("recipeUrl"));
			recipe.setImageUrl(result.getString("imageUrl"));
			
			String mealTypeString = result.getString("mealType");
			MealType mealType = MealType.convertStringtoMealType(mealTypeString);
			recipe.setMealType(mealType);
			
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
	public List<Recipe> getRecipesByMealType(MealType mealType) {
		List<Recipe> myRecipes = new ArrayList<Recipe>();
		ResultSet result = null;
		PreparedStatement statement = null;
		
		try {
			statement = connection.prepareStatement(selectRecipesByMealType);
			statement.setString(1, mealType.toString());
			
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


	@Override
	public Recipe createRecipe(Recipe newRecipe) {
		PreparedStatement statement = null;
				
		try {
			statement = connection.prepareStatement(createRecipe);
			statement.setString(1, newRecipe.getName());
			statement.setString(2, newRecipe.getRecipeUrl());
			statement.setString(3, newRecipe.getImageUrl());
			int rowCount = statement.executeUpdate();
			System.out.println("rows inserted: " + rowCount);
			int newRecipeId = getNewRecipeId();
			newRecipe.setId(newRecipeId);
		} catch (SQLException e) {		
			e.printStackTrace(); 
		}
		return newRecipe;
	}
	
	//utility to make new recipeId
	private int getNewRecipeId() {
		ResultSet rs = null;
		Statement statement = null;
		int newRecipeId = 0;

		try {
			statement = connection.createStatement();
			rs = statement.executeQuery(selectNewRecipeId);
			
			while (rs.next()) {
				newRecipeId = rs.getInt("id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return newRecipeId;
	}

	
	
}

   
