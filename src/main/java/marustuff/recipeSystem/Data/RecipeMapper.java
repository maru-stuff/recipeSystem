package marustuff.recipeSystem.Data;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RecipeMapper {
    @Select("SELECT * FROM recipes")
    public List<Recipe> findAllRecipes();

    @Select("SELECT Name FROM recipes")
    public List<String> findAllRecipeNames();

    @Select("SELECT * FROM recipes WHERE Id=#{id}")
    public Recipe findRecipeById(long id);

    @Delete("DELETE FROM recipes WHERE Id = #{id}")
    public int deleteRecipeById(long id);

    @Insert("INSERT INTO recipes(Name,Instructions) VALUES(#{name},#{instructions})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertRecipe(Recipe recipe);

    @Update("UPDATE recipes SET Name=#{name},Instructions=#{instructions},#{dateAdded})")
    public int updateRecipe(Recipe recipe);

    @Select("SELECT Name,Amount,Id FROM ingredients LEFT JOIN recipes_ingredients_amount ON ingredients.id = recipes_ingredients_amount.IngredientId WHERE recipes_ingredients_amount.RecipeId=#{id};")
    public List<IngredientWithAmount> getIngredientWithAmountListForRecipeId(Long id);
}
