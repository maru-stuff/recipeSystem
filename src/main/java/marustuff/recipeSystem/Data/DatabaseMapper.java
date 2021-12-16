package marustuff.recipeSystem.Data;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper //podziel ten interface na kilka w zależności od odpowiedzialności
public interface DatabaseMapper {

    @Select("SELECT * FROM ingredients")
    // tutaj IDE podpowiada, że somfing łent łong
    public List<Ingredient> findAllIngredients();

    @Select("SELECT DISTINCT Name FROM ingredients")
    public List<String> findAllIngredientNames();

    @Select("SELECT * FROM ingredients WHERE Id = #{id}")
    public Ingredient findIngredientById(long id);

    @Delete("DELETE FROM ingredients WHERE Id = #{id}")
    public int deleteIngredientById(long id);

    @Insert("INSERT INTO ingredients(Name,Price) VALUES (#{name},#{price})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    public int insertIngredient(Ingredient ingredient);

    @Update("Update ingredients set Name=#{name},Price=#{price} Where id=#{id}")
    public int updateIngredient(Ingredient ingredient);

    @Select("SELECT EXISTS(SELECT 1 FROM ingredients WHERE Name=#{name})")
    boolean isIngredientPresentByName(String name);

    @Select("SELECT Id FROM ingredients WHERE Name=#{name}")
    long getIngredientIdByName(String name);

    @Select("SELECT * FROM recipes")
    public List<Recipe> findAllRecipes();

    @Select("SELECT Name FROM recipes")
    public List<String> findAllRecipeNames();

    @Select("SELECT * FROM recipes WHERE Id=#{id}")
    public Recipe findRecipeById(long id);

    @Delete("DELETE FROM recipes WHERE Id = #{id}")
    public int deleteRecipeById(long id);

    @Insert("INSERT INTO recipes(Name,Instructions) VALUES(#{name},#{instructions})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    public int insertRecipe(Recipe recipe);

    @Update("UPDATE recipes SET Name=#{name},Instructions=#{instructions},#{dateAdded})")
    public int updateRecipe(Recipe recipe);
    // recipesingredientsamount <-- tego się nie da przeczytać :P https://www.sqlshack.com/learn-sql-naming-conventions/
    @Select("SELECT Name,Amount,Id FROM ingredients LEFT JOIN recipesingredientsamount ON ingredients.id = recipesingredientsamount.IngredientId WHERE recipesingredientsamount.RecipeId=#{id};")
    public List<IngredientWithAmount> getIngredientWithAmountListForRecipeId(Long id);
//DUŻO WOLNEJ PRZESTRZENI
//

    @Insert("INSERT INTO recipesingredientsamount(IngredientId,RecipeId,amount) VALUES (#{ingredientId},#{recipeId},#{amount})")
    @Options(keyProperty = "IngredientId,RecipeId")
    public void insertRecipeIngredientsAmount(long ingredientId, long recipeId, String amount);

    @Select("SELECT Name,Id FROM recipes ORDER BY Id DESC LIMIT 15;")
    public List<Recipe> getNameIdRecipe();

    @Select("SELECT COUNT(*)/5 FROM recipes")
    public double getNumberOfPages();

    @Select("SELECT Name,Id FROM recipes ORDER BY Id LIMIT 5 OFFSET #{offset}")
    public List<Recipe> getNameIdRecipeByPage(int offset,int currentPage);
}
