package util;

import model.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileUtil {


    private static final String FILE_NAME = "users.txt";

    public static void saveUsers(List<User> users) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(FILE_NAME))) {
            oos.writeObject(users);
        }

    }

    @SuppressWarnings("Unchecked")

    public static List<User> loadUsers() throws IOException, ClassNotFoundException {
        Path path = Path.of(FILE_NAME);
        if (!Files.exists(path)) {
            return List.of();  // Empty List

        }
        try (ObjectInputStream ois = new ObjectInputStream(

                new FileInputStream(FILE_NAME))) {
            return (List<User>) ois.readObject();
        }
    }
}














