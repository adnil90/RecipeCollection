import interfaces.IRecipe;
import interfaces.IRecipeCollection;

import java.util.ArrayList;

public class RecipeCollection implements IRecipeCollection {
    private final ArrayList<IRecipe> recipes;
    private final String file;

    public RecipeCollection() {
        this.file = "";
        this.recipes = new ArrayList<>();
    }

    public RecipeCollection(String file) {
        this.file = file;
        this.recipes = FileHandler.loadFromFile(file);
    }

    @Override
    public IRecipe insert(IRecipe recipe) {
        for (IRecipe r : recipes) {
            if (r.getId() != recipe.getId()) continue;
            throw new IllegalArgumentException("Ett recept med samma ID finns redan.");
        }

        IRecipe newRecipe = this.cloneRecipeWithNewId(recipe);

        this.recipes.add(newRecipe);
        FileHandler.saveToFile(this.file, this.recipes);

        return newRecipe;
    }

    @Override
    public IRecipe update(IRecipe recipe) {
        for (int i = 0; i < this.recipes.size(); i++) {
            if (this.recipes.get(i).getId() != recipe.getId()) continue;
            this.recipes.set(i, recipe);
            FileHandler.saveToFile(this.file, this.recipes);
            return recipe;
        }
        throw new IllegalArgumentException("Receptet du försökte uppdatera existerar inte i samlingen.");
    }

    @Override
    public void delete(int id) {
        recipes.removeIf(recipe -> recipe.getId() == id);
        FileHandler.saveToFile(this.file, this.recipes);
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
