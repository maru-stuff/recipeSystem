package marustuff.recipeSystem.Service;

import lombok.RequiredArgsConstructor;
import marustuff.recipeSystem.Data.DatabaseMapper;
import marustuff.recipeSystem.Data.IngredientWithAmount;
import marustuff.recipeSystem.Data.Recipe;
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

    @Autowired
    private final DatabaseMapper databaseMapper;

    @Autowired
    private final RecipeSystemService recipeSystemService;

    public String getRecipeShowView(Model model, Long id) {
        Recipe recipe = recipeSystemService.getRecipeById(id);
        model.addAttribute(RECIPE_ATTRIBUTE,recipe);
        return SHOW_VIEW;
    }

    public String getIndexView(Model model) {
        return INDEX_VIEW;
    }
//zbędny kod
    public String getRecipeAdd(Model model) {
        //////NOT IN USE
        Recipe recipe = new Recipe();
        for (int i = 1; i <= 3; i++) {
            recipe.addIngredientWithAmount(new IngredientWithAmount());
        }
        model.addAttribute(ADD_RECIPE_MODEL_ATTRIBUTE_NAME, recipe);
        return ADD_VIEW;
    }

    public String getRecentView(Model model) {
        List<Recipe> recentRecipes = databaseMapper.getNameIdRecipe();
        model.addAttribute(RECIPE_LIST_ATTRIBUTE,recentRecipes);
        return RECENT_VIEW;
    }
    public String getSaveRecipe(Recipe recipe){
        recipeSystemService.saveRecipe(recipe);
        return RECENT_VIEW;
    }
//zbędny kod
    /*public String getBrowseView(Model model, int page) {
        return BROWSE_VIEW;
    }*/

    public String getSearchView(Model model) {
        return SEARCH_VIEW;
    }

    public String getAddView(Model model) {
        Recipe recipe = new Recipe();
        //zbędny kod
        //for (int i = 1; i <= 3; i++) {
            recipe.addIngredientWithAmount(new IngredientWithAmount());
        //}
        model.addAttribute(ADD_RECIPE_MODEL_ATTRIBUTE_NAME, recipe);
        return ADD_VIEW;

    }

    public String getBrowseView(Model model, int pageNumber){
        int numberOfPages=recipeSystemService.getNumberOfRecipePages();
        //będziesz tego używał?
        int previousPage;
        //będziesz tego używał?
        int nextPage;
        List<Recipe> pageOfRecipes = databaseMapper.getNameIdRecipeByPage(RECIPE_PAGE_SIZE*pageNumber,pageNumber);
        // nie lepiej byłoby to obsłużyć na frontendzie?
        if(numberOfPages>1){
            if(pageNumber==0){
                model.addAttribute(NEXT_PAGE_ATTRIBUTE,nextPage=pageNumber+1);
            } else if(pageNumber+1==numberOfPages) {
                model.addAttribute(PREVIOUS_PAGE_ATTRIBUTE,previousPage=pageNumber-1);
            }else{
                model.addAttribute(PREVIOUS_PAGE_ATTRIBUTE,previousPage=pageNumber-1);
                model.addAttribute(NEXT_PAGE_ATTRIBUTE,nextPage=pageNumber+1);
            }
        }
        model.addAttribute(RECIPE_LIST_ATTRIBUTE,pageOfRecipes);
        model.addAttribute(PAGE_NUMBER_ATTRIBUTE,pageNumber);
        model.addAttribute(NUMBER_OF_PAGES_ATTRIBUTE,numberOfPages);
        return BROWSE_VIEW;
    }
}
