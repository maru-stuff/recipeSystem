package marustuff.recipeSystem.Data;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@ToString(callSuper = true)
@NoArgsConstructor
public class IngredientWithAmount extends Ingredient {
    @NotBlank
    private String amount;

    public IngredientWithAmount(Long id, String name, String amount) {
        super(id, name);
        this.amount = amount;
    }
}
