package marustuff.recipeSystem.controller;

import lombok.RequiredArgsConstructor;
import marustuff.recipeSystem.Data.Recipe;
import marustuff.recipeSystem.Data.SearchEntity;
import marustuff.recipeSystem.Service.UIService;
import marustuff.recipeSystem.Service.RecipeValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;

@RequestMapping("/")
@RequiredArgsConstructor
@Controller
public class UIController {
    @Autowired
    private final UIService uiService;

    @Autowired
    private final RecipeValidatorService recipeValidatorService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/")
    public String getIndex(Model model) {
        return uiService.getIndexView(model);
    }

    @GetMapping("/recent")
    public String getRecent(Model model) {
        return uiService.getRecentView(model);
    }

    @GetMapping("/browse/{page}")
    public String getBrowse(@PathVariable("page") int page, Model model) {
        return uiService.getBrowseView(model, page);
    }

    @GetMapping("/search")
    public String getSearch(Model model) {
        return uiService.getSearchView(model);
    }

    @PostMapping("/search")
    public String performSearch(@ModelAttribute SearchEntity searchEntity, Model model) {
        return uiService.getShowSearch(searchEntity, model);
    }

    @GetMapping("/add/recipe")
    public String getAdd(Model model) {
        return uiService.getAddView(model);
    }

    @GetMapping("/show/recipe/{id}")
    public String getRecipe(@PathVariable("id") Long id, Model model) {
        return uiService.getRecipeShowView(model, id);
    }

    @PostMapping("/submit")
    public String saveRecipe(@ModelAttribute Recipe recipe) {
        try {
            recipeValidatorService.validateRecipe(recipe);
            logger.info("saveRecipe" + recipe.getIngredientsWithAmounts().toString());
            return uiService.getSaveRecipe(recipe);
        } catch (ConstraintViolationException e) {
            return uiService.getErrorView();
        }
    }
}
