package marustuff.recipeSystem.Data;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@ToString(callSuper = true)
@NoArgsConstructor
public class IngredientWithAmount extends Ingredient {
    @NotNull
    @NotEmpty
    private String amount;

    public IngredientWithAmount(Long id, String name, String amount) {
        super(id, name);
        this.amount = amount;
    }
}
