package com.example.demo.models;
import jakarta.persistence.*;



@Entity
@Table(name="person")
public class Person {
    @Id // Indica a nuestra app que es una PRIMARY key
    @GeneratedValue(strategy=GenerationType.IDENTITY)//Notación para indicar que es autoincremental
    private long id;

    @Column(name ="name")
    String name;

    @Column(name = "age")
    int age;


    public Person() {} // Necesario el contructor vacio

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}


