package robby;

import java.awt.Point;

public class RoomModelView {
	Tile[][] tiles;
	
	public RoomModelView(){
		this.tiles = new Tile[10][10];
		for(int row = 0; row < tiles.length; row ++){
			for (int col = 0; col < tiles.length; col ++){
				Tile tile = new Tile();
				tile.position = new Point(row * Tile.size.width, col * Tile.size.height);
				this.tiles[row][col] = tile;
			}
		}
		//Timeline timeline = new Timeline();

	}

}
