package com.pgs.java_dev.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Customer")
public class User {

    @Id
    @GeneratedValue
    private long id;
    @NotNull
    @Size(min = 2, max = 25, message = "nickName must be between 2 and 25 characters")
    @Column(name = "nick")
    private String nickName;
    private String email;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User() {
    }

    public User(long id, String nickName, String email) {
        this.id = id;
        this.nickName = nickName;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
