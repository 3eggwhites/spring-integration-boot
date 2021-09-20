package com.integration.boot.sprintintegrationboot.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter @NoArgsConstructor
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private int age;

    @Override
    public String toString() {
        return "Student Object: { "
                + "id: "
                + this.id
                + " name: "
                + this.name
                + " age: "
                + String.valueOf(this.age)
                + " }";
    }
}
