package com.yeschef.api.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.yeschef.api.models.MealPlan;
import com.yeschef.api.models.MealType;
import com.yeschef.api.models.Recipe;

public class MealPlanDaoImpl implements MealPlanDao {

	private Connection connection = MariaDbUtil.getConnection();

	private static String createMealPlan = "INSERT INTO mealPlan (mealPlanName) " + "VALUES " + "(?)";

	private static String selectAllMealPlans = "SELECT * " + " FROM mealplan";

	private static String selectMealPlanById = "SELECT * " + "FROM mealPlan " + "WHERE id = ?";

	private static String selectRecipesFromMealPlanById = "SELECT * " + "FROM recipes " + "WHERE mealPlanId = ?";

	private static String addRecipeToMealPlan = "INSERT INTO "
			+ "mealPlan (id, recipeId, name, recipeUrl, imageUrl, mealType) " + "VALUES (?, ?, ?, ?, ?, ?)";

	private static String deleteMealPlanById = "DELETE FROM " + "mealPlan " + "WHERE id = ?";

	private static String deleteRecipesFromMealPlan = "DELETE FROM recipes " + "WHERE mealPlanId = ? " + "AND id = ?";

	/*
	 * End of SQL statements
	 * 
	 */
	private List<MealPlan> makeMealPlan(ResultSet result) throws SQLException {
		List<MealPlan> myMealPlans = new ArrayList<MealPlan>();
		while (result.next()) {

			MealPlan mealPlan = new MealPlan();
			mealPlan.setId(result.getInt("id"));
			mealPlan.setMealPlanName(result.getString("mealPlanName"));

			mealPlan.setUpdateDateTime(result.getObject("updateDateTime", LocalDateTime.class));
			mealPlan.setCreateDateTime(result.getObject("createDateTime", LocalDateTime.class));

			// also need to get the recipes associated with the meal plan
			List<Recipe> recipes = getRecipesFromMealPlanById(mealPlan.getId());
			mealPlan.setRecipes(recipes);

			myMealPlans.add(mealPlan);
		}

		return myMealPlans;
	}

	@Override
	public List<MealPlan> getMealPlans() {
		List<MealPlan> myMealPlans = new ArrayList<MealPlan>();
		ResultSet result = null;
		Statement statement = null;

		Connection connection = MariaDbUtil.getConnection();

		try {
			statement = connection.createStatement();
			result = statement.executeQuery(selectAllMealPlans);
			myMealPlans = makeMealPlan(result);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return myMealPlans;
	}

	@Override
	public MealPlan createMealPlan(MealPlan newMealPlan) {
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(createMealPlan);
			statement.setString(1, newMealPlan.getMealPlanName());

			int rowCount = statement.executeUpdate();
			System.out.println("rows inserted: " + rowCount);
			int newMealPlanId = getNewMealPlanId();
			newMealPlan.setId(newMealPlanId);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newMealPlan;
	}

	private int getNewMealPlanId() {
		ResultSet rs = null;
		Statement statement = null;
		int newMealPlanId = 0;

		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT LAST_INSERT_ID() AS id");

			while (rs.next()) {
				newMealPlanId = rs.getInt("id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return newMealPlanId;
	}

//brought in convenience method from Recipe Impl class to help make recipes	
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
	public List<Recipe> getRecipesFromMealPlanById(Integer mealPlanId) {
		List<Recipe> myRecipes = new ArrayList<Recipe>();
		ResultSet result = null;
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(selectRecipesFromMealPlanById);
			statement.setInt(1, mealPlanId);

			result = statement.executeQuery();
			myRecipes = makeRecipe(result);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return myRecipes;
	}

	@Override
	public List<MealPlan> getMealsPlanById(Integer id) {
		List<MealPlan> mealPlans = new ArrayList<>();
		ResultSet result = null;
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(selectMealPlanById);
			statement.setInt(1, id);

			result = statement.executeQuery();
			mealPlans = makeMealPlan(result);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return mealPlans;
	}

	@Override
	public Recipe addRecipeToMealPlanById(Integer id, Recipe recipe) {
		Recipe recipeToAdd = null;
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(addRecipeToMealPlan);
			statement.setInt(1, recipe.getMealPlanId());
			statement.setInt(2, recipe.getId());
			statement.setString(3, recipe.getName());
			statement.setString(4, recipe.getRecipeUrl());
			statement.setString(5, recipe.getImageUrl());
			statement.setString(6, recipe.getMealType().toString());

			int updateRowCount = statement.executeUpdate();
			recipeToAdd = recipe;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recipeToAdd;

	}

	@Override
	public MealPlan deleteMealPlanById(Integer id) {
		List<MealPlan> mealPlans = this.getMealsPlanById(id);
		MealPlan mealPlanToDelete = null;

		if (mealPlans.size() > 0) {
			mealPlanToDelete = mealPlans.get(0);
			int updateRowCount = 0;
			PreparedStatement ps = null;
			try {
				ps = connection.prepareStatement(deleteMealPlanById);
				ps.setInt(1, id);
				updateRowCount = ps.executeUpdate();
				System.out.println("rows deleted: " + updateRowCount);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return mealPlanToDelete;
	}

	@Override
	public Recipe deleteRecipeFromMealPlanById(Integer id, Integer recipeId) {

		List<Recipe> recipes = this.getRecipesFromMealPlanById(id);
		Recipe recipeToDelete = null;
		PreparedStatement statement = null;

		if (recipes.size() > 0) {
			recipeToDelete = recipes.get(0);
			int updateRowCount = 0;
			try {
				statement = connection.prepareStatement(deleteRecipesFromMealPlan);
				statement.setInt(1, id);
				statement.setInt(2, recipeId);
				updateRowCount = statement.executeUpdate();
				System.out.println("rows deleted: " + updateRowCount);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return recipeToDelete;
	}
}
