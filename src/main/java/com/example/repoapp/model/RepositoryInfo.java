package com.example.repoapp.model;

import lombok.*;

@Getter
@Setter

public class RepositoryInfo {

    private String name;
    private int stars;

    public RepositoryInfo(String name, int stars) {
        this.name = name;
        this.stars = stars;
    }
}
