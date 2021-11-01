package com.election.valueobject;

import java.util.Objects;

public class Citizen implements Comparable<Citizen>{
    private int id;
    private String name;
    private String passportNumber;
    private long identificationNumber;
    private int yearOfBirth;
    private boolean ifMilitary;
    private boolean ifOnQuarantine;

    // for candidate
    private int rating;
    private Party party;

    // for precinct
    private Precinct precinct;

    public Citizen(int id, String name, String passportNumber, long identificationNumber, int yearOfBirth, boolean ifMilitary, boolean ifOnQuarantine) {
        this.id = id;
        this.name = name;
        this.passportNumber = passportNumber;
        this.identificationNumber = identificationNumber;
        this.yearOfBirth = yearOfBirth;
        this.ifMilitary = ifMilitary;
        this.ifOnQuarantine = ifOnQuarantine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public long getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(long identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public boolean isIfMilitary() {
        return ifMilitary;
    }

    public void setIfMilitary(boolean ifMilitary) {
        this.ifMilitary = ifMilitary;
    }

    public boolean isIfOnQuarantine() {
        return ifOnQuarantine;
    }

    public void setIfOnQuarantine(boolean ifOnQuarantine) {
        this.ifOnQuarantine = ifOnQuarantine;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public Precinct getPrecinct() {
        return precinct;
    }

    public void setPrecinct(Precinct precinct) {
        this.precinct = precinct;
    }

    @Override
    public String toString() {
        return "\n" +
                "╔═ ID: " + id +  "\n" +
                "╠═ Name: " + name +  "\n" +
                "╠═ Passport number: " + passportNumber + "\n" +
                "╠═ Identification Number: " + identificationNumber + "\n" +
                "╠═ Year of Birth: " + yearOfBirth + "\n" +
                "╠═ Rating: " + (rating == 0 ? "Without rating" : rating) + "\n" +
                "╠═ Military: " + (ifMilitary ? "Yes" : "No") + "\n" +
                "╚═ Is in quarantine: " + (ifOnQuarantine ? "Yes" : "No")  + "\n";
    }

    @Override
    public int compareTo(Citizen citizen) {
        return citizen.getRating() - rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Citizen citizen = (Citizen) o;
        return id == citizen.id && identificationNumber == citizen.identificationNumber && yearOfBirth == citizen.yearOfBirth && ifMilitary == citizen.ifMilitary && ifOnQuarantine == citizen.ifOnQuarantine && rating == citizen.rating && Objects.equals(name, citizen.name) && Objects.equals(passportNumber, citizen.passportNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, passportNumber, identificationNumber, yearOfBirth, ifMilitary, ifOnQuarantine, rating);
    }
}
