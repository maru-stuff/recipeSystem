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

    public void saveRecipe(Recipe recipe) {
        recipeMapper.insertRecipe(recipe);
        logger.info(recipe.toString());
        for (IngredientWithAmount ingredient : recipe.getIngredientsWithAmounts()) {
            boolean isIngredientPresent = ingredientMapper.isIngredientPresentByName(ingredient.getName());
            if (!isIngredientPresent) {
                ingredientMapper.insertIngredient(ingredient);
            } else {
                long ingredientId = ingredientMapper.getIngredientIdByName(ingredient.getName());
                ingredient.setId(ingredientId);
            }

            resourceMapper.insertRecipeIngredientsAmount(ingredient.getId(), recipe.getId(), ingredient.getAmount());
        }
        logger.info("id receptury=" + recipe.getId());
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
        if (searchEntity.getSearchByRecipeName() != null && !searchEntity.getSearchByIngredient().equals("")) {
            foundRecipes = resourceMapper.searchByRecipeName(searchEntity.getSearchByRecipeName());
        } else if (searchEntity.getSearchByIngredient() != null && !searchEntity.getSearchByIngredient().equals("")) {
            foundRecipes = resourceMapper.searchByIngredient(searchEntity.getSearchByIngredient());
        } else {
            foundRecipes = null;
        }
        return foundRecipes;
    }
}


