import interfaces.IRecipe;
import interfaces.IRecipeCollection;

import java.util.ArrayList;

public class RecipeCollection implements IRecipeCollection {
    private final ArrayList<IRecipe> recipes;

    public RecipeCollection() {
        this.recipes = new ArrayList<>();
    }

    // TODO gör klart insert metod
    @Override
    public void insert(IRecipe recipe) {
        for (IRecipe r : recipes) {
            if (r.getId() != recipe.getId()) continue;
            throw new IllegalArgumentException("Ett recept med samma ID finns redan.");
        }


        this.recipes.add(recipe);
    }

    // TODO gör klart delete metod
    @Override
    public void delete(int id) {
        recipes.removeIf(recipe -> recipe.getId() == id);
    }

    @Override
    public IRecipe findOne(int id) {
        for (IRecipe recipe : recipes) {
            if (recipe.getId() == id) {
                return recipe;
            }
        }
        throw new IllegalArgumentException("Det receptet finns inte.");
    }

    @Override
    public ArrayList<IRecipe> findAll() {
        return new ArrayList<>(recipes);
    }
}
