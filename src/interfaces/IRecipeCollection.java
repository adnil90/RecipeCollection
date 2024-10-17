package interfaces;

import java.util.ArrayList;

public interface IRecipeCollection {
    IRecipe insert(IRecipe recipe);

    IRecipe update(IRecipe recipe);

    void delete(int id);

    IRecipe findOne(int id);

    ArrayList<IRecipe> findAll();
}
