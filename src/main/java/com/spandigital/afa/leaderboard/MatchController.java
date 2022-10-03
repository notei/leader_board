package com.spandigital.afa.leaderboard;

import java.util.List;

import com.spandigital.afa.leaderboard.data.TeamData;
import com.spandigital.afa.leaderboard.service.MatchService;
import com.spandigital.afa.leaderboard.to.Team;

public class MatchController {

	private static final int TIE = 1;
	private static final int WON = 3;
	private static final int LOOSE = 0;

	private TeamData data = new TeamData();

	/**
	 * Method to add the result of a game
	 * 
	 * @param name   name of the first team
	 * @param score  number of goals from the first team
	 * @param name2  name of the second team
	 * @param score2 number of goals from the second team
	 */
	public void addGame(String name, int score, String name2, int score2) {

		int result = MatchService.matchResult(score, score2);

		switch (result) {
		case 0:
			data.addTeamPoints(name, TIE);
			data.addTeamPoints(name2, TIE);
			break;

		case 1:
			data.addTeamPoints(name, WON);
			data.addTeamPoints(name2, LOOSE);
			break;
		case -1:
			data.addTeamPoints(name2, WON);
			data.addTeamPoints(name, LOOSE);
			break;
		}
	}

	/**
	 * Method to print the leader board to the standard output
	 */
	public void printResult() {
		MatchService matchService = new MatchService();

		List<Team> list = matchService.getPositonTable(data.getTeamList());

		list.forEach((team) -> System.out
				.println(team.getPossition() + " - " + team.getName() + ", " + team.getPoints() + " pts"));
	}

}
