package com.election;

import com.election.service.*;
import com.election.type.PrecinctType;
import com.election.valueobject.Citizen;
import com.election.valueobject.Election;
import com.election.valueobject.Party;
import com.election.valueobject.Precinct;

import java.util.*;
import java.util.stream.Collectors;

public class ElectionMenu {

    private final Scanner in = new Scanner(System.in);
    private List<Precinct> precinctList = new ArrayList<>();
    private List<Citizen> citizenList = new ArrayList<>();
    private List<Party> partiesList = new ArrayList<>();
    private final List<Election> elections = new ArrayList<>();

    private final PrecinctConsoleService precinctConsoleService = new PrecinctConsoleService(in);
    private final CitizenConsoleService citizenConsoleService = new CitizenConsoleService(in);
    private final PartyConsoleService partyConsoleService = new PartyConsoleService(in);
    private final ElectionConsoleService electionConsoleService = new ElectionConsoleService(in);

    public ElectionMenu(List<Precinct> precinctList, List<Citizen> citizens, List<Party> parties) {
        this.partiesList = parties;
        this.citizenList = citizens;
        this.precinctList = precinctList;
    }

    public ElectionMenu() {
    }

    public void run() {
        while (true) {
            try {
                showMenu();
                String menuChoice = in.nextLine();
                switch (menuChoice) {
                    case "1":
                        CollectionUtil.addObjectToListIfNotExist(citizenList, citizenConsoleService.getCitizenFromConsole(), Citizen::getId);
                        break;
                    case "2":
                        CollectionUtil.addObjectToListIfNotExist(precinctList, precinctConsoleService.getPrecinct(), Precinct::getIndex);
                        break;
                    case "3":
                        CollectionUtil.addObjectToListIfNotExist(partiesList, partyConsoleService.getParty(), Party::getId);
                        break;
                    case "4":
                        CollectionUtil.showCollection(citizenList);
                        break;
                    case "5":
                        precinctMenu();
                        break;
                    case "6":
                        partyMenu();
                        break;
                    case "7":
                        runElection();
                        break;
                    case "8":
                        showResult();
                        break;
                    case "9":
                        return;
                    default:
                        System.err.println("Invalid choice");

                }
            } catch (Exception e) {
                System.err.println("Error: "
                        + e.getMessage()
                        + "\n"
                        + "Повторити попытку с корректными данными.\n");
            }
        }
    }

    private void showMenu() {
        System.out.println("Make your choice...");
        System.out.println("1. Add citizen.");
        System.out.println("2. Add precinct.");
        System.out.println("3. Add party.");
        System.out.println("4. Show all citizen's.");
        System.out.println("5. Precinct menu.");
        System.out.println("6. Party menu.");
        System.out.println("7. Run elections.");
        System.out.println("8. Show election results.");
        System.out.println("9. Out of program.");
    }

    private void showPrecinctMenu() {
        System.out.println("1. Add citizen to precinct by precinct index and citizen id.");
        System.out.println("2. Remove citizen from precinct.");
        System.out.println("3. Show all precinct.");
        System.out.println("4. Show voters from precinct by index.");
        System.out.println("5. Return to the previous menu.");
    }

    private void precinctMenu() throws Exception {
        while (true) {
            showPrecinctMenu();
            String menuChoice = in.nextLine();
            switch (menuChoice) {
                case "1":
                    addCitizenToPrecinctVoters();
                    break;
                case "2":
                    removeCitizenFromPrecinctVoters();
                    break;
                case "3":
                    CollectionUtil.showCollection(precinctList);
                    break;
                case "4":
                    CollectionUtil.showCollection(CollectionUtil.getByValue(
                            precinctList,
                            precinctConsoleService.getPrecinctIndex(),
                            Precinct::getIndex).getVoters());
                    break;
                case "5":
                    return;
                default:
                    System.err.println("Invalid choice");
            }
        }
    }


