package com.hamsoace.AppForAll.models;

public class Marks {
    private int id;
    private String firstName;
    private String lastName;

    private String course;
    private String credits;
    private String marks;

    public Marks(String firstName, String lastName, String course, String credits, String marks) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.course = course;
        this.credits = credits;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }
}
