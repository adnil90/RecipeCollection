package interfaces;

import java.util.ArrayList;

public interface IRecipeCollection {
    void insert(IRecipe recipe);

    void delete(int id);

    IRecipe findOne(int id);

    ArrayList<IRecipe> findAll();
}
