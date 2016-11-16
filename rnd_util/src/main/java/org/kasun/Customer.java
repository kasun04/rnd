package org.kasun;

/**
 * Created by kasun on 11/16/16.
 */
public class Customer {
    private String name;
    private String id;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Customer(String name, String id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }
}
