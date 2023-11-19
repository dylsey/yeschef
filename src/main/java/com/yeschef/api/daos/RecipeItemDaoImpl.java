package com.yeschef.api.daos;

import com.yeschef.api.models.RecipeItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RecipeItemDaoImpl implements RecipeItemDao {

	private Connection connection = MariaDbUtil.getConnection();

	// SQL statements
	private static String selectAllRecipeItems = "SELECT * FROM recipeItems";

	private static String selectRecipeItemsByRecipeId = "SELECT * " + "FROM recipeItems " + "WHERE recipeId = ?";
	
	private static String deleteRecipeItemsByRecipeId = "DELETE FROM recipeItems WHERE recipeId = ?";
	
	
	// convenience method to make a List of any Recipe items that are needed for
	// each method
	private List<RecipeItem> makeRecipeItem(ResultSet result) throws SQLException {
		List<RecipeItem> myRecipeItems = new ArrayList<RecipeItem>();
		while (result.next()) {

			RecipeItem recipeItem = new RecipeItem();
			recipeItem.setId(result.getInt("id"));
			recipeItem.setRecipeItem(result.getString("recipeItemName"));
			recipeItem.setRecipeId(result.getInt("recipeId"));
			recipeItem.setSpoonacularRecipeId(result.getInt("spoonacularRecipeId"));
			recipeItem.setImageUrl(result.getString("imageUrl"));
			recipeItem.setQuantity(result.getString("quantity"));
			recipeItem.setUpdateDateTime(result.getObject("updateDateTime", LocalDateTime.class));
			recipeItem.setCreateDateTime(result.getObject("createDateTime", LocalDateTime.class));

			myRecipeItems.add(recipeItem);
		}

		return myRecipeItems;
	}
//	not sure if this method is necessary   
	@Override
	public List<RecipeItem> getRecipeItems() {
		List<RecipeItem> myRecipeItems = new ArrayList<>();
		ResultSet result = null;
		Statement statement = null;

		try {
			statement = connection.createStatement();
			result = statement.executeQuery(selectAllRecipeItems);
			myRecipeItems = makeRecipeItem(result);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return myRecipeItems;
	}

	@Override
	public List<RecipeItem> getRecipeItemsByRecipeId(Integer recipeId) {
		List<RecipeItem> myRecipeItems = new ArrayList<>();
		ResultSet result = null;
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(selectRecipeItemsByRecipeId);
			statement.setInt(1, recipeId);

			result = statement.executeQuery();
			myRecipeItems = makeRecipeItem(result);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return myRecipeItems;
	}

	@Override
	public RecipeItem deleteRecipeItemsByRecipeId(Integer recipeId) {
		List<RecipeItem> recipeItems = this.getRecipeItemsByRecipeId(recipeId);
		RecipeItem recipeItemsToDelete = null;
		
		if (recipeItems.size() > 0) {
			recipeItemsToDelete = recipeItems.get(0);
			int updateRowCount = 0;
			PreparedStatement ps = null;
			try {
				ps = connection.prepareStatement(deleteRecipeItemsByRecipeId);
				ps.setInt(1, recipeId);
				ps.executeUpdate();
				updateRowCount = ps.executeUpdate();
				System.out.println("rows deleted: " + updateRowCount);
			} catch (SQLException e) {
				e.printStackTrace(); 
			}
		}	
		return recipeItemsToDelete;
	}

	@Override
	public RecipeItem createRecipeItem(RecipeItem recipeItem) {
		String sql = "INSERT INTO recipe_items (recipe_id, name, spoonacular_ingredient_id, image_url, quantity) "
				+ "VALUES (?, ?, ?, ?, ?)";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
			preparedStatement.setInt(1, recipeItem.getRecipeId());
			preparedStatement.setString(2, recipeItem.getRecipeItem());
			preparedStatement.setInt(3, recipeItem.getSpoonacularRecipeId());
			preparedStatement.setString(4, recipeItem.getImageUrl());
			preparedStatement.setString(5, recipeItem.getQuantity());

			preparedStatement.executeUpdate();

			
			try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					recipeItem.setId(generatedKeys.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recipeItem;
	}

}
