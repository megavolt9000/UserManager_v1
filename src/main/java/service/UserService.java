package service;

import database.DatabaseManager;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        String sql = "INSERT INTO users (name, age, email) VALUES (?,?,?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getName());
            pstmt.setInt(2, user.getAge());
            pstmt.setString(3, user.getEmail());
            pstmt.executeUpdate();
            System.out.println("Пользватель добавлен");
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении " + e.getMessage());

        }

    }

    //READ ALL
    public List<User> getALLUsers() {

        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                User user = new User(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("email")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при чтении" + e.getMessage());
        }
        return users;
    }

    //FIND BY NAME
    public Optional<User> findByName(String name) {
        String sql = "SELECT * FROM users WHERE LOWER(name) = LOWER(?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                User user = new User(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("email")
                );
                return Optional.of(user);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при поиске" + e.getMessage());
            ;
        }
        return Optional.empty();
    }

    //DELETE BY EMAIL
    public boolean removeByEmail(String email) {
        String sql = "DELETE FROM users WHERE email = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении" + e.getMessage());
            return false;
        }
    }

    //SORT BY NAME
    public List<User> sortByName() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users ORDER BY name";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                users.add(new User(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("email")
                ));

            }
        } catch (SQLException e) {
            System.out.println("Ошибка при сортировке " + e.getMessage());
        }
        return users;
    }

    //SORT BY AGE
    public List<User> sortByAge() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users ORDER BY age";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                users.add(new User(
                        rs.getString("name"),
                        rs.getInt(("age")),
                        rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при сортировке" + e.getMessage());
        }
        return users;
    }

    // PRINT ALL USERS
    public void printALL() {
        List<User> users = getALLUsers();
        if (users.isEmpty()) {
            System.out.println("Список пользователей пуст");
        } else {
            users.forEach(System.out::println);
        }
    }
}
