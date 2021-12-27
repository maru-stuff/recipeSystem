package marustuff.recipeSystem.Data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    private long id;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String instructions;
    private Date createDate;
    @NotNull
    @NotEmpty
    private List<IngredientWithAmount> ingredientsWithAmounts = new ArrayList<>();

    public Recipe(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addIngredientWithAmount(IngredientWithAmount ingredientWithAmount) {
        this.ingredientsWithAmounts.add(ingredientWithAmount);
    }
}
