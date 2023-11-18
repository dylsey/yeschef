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
	private static String selectRecipeItemsbyRecipeId = "SELECT * " + "FROM recipeItems " + "WHERE recipeId = ?";
	
	
	

	@Override
	public List<RecipeItem> getRecipeItemsByRecipeId(Integer recipeId) {
		List<RecipeItem> myRecipeItems = new ArrayList<>();
		ResultSet result = null;
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(selectRecipeItemsbyRecipeId);
			statement.setInt(1, recipeId);

			result = statement.executeQuery();
			myRecipeItems = makeRecipeItem(result);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return myRecipeItems;
	}

	// convenience method to make an List of any Recipe items that are needed for each method
	private List<RecipeItem> makeRecipeItem(ResultSet result) throws SQLException {
		List<RecipeItem> myRecipeItems = new ArrayList<RecipeItem>();
		while (result.next()) {

			RecipeItem recipeItem = new RecipeItem();
			recipeItem.setId(result.getInt("id"));
			recipeItem.setRecipeItemName(result.getString("recipeItemName"));
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


	@Override
	public List<RecipeItem> deleteRecipeItemsByRecipeId(Integer recipeId) {
		String sql = "DELETE FROM recipe_items WHERE recipe_id = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, recipeId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(); // Handle the exception according to your application's needs
		}
		return null;
	}

	@Override
	public RecipeItem createRecipeItem(RecipeItem recipeItem) {
		String sql = "INSERT INTO recipe_items (recipe_id, name, spoonacular_ingredient_id, image_url, quantity) "
				+ "VALUES (?, ?, ?, ?, ?)";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setInt(1, recipeItem.getRecipeId());
			preparedStatement.setString(2, recipeItem.getRecipeItemName());
			preparedStatement.setInt(3, recipeItem.getSpoonacularRecipeId());
			preparedStatement.setString(4, recipeItem.getImageUrl());
			preparedStatement.setString(5, recipeItem.getQuantity());

			preparedStatement.executeUpdate();

			// Retrieve the auto-generated ID
			try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					recipeItem.setId(generatedKeys.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Handle the exception according to your application's needs
		}
		return recipeItem;
	}

//		not sure if this method is necessary   
//    @Override
//    public List<RecipeItem> getRecipeItems() {
//        List<RecipeItem> recipeItems = new ArrayList<>();
//        String sql = "SELECT * FROM recipe_items";
//
//        try (Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery(sql)) {
//
//            while (resultSet.next()) {
//                RecipeItem recipeItem = mapResultSetToRecipeItem(resultSet);
//                recipeItems.add(recipeItem);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace(); 
//        }
//        return recipeItems;
//    }

}
