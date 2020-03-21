package ru.liga.intership.badcode.domain;

public class Person {
    private final Long id;
    private final String sex;
    private final String name;
    private final Long age;
    private final Long weight;
    private final Long height;

    public Person(Long id, String sex, String name, Long age, Long weight, Long height) {
        this.id = id;
        this.sex = sex;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    public Person() {
        this.id = 0L;
        this.sex = "";
        this.name = "";
        this.age = 0L;
        this.weight = 0L;
        this.height = 0L;
    }

    public Long getId() {
        return id;
    }

    public String getSex() {
        return sex;
    }

    public String getName() {
        return name;
    }

    public Long getWeight() {
        return weight;
    }

    public Long getHeight() {
        return height;
    }

    public Long getAge() {
        return age;
    }
}
