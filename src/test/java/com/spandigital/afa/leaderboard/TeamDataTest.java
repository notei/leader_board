package com.spandigital.afa.leaderboard;


import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.spandigital.afa.leaderboard.data.TeamData;
import com.spandigital.afa.leaderboard.service.MatchService;
import com.spandigital.afa.leaderboard.to.Team;

public class TeamDataTest {

	@Test
	public void AddTeams() {
		TeamData data = new TeamData();
		MatchService matchService = new MatchService();
		
		data.addTeamPoints("Team1", 0);
		data.addTeamPoints("Team2", 0);
		data.addTeamPoints("Team3", 0);
		
		List<Team> list = matchService.getPositonTable(data.getTeamList());
		
		Assert.assertEquals(list.size(), 3);
		
	}
	
	
	@Test
	public void AddTeamsDuplicated() {
		TeamData data = new TeamData();
		MatchService matchService = new MatchService();
		
		data.addTeamPoints("Team1", 0);
		data.addTeamPoints("Team2", 1);
		data.addTeamPoints("Team3", 2);
		data.addTeamPoints("Team3", 3);
		
		List<Team> list = matchService.getPositonTable(data.getTeamList());
		
		Assert.assertEquals(list.size(), 3);
		
	}
	
	@Test
	public void AddTeamPoints() {
		TeamData data = new TeamData();
		String teamName = "Team1";
		
		data.addTeamPoints(teamName, 1);
		data.addTeamPoints(teamName, 3);
		data.addTeamPoints(teamName, 1);
		
		Team team = data.getTeamByName(teamName);
		
		Assert.assertEquals(team.getPoints(), 5);
	}
	
}
