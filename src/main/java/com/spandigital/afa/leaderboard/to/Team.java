package com.spandigital.afa.leaderboard.to;

import java.io.Serializable;

public class Team implements Serializable {

	private static final long serialVersionUID = 6195816469198234096L;
	
	private  String name;
	private int points;
	private transient int possition;
	
	
	public Team(String name) {
		super();
		this.name = name;
	}
	
	
	public String getName() {
		return name;
	}
	
	
	public int getPoints() {
		return points;
	}
	
	public void addPoints(int points) {
		this.points += points;
	}

	public int getPossition() {
		return possition;
	}

	public void setPossition(int possition) {
		this.possition = possition;
	}	
}
