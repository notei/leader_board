package com.spandigital.afa.leaderboard;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class MatchLoaderTest {
	
	MatchLoader ml;
	
	@Before
	public void init() {
		ml = new MatchLoader();
	}
	
	@Test
	public void noArgsTest() throws FileNotFoundException {
		String expectedResponse = "To run the program you have the following options:"
				+ "1.- to use stdin use: java -jar leaderboard-0.0.1-SNAPSHOT.jar -c"
				+ "2.- to use file use: java -jar leaderboard-0.0.1-SNAPSHOT.jar -f filepath";
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		MatchLoader.main(new String[]{});
		
		String output = out.toString();
		output = output.replace("\n", "").replace("\r", "");
		
		assertEquals(expectedResponse, output );
		
		System.setOut(System.out);
	}
	
	
	@Test
	public void getTeamNameTest() {
		String result = "FC Awesome 14";
		String name = ml.getTeamName(result);
		Assert.assertEquals(name, "FC Awesome");
	}
	
	
	
	
	@Test
	public void getTeamScoreTest() {
		String result = "FC Awesome 14";
		int score = ml.getScore(result);
		Assert.assertEquals(score, 14);
	}
	
	
	
	
}
