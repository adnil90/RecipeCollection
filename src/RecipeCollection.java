import interfaces.IRecipe;
import interfaces.IRecipeCollection;

import java.util.ArrayList;

public class RecipeCollection implements IRecipeCollection {
    private final ArrayList<IRecipe> recipes;

    public RecipeCollection() {
        this.recipes = new ArrayList<>();
    }

    @Override
    public void insert(IRecipe recipe) {
        for (IRecipe r : recipes) {
            if (r.getId() != recipe.getId()) continue;
            throw new IllegalArgumentException("Ett recept med samma ID finns redan.");
        }

        IRecipe newRecipe = this.cloneRecipeWithNewId(recipe);

        this.recipes.add(newRecipe);
    }

    @Override
    public void update(IRecipe recipe) {
        for (int i = 0; i < this.recipes.size(); i++) {
            if (this.recipes.get(i).getId() != recipe.getId()) continue;
            this.recipes.set(i, recipe);
            return;
        }
    }

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

    private int getNextId() {
        if (this.recipes.isEmpty()) {
            return 1;
        } else {
            return this.recipes.getLast().getId() + 1;
        }
    }

    private IRecipe cloneRecipeWithNewId(IRecipe recipe) {
        switch (recipe.getCategory()) {
            case "Frukost":
                return new Breakfast(
                        this.getNextId(),
                        recipe.getTitle(),
                        recipe.getInstructions(),
                        recipe.getIngredients()
                );

            case "Lunch":
                return new Lunch(
                        this.getNextId(),
                        recipe.getTitle(),
                        recipe.getInstructions(),
                        recipe.getIngredients()
                );

            case "Middag":
                return new Dinner(
                        this.getNextId(),
                        recipe.getTitle(),
                        recipe.getInstructions(),
                        recipe.getIngredients()
                );

            default:
                throw new IllegalArgumentException("Receptet har felaktig Kategori.");
        }
    }
}
