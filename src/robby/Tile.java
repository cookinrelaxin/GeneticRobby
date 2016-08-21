package robby;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Random;

public class Tile {
	
	boolean hasGarbage;
	Point position;
	
	
	static final Dimension size = new Dimension (60,60);
	public Tile(){
		Random rand = new Random();
		int n = rand.nextInt(2);
		if (n == 0){
			this.hasGarbage = false;
			return;
		}
		this.hasGarbage = true;
	}
}
