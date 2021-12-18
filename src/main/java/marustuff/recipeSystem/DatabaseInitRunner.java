package marustuff.recipeSystem;

import lombok.RequiredArgsConstructor;
import marustuff.recipeSystem.Data.ResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseInitRunner implements CommandLineRunner {
    @Autowired
    private final ResourceMapper resourceMapper;

    @Override
    public void run(String... args) {
        if (resourceMapper.isRecipesTablePresent().equals("0")) {
            resourceMapper.createRecipesTable();
        }
        if (resourceMapper.isIngredientsTablePresent().equals("0")) {
            resourceMapper.createIngredientsTable();
        }
        if (resourceMapper.isRecipesIngredientsAmountsTablePresent().equals("0")) {
            resourceMapper.createRecipesIngredientsAmountsTable();
        }

    }


}

