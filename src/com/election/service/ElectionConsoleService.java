package com.election.service;

import com.election.valueobject.Election;
import java.util.Date;
import java.util.Scanner;

public class ElectionConsoleService {

    private final Scanner in;

    public ElectionConsoleService(Scanner in) {
        this.in = in;
    }

    public Election getElection() throws Exception {
        int id = getElectionId();
        System.out.println("Введите дату выборов в формате ДД.ММ.ГГГГ");
        Date electionDate = DateUtil.getDateByFormat(in.nextLine());
        return new Election(id, electionDate);
    }

    public int getElectionId() {
        System.out.println("Введите id выборов: ");
        return Integer.parseInt(in.nextLine());
    }
}
