package marustuff.recipeSystem.Data;

import lombok.*;

@Data
@AllArgsConstructor
@ToString(callSuper = true)
@NoArgsConstructor
public class IngredientWithAmount extends Ingredient {
    private String amount; //Może jakiś obiekt, który by zawierał jednostkę miary i ilość?

    @Builder //po co builder?
    public IngredientWithAmount(Long id,String name,String amount){
        super(id,name);
        this.amount = amount;
    }
}
