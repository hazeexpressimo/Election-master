package com.election.service;

import com.election.type.PrecinctType;
import com.election.valueobject.Precinct;
import java.util.Scanner;

public class PrecinctConsoleService {

    private final Scanner in;

    public PrecinctConsoleService(Scanner scanner) {
        this.in = scanner;
    }

    public Precinct getPrecinct() throws Exception {
        int index = getPrecinctIndex();
        String address = getAddress();
        PrecinctType precinctType = getTypeOfPrecinct();
        return new Precinct(index, address, precinctType);
    }

    private PrecinctType getTypeOfPrecinct() throws Exception {
        System.out.println("Выберите тип участка: 1 - Обычный, 2 - для граждан на карантине, 3 - для военных или работников спецслужб.");
        switch (Integer.parseInt(in.nextLine())) {
            case 1: return PrecinctType.NORMAL;
            case 2: return PrecinctType.QUARANTINE;
            case 3: return PrecinctType.MILITARY;
            default: throw new Exception("Не верный тип участка");
        }
    }

    private String getAddress() {
        System.out.println("Введите адрес участка: ");
        String address;
        do {
            address = in.nextLine();
        } while (address.trim().isEmpty());
        return address;
    }

    public int getPrecinctIndex() {
        System.out.println("Введите индекс участака: ");
        return Integer.parseInt(in.nextLine());
    }
}
