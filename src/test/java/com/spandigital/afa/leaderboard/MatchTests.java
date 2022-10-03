package com.spandigital.afa.leaderboard;


import org.junit.Assert;
import org.junit.Test;

import com.spandigital.afa.leaderboard.service.MatchService;




public class MatchTests {

	@Test
	public void matchResultTieTest() {
		
		int result = MatchService.matchResult(0, 0);
		Assert.assertEquals(result, 0);
	}
	
	@Test
	public void matchResultTeamAWinsTest() {
		int result = MatchService.matchResult(1, 0);
		Assert.assertEquals(result, 1);
	}
	
	@Test
	public void matchResultTeamBWinsTest() {
		int result = MatchService.matchResult(0, 1);
		Assert.assertEquals(result, -1);
	}
	
	
	@Test
	public void matchLeaderBoardTest() {
		MatchController controller = new MatchController();
		
		controller.addGame("Lions", 3, "Snakes", 3);
		controller.addGame("Tarantulas", 1, "FC Awesome", 0);
		controller.addGame("Lions", 1, "FC Awesome", 1);
		controller.addGame("Tarantulas", 3, "Snakes", 1);
		controller.addGame("Lions", 4, "Grouches", 0);
		controller.addGame("Lions", 1, "Grouches", 5);
		
		controller.printResult();
		
		
	}
	
	

}
