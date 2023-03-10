package org.example.Model;

public class Subject {
    private String Id;
    private  String subject_name;

    public Subject() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "Id='" + Id + '\'' +
                ", subject_name='" + subject_name + '\'' +
                '}';
    }
}
