package marustuff.recipeSystem.Data;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Ingredient {
    private long id;
    private String name;
    private BigDecimal price;

    public Ingredient(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Ingredient(long id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;

    }
}
