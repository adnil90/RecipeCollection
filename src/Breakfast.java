import interfaces.IIngredient;
import interfaces.IRecipe;

import java.util.ArrayList;

public class Breakfast extends RecipeBase implements IRecipe {
    public Breakfast(int id, String title, String instructions, ArrayList<IIngredient> ingredients) {
        super(id, title, instructions, ingredients);
    }

    public Breakfast(String title, String instructions, ArrayList<IIngredient> ingredients) {
        super(title, instructions, ingredients);
    }

    public Breakfast(String title) {
        super(title);
    }

    @Override
    public String getCategory() {
        return "Frukost";
    }
}
