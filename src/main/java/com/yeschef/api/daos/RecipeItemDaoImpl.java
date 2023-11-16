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


    @Override
    public List<RecipeItem> getRecipeItems() {
        List<RecipeItem> recipeItems = new ArrayList<>();
        String sql = "SELECT * FROM recipe_items";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                RecipeItem recipeItem = mapResultSetToRecipeItem(resultSet);
                recipeItems.add(recipeItem);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's needs
        }
        return recipeItems;
    }

    @Override
    public List<RecipeItem> getRecipeItemsByRecipeId(Integer recipeId) {
        List<RecipeItem> recipeItems = new ArrayList<>();
        String sql = "SELECT * FROM recipe_items WHERE recipe_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, recipeId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    RecipeItem recipeItem = mapResultSetToRecipeItem(resultSet);
                    recipeItems.add(recipeItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's needs
        }
        return recipeItems;
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
        String sql = "INSERT INTO recipe_items (recipe_id, name, spoonacular_ingredient_id, image_url, quantity) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, recipeItem.getRecipeId());
            preparedStatement.setString(2, recipeItem.getRecipeItemName());
            preparedStatement.setInt(3, recipeItem.getSpoonacularIngredientId());
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

    // Helper method to map ResultSet to RecipeItem object
    private RecipeItem mapResultSetToRecipeItem(ResultSet resultSet) throws SQLException {
        RecipeItem recipeItem = new RecipeItem();
        recipeItem.setId(resultSet.getInt("id"));
        recipeItem.setRecipeId(resultSet.getInt("recipe_id"));
        recipeItem.setRecipeItemName(resultSet.getString("name"));
        recipeItem.setSpoonacularIngredientId(resultSet.getInt("spoonacular_ingredient_id"));
        recipeItem.setImageUrl(resultSet.getString("image_url"));
        recipeItem.setQuantity(resultSet.getString("quantity"));
        return recipeItem;
    }



}
