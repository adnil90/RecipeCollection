import interfaces.IIngredient;
import interfaces.IRecipe;

import java.util.ArrayList;

public class Dinner extends RecipeBase implements IRecipe {
    public Dinner(int id, String title, String instructions, ArrayList<IIngredient> ingredients) {
        super(id, title, instructions, ingredients);
    }

    public Dinner(String title, String instructions, ArrayList<IIngredient> ingredients) {
        super(title, instructions, ingredients);
    }

    public Dinner(String title) {
        super(title);
    }

    @Override
    public String getCategory() {
        return "Middag";
    }
}
