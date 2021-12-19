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
        if (!resourceMapper.isRecipesTablePresent()) {
            resourceMapper.createRecipesTable();
        }
        if (!resourceMapper.isIngredientsTablePresent()) {
            resourceMapper.createIngredientsTable();
        }
        if (!resourceMapper.isRecipesIngredientsAmountsTablePresent()) {
            resourceMapper.createRecipesIngredientsAmountsTable();
        }

    }


}

