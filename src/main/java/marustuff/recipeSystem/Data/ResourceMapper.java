package marustuff.recipeSystem.Data;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ResourceMapper {

    @Insert("INSERT INTO recipes_ingredients_amount(IngredientId,RecipeId,amount) VALUES (#{ingredientId},#{recipeId},#{amount})")
    @Options(keyProperty = "IngredientId,RecipeId")
    public void insertRecipeIngredientsAmount(long ingredientId, long recipeId, String amount);

    @Select("SELECT Name,Id FROM recipes ORDER BY Id DESC LIMIT 15;")
    public List<Recipe> getNameIdRecipe();

    @Select("SELECT COUNT(*)/5 FROM recipes")
    public double getNumberOfPages();

    @Select("SELECT Name,Id FROM recipes ORDER BY Id LIMIT 5 OFFSET #{offset}")
    public List<Recipe> getNameIdRecipeByPage(int offset, int currentPage);

    @Select("Select Name,Id FROM recipes WHERE Name Like #{search}")
    public List<Recipe> searchByRecipeName(String search);

    @Select("Select DISTINCT recipes.Name,recipes.Id FROM  recipes LEFT JOIN (recipes_ingredients_amount  LEFT JOIN ingredients ON ingredients.id=recipes_ingredients_amount.ingredientId)ON recipes_ingredients_amount.recipeID=recipes.id WHERE ingredients.Name Like #{search};")
    public List<Recipe> searchByIngredient(String search);

    @Select("SELECT EXISTS(SELECT * FROM information_schema.tables WHERE table_schema='recipedb' AND table_name='recipes')")
    public boolean isRecipesTablePresent();

    @Select("SELECT EXISTS(SELECT * FROM information_schema.tables WHERE table_schema='recipedb' AND table_name='ingredients')")
    public boolean isIngredientsTablePresent();

    @Select("SELECT EXISTS(SELECT * FROM information_schema.tables WHERE table_schema='recipedb' AND table_name='recipes_ingredients_amount')")
    public boolean isRecipesIngredientsAmountsTablePresent();

    @Update("CREATE TABLE `recipes` (\n" +
            "  `Id` int NOT NULL AUTO_INCREMENT,\n" +
            "  `Name` varchar(40) NOT NULL,\n" +
            "  `Instructions` blob NOT NULL,\n" +
            "  `DateAdded` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
            "  PRIMARY KEY (`Id`)\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci")
    void createRecipesTable();

    @Update("CREATE TABLE `ingredients` (\n" +
            "  `Id` int NOT NULL AUTO_INCREMENT,\n" +
            "  `Name` varchar(25) NOT NULL,\n" +
            "  `Price` decimal(3,2) DEFAULT NULL,\n" +
            "  PRIMARY KEY (`Id`)\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci")
    void createIngredientsTable();

    @Update("CREATE TABLE `recipesingredientsamount` (\n" +
            "  `IngredientId` int NOT NULL,\n" +
            "  `RecipeId` int NOT NULL,\n" +
            "  `Amount` varchar(20) NOT NULL,\n" +
            "  KEY `IngredientId` (`IngredientId`),\n" +
            "  KEY `RecipeId` (`RecipeId`),\n" +
            "  CONSTRAINT `recipesingredientsamount_ibfk_1` FOREIGN KEY (`IngredientId`) REFERENCES `ingredients` (`Id`),\n" +
            "  CONSTRAINT `recipesingredientsamount_ibfk_2` FOREIGN KEY (`RecipeId`) REFERENCES `recipes` (`Id`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci")
    void createRecipesIngredientsAmountsTable();
}
