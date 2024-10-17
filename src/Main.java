public class Main {
    public static void main(String[] args) {
        RecipeCollection recipeCollection = new RecipeCollection();
        MenuHandler menuHandler = new MenuHandler(recipeCollection);
        menuHandler.handle();
    }
}