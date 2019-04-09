package mate.academy.model;

import java.io.Serializable;

public class Human implements Model, Serializable {
    private String name;
    private int age;

    public Human(String name, int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
