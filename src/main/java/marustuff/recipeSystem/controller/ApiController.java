package marustuff.recipeSystem.controller;

import lombok.RequiredArgsConstructor;
import marustuff.recipeSystem.Data.DatabaseMapper;
import marustuff.recipeSystem.Service.RecipeSystemService;
import marustuff.recipeSystem.Data.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApiController {
    @Autowired
    private final RecipeSystemService recipeSystemService;

    @Autowired
    private final DatabaseMapper databaseMapper;

    @GetMapping("/recipe/{id}")
    public Recipe getRecipe(@PathVariable("id") Long id){
        return recipeSystemService.getRecipeById(id);
    }
    @GetMapping("/autocomplete/ingredients")
    public List<String> autoIngredients(){
        return databaseMapper.findAllIngredientNames();
    }
}
