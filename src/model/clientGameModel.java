package model;

import java.util.Date;

public class clientGameModel {

	private char[][] pipeBoard;	
	private long time = 0;
	private Integer stepsCounter = 0;
	private int x,y;
	
	public clientGameModel(char[][] pipeBoard)
	{
		this.pipeBoard = pipeBoard;
		this.x = pipeBoard.length;
		this.y = pipeBoard[0].length;
	}
	
	public clientGameModel(long difference, Integer stepsCounter, char[][] pipeBoard) {
		this.pipeBoard = pipeBoard;
		this.x = pipeBoard.length;
		this.y = pipeBoard[0].length;
		this.time = difference;
		this.stepsCounter = stepsCounter;
	}

	public char[][] getPipeBoard() {
		return pipeBoard;
	}

	public void setPipeBoard(char[][] pipeBoard) {
		this.pipeBoard = pipeBoard;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public Integer getStepsCounter() {
		return stepsCounter;
	}

	public void setStepsCounter(Integer stepsCounter) {
		this.stepsCounter = stepsCounter;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}


