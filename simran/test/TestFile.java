package simran.test;

import org.junit.jupiter.api.Test;
import simran.Delivery;
import simran.Main;
import simran.Match;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestFile {

    @Test
    public void numberOfMatchesPlayedPerYearTest(){
        Match match1=new Match();
        match1.setSeason("2009");

        Match match2=new Match();
        match2.setSeason("2009");

        Match match3=new Match();
        match3.setSeason("2009");

        Match match4=new Match();
        match4.setSeason("2012");

        Match match5=new Match();
        match5.setSeason("2012");

        Match match6=new Match();
        match6.setSeason("2013");


        List<Match> matches= Arrays.asList(match1,match2,match3,match4,match5,match6);


        HashMap<String,Integer> result= Main.numberOfMatches(matches);

        assertEquals(result,Main.numberOfMatches(matches));
        assertEquals(3,(int)result.get("2009"));
        assertEquals(2,(int)result.get("2012"));
        assertEquals(1,(int)result.get("2013"));
        assertNull(result.get("2023"));
    }

    @Test
    public void numberOfMatchesWonPerTeamPerYearTest() {
        Match match1 = new Match();
        match1.setSeason("2017");
        match1.setWinner("Sunrisers Hyderabad");

        Match match2 = new Match();
        match2.setSeason("2009");
        match2.setWinner("Mumbai Indians");

        Match match3 = new Match();
        match3.setSeason("2016");
        match3.setWinner("Sunrisers Hyderabad");

        Match match4 = new Match();
        match4.setSeason("2012");
        match4.setWinner("Chennai Super Kings");

        Match match5 = new Match();
        match5.setSeason("2012");
        match5.setWinner("Mumbai Indians");

        Match match6 = new Match();
        match6.setSeason("2017");
        match6.setWinner("Sunrisers Hyderabad");

        Match match7 = new Match();
        match7.setSeason("2017");
        match7.setWinner("Mumbai Indians");

        List<Match> matches = Arrays.asList(match1, match2, match3, match4, match5, match6, match7);

        HashMap<String, HashMap<String, Integer>> result = Main.MatchesWonPerTeamPerYear(matches);

        // Verify the number of matches won for each team in each year
        assertEquals(2, (int) result.get("2017").get("Sunrisers Hyderabad"));
        assertEquals(1, (int) result.get("2017").get("Mumbai Indians"));
        assertEquals(1, (int) result.get("2009").get("Mumbai Indians"));
        assertEquals(1, (int) result.get("2016").get("Sunrisers Hyderabad"));
        assertEquals(1, (int) result.get("2012").get("Chennai Super Kings"));
        assertEquals(1, (int) result.get("2012").get("Mumbai Indians"));
    }

    @Test
    public void extraRunscoredByTeamsIn2016Test(){
        Match match1 = new Match();
        match1.setSeason("2016");
        match1.setId("1");

        Match match2 = new Match();
        match2.setSeason("2016");
        match2.setId("2");

        Match match3 = new Match();
        match3.setSeason("2017"); // This match should be ignored
        match3.setId("3");

        List<Match> matches = Arrays.asList(match1, match2, match3);

        Delivery delivery1 = new Delivery();
        delivery1.setMatch_id("1");
        delivery1.setBowling_team("Chennai Super Kings");
        delivery1.setExtra_runs("5");

        Delivery delivery2 = new Delivery();
        delivery2.setMatch_id("2");
        delivery2.setBowling_team("Mumbai Indians");
        delivery2.setExtra_runs("5");

        Delivery delivery3 = new Delivery();
        delivery3.setMatch_id("1"); // This delivery should be ignored
        delivery3.setBowling_team("Chennai Super Kings");
        delivery3.setExtra_runs("3");

        List<Delivery> deliveries = Arrays.asList(delivery1, delivery2, delivery3);

        HashMap<String, Integer> result = Main.extraRunscoredByTeamsIn2016(matches, deliveries);

        assertEquals(8, (int)result.get("Chennai Super Kings"));
        assertEquals(5, (int)result.get("Mumbai Indians"));


    }



    @Test
    public void teamWonTossAndMatchesTest() {
        Match match1 = new Match();
        match1.setSeason("2016");
        match1.setId("1");
        match1.setToss_winner("Chennai Super Kings");
        match1.setWinner("Chennai Super Kings");

        Match match2 = new Match();
        match2.setSeason("2016");
        match2.setId("2");
        match2.setToss_winner("Mumbai Indians");
        match2.setWinner("Mumbai Indians");

        Match match3 = new Match();
        match3.setSeason("2017");
        match3.setId("3"); // This match should be ignored
        match3.setToss_winner("Kolkata Knight Riders");
        match3.setWinner("Kolkata Knight Riders");

        List<Match> matches = Arrays.asList(match1, match2, match3);

        HashMap<String, Integer> result  =  Main.teamWonTossAndMatches(matches);

        assertEquals(1, (int)result.get("Chennai Super Kings"));
        assertEquals(1, (int)result.get("Mumbai Indians"));
        assertEquals(1, (int)result.get("Kolkata Knight Riders"));



    }





}