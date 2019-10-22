package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * CustomerList
 */
@Entity
public class CustomerList extends AbstractEntity{
    @Column(nullable = false, unique = true)
    @JsonProperty
    private String userId;
    @JsonIgnore
    private String userPw;
    @JsonProperty
    private String userName;
    @JsonProperty
    private String userEmail;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void update(CustomerList newUser) {
        this.userPw = newUser.userPw;
        this.userName = newUser.userName;
        this.userEmail = newUser.userEmail;
    }

    @Override
    public String toString() {
        return "CustomerList [" + super.toString() + ", userId=" + userId + ", userPw=" + userPw + ", userName=" + userName
                + ", userEmail=" + userEmail + "]";
    }
}