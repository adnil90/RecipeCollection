import interfaces.IIngredient;
import interfaces.IRecipe;

import java.util.ArrayList;

public abstract class RecipeBase implements IRecipe {
    private final int id;
    private final ArrayList<IIngredient> ingredients;
    private String title;
    private String instructions;

    protected RecipeBase(int id, String title, String instructions, ArrayList<IIngredient> ingredients) {
        this.id = id;
        this.title = title;
        this.instructions = instructions;
        this.ingredients = ingredients;
    }

    protected RecipeBase(String title, String instructions, ArrayList<IIngredient> ingredients) {
        this(-1, title, instructions, ingredients);
    }

    protected RecipeBase(String title) {
        this(-1, title, "", new ArrayList<>());
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
        for (IIngredient i : ingredients) {
            if (i.getId() != ingredient.getId()) continue;
            throw new IllegalArgumentException("En ingrediens med samma ID finns redan.");
        }

        IIngredient newIngredient = new Ingredient(
                this.getNextId(),
                ingredient.getAmount(),
                ingredient.getMeasurement(),
                ingredient.getName()
        );

        this.ingredients.add(newIngredient);
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

    @Override
    public String toString() {
        // TODO: Bygg denna override metoden så att den fungerar som i testet.
        return "";
    }

    public abstract String getCategory();

    private int getNextId() {
        if (this.ingredients.isEmpty()) {
            return 1;
        } else {
            return this.ingredients.getLast().getId() + 1;
        }
    }
}
