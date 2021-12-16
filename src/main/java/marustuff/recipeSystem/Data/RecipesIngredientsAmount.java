package marustuff.recipeSystem.Data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipesIngredientsAmount { //czym jest ta klasa? Czemu liczba mnoga skoro nie masz list tylko pojedyncze zmienne?
    private Long ingredientId;
    private Long recipeId;
    private String Amount;
}
