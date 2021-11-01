package com.election.valueobject;

import com.election.type.PrecinctType;

import java.util.*;

public class Precinct {

    private int index;
    private String address;
    private PrecinctType typeOfPrecinct;
    private List<Citizen> voters = new ArrayList<>();

    private int numberOfVotes;
    //partyId, numberOfVotes map
    private Map<Party, Integer> partyVotesMap = new HashMap<>();

    public Precinct(int index, String address, PrecinctType typeOfPrecinct) {
        this.index = index;
        this.address = address;
        this.typeOfPrecinct = typeOfPrecinct;
    }

    public Precinct(int index, String address, PrecinctType typeOfPrecinct, List<Citizen> voters) {
        this.index = index;
        this.address = address;
        this.typeOfPrecinct = typeOfPrecinct;
        this.voters = voters;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public PrecinctType getTypeOfPrecinct() {
        return typeOfPrecinct;
    }

    public void setTypeOfPrecinct(PrecinctType typeOfPrecinct) {
        this.typeOfPrecinct = typeOfPrecinct;
    }

    public List<Citizen> getVoters() {
        return voters;
    }

    public void setVoters(List<Citizen> voters) {
        this.voters = voters;
    }

    public void addVoter(Citizen citizen) {
        voters.add(citizen);
    }

    public void removeVoter(Citizen citizen) {
        voters.removeIf(c -> citizen.getId() == citizen.getId());
    }


    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    public void setPartyVotesMap(Map<Party, Integer> partyVotesMap) {
        this.partyVotesMap = partyVotesMap;
    }

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public Map<Party, Integer> getPartyVotesMap() {
        return partyVotesMap;
    }

    public Precinct clone() {
        return new Precinct(index, address, typeOfPrecinct, voters);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Precinct precinct = (Precinct) o;
        return index == precinct.index &&
                Objects.equals(address, precinct.address) &&
                typeOfPrecinct == precinct.typeOfPrecinct;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, address, typeOfPrecinct);
    }

    @Override
    public String toString() {
        return "\n" +
                "╔═ Precinct index: " + index  + "\n" +
                "╠═ Address: " + address  + "\n" +
                "╚═ Type: " + typeOfPrecinct  + "\n";
    }
}
