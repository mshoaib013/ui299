package com.example.darkshadow.qskip;

public class QueController {
    String id;
    Integer position;

    public QueController(String id, Integer position) {
        this.id = id;
        this.position = position;
    }

    public QueController() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
