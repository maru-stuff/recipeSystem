package marustuff.recipeSystem.Service;

import lombok.RequiredArgsConstructor;
import marustuff.recipeSystem.Data.*;
import marustuff.recipeSystem.Data.ResourceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeSystemService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final ResourceMapper resourceMapper;

    @Autowired
    private final IngredientMapper ingredientMapper;

    @Autowired
    private final RecipeMapper recipeMapper;



    public Recipe getRecipeById(Long id) {
        Recipe recipe = recipeMapper.findRecipeById(id);
        recipe.setIngredientsWithAmounts(recipeMapper.getIngredientWithAmountListForRecipeId(id));
        logger.info(recipe.toString());
        return recipe;
    }

    public boolean saveRecipe(Recipe recipe) {
        if (recipe != null && recipe.getName() != null && recipe.getInstructions() != null)
            recipeMapper.insertRecipe(recipe);
        logger.info(recipe.toString());
        for (IngredientWithAmount ingredient : recipe.getIngredientsWithAmounts()) {
            logger.info("Status of search" + ingredientMapper.isIngredientPresentByName(ingredient.getName()));
            if (ingredient != null && ingredient.getName() != null && !ingredient.getName().equals("")) {
                if (!ingredientMapper.isIngredientPresentByName(ingredient.getName())) {
                    ingredientMapper.insertIngredient(ingredient);
                    logger.info(ingredient.toString());
                } else {
                    logger.info("get id by name" + ingredientMapper.getIngredientIdByName(ingredient.getName()));
                    ingredient.setId(ingredientMapper.getIngredientIdByName(ingredient.getName()));
                    logger.info("Reused ingredient" + ingredient);
                }

            }
            try {
                resourceMapper.insertRecipeIngredientsAmount(ingredient.getId(), recipe.getId(), ingredient.getAmount());
            } catch (Exception e) {
                logger.error(e.getMessage());
                return false;
            }

        }
        logger.info("id receptury=" + recipe.getId());
        return true;
    }

    public int getNumberOfRecipePages() {
        double numberOfPages = resourceMapper.getNumberOfPages();
        if (numberOfPages == (int) numberOfPages) {
            return (int) numberOfPages;
        } else {
            return (int) numberOfPages + 1;
        }
    }

    public List<Recipe> searchForRecipes(SearchEntity searchEntity) {
        List<Recipe> foundRecipes;
        if (searchEntity.getSearchByRecipeName() != null && searchEntity.getSearchByRecipeName() != "") {
            foundRecipes = resourceMapper.searchByRecipeName(searchEntity.getSearchByRecipeName());
        } else if (searchEntity.getSearchByIngredient() != null && searchEntity.getSearchByIngredient() != "") {
            foundRecipes = resourceMapper.searchByIngredient(searchEntity.getSearchByIngredient());
        } else {
            foundRecipes = null;
        }
        return foundRecipes;
    }


}


