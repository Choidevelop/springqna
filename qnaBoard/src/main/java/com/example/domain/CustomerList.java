package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * CustomerList
 */
@Entity
public class CustomerList {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String userId;
    private String userPw;
    private String userName;
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

    @Override
    public String toString() {
        return "CustomerList [userEmail=" + userEmail + ", userId=" + userId + ", userName=" + userName + ", userPw="
                + userPw + "]";
    }

	public void update(CustomerList newUser) {
        this.userPw = newUser.userPw;
        this.userName = newUser.userName;
        this.userEmail = newUser.userEmail;
	}

}