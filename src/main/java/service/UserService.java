package main.java.service;

import main.java.model.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class UserService {
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);

    }

    public List<User> getALLUsers() {

        return new ArrayList<>(users);
    }

    public Optional<User> findByName(String name) {
        return users.stream()
                .filter(u -> u.getName().equalsIgnoreCase(name))
                .findFirst();

    }

    public boolean removeByEmail(String email) {
        return users.removeIf(u -> u.getEmail().equals(email));

    }

    public void sortByName() {
        users.sort(Comparator.comparing(User::getName));
    }

    public void sortByAge() {
        users.sort(Comparator.comparing(User::getAge));

    }

    public void printALL() {
        users.forEach(System.out::println);


    }


}
