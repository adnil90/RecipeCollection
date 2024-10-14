import interfaces.IIngredient;
import interfaces.IRecipe;

import java.util.ArrayList;

public abstract class Recipe implements IRecipe {
    private final int id;
    private final ArrayList<IIngredient> ingredients;
    private String title;
    private String instructions;

    public Recipe(int id, String title, String instructions, ArrayList<IIngredient> ingredients) {
        this.id = id;
        this.title = title;
        this.instructions = instructions;
        this.ingredients = ingredients;
    }

    public Recipe(String title, String instructions, ArrayList<IIngredient> ingredients) {
        this(-1, title, instructions, ingredients);
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getInstructions() {
        return this.instructions;
    }

    @Override
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Override
    public ArrayList<IIngredient> getIngredients() {
        return this.ingredients;
    }

    @Override
    public void insertIngredient(IIngredient ingredient) {
        this.ingredients.add(ingredient);
    }

    @Override
    public void updateIngredient(IIngredient ingredient) {
        for (int i = 0; i < this.ingredients.size(); i++) {
            if (this.ingredients.get(i).getId() != ingredient.getId()) continue;
            this.ingredients.set(i, ingredient);
            return;
        }
    }

    @Override
    public void deleteIngredient(int id) {
        for (int i = 0; i < this.ingredients.size(); i++) {
            if (this.ingredients.get(i).getId() != id) continue;
            this.ingredients.remove(i);
            return;
        }
    }

    public abstract String getCategory();
}
