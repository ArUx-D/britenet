package com.miszczuk.dawid.britenet.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Person {
    private String name;
    private String surname;
    private int age;
    private String city;
    private List<String> extraInformationList;

    public Person(){
        //default constructor for Jackson Serializer
    };

    public Person(String name, String surname, int age, String city) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.city = city;
    }

    public void addExtraInformation(String extraInformation) {
        if (extraInformationList == null) {
            this.extraInformationList = new ArrayList<>();
        }

        this.extraInformationList.add(extraInformation);
    }
}
