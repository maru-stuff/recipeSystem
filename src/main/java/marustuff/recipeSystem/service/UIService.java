package marustuff.recipeSystem.Service;

import lombok.RequiredArgsConstructor;
import marustuff.recipeSystem.Data.ResourceMapper;
import marustuff.recipeSystem.Data.IngredientWithAmount;
import marustuff.recipeSystem.Data.Recipe;
import marustuff.recipeSystem.Data.ResourceMapper;
import marustuff.recipeSystem.Data.SearchEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UIService {
    private static final String INDEX_VIEW = "/ui/recent";
    private static final String RECENT_VIEW = "/ui/recent";
    private static final String BROWSE_VIEW = "/ui/browse";
    private static final String SEARCH_VIEW = "/ui/search";
    private static final String SHOW_VIEW = "/ui/show";
    private static final String ADD_VIEW = "/ui/add";
    private static final String RECIPE_LIST_ATTRIBUTE = "recipeList";
    private static final String RECIPE_ATTRIBUTE = "recipe";
    private static final String ADD_RECIPE_MODEL_ATTRIBUTE_NAME = "recipe";
    private static final int RECIPE_PAGE_SIZE = 5;
    private static final String PAGE_NUMBER_ATTRIBUTE = "pageNumber";
    private static final String NUMBER_OF_PAGES_ATTRIBUTE = "numberOfPages";
    private static final String NEXT_PAGE_ATTRIBUTE = "nextPage";
    private static final String PREVIOUS_PAGE_ATTRIBUTE = "previousPage";
    private static final String SEARCH_ENTITY_ATTRIBUTE = "searchEntity";
    private static final String FOUND_VIEW = "/ui/found";

    @Autowired
    private final ResourceMapper resourceMapper;

    @Autowired
    private final RecipeSystemService recipeSystemService;

    public String getRecipeShowView(Model model, Long id) {
        Recipe recipe = recipeSystemService.getRecipeById(id);
        model.addAttribute(RECIPE_ATTRIBUTE, recipe);
        return SHOW_VIEW;
    }

    public String getIndexView(Model model) {
        return INDEX_VIEW;
    }

    public String getRecentView(Model model) {
        List<Recipe> recentRecipes = resourceMapper.getNameIdRecipe();
        model.addAttribute(RECIPE_LIST_ATTRIBUTE, recentRecipes);
        return RECENT_VIEW;
    }

    public String getSaveRecipe(Recipe recipe) {
        recipeSystemService.saveRecipe(recipe);
        return RECENT_VIEW;
    }


    public String getSearchView(Model model) {
        SearchEntity searchEntity = new SearchEntity();
        model.addAttribute(SEARCH_ENTITY_ATTRIBUTE, searchEntity);
        return SEARCH_VIEW;
    }

    public String getAddView(Model model) {
        Recipe recipe = new Recipe();
        recipe.addIngredientWithAmount(new IngredientWithAmount());
        model.addAttribute(ADD_RECIPE_MODEL_ATTRIBUTE_NAME, recipe);
        return ADD_VIEW;

    }

    public String getBrowseView(Model model, int pageNumber) {
        int numberOfPages = recipeSystemService.getNumberOfRecipePages();
        List<Recipe> pageOfRecipes = resourceMapper.getNameIdRecipeByPage(RECIPE_PAGE_SIZE * pageNumber, pageNumber);
        if (numberOfPages > 1) {
            if (pageNumber == 0) {
                model.addAttribute(NEXT_PAGE_ATTRIBUTE,pageNumber + 1);
            } else if (pageNumber + 1 == numberOfPages) {
                model.addAttribute(PREVIOUS_PAGE_ATTRIBUTE,pageNumber - 1);
            } else {
                model.addAttribute(PREVIOUS_PAGE_ATTRIBUTE,pageNumber - 1);
                model.addAttribute(NEXT_PAGE_ATTRIBUTE,pageNumber + 1);
            }
        }
        model.addAttribute(RECIPE_LIST_ATTRIBUTE, pageOfRecipes);
        model.addAttribute(PAGE_NUMBER_ATTRIBUTE, pageNumber);
        model.addAttribute(NUMBER_OF_PAGES_ATTRIBUTE, numberOfPages);
        return BROWSE_VIEW;
    }

    public String getShowSearch(SearchEntity searchEntity, Model model) {
        List<Recipe> foundRecipes = recipeSystemService.searchForRecipes(searchEntity);
        model.addAttribute("recipeList", foundRecipes);
        return FOUND_VIEW;


    }
}
