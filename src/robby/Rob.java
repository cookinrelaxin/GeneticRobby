package robby;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;
public class Rob {
	
	private static final int ACTIONS_PER_SESSION = 200;
	private int action_count;
	public int score;
	public Point position;
	private Tile[][] grid;
	private Strategy strategy;
	
	public Rob(Tile[][] grid, Strategy strategy){
		this.grid = grid;
		this.strategy = strategy;
		this.position = new Point(0,0);
		this.action_count = 0;
		this.score = 0;
	}
	
	public int go(){
		while (this.action_count < ACTIONS_PER_SESSION){
			String situation = calculateSituation();
			chooseMove(situation);
			this.action_count ++;
		}
		
		return score;
	}
	
	public void goAndShowMoves(StrategyDisplayPanel panel){
		while (this.action_count < ACTIONS_PER_SESSION){
			String situation = calculateSituation();
			chooseMove(situation);
			try {
			    Thread.sleep(10);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			panel.repaint();
			this.action_count ++;
		}
	}
	
	private void chooseMove(String situation){
		int indexOfSituation = strategy.situations.indexOf(situation);
		int move = Integer.valueOf(strategy.responses.substring(indexOfSituation, indexOfSituation + 1));
		switch(move){
		case 0:
		{
			moveNorth();
			return;
		}
		case 1:
		{
			moveEast();
			return;
		}
		case 2:
		{
			moveWest();
			return;
		}
		case 3:
		{
			moveSouth();
			return;
		}
		case 4:
		{
			stayPut();
			return;
		}
		case 5:
		{
			pickUp();
			return;
		}
		case 6:
		{
			randomMove();
			return;
		}
		
	}
		
	}
	
	public String calculateSituation(){
		// north, south, east, west, current;
		char[] sit = new char[5];
		sit[0] = emptyOrCanOrWall(new Point(position.x, position.y + 1));
		sit[1] = emptyOrCanOrWall(new Point(position.x, position.y - 1));
		sit[2] = emptyOrCanOrWall(new Point(position.x + 1, position.y));
		sit[3] = emptyOrCanOrWall(new Point(position.x - 1, position.y));
		sit[4] = emptyOrCanOrWall(new Point(position.x, position.y));
		//System.out.println(new String(sit));
		return new String(sit);
	}
	
	private char emptyOrCanOrWall(Point point){
		if (isOutOfBounds(point)){
			return 'c';
		}
		Tile tile = grid[point.x][point.y];
		if (tile.hasGarbage){
			return 'b';
		}
		return 'a';
		
	}
	
	private boolean isOutOfBounds(Point point){
		if ((point.x < 0) || (point.x > 9) || (point.y < 0) || (point.y > 9)){
			return	true;
		}
		return false;
	}
	
	private void bumpedIntoWallOrFakedACan(){
		//System.out.println("oops");
		this.score -= 3;
	}
	
	private void pickedUpCan(){
		this.score += 10;
	}
	
	private void moveNorth(){
		Point newPosition = new Point(this.position.x, this.position.y + 1);
		if (isOutOfBounds(newPosition)){
			bumpedIntoWallOrFakedACan();
			return;
		}
		this.position = newPosition;
	}
	
	private void moveSouth(){
		Point newPosition = new Point(this.position.x, this.position.y - 1);
		if (isOutOfBounds(newPosition)){
			bumpedIntoWallOrFakedACan();
			return;
		}
		this.position = newPosition;
	}
	
	private void moveEast(){
		Point newPosition = new Point(this.position.x + 1, this.position.y);
		if (isOutOfBounds(newPosition)){
			bumpedIntoWallOrFakedACan();
			return;
		}
		this.position = newPosition;
	}
	
	private void moveWest(){
		Point newPosition = new Point(this.position.x - 1, this.position.y);
		if (isOutOfBounds(newPosition)){
			bumpedIntoWallOrFakedACan();
			return;
		}
		this.position = newPosition;
		
	}
	
	private void stayPut(){
		//do nothing
		return;
	}
	
	private void pickUp(){
		if (this.grid[this.position.x][this.position.y].hasGarbage){
			//System.out.println("pick up");
			this.grid[this.position.x][this.position.y].hasGarbage = false;
			pickedUpCan();
			return;
		}
		bumpedIntoWallOrFakedACan();
	}
	
	private void randomMove(){
		Random rand = new Random();
		int n = rand.nextInt(3);
		switch(n){
			case 0:
			{
				moveNorth();
				return;
			}
			case 1:
			{
				moveEast();
				return;
			}
			case 2:
			{
				moveWest();
				return;
			}
			case 3:
			{
				moveSouth();
				return;
			}
		}
	}

}
