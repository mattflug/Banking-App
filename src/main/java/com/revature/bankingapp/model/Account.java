package com.revature.bankingapp.model;

import javax.persistence.*;
import java.util.Objects;

@Entity // Marks this class as an entity that needs to be stored in the database
@Table(name = "list")
// Allows us to add in config info for the table that will be created by Spring Data and Hibernate
public class ToDo {

    @Id // Denotes this as the primary key field
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // This will automatically create a pk that is unique and auto-increments
    private int id; // This will be the primary key field in my database

    private String text;

    @Column(name = "completed")
    // This is similar to the @Table annotation allowing us to set config info for the column instead
    private boolean isCompleted;


    public ToDo(){
    }

    // Recall that getters are used to retrieve the value of an instance variable that has been marked private
    public int getId(){
        return this.id;
    }

    // Recall setters are used to set the value of an instance variable, we can provide additional logic in these
    // methods so the value is never something unexpected (like a negative number for scoops available)
    public void setId(int id){
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDo toDo = (ToDo) o;
        return id == toDo.id && isCompleted == toDo.isCompleted && Objects.equals(text, toDo.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, isCompleted);
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
