import interfaces.IIngredient;
import interfaces.IRecipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeTest {
    private IRecipe recipe;
    private IIngredient ingredient1;
    private IIngredient ingredient2;

    @BeforeEach
    void setUp() {
        ingredient1 = new Ingredient(1, 8.5f, "dl", "Multisodiumglutamat");
        ingredient2 = new Ingredient(2, 5f, "g", "Salt");
        ArrayList<IIngredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        recipe = new Lunch(1, "titel", "instruktioner", ingredients);
    }

    @Test
    void shouldReturnValues() {
        assertEquals(1, recipe.getId());
        assertEquals("titel", recipe.getTitle());
        assertEquals("instruktioner", recipe.getInstructions());
    }

    @Test
    void shouldReturnCategoryForBreakfast() {
        recipe = new Breakfast("titel");
        assertEquals("Frukost", recipe.getCategory());
    }

    @Test
    void shouldReturnCategoryForLunch() {
        recipe = new Lunch("titel");
        assertEquals("Lunch", recipe.getCategory());
    }

    @Test
    void shouldReturnCategoryForDinner() {
        recipe = new Dinner("titel");
        assertEquals("Middag", recipe.getCategory());
    }

    @Test
    void shouldUpdateTitle() {
        recipe.setTitle("New Title");
        assertEquals("New Title", recipe.getTitle());
    }

    @Test
    void shouldUpdateInstructions() {
        recipe.setInstructions("New Instructions");
        assertEquals("New Instructions", recipe.getInstructions());
    }

    @Test
    void shouldReturnIngredients() {
        ArrayList<IIngredient> ingredients = recipe.getIngredients();
        assertEquals(2, ingredients.size());
        assertTrue(ingredients.contains(ingredient1));
        assertTrue(ingredients.contains(ingredient2));
    }

    @Test
    void shouldInsertIngredient() {
        IIngredient newIngredient = new Ingredient(2f, "tesked", "Socker");
        recipe.insertIngredient(newIngredient);
        assertEquals(3, recipe.getIngredients().size());
        assertEquals(3, recipe.getIngredients().getLast().getId());
        assertEquals(2f, recipe.getIngredients().getLast().getAmount());
        assertEquals("tesked", recipe.getIngredients().getLast().getMeasurement());
        assertEquals("Socker", recipe.getIngredients().getLast().getName());
    }

    @Test
    void shouldUpdateIngredient() {
        recipe.updateIngredient(new Ingredient(1, 10f, "dl", "Multisodiumglutamat"));
        assertEquals(10.0f, recipe.getIngredients().get(0).getAmount());
    }

    @Test
    void shouldDeleteIngredient() {
        recipe.deleteIngredient(1);
        assertEquals(1, recipe.getIngredients().size());
        assertFalse(recipe.getIngredients().contains(ingredient1));
    }

    @Test
    void shouldReturnFormattedString() {
        assertEquals("Titel: titel\nKategori: Lunch\nInstruktioner:\ninstruktioner\nIngredienser:\n8.5 dl Multisodiumglutamat\n5 g Salt", recipe.toString());
    }
}

