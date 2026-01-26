package com.luchersol.cli.example;

public class Person {
    private String name;
    private int age;

    public boolean hasName() {
        return name != null;
    }

    public boolean isGreaterAge(int age) {
        return this.age > age;
    }
}
