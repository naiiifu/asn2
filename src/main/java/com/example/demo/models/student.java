package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name="students")
public class student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    private String sid;
    private String name;
    private String height;
    private String weight;
    private String haircolor;
    private String gpa;
    public int getUid() {
        return uid;
    }
    public String getSid() {
        return sid;
    }
    public String getName() {
        return name;
    }
    public String getHeight() {
        return height;
    }
    public String getWeight() {
        return weight;
    }
    public String getHaircolor() {
        return haircolor;
    }
    public String getGpa() {
        return gpa;
    }
    
    public void setUid(int uid) {
        this.uid = uid;
    }
    public void setSid(String sid) {
        this.sid = sid;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setHeight(String height) {
        this.height = height;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }
    public void setHaircolor(String haircolor) {
        this.haircolor = haircolor;
    }
    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    //constructor
    public student() {
    }
    public student(String sid, String name, String height, String weight, String haircolor, String gpa) {
        this.name = name;
        this.sid = sid;
        this.height = height;
        this.weight = weight;
        this.haircolor = haircolor;
        this.gpa = gpa;
    }
}
