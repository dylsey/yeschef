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

	private static String selectAllRecipes = "SELECT id, name, spoonacular_recipe_id, image_url, 		updatepdateDateTime, createDateTime "
			+ "FROM recipes";

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

	
	private List<Recipe> makeRecipe(ResultSet result) throws SQLException {
		List<Recipe> myRecipes = new ArrayList<Recipe>();
		while (result.next()) {

			Recipe recipe = new Recipe();
			recipe.setId(result.getInt("id"));
			recipe.setName(result.getString("name"));
			recipe.setSpoonacularRecipeId(result.getInt("spoonacular_recipe_id"));
			recipe.setImageUrl(result.getString("image_url"));

//			recipe.setUpdateDateTime(result.getObject("updateDateTime", LocalDateTime.class));
//			recipe.setCreateDateTime(result.getObject("createDateTime", LocalDateTime.class));

			myRecipes.add(recipe);
		}

		return myRecipes;
	}

	@Override
	public Recipe getRecipeById(Integer id) {
		Recipe recipe = null;
		
		try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM recipes WHERE id = ?")) {
			preparedStatement.setInt(1, id);
			try (ResultSet result = preparedStatement.executeQuery()) {
				if (result.next()) {
					recipe = makeRecipe(result);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
		return recipe;
	}

	@Override
	public Recipe deleteRecipeById(Integer id) {
		Recipe recipe = getRecipeById(id); // Retrieve the recipe before deleting
		try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM recipes WHERE id = ?")) {
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(); // Handle the exception according to your application's needs
		}
		return recipe;
	}

	@Override
	public List<Recipe> searchRecipesByName(String searchTerm) {
		List<Recipe> recipes = new ArrayList<>();
		try (PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT * FROM recipes WHERE name LIKE ?")) {
			preparedStatement.setString(1, "%" + searchTerm + "%");
			try (ResultSet result = preparedStatement.executeQuery()) {
				while (result.next()) {
					Recipe recipe = makeRecipe(result);
					recipes.add(recipe);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Handle the exception according to your application's needs
		}
		return recipes;
	}

	@Override
	public Recipe createRecipe(Recipe recipe) {
		try (PreparedStatement preparedStatement = connection.prepareStatement(
				"INSERT INTO recipes (name, spoonacular_recipe_id, image_url) VALUES (?, ?, ?)",
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
			e.printStackTrace(); // Handle the exception according to your application's needs
		}
		return recipe;
	}

}
