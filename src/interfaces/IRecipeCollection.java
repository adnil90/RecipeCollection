package interfaces;

import java.util.ArrayList;

public interface IRecipeCollection {
    void insert(IRecipe recipe);
    void delete(String id);
    IRecipe findOne(String id);
    ArrayList<IRecipe> findAll();
}
