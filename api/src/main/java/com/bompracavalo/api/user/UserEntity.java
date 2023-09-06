package com.bompracavalo.api.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @Column(name = "use_sub")
    private String sub;

    @Column(name = "use_name")
    private String name;

    @Column(name = "use_email")
    private String email;

    @Column(name = "use_image")
    private String image;

    public UserEntity() {
    }

    public UserEntity(String sub, String name, String email, String image) {
        this.sub = sub;
        this.name = name;
        this.email = email;
        this.image = image;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
