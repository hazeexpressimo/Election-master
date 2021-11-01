package com.election.service;

import com.election.valueobject.Citizen;
import com.election.valueobject.Party;
import java.util.List;
import java.util.Scanner;

public class CitizenConsoleService {
    private final Scanner in;

    public CitizenConsoleService(Scanner in) {
        this.in = in;
    }

    public Citizen getCitizenFromConsole() throws Exception {
        int id = getCitizenId();
        System.out.println("Введите имя гражданина: ");
        String name = in.nextLine();
        System.out.println("Введите номер паспорта гражданина: ");
        String passNumber = in.nextLine();
        System.out.println("Введите ИНН гражданина: ");
        long inn = getInn();
        System.out.println("Введите год рождения гражданина: ");
        int yearOfBirth = Integer.parseInt(in.nextLine());
        System.out.println("Является ли избиратель военным или сотрудником спецслужб: 1 - Да, 2 - Нет.");
        boolean ifMilitary = Integer.parseInt(in.nextLine()) == 1;
        System.out.println("Находится ли избиратель на карантине: 1 - Да, 2 - Нет.");
        boolean ifOnQuarantine = Integer.parseInt(in.nextLine()) == 1;
        return new Citizen(id, name, passNumber, inn, yearOfBirth, ifMilitary, ifOnQuarantine);
    }

    private long getInn() throws Exception {
        long inn = Long.parseLong(in.nextLine());
        int length = (int) (Math.log10(inn) + 1);
        if (length != 10) {
            throw new Exception("ИНН не равен 10 символам!");
        }
        return inn;
    }

    public int getCitizenId() {
        System.out.println("Введите id гражданина: ");
        return Integer.parseInt(in.nextLine());
    }

    public int getRating() {
        System.out.println("Введите рейтинг гражданина: ");
        return Integer.parseInt(in.nextLine());
    }

    public boolean youWantToVote(Citizen citizen) {
        System.out.println(citizen.getName() + ", Вы хотите проголосовать! 1 - да, 2 - нет");
        return in.nextLine().equals("1");
    }

    public Party vote(List<Party> partyList) {
        CollectionUtil.showCollection(partyList);
        System.out.println("Введите id партии: ");
        int partyId  = Integer.parseInt(in.nextLine());
        Party party = partyList.stream().filter(p -> p.getId() == partyId).findFirst().orElse(null);
        if (party == null) {
            System.err.println("Такой партии нет, повторите попытку ");
            return vote(partyList);
        }
        return party;
    }
}
