package com.spandigital.afa.leaderboard.data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import com.spandigital.afa.leaderboard.to.Team;

public class TeamData {
	
	private List<Team> teamList = new ArrayList<>(); 
	
	
	/**
	 * Recovers the team by name
	 * @param name
	 * @return
	 */
	public Team getTeamByName(String name) {
	
		List<Team> res = teamList.stream()
				.filter(team -> team.getName().equalsIgnoreCase(name) )
				.collect(Collectors.toList());

		if(res.size() == 1) {
			return res.get(0);
		}
		
		return null;
	}

	

	/**
	 * Method to add the points to a team, if team do not exist, it will be created
	 * @param name
	 * @param points
	 */
	public void addTeamPoints(String name, int points) {
		Team team = getTeamByName (name); 
		if( team == null) {
			team = new Team(name);
			teamList.add(team);
		}
		
		team.addPoints(points);
		
	}

	public List<Team> getTeamList(){
		return teamList;
	}
	
	
}
