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
        ArrayList<IRecipe> recipes = recipeCollection.findAll();
        assertEquals(1, recipes.size());
        assertTrue(recipes.contains(recipe));
    }

    @Test
    void shouldFindRecipe() {
        assertEquals(recipe, recipeCollection.findOne(1));
    }

    @Test
    void shouldDeleteRecipe() {
        recipeCollection.delete(1);
        assertThrows(recipeCollection.findOne(1));
    }
}

