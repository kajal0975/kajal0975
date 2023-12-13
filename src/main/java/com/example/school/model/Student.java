package com.example.school.model;

public class Student {
    private int StudentId;
    private String studentName;
    private int standard;
    private String gender;

    public Student(int studentId, String studentName, String gender, int standard) {
        this.StudentId = studentId;
        this.studentName = studentName;
        this.gender = gender;
        this.standard = standard;
    }

    public void setStudentId(int studentId) {
        this.StudentId = studentId;
    }

    public int getStudentId() {
        return StudentId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }

    public int getStandard() {
        return standard;
    }
}