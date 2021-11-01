package com.election.service;

import com.election.type.FractionType;

import com.election.valueobject.Party;
import java.util.Date;
import java.util.Scanner;

public class PartyConsoleService {

    private final Scanner in;

    public PartyConsoleService(Scanner in) {
        this.in = in;
    }

    public Party getParty() throws Exception {
        int id = getPartyId();
        System.out.println("Введите название политической партии: ");
        String nameOfParty = in.nextLine();
        System.out.println("Какой фракции принадлежит партия: 1 - Левая, 2 - Правая, 3 - Центр.");
        FractionType partyType = getPartyTape();
        System.out.println("Введите дату создания партии в формате ДД.ММ.ГГГГ");
        Date dateOfCreationParty = DateUtil.getDateByFormat(in.nextLine());
        return new Party(id, nameOfParty, partyType, dateOfCreationParty);
    }

    private FractionType getPartyTape() throws Exception {
        String numberOfParty = in.nextLine();
        switch (numberOfParty) {
            case "1" : return FractionType.LEFT;
            case "2" : return FractionType.RIGHT;
            case "3" : return FractionType.CENTER;
            default : throw new Exception("Фракция не найдена");
        }
    }

    public int getPartyId() {
        System.out.println("Введите id партии: ");
        return Integer.parseInt(in.nextLine());
    }
}
