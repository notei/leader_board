package com.spandigital.afa.leaderboard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class MatchLoader {

	
	private MatchController matchController;
	
	
	/**
	 * Main method
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) {
		MatchLoader ml = new MatchLoader();
		ml.init(args);
	}
	
	
	
	/**
	 * Init method
	 * @param args params to execute the method
	 * @throws FileNotFoundException
	 */
	private void init(String[] args)  {
		if(args == null || args.length == 0 || args.length > 2) {
			printInstructions();
		}
		
		else if(args.length == 1) {
			if(args[0].equalsIgnoreCase("-c")) {
				initStdin();
			}
		}
		
		else if(args[0].equals("-f")) {
			try {
				initFilein(args[1]);
			} catch (FileNotFoundException e) {
				printFileError(args[1]);
			}
		}
	}

	
	/**
	 * Inits the class for standard input
	 */
	private void initStdin() {
		printConsoleInstructions();
		
		matchController = new MatchController();
		Scanner scanner = new Scanner(System.in);
		lineReader(scanner);
	}
	
	/**
	 * Inits the class from a file
	 * @param path path from the file to load the matches
	 * @throws FileNotFoundException if the file do not exists
	 */
	private void initFilein(String path) throws FileNotFoundException {
		
		printFileInstructions();
		
		Scanner scanner = null;
		matchController = new MatchController();
		File f = new File(path);
		try {
			scanner = new Scanner(f);
			lineReader(scanner);
		} catch (FileNotFoundException e) {
			throw e;
		} finally {
	        if(scanner!=null) 
	        	scanner.close();
	    }
	}
	
	
	/**
	 * Method to read the input
	 * @param scanner Scanner to read from
	 */
	private void lineReader(Scanner scanner) {
		String line = "";
		while(scanner.hasNextLine()) {
			
			 line = scanner.nextLine();
			 if(line.equalsIgnoreCase("finish") || line.equals("")) {
				 break;
			 }
			 
			 addMatch(line);
		}
		
		scanner.close();
		
		matchController.printResult();
	}
	
	
	/**
	 * Method to create a match from a readed line
	 * @param line string of a match result
	 */
	private void addMatch(String line) {
		
		String[]teams = line.trim().split(",");
		
		int scoreA = getScore(teams[0]);
		String teamA = getTeamName(teams[0]);
		int scoreB = getScore(teams[1]);
		String teamB = getTeamName(teams[1]);
		
		matchController.addGame(teamA, scoreA, teamB, scoreB);
	}
	
	
	/**
	 * Method to recover the Team name from string
	 * @param line
	 * @return
	 */
	public String getTeamName(String line) {
		String[]team = line.trim().split(" ");
		if(team.length == 2) {
			return team[0];
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < team.length-1; i++) {
			sb.append(team[i] + " ");
		}
		
		return sb.toString().trim();
	}
	
	/**
	 * Method to get the score from a match
	 * @param line
	 * @return
	 */
	public int getScore(String line) {
		String[]team = line.trim().split(" ");

		String r = Arrays.stream(team)
				.reduce((first, second) -> second)
				.orElse(null);
		
		return Integer.parseInt(r);
	}
	

	/**
	 * Method to print instructions
	 */
	private static void printInstructions() {
		System.out.println("To run the program you have the following options:");
		System.out.println("1.- to use stdin use: java -jar leaderboard-0.0.1-SNAPSHOT.jar -c");
		System.out.println("2.- to use file use: java -jar leaderboard-0.0.1-SNAPSHOT.jar -f filepath");
	}
	
	
	/**
	 * Method to print instructions to use the consolse
	 */
	private  void printConsoleInstructions() {
		System.out.println("Welcome to LeaderBoard system");
		System.out.println("Please type the game result or type finish to print the leaderBoard");
		System.out.println("When donde, press enter");
	}
	
	
	/**
	 * Method to print instructions to use a file
	 */
	private void printFileInstructions() {
		System.out.println("Welcome to LeaderBoard system");
		System.out.println("please wait while reading file");
	}
	
	private void printFileError(String path) {
		System.err.println("Ups, it looks like there is an error");
		System.err.println("please verify the file in the following path: " + path);
	}
	
}
