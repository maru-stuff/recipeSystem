package marustuff.recipeSystem.Data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {
    private long id;
    private String name;
    private double price;
    private List<Recipe> recipes = new ArrayList<>();

    public Ingredient(Long id,String name){
        this.id=id;
        this.name=name;
    }

    public Ingredient(long id,String name,double price){
        this.id=id;
        this.name=name;
        this.price=price;
        recipes=null;

    }
}