    public void addCitizenToPrecinctVoters() throws Exception {
        Precinct precinct = CollectionUtil.getByValue(precinctList, precinctConsoleService.getPrecinctIndex(), Precinct::getIndex);
        Citizen citizen = CollectionUtil.getByValue(citizenList, citizenConsoleService.getCitizenId(), Citizen::getId);

        if (citizen.getPrecinct() != null) {
            throw new Exception("Гражданин уже привязан к участку с индексом: " + citizen.getPrecinct().getIndex());
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        if (year - citizen.getYearOfBirth() < 18) {
            throw new Exception("Гражданин слишком молод, < 18 лет");
        }

        if (citizen.isIfMilitary() && !precinct.getTypeOfPrecinct().equals(PrecinctType.MILITARY)) {
            throw new Exception("Гражданин может быть привязан только к военному участку");
        }

        if (citizen.isIfOnQuarantine() && !precinct.getTypeOfPrecinct().equals(PrecinctType.QUARANTINE)) {
            throw new Exception("Гражданин может быть привязан только к карантинному участку");
        }

        if (!citizen.isIfOnQuarantine() && !citizen.isIfMilitary() && !precinct.getTypeOfPrecinct().equals(PrecinctType.NORMAL)) {
            throw new Exception("Гражданин может быть привязан только к обычному участку");
        }

        precinct.addVoter(citizen);
        citizen.setPrecinct(precinct);
    }

    public void removeCitizenFromPrecinctVoters() throws Exception {
        Precinct precinct = CollectionUtil.getByValue(precinctList, precinctConsoleService.getPrecinctIndex(), Precinct::getIndex);
        Citizen citizen = CollectionUtil.getByValue(precinct.getVoters(), citizenConsoleService.getCitizenId(), Citizen::getId);
        citizen.setPrecinct(null);
        precinct.removeVoter(citizen);
    }

    private void showPartyMenu() {
        System.out.println("1. Add citizen to party by party id and citizen id.");
        System.out.println("2. Remove candidate(citizen) from party.");
        System.out.println("3. Show all party.");
        System.out.println("4. Show candidate from party by id.");
        System.out.println("5. Return to the previous menu.");
    }


    private void partyMenu() throws Exception {
        while (true) {
            showPartyMenu();
            String menuChoice = in.nextLine();
            switch (menuChoice) {
                case "1":
                    addCandidateToParty();
                    break;
                case "2":
                    removeCandidateFromParty();
                    break;
                case "3":
                    CollectionUtil.showCollection(partiesList);
                    break;
                case "4":
                    CollectionUtil.showCollection(CollectionUtil.getByValue(
                            partiesList,
                            partyConsoleService.getPartyId(),
                            Party::getId).getCandidates());
                    break;
                case "5":
                    return;
                default:
                    System.err.println("Invalid choice");
            }
        }
    }

    private void addCandidateToParty() throws Exception {
        Party party = CollectionUtil.getByValue(partiesList, partyConsoleService.getPartyId(), Party::getId);
        int citizenId = citizenConsoleService.getCitizenId();
        int rating = citizenConsoleService.getRating();
        Citizen citizen = CollectionUtil.getByValue(citizenList, citizenId, Citizen::getId);
        if (citizen.getParty() != null) {
            throw new Exception("Гражданин уже привязан к партие с ID : " + citizen.getParty().getId());
        }
        citizen.setRating(rating);
        party.addCandidate(citizen);
        citizen.setParty(party);
    }

    public void removeCandidateFromParty() throws Exception {
        Party party = CollectionUtil.getByValue(partiesList, partyConsoleService.getPartyId(), Party::getId);
        Citizen citizen = CollectionUtil.getByValue(party.getCandidates(), citizenConsoleService.getCitizenId(), Citizen::getId);
        citizen.setPrecinct(null);
        citizen.setRating(0);
        party.removeCandidate(citizen);
    }

    private void runElection() throws Exception {
        Election election = electionConsoleService.getElection();
        CollectionUtil.addObjectToListIfNotExist(elections, election, Election::getId);
        List<Precinct> copyPrecinctList = precinctList.stream().map(Precinct::clone).collect(Collectors.toList());
        copyPrecinctList.forEach(precinct -> {
            int countOfVotes = 0;
            Map<Party, Integer> partyNumberOfVotesMap = new HashMap<>();
            List<Citizen> voters = precinct.getVoters();
            for (Citizen voter : voters) {
                boolean isWant = citizenConsoleService.youWantToVote(voter);
                if (isWant) {
                    Party party = citizenConsoleService.vote(partiesList);
                    incrementCountInPartyMap(partyNumberOfVotesMap, party);
                    countOfVotes++;
                }
            }
            precinct.setNumberOfVotes(countOfVotes);
            precinct.setPartyVotesMap(partyNumberOfVotesMap);
        });
        election.setPrecinctList(copyPrecinctList);
    }

    private void showResult() throws Exception {
        int electionId = electionConsoleService.getElectionId();
        Election election = CollectionUtil.getByValue(elections, electionId, Election::getId);
        List<Precinct> precincts = election.getPrecinctList();
        precincts.forEach(element -> {
            System.out.println("*****************************************************\n");
            System.out.println("Результат на участке c индексом: " + element.getIndex());
            Map<Party, Integer> partyVotesMap = element.getPartyVotesMap();
            partyVotesMap.forEach((party, numberOfVotes)
                    -> System.out.println("Количество отданых голосов с участка = " + numberOfVotes + ", за партию: " + party.getName()));
            System.out.println("--------------------------------");
            System.out.println("Количество проголосовавших на участке: " + element.getNumberOfVotes());
            System.out.println("Количество избирателей на участке: " + element.getVoters().size() + "\n");
        });
    }

    private void incrementCountInPartyMap(Map<Party, Integer> partyCountOfVotesMap, Party party) {
        Integer votes = partyCountOfVotesMap.get(party);
        if (votes != null) {
            votes++;
            partyCountOfVotesMap.put(party, votes);
        } else {
            partyCountOfVotesMap.put(party, 1);
        }
    }
}
