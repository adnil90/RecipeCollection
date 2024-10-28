import interfaces.IRecipe;
import interfaces.IRecipeCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RecipeCollectionTest {
    private IRecipe recipe;
    private IRecipeCollection recipeCollection;

    @BeforeEach
    void setUp() {
        recipe = new Lunch("titel");
        recipeCollection = new RecipeCollection();
        recipeCollection.insert(recipe);
    }

    @Test
    void shouldInsertAndDisplayAllRecipes() {
        ArrayList<IRecipe> recipes = recipeCollection.findAll();
        assertEquals(1, recipes.size());
        assertEquals(1, recipes.getLast().getId());
        assertEquals("Lunch", recipes.getLast().getCategory());
        assertEquals("titel", recipes.getLast().getTitle());
    }

    @Test
    void shouldFindRecipe() {
        assertEquals(recipeCollection.findAll().getLast(), recipeCollection.findOne(1));
    }

    @Test
    void shouldUpdateRecipe() {
        recipeCollection.update(new Lunch(1, "ny titel", "nya instruktioner", recipe.getIngredients()));
        assertEquals("ny titel", recipeCollection.findOne(1).getTitle());
        assertEquals("nya instruktioner", recipeCollection.findOne(1).getInstructions());
    }

    @Test
    void shouldDeleteRecipe() {
        recipeCollection.delete(1);
        assertThrows(IllegalArgumentException.class, () -> recipeCollection.findOne(1));
    }

    @Test
    void shouldThrowExceptionWhenRecipeNotFound() {
        assertThrows(IllegalArgumentException.class, () -> recipeCollection.findOne(999));
    }
}
