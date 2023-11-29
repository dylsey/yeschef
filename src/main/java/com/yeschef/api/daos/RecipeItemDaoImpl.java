//package com.yeschef.api.daos;
//
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
////import com.yeschef.api.models.Recipe;
//import com.yeschef.api.models.RecipeItem;
//
//public class RecipeItemDaoImpl implements RecipeItemDao {
//	//private Recipe recipe;
//
//	private Connection connection = MariaDbUtil.getConnection();
//
//	// SQL statements
//	private static String selectAllRecipeItems = "SELECT * FROM recipeItems";
//
//	private static String selectRecipeItemsByRecipeId = "SELECT * " + "FROM recipeItems " + "WHERE recipeId = ?";
//
//	private static String deleteRecipeItemsByRecipeId = "DELETE " + "FROM recipeItems" + " WHERE recipeId = ?";
//
//	private static String createRecipeItem = "INSERT INTO "
//			+ "recipeItems (recipeItemName, spoonacularRecipeId, imageUrl, quantity) " + "VALUES ( ?, ?, ?, ?)";
//
//	private static String selectNewRecipeItemId = "SELECT LAST_INSERT_ID() " + "AS 'id'";
//
//	// convenience method to make a List of any Recipe items that are needed for
//	// each method
//	private List<RecipeItem> makeRecipeItem(ResultSet result) throws SQLException {
//		List<RecipeItem> myRecipeItems = new ArrayList<RecipeItem>();
//		while (result.next()) {
//
//			RecipeItem recipeItem = new RecipeItem();
//			recipeItem.setId(result.getInt("id"));
//			recipeItem.setRecipeItemName(result.getString("recipeItemName"));
//			recipeItem.setRecipeId(result.getInt("recipeId"));
//			// will there need to be logic to make sure this id is the same as the
//			// corresponding recipe?
//			recipeItem.setSpoonacularRecipeId(result.getInt("spoonacularRecipeId"));
//			recipeItem.setImageUrl(result.getString("imageUrl"));
//			recipeItem.setQuantity(result.getString("quantity"));
//			recipeItem.setUpdateDateTime(result.getObject("updateDateTime", LocalDateTime.class));
//			recipeItem.setCreateDateTime(result.getObject("createDateTime", LocalDateTime.class));
//
//			myRecipeItems.add(recipeItem);
//		}
//		return myRecipeItems;
//	}
//
////	not sure if this method is necessary   
//	@Override
//	public List<RecipeItem> getRecipeItems() {
//		List<RecipeItem> myRecipeItems = new ArrayList<>();
//		ResultSet result = null;
//		Statement statement = null;
//
//		try {
//			statement = connection.createStatement();
//			result = statement.executeQuery(selectAllRecipeItems);
//			myRecipeItems = makeRecipeItem(result);
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return myRecipeItems;
//	}
//
//	@Override
//	public List<RecipeItem> getRecipeItemsByRecipeId(Integer recipeId) {
//		List<RecipeItem> myRecipeItems = new ArrayList<>();
//		ResultSet result = null;
//		PreparedStatement statement = null;
//
//		try {
//			statement = connection.prepareStatement(selectRecipeItemsByRecipeId);
//			statement.setInt(1, recipeId);
//
//			result = statement.executeQuery();
//			myRecipeItems = makeRecipeItem(result);
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return myRecipeItems;
//	}
//
//	@Override
//	public RecipeItem deleteRecipeItemsByRecipeId(Integer recipeId) {
//		List<RecipeItem> recipeItems = this.getRecipeItemsByRecipeId(recipeId);
//		RecipeItem recipeItemsToDelete = null;
//
//		if (recipeItems.size() > 0) {
//			recipeItemsToDelete = recipeItems.get(0);
//			int updateRowCount = 0;
//			PreparedStatement ps = null;
//			try {
//				ps = connection.prepareStatement(deleteRecipeItemsByRecipeId);
//				ps.setInt(1, recipeId);
//				updateRowCount = ps.executeUpdate();
//				System.out.println("rows deleted: " + updateRowCount);
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return recipeItemsToDelete;
//	}
//
//	@Override
//	public RecipeItem createRecipeItem(RecipeItem newRecipeItem) {
//		PreparedStatement statement = null;
//		
//		try {
//			statement = connection.prepareStatement(createRecipeItem, Statement.RETURN_GENERATED_KEYS);
//			statement.setInt(1, recipeId);
//			statement.setString(2, newRecipeItem.getRecipeItemName());
//			statement.setInt(3, newRecipeItem.getSpoonacularRecipeId());
//			statement.setString(4, newRecipeItem.getImageUrl());
//			statement.setString(5, newRecipeItem.getQuantity());
//			int rowCount = statement.executeUpdate();
//			System.out.println("rows inserted: " + rowCount);
//			
//			if (rowCount > 0) {
//				ResultSet generatedKeys = statement.getGeneratedKeys();
//				if (generatedKeys.next()) {
//					int newRecipeItemId = generatedKeys.getInt(1);
//					newRecipeItem.setId(newRecipeItemId);
//				}
//			}
//			
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		}
//		
//		return newRecipeItem;
//	}
//	
////is this necessary if I am trying to set the item based on the Basis class and the OG SQL Recipe ID set? 	
//	@SuppressWarnings("unused")
//	private int getNewRecipeItemId() {
//		ResultSet rs = null;
//		Statement statement = null;
//		int newRecipeItemId = 0;
//
//		try {
//			statement = connection.createStatement();
//			rs = statement.executeQuery(selectNewRecipeItemId);
//			while (rs.next()) {
//				newRecipeItemId = rs.getInt("recipeItemId");
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return newRecipeItemId;
//	}
//
//}
