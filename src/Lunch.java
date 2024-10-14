import interfaces.IIngredient;
import interfaces.IRecipe;

import java.util.ArrayList;

public class Lunch extends RecipeBase implements IRecipe {
    public Lunch(int id, String title, String instructions, ArrayList<IIngredient> ingredients) {
        super(id, title, instructions, ingredients);
    }

    public Lunch(String title, String instructions, ArrayList<IIngredient> ingredients) {
        super(title, instructions, ingredients);
    }

    public Lunch(String title) {
        super(title);
    }

    @Override
    public String getCategory() {
        return "Lunch";
    }
}
