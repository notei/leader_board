package com.spandigital.afa.leaderboard;

import static org.junit.Assert.assertEquals;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MatchLoaderFileTest {

	private final ByteArrayOutputStream out = new ByteArrayOutputStream();
	private final ByteArrayOutputStream outErr = new ByteArrayOutputStream();
	private PrintStream psOut;
	private PrintStream psErr;
	
	
	private final String expectedResponse = "Welcome to LeaderBoard system" + "please wait while reading file"
			+ "1 - Tarantulas, 6 pts" + "2 - Lions, 5 pts" + "3 - FC Awesome, 1 pts" + "3 - Snakes, 1 pts"
			+ "5 - Grouches, 0 pts";
	
	private final String expectedResponseError = "Ups, it looks like there is an error"
			+ "please verify the file in the following path: src/test/resources/matches.tx";

	@Before
	public void redirectOut() {
		psOut = System.out;
		psErr = System.err;
		System.setOut(new PrintStream(out));
		System.setErr(new PrintStream(outErr));
	}

	@Test
	public void fileInputTest() {
		

			MatchLoader.main(new String[] { "-f", "src/test/resources/matches.txt" });

			String output = out.toString();
			output = output.replace("\n", "").replace("\r", "");

			assertEquals(expectedResponse, output);
		
	}

	@Test
	public void fileInputrrorTest() {
		
			MatchLoader.main(new String[] { "-f", "src/test/resources/matches.tx" });
			
			String output = outErr.toString();
			output = output.replace("\n", "").replace("\r", "");
		
			assertEquals(expectedResponseError, output);
	}

	@After
	public void cleanUpOut() {
		System.setOut(psOut);
		System.setOut(psErr);
	}
}
