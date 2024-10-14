import interfaces.IIngredient;
import interfaces.IRecipe;

import java.util.ArrayList;

public class Lunch extends RecipeBase implements IRecipe {
    protected Lunch(int id, String title, String instructions, ArrayList<IIngredient> ingredients) {
        super(id, title, instructions, ingredients);
    }

    protected Lunch(String title, String instructions, ArrayList<IIngredient> ingredients) {
        super(title, instructions, ingredients);
    }

    @Override
    public String getCategory() {
        return "Lunch";
    }
}
