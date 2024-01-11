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

	private static String updateMealPlanById = "UPDATE mealplan " + "SET mealPlanName = ?" + "WHERE id = ?";

	private static String selectAllMealPlans = "SELECT * " + " FROM mealplan";

	private static String selectMealPlanById = "SELECT * " + "FROM mealPlan " + "WHERE id = ?";

	private static String selectRecipesFromMealPlanById = "SELECT * " + "FROM recipes " + "WHERE mealPlanId = ?";

	private static String addRecipeToMealPlan = "UPDATE recipes " + "SET mealPlanId = ? " + "WHERE id = ?";

	private static String deleteMealPlanById = "DELETE FROM " + "mealPlan " + "WHERE id = ?";

	private static String removeRecipeFromMealPlan = "UPDATE recipes " + "SET mealPlanId = NULL "
			+ "WHERE mealPlanId = ? " + "AND id = ?";

	private static String selectRecipesById = "SELECT * " + "FROM recipes " + "WHERE id = ?";

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
			List<Recipe> recipes = getRecipesFromMealPlanByMealPlanId(mealPlan.getId());
			mealPlan.setRecipes(recipes);

			myMealPlans.add(mealPlan);
		}

		return myMealPlans;
	}

	// brought in convenience method from Recipe Impl class to help make recipes
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
	public List<MealPlan> getMealPlansById(Integer id) {
		List<MealPlan> mealPlans = new ArrayList<MealPlan>();
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

	@Override
	public MealPlan updateMealPlan(MealPlan updateMealPlan) {
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(updateMealPlanById);
			statement.setString(1, updateMealPlan.getMealPlanName());
			statement.setInt(2, updateMealPlan.getId());
			int updateRowCount = statement.executeUpdate();
			System.out.println("rows updated: " + updateRowCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return updateMealPlan;
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
	public Recipe addRecipeToMealPlanByMealPlanId(Integer mealPlanId, Integer recipeId) {

		List<Recipe> recipes = this.getRecipesById(recipeId);
		Recipe recipeToAdd = null;
//		ResultSet result = null;

		if (recipes.size() > 0) {
			recipeToAdd = recipes.get(0);
			PreparedStatement statement = null;

			try {
				statement = connection.prepareStatement(addRecipeToMealPlan);
				statement.setInt(1, mealPlanId);
				statement.setInt(2, recipeId);
				int updateRowCount = statement.executeUpdate();

				// result = statement.executeQuery();
				// recipes = makeRecipe(result);

				// recipeToAdd = makeRecipe(result);

				System.out.println("id: " + mealPlanId);
				System.out.println("recipeId: " + recipeId);
				System.out.println("rows updated: " + updateRowCount);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return recipeToAdd;
	}

	@Override
	public Recipe removeRecipeFromMealPlanByMealPlanId(Integer mealPlanId, Integer recipeId) {
		List<Recipe> recipes = this.getRecipesById(recipeId);
		Recipe recipeToRemove = null;

		if (recipes.size() > 0) {
			recipeToRemove = recipes.get(0);
			PreparedStatement statement = null;

			try {
				statement = connection.prepareStatement(removeRecipeFromMealPlan);
				statement.setInt(1, mealPlanId);
				statement.setInt(2, recipeId);
				int updateRowCount = statement.executeUpdate();

				// result = statement.executeQuery();
				// recipes = makeRecipe(result);

				// recipeToAdd = makeRecipe(result);

				System.out.println("id: " + mealPlanId);
				System.out.println("recipeId: " + recipeId);
				System.out.println("rows updated: " + updateRowCount);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return recipeToRemove;
	}

	@Override
	public List<Recipe> getRecipesFromMealPlanByMealPlanId(Integer id) {
		List<Recipe> myRecipes = new ArrayList<Recipe>();
		ResultSet result = null;
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(selectRecipesFromMealPlanById);
			statement.setInt(1, id);

			result = statement.executeQuery();
			myRecipes = makeRecipe(result);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return myRecipes;
	}

	@Override
	public MealPlan deleteMealPlanById(Integer id) {
		List<MealPlan> mealPlans = this.getMealPlansById(id);
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

}
