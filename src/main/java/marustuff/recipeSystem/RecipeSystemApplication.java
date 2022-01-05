package marustuff.recipeSystem;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class RecipeSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecipeSystemApplication.class, args);
    }

}


