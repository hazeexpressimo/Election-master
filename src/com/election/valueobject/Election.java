package com.election.valueobject;

import java.util.*;

public class  Election {

    private int id;
    private Date date;
    private List<Precinct> precinctList = new ArrayList<>();

    public Election(int id, Date date) {
        this.id = id;
        this.date = date;
    }

    public Election() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Precinct> getPrecinctList() {
        return precinctList;
    }

    public void setPrecinctList(List<Precinct> precinctList) {
        this.precinctList = precinctList;
    }
}
