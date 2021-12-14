package marustuff.recipeSystem.Data;

import lombok.*;

@Data
@AllArgsConstructor
@ToString(callSuper = true)
@NoArgsConstructor
public class IngredientWithAmount extends Ingredient {
    private String amount;

    @Builder
    public IngredientWithAmount(Long id,String name,String amount){
        super(id,name);
        this.amount = amount;
    }
}
