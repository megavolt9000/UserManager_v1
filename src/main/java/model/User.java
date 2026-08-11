package model;

import java.io.Serializable;
import java.util.Objects;

public class User implements Comparable<User>, Serializable {
    private String name;
    private int age;
    String email;

    public User(String name, int age, String email) {

        this.name = name;
        this.age = age;
        this.email = email;
    }

    //Getters & Setters

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;

    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;

    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;

    }

    @Override

    public int compareTo(User other) {
        return this.name.compareTo(other.name); // Sort by name
    }

    @Override

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof User))
            return false;
        User user = (User) o;
        return Objects.equals(email, user.email);

    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override

    public String toString() {
        return String.format("%s (%d - %s)", name, age, email);
    }


}
