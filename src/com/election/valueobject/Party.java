package com.election.valueobject;


import com.election.type.FractionType;

import java.text.SimpleDateFormat;
import java.util.*;

public class Party {

    private int id;
    private String name;
    private FractionType fraction;
    private Date dateOfCreatingParty;
    private Set<Citizen> candidates = new TreeSet<>();

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public Party(int id, String name, FractionType fraction, Date dateOfCreatingParty) {
        this.id = id;
        this.name = name;
        this.fraction = fraction;
        this.dateOfCreatingParty = dateOfCreatingParty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FractionType getFraction() {
        return fraction;
    }

    public void setFraction(FractionType fraction) {
        this.fraction = fraction;
    }

    public Date getDateOfCreatingParty() {
        return dateOfCreatingParty;
    }

    public void setDateOfCreatingParty(Date dateOfCreatingParty) {
        this.dateOfCreatingParty = dateOfCreatingParty;
    }

    public Set<Citizen> getCandidates() {
        return candidates;
    }

    public void setCandidates(Set<Citizen> candidates) {
        this.candidates = candidates;
    }

    public void setCandidatesByList(List<Citizen> candidates) {
        this.candidates = new TreeSet<>(candidates);
    }

    public void addCandidate(Citizen candidate) {
        candidates.add(candidate);
    }

    public void removeCandidate(Citizen candidate) {
         candidates.removeIf(c -> c.getId() == candidate.getId());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "\n" +
                "╔═ Party id: " + id  + "\n" +
                "╠═ Party name: " + name  + "\n" +
                "╠═ Fraction: " + fraction + "\n" +
                "╚═ Date of creating: " + simpleDateFormat.format(dateOfCreatingParty) + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Party party = (Party) o;
        return id == party.id &&
                Objects.equals(name, party.name) &&
                fraction == party.fraction &&
                Objects.equals(dateOfCreatingParty, party.dateOfCreatingParty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, fraction, dateOfCreatingParty);
    }
}
