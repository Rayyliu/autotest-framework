package com.autotest.example;

public class QueryOrder {

    private int id;

    private String information;

    public void setId(int id) {
        this.id = id;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public int getId() {
        return id;
    }

    public String getInformation() {
        return information;
    }

    @Override
    public String toString() {
        return "QueryOrder{" +
                "id=" + id +
                ", name='" + information + '\'' +
                '}';
    }
}

