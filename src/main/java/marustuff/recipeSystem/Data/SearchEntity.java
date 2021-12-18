package marustuff.recipeSystem.Data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchEntity {
    private String searchByRecipeName = "";
    private String searchByIngredient = "";
}
