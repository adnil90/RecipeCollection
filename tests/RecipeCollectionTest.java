import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class RecipeCollectionTest {
    private IRecipe recipe;
    private IRecipeCollection recipeCollection;

    @BeforeEach
    void setUp() {
        recipe = new Lunch(1, "titel", "instruktioner", ingredients);
        recipeCollection = new RecipeCollection();
        recipeCollection.insert(recipe);
    }

    @Test
    void shouldInsertAndDisplayAllRecipes() {
        ArrayList<IRecipe> recipes = new ArrayList<IRecipe>();
        recipes.add(recipe);
        assertEquals(recipes, recipeCollection.all());
    }

    @Test
    void shouldFindRecipe() {
        assertEquals(recipe, recipeCollection.find(1));
    }

    @Test
    void shouldDeleteRecipe() {
        recipeCollection.delete(1);
        assertNull(recipeCollection.find(1));
    }
}

