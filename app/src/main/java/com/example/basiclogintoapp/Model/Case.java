package com.example.basiclogintoapp.Model;

public class Case {
    private String title;
    private String description;
    private String progress;

    // Empty constructor needed for Firebase
    public Case() {
    }

    public Case(String title, String description, String progress) {
        this.title = title;
        this.description = description;
        this.progress = progress;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getProgress() {
        return progress;
    }
}
