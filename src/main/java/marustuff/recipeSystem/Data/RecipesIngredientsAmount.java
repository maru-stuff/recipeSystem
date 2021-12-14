package marustuff.recipeSystem.Data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipesIngredientsAmount {
    private Long ingredientId;
    private Long recipeId;
    private String Amount;
}
