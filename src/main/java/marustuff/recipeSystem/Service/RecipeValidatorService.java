package marustuff.recipeSystem.Service;

import marustuff.recipeSystem.Data.IngredientWithAmount;
import marustuff.recipeSystem.Data.Recipe;
import org.springframework.stereotype.Service;

import javax.validation.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Service
public class RecipeValidatorService {
    public void validateRecipe(Recipe recipe) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Recipe>> recipeViolations = validator.validate(recipe);
        Set<ConstraintViolation<IngredientWithAmount>> ingredientViolations = new HashSet<>();
        for (IngredientWithAmount ingredient : recipe.getIngredientsWithAmounts()) {
            if (!validator.validate(ingredient).isEmpty()) {
                System.out.println(ingredientViolations);
                System.out.println(validator.validate(ingredient));
                System.out.println(ingredient);
                ingredientViolations.addAll(validator.validate(ingredient));
            }
        }

        if (!recipeViolations.isEmpty() || !ingredientViolations.isEmpty()) {
            throw new ConstraintViolationException(recipeViolations);
        }

    }
}
