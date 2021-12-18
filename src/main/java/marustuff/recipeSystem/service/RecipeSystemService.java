package marustuff.recipeSystem.Service;

import lombok.RequiredArgsConstructor;
import marustuff.recipeSystem.Data.DatabaseMapper;
import marustuff.recipeSystem.Data.IngredientWithAmount;
import marustuff.recipeSystem.Data.Recipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeSystemService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final DatabaseMapper databaseMapper;

    public Recipe getRecipeById(Long id){
        Recipe recipe = databaseMapper.findRecipeById(id);
        recipe.setIngredientsWithAmounts(databaseMapper.getIngredientWithAmountListForRecipeId(id));
        logger.info(recipe.toString());
        return recipe;
    }

    public boolean saveRecipe(Recipe recipe) {
        databaseMapper.insertRecipe(recipe);
        logger.info(recipe.toString());
        for (IngredientWithAmount ingredient : recipe.getIngredientsWithAmounts()) {
            logger.info("Status of search"+databaseMapper.isIngredientPresentByName(ingredient.getName()));
            if(ingredient.getName()!=null&&!ingredient.getName().equals("")){
                if (!databaseMapper.isIngredientPresentByName(ingredient.getName())) {
                    databaseMapper.insertIngredient(ingredient);
                    logger.info(ingredient.toString());
                } else{
                    logger.info("get id by name"+databaseMapper.getIngredientIdByName(ingredient.getName()));
                    ingredient.setId(databaseMapper.getIngredientIdByName(ingredient.getName()));
                    logger.info("Reused ingredient"+ingredient.toString());
                }

            }
            try {
                databaseMapper.insertRecipeIngredientsAmount(ingredient.getId(), recipe.getId(), ingredient.getAmount());
            } catch (Exception e) {
                logger.error(e.getMessage());
                return false;
            }

        }
        logger.info("id receptury=" + recipe.getId());
        return true;
    }

    public int getNumberOfRecipePages(){
        double numberOfPages =databaseMapper.getNumberOfPages();
        if(numberOfPages==(int)numberOfPages){
            return (int) numberOfPages;
        }else {
            return (int) numberOfPages + 1;
        }
    }


}


