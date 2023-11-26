package simran;

import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Main {
    private static final int MATCH_ID = 0;
    private static final int MATCH_YEAR = 1;
    private static final int MATCH_CITY = 2;
    private static final int MATCH_DATE = 3;
    private static final int TEAM_1 = 4;
    private static final int TEAM_2 = 5;
    private static final int TOSS_WON = 6;
    private static final int TOSS_DECISION = 7;
    private static final int RESULT = 8;
    private static final int DL_APPLIED = 9;
    private static final int TEAM_WON = 10;
    private static final int WIN_BY_RUNS = 11;
    private static final int WIN_BY_WICKETS = 12;
    private static final int PLAYER_OF_MATCH = 13;
    private static final int MATCH_VENUE = 14;
    private static final int UMPIRE_1 = 15;
    private static final int UMPIRE_2 = 16;


    private static final int INNING = 1;
    private static final int BATTING_TEAM = 2;
    private static final int BOWLING_TEAM = 3;
    private static final int OVER = 4;
    private static final int BALL = 5;
    private static final int BATSMAN = 6;
    private static final int NON_STRIKER = 7;
    private static final int BOWLER = 8;
    private static final int IS_SUPER_OVER = 9;
    private static final int WIDE_RUNS = 10;
    private static final int BYE_RUNS = 11;
    private static final int LEG_BYE_RUNS = 12;
    private static final int NO_BALL_RUNS = 13;
    private static final int PENALTY_RUNS = 14;
    private static final int BATSMAN_RUNS = 15;
    private static final int EXTRA_RUNS = 16;
    private static final int TOTAL_RUNS = 17;
    private static final int PLAYER_DISMISSED = 18;
    private static final int DISMISSAL_KIND = 19;
    private static final int FIELDER = 20;

    public static List<Delivery> getDeliveriesData() throws IOException {
        List<Delivery> deliveries = new ArrayList<>();
        String line;

        BufferedReader br = new BufferedReader(new FileReader("deliveries.csv"));
        boolean mover = false;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            if (mover) {
                Delivery delivery = new Delivery();
                delivery.setMatch_id(data[MATCH_ID]);
                delivery.setInning(data[INNING]);
                delivery.setBatting_team(data[BATTING_TEAM]);
                delivery.setBowling_team(data[BOWLING_TEAM]);
                delivery.setOver(data[OVER]);
                delivery.setBall(data[BALL]);
                delivery.setBatsman(data[BATSMAN]);
                delivery.setNon_striker(data[NON_STRIKER]);
                delivery.setBowler(data[BOWLER]);
                delivery.setIs_super_over(data[IS_SUPER_OVER]);
                delivery.setWide_runs(data[WIDE_RUNS]);
                delivery.setBye_runs(data[BYE_RUNS]);
                delivery.setLegbye_runs(data[LEG_BYE_RUNS]);
                delivery.setNoball_runs(data[NO_BALL_RUNS]);
                delivery.setPenalty_runs(data[PENALTY_RUNS]);
                delivery.setBatsman_runs(data[BATSMAN_RUNS]);
                delivery.setExtra_runs(data[EXTRA_RUNS]);
                delivery.setTotal_runs(data[TOTAL_RUNS]);
                if (data.length - 1 >= PLAYER_DISMISSED)
                    delivery.setPlayer_dismissed(data[PLAYER_DISMISSED]);
                if (data.length - 1 >= DISMISSAL_KIND)
                    delivery.setDismissed_kind(data[DISMISSAL_KIND]);
                if (data.length - 1 >= FIELDER)
                    delivery.setFielder(data[FIELDER]);
                deliveries.add(delivery);
            }
            mover = true;
        }
        return deliveries;
    }

    public static List<Match> getMatchesData() throws IOException {
        List<Match> matches = new ArrayList<>();
        String line;
        BufferedReader reader = new BufferedReader(new FileReader("matches.csv"));
        boolean skipper = false;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (skipper) {
                Match match = new Match();
                match.setId(data[MATCH_ID]);
                match.setSeason(data[MATCH_YEAR]);
                match.setVenue(data[MATCH_CITY]);
                match.setDate(data[MATCH_DATE]);
                match.setTeam1(data[TEAM_1]);
                match.setTeam2(data[TEAM_2]);
                match.setToss_winner(data[TOSS_WON]);
                match.setToss_decision(data[TOSS_DECISION]);
                match.setResult(data[RESULT]);
                match.setDl_applied(data[DL_APPLIED]);
                match.setWinner(data[TEAM_WON]);
                match.setWin_by_runs(data[WIN_BY_RUNS]);
                match.setWin_by_wickets(data[WIN_BY_WICKETS]);
                match.setPlayer_of_match(data[PLAYER_OF_MATCH]);
                match.setVenue(data[MATCH_VENUE]);
                if (data.length - 1 >= UMPIRE_1)
                    match.setUmpire1(data[UMPIRE_1]);
                if (data.length - 1 >= UMPIRE_2)
                    match.setUmpire2(data[UMPIRE_2]);
                matches.add(match);
            }
            skipper = true;
        }
        return matches;
    }

    //    Number of matches played per year of all the years in IPL.
    public static void numberOfMatches(List<Match> matches) {
        HashMap<String, Integer> totalMatchesInYear = new HashMap<>();
        for (Match match : matches) {
            String season = match.getSeason();

            if (totalMatchesInYear.containsKey(season)) {
                totalMatchesInYear.put(season, totalMatchesInYear.get(season) + 1);
            } else {
                totalMatchesInYear.put(season, 1);
            }
        }
        System.out.println(totalMatchesInYear);
    }

    public static void MatchesWonPerTeamPerYear(List<Match> matches) {
        HashMap<String, HashMap<String, Integer>> totalMatchesWonPerYear = new HashMap<>();

        for (Match match : matches) {
            String season = match.getSeason();
            String winner = match.getWinner();

            if (totalMatchesWonPerYear.containsKey(season)) {
                HashMap<String, Integer> teamWins = totalMatchesWonPerYear.get(season);

                if (teamWins.containsKey(winner)) {
                    teamWins.put(winner, teamWins.get(winner) + 1);
                } else {
                    teamWins.put(winner, 1);
                }
            } else {
                HashMap<String, Integer> teamsWins = new HashMap<>();
                teamsWins.put(winner, 1);

                totalMatchesWonPerYear.put(season, teamsWins);
            }
        }
        System.out.println(totalMatchesWonPerYear);
    }

    //    extra run conceded per team in the year 2016
    public static void extraRunscoredByTeamsIn2016(List<Match> matches, List<Delivery> deliveries) {
        List<String> matchId2016 = new ArrayList<>();

        for (Match match : matches) {
            if (match.getSeason().contains("2016")) {
                matchId2016.add(match.getId());
            }
        }

        HashMap<String, Integer> extraRunsByTeam = new HashMap<>();

        for (Delivery delivery : deliveries) {
            if (matchId2016.contains(delivery.getMatch_id())) {
                String bowlingTeam = delivery.getBowling_team();
                int extraRun = Integer.parseInt(delivery.getExtra_runs());
                extraRunsByTeam.put(bowlingTeam, extraRunsByTeam.getOrDefault(bowlingTeam, 0) + extraRun);
            }
        }
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(extraRunsByTeam.entrySet());
        entryList.sort(Comparator.comparing(Map.Entry::getValue));

        LinkedHashMap<String, Integer> sortedExtraRunsByTeam = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : entryList) {
            sortedExtraRunsByTeam.put(entry.getKey(), entry.getValue());
        }

        // Print the sorted extraRunsByTeam
        System.out.println(sortedExtraRunsByTeam);
    }

    public static void findTop10EconomicalBowlers(List<Match> matches, List<Delivery> deliveries) {
        HashMap<String, Integer> totalRunsByBowlers = new HashMap<>();
        HashMap<String, Integer> totalDeliveriesByBowlers = new HashMap<>();
        HashMap<String, Double> economyByBowlers = new HashMap<>();
        for (Match match : matches) {
            if (match.getSeason().equals("2015")) {
                for (Delivery delivery : deliveries) {
                    if(delivery.getMatch_id().equals(match.getId())) {
                        String bowler = delivery.getBowler();
                        String runs = delivery.getTotal_runs();
                        totalRunsByBowlers.put(bowler, totalRunsByBowlers.getOrDefault(bowler, 0)+Integer.parseInt(runs));
                        totalDeliveriesByBowlers.put(bowler, totalDeliveriesByBowlers.getOrDefault(bowler, 0)+1);
                    }
                }
            }
        }
        for (String bowler : totalRunsByBowlers.keySet()) {
            int runs = totalRunsByBowlers.get(bowler);
            int delivery = totalDeliveriesByBowlers.get(bowler);
            double economy = ((double) runs/(double) delivery )*6;
            economyByBowlers.put(bowler, economy);
        }
        List<Map.Entry<String, Double>> entryList = new ArrayList<>(economyByBowlers.entrySet());
        entryList.sort(Map.Entry.comparingByValue());
        Map<String, Double> sortedMap = new LinkedHashMap<>();
        for(Map.Entry<String, Double> entry : entryList){
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        System.out.println("Top economical bowlers in 2015 ");
        System.out.println(sortedMap);
    }



    public static void main(String[] args) throws IOException{

        List<Match> matches = getMatchesData();
        List<Delivery> deliveries = getDeliveriesData();
        numberOfMatches(matches);
        MatchesWonPerTeamPerYear(matches);
        extraRunscoredByTeamsIn2016(matches,deliveries);
        findTop10EconomicalBowlers(matches,deliveries);
    }







}
