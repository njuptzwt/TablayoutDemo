package com.example.tablayoutdemo.Model;

import java.io.Serializable;

public class Item implements Serializable {

    private String description;

    private int imageId;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
