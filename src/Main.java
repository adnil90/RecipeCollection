import interfaces.IRecipeCollection;

public class Main {
    public static void main(String[] args) {
        IRecipeCollection recipeCollection = new RecipeCollection("recipes.ser");
        MenuHandler menuHandler = new MenuHandler(recipeCollection);
        menuHandler.handle();
    }
}