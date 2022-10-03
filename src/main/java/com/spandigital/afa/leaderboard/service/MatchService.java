package com.spandigital.afa.leaderboard.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.spandigital.afa.leaderboard.to.Team;

public class MatchService {

	/**
	 * Calculates the match result
	 * @param scoreTeamA score from team A
	 * @param scoreTeamB score from team B
	 * @return 0, for tie, 1 for team A win, -1 for team B wins
	 */
	public static int matchResult(int scoreTeamA, int scoreTeamB) {
		int result = scoreTeamA - scoreTeamB;
		if (result == 0) {
			return 0;
		} else if (result > 0) {
			return 1;
		} else {
			return -1;
		}
	}
	
	/**
	 * Recovers a list in in order of points and for ties uses alphabetical order
	 * @return
	 */
	private List<Team> orderPositonTable(List<Team> teamList) {
		return teamList.stream()
				.sorted((teamA, teamB) -> teamA.getName().compareTo(teamB.getName())) //Orders alphabetical
				.sorted(Comparator.comparingInt(Team::getPoints).reversed()) //Orders by points
				.collect(Collectors.toList()); // create the final list
	}
	
	
	/**
	 * Recovers a list in in order of position and for ties uses alphabetical order
	 * @return
	 */
	public List<Team> getPositonTable(List<Team> teamList) {
		int pos = 0;
		int lastPos = 0;
		
		int maxScore = Integer.MAX_VALUE;
		
		List<Team> list = orderPositonTable(teamList);
		
		for(Team t: list) {
			if(t.getPoints() < maxScore) {
				pos++;
				lastPos = pos;
				maxScore = t.getPoints();
				t.setPossition(pos);
			}else{
				pos++;
				t.setPossition(lastPos);
			}
			
			
		}
		return list;
	}
}
