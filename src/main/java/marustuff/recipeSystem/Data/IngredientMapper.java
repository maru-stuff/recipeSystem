package marustuff.recipeSystem.Data;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IngredientMapper {
    @Select("SELECT * FROM ingredients")
    public List<Ingredient> findAllIngredients();

    @Select("SELECT DISTINCT Name FROM ingredients")
    public List<String> findAllIngredientNames();

    @Select("SELECT * FROM ingredients WHERE Id = #{id}")
    public Ingredient findIngredientById(long id);

    @Delete("DELETE FROM ingredients WHERE Id = #{id}")
    public int deleteIngredientById(long id);

    @Insert("INSERT INTO ingredients(Name,Price) VALUES (#{name},#{price})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertIngredient(Ingredient ingredient);

    @Update("Update ingredients set Name=#{name},Price=#{price} Where id=#{id}")
    public int updateIngredient(Ingredient ingredient);

    @Select("SELECT EXISTS(SELECT 1 FROM ingredients WHERE Name=#{name})")
    boolean isIngredientPresentByName(String name);

    @Select("SELECT Id FROM ingredients WHERE Name=#{name}")
    long getIngredientIdByName(String name);
}
