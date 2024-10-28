import interfaces.IIngredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTest {
    private IIngredient ingredient;

    @BeforeEach
    public void setUp() {
        ingredient = new Ingredient(1, 8.5f, "dl", "Multisodiumglutamat");
    }

    @Test
    void shouldAllowOverloadingWithDefaultId() {
        ingredient = new Ingredient(8.5f, "dl", "Multisodiumglutamat");
        assertEquals(-1, ingredient.getId());
        assertEquals(8.5f, ingredient.getAmount());
        assertEquals("dl", ingredient.getMeasurement());
        assertEquals("Multisodiumglutamat", ingredient.getName());
    }

    @Test
    void shouldReturnValues() {
        assertEquals(1, ingredient.getId());
        assertEquals(8.5f, ingredient.getAmount());
        assertEquals("dl", ingredient.getMeasurement());
        assertEquals("Multisodiumglutamat", ingredient.getName());
    }

    @Test
    void shouldReturnFormattedString() {
        assertEquals("8.5 dl Multisodiumglutamat", ingredient.toString());
    }

    @Test
    void shouldSetAmount() {
        ingredient.setAmount(3);
        assertEquals(3, ingredient.getAmount());
        assertEquals("3 dl Multisodiumglutamat", ingredient.toString());
    }

    @Test
    void shouldSetMeasurement() {
        ingredient.setMeasurement("cl");
        assertEquals("cl", ingredient.getMeasurement());
        assertEquals("8.5 cl Multisodiumglutamat", ingredient.toString());
    }

    @Test
    void shouldSetName() {
        ingredient.setName("Salt");
        assertEquals("Salt", ingredient.getName());
        assertEquals("8.5 dl Salt", ingredient.toString());
    }
}
