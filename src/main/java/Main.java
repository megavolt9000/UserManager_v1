import database.DatabaseManager;
import model.User;
import service.UserService;
import util.FileUtil;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        DatabaseManager.createTableIfNotExist();

        UserService service = new UserService();

        service.addUser(new User("Ivan", 25, "ivan@mail.com"));
        service.addUser(new User("Alex", 30, "alex@mail.com"));
        service.addUser(new User("Petr", 35, "petr@mail.com"));

        System.out.println("Все пользователи : ");
        service.printALL();

        System.out.println("\nСортировка по имени: ");
       service.sortByName().forEach((System.out::println));


        System.out.println("\nСортировка по возрасту: ");
        service.sortByAge().forEach(System.out::println);

        System.out.println("\nПоиск пользователя по имени 'Alex' :");
        service.findByName("Alex")
                .ifPresentOrElse(
                        user -> System.out.println("Найден " + user),
                        () -> System.out.println("Не найден")
                );
        System.out.println("\nУдаление пользователя с email 'alex@mail.com' ");
        boolean removed = service.removeByEmail("alex@mail.com");
        System.out.println(removed ? "Удален" : "Не найден");


        System.out.println("\nПосле удаления: ");
        service.printALL();





    }
}