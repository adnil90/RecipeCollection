package interfaces;

import java.util.ArrayList;

public interface IRecipe {
    int getId();
    String getCategory();

    String getTitle();
    void setTitle(String title);

    String getInstructions();
    void setInstructions(String instructions);

    ArrayList<IIngredient> getIngredients();
    void insertIngredient(IIngredient ingredient);
    void updateIngredient(IIngredient ingredient);
    void deleteIngredient(int id);

    String toString();
}
