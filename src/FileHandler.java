import interfaces.IRecipe;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    public static void saveToFile(String file, ArrayList<IRecipe> recipes) {
        if (file.isEmpty()) return;
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(recipes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<IRecipe> loadFromFile(String file) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (ArrayList<IRecipe>) in.readObject();
        } catch (IOException e) {
            return new ArrayList<>();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
