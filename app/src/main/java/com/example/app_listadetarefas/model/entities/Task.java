package com.example.app_listadetarefas.model.entities;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Task {

    private String description;
    private String title;
    private boolean favorite;
    private List<Tag> tags;

    private void init(){
        tags = new ArrayList<>();
    }

    public Task(String description, String title) {
        this.description = description;
        this.title = title;
        init();
    }

    public Task(String description, String title, boolean favorite) {
        this.description = description;
        this.title = title;
        this.favorite = favorite;
        init();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
    public List<Tag> getTags(){
        return tags;
    }

    @NonNull
    @Override
    public String toString() {
        return "Title: " + title;
    }
}
