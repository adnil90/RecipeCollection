import interfaces.IRecipe;
import interfaces.IRecipeCollection;

import java.util.Scanner;

public class MenuHandler {
    private final IRecipeCollection recipeCollection;

    MenuHandler(IRecipeCollection recipeCollection) {
        this.recipeCollection = recipeCollection;
    }

    private IRecipe createNewRecipe() throws Exception {
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
