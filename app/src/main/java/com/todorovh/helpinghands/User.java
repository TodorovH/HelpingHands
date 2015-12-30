package com.todorovh.helpinghands;

public class User {
    String name, city, username,
            password, address, phone, email;
    int age;

    public User (
            String name,
            int age,
            String city,
            String username,
            String password,
            String address,
            String phone,
            String email
    ){
        this.name = name;
        this.age = age;
        this.city = city;
        this.username = username;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public User (
            String name,
            int age,
            String city,
            String username,
            String address,
            String phone,
            String email
    ){
        this.name = name;
        this.age = age;
        this.city = city;
        this.username = username;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public User (
            String name,
            int age,
            String city,
            String username,
            String password
    ){
        this.name = name;
        this.age = age;
        this.city = city;
        this.username = username;
        this.password = password;
        this.address = "";
        this.phone = "";
        this.email = "";
    }

    public User (
            String username,
            String password
    ){
        this.username = username;
        this.password = password;
        this.name = "";
        this.age = -1;
        this.city = "";
        this.address = "";
        this.phone = "";
        this.email = "";
    }

    public User (String username){
        this.username = username;
        this.password = "";
        this.name = "";
        this.age = -1;
        this.city = "";
        this.address = "";
        this.phone = "";
        this.email = "";
    }
}
