import interfaces.IRecipe;
import interfaces.IRecipeCollection;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuHandler {
    private final IRecipeCollection recipeCollection;

    MenuHandler(IRecipeCollection recipeCollection) {
        this.recipeCollection = recipeCollection;
    }

    public void handle() {
        while (true) {
            ArrayList<String> menu = new ArrayList<>();

            menu.add("Recept:");
            for (IRecipe r : this.recipeCollection.findAll()) {
                menu.add(String.format("[%d] %s (%s)", r.getId(), r.getTitle(), r.getCategory()));
            }
            menu.add("\n[A] Lägg till ett recept");
            menu.add("[Q] Avsluta Receptsamling");

            String question = String.join("\n", menu);
            String answer = this.askForString(question);

            try {
                if (answer.equals("A") || answer.equals("a")) {
                    this.createRecipe();
                } else if (answer.equals("Q") || answer.equals("q")) {
                    System.exit(0);
                } else if (Integer.getInteger(answer) > 0) {
                    this.displayRecipeWithOptions(Integer.getInteger(answer));
                } else {
                    System.out.println("Felaktigt svar. Försök igen.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void createRecipe() {
        try {
            IRecipe recipe = this.insertRecipe();
            this.updateRecipe(recipe);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void displayRecipeWithOptions(int id) {
        IRecipe recipe = this.recipeCollection.findOne(id);

        String[] menu = {
                "[B] Tillbaka",
                "[E] Ändra",
                "[D] Ta bort"
        };
        String question = String.join(" | ", menu);

        while (true) {
            System.out.println(recipe.toString());
            String answer = this.askForString(question);

            switch (answer.toUpperCase()) {
                case "B":
                    return;
                case "E":
                    this.updateRecipe(recipe);
                case "D":
                    this.recipeCollection.delete(id);
                    return;
                default:
                    System.out.println("Felaktigt svar. Försök igen.");
            }
        }
    }

    private IRecipe insertRecipe() throws Exception {
        String title = this.askForString("Ange titel på receptet > ");

        String[] categoryMenu = {
                "Tillgängliga kategorier:",
                "[1] Frukost",
                "[2] Lunch",
                "[3] Middag",
                "Ange kategori > "
        };

        String question = String.join("\n", categoryMenu);
        int category = this.askForInteger(question, 1, 3);

        switch (category) {
            case 1:
                return this.recipeCollection.insert(new Breakfast(title));
            case 2:
                return this.recipeCollection.insert(new Lunch(title));
            case 3:
                return this.recipeCollection.insert(new Dinner(title));
            default:
                throw new Exception("Ett oväntat fel uppstod.");
        }
    }

    private void updateRecipe(IRecipe recipe) {
        String[] menu = {
                String.format("Ändra %s:", recipe.getTitle()),
                "[1] Ändra titel",
                "[2] Ändra instruktioner",
                // "[3] Lägg till ingrediens", TODO lägg till dessa funktionaliteter
                // "[4] Ändra ingrediens", TODO
                // "[5] Ta bort ingrediens", TODO
                "[0] Gå tillbaka"
        };

        while (true) {
            int choice = this.askForInteger(String.join("\n", menu), 0, 5);

            switch (choice) {
                case 1:
                    String title = this.askForString("Ange ny titel på receptet > ");
                    recipe.setTitle(title);
                    break;
                case 2:
                    String instructions = this.askForString("Ange nya instruktioner till receptet > ");
                    recipe.setInstructions(instructions);
                    break;
//                case 3:
//                    IIngredient ingredient = this.askForNewIngredient();
//                    recipe.insertIngredient(ingredient);
//                    break;
//                case 4:
//                    IIngredient ingredient = this.askForUpdatedIngredient(recipe);
//                    recipe.updateIngredient(ingredient);
//                    break;
//                case 5:
//                    int removal = this.askForExistingIngredient(recipe);
//                    recipe.deleteIngredient(removal);
//                    break;
                case 0:
                    return;
                default:
                    return;
            }

            recipe = this.recipeCollection.update(recipe);
        }
    }

    private String askForString(String question) {
        return this.askForString(question, 1, 9999);
    }

    private String askForString(String question, int minimum, int maximum) {
        Scanner input = new Scanner(System.in);
        while (true) {
            try {
                System.out.println(question);
                String answer = input.nextLine();
                if (answer.length() < minimum) {
                    throw new IllegalArgumentException(
                            String.format("Du måste ange en sträng som är längre eller exakt %d", minimum)
                    );
                }
                if (answer.length() > maximum) {
                    throw new IllegalArgumentException(
                            String.format("Du måste ange en sträng som är kortare eller exakt %d", maximum)
                    );
                }
                return answer;
            } catch (Exception e) {
                System.out.println(e.getMessage() + "\n");
            }
        }
    }

    private int askForInteger(String question) {
        return this.askForInteger(question, 1, 9999);
    }

    private int askForInteger(String question, int minimum, int maximum) {
        Scanner input = new Scanner(System.in);
        while (true) {
            try {
                System.out.println(question);
                int answer = input.nextInt();
                input.nextLine();
                if (answer < minimum) {
                    throw new IllegalArgumentException(
                            String.format("Du måste ange en siffra som är större eller lika med %d", minimum)
                    );
                }
                if (answer > maximum) {
                    throw new IllegalArgumentException(
                            String.format("Du måste ange en siffra som är mindre eller lika med %d", maximum)
                    );
                }
                return answer;
            } catch (Exception e) {
                System.out.println(e.getMessage() + "\n");
            }
        }
    }
}
