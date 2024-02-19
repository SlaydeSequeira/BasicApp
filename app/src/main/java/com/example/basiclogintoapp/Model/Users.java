package com.example.basiclogintoapp.Model;
public class Users {

    private String id;
    private String username;
    private String imageURL;
    private String status;

    // Constructors;
    public Users() {
    }

    public Users(String id, String username, String imageURL, String status) {
        this.id = id;
        this.username = username;
        this.imageURL = imageURL;
        this.status = status;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Method to set default anonymous username
    public void setDefaultAnonymousUsername(int anonymousUserCount) {
        this.username = "Anonymous User #" + anonymousUserCount;
    }
    public void setDefaultProfilePicture() {
        // Set the default profile picture URL here
        this.imageURL = "default_profile_picture_url";
    }
}
