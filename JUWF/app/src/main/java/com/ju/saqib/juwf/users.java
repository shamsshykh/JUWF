package com.ju.saqib.juwf;

/**
 * Created by HAM ZA on 03-Oct-17.
 */

public class users {

    private String displayName;
    private String email;
    private Long createdAt;




    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setEmail(String email) {
        this.email = email;
    }





    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }


    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    //ab is men jo jo change karna wo battaa ja

    public users(String displayName, String email,  Long createdAt) {
        this.displayName = displayName;
        this.email = email;
        this.createdAt = createdAt;
    }


    public users() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }
//
//    public User(String username, String email, String token, String fuid) {
//        this.username = username;
//        this.email = email;
//        this.token = token;
//        this.fuid = fuid;
//    }

}