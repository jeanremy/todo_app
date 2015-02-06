package com.praud.jr.todolist.Model;

/**
 * Created by JR on 06/02/2015.
 */
public class Task {
    private Integer id;
    private String nom;
    private Integer position;

    public Task() {
    }

    public Task(Integer id, String nom, Integer position) {
        this.id = id;
        this.nom = nom;
        this.position = position;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}

