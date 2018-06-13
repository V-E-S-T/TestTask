package com.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "users")
public class User extends AbstractEntity {

    @Column(name = "firstName", nullable = false)
    @NotBlank
    @Size(max = 100)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    @NotBlank
    @Size(min = 5, max = 100)
    private String lastName;

    @Column(name = "birthDay", nullable = false)
    @NotNull
    private Date birthDay;

    @Column(name = "male", nullable = false)
    private boolean male;

    public User() {
    }

    public User(User u) {
        this(u.getId(), u.getFirstName(), u.getLastName(), u.getBirthDay(), u.isMale());
    }

    public User(Integer id, String firstName, String lastName, Date birthDay, boolean male) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.male = male;
    }

    public String getFirstName()  {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public boolean isMale() {
        return male;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {

        String gender = male ? "male" : "female";

        return "User{" +
                "id=" + id +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", birthDay=" + birthDay +
                ", gender=" + gender;
    }


}
