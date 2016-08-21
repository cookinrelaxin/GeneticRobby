package robby;
//import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

class StrategyDisplayPanel extends JPanel {
	private Strategy strategy;
	private RoomModelView room;
	private Rob tester;

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public StrategyDisplayPanel(Strategy strat) {
        JFrame f = new JFrame("alpha male");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(this);
        f.pack();
        f.setVisible(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
        this.strategy = strat;
        this.room = new RoomModelView();
        this.tester = new Rob(this.room.tiles, this.strategy);
        tester.goAndShowMoves(this);

    }

    public Dimension getPreferredSize() {
        return new Dimension(600,600);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
       // System.out.println("draw");
        
        for(Tile[] row : room.tiles){
	        for(Tile tile : row){
	        	g.drawRect(tile.position.x, tile.position.y, Tile.size.width, Tile.size.height);
	        	if (tile.hasGarbage) {
	        		g.setColor(Color.red);
	        		g.fillRect(tile.position.x + 15, tile.position.y + 15, Tile.size.width / 2, Tile.size.height / 2);
	        		g.setColor(Color.black);
	        	}
	
	        }
	     }
        g.setColor(Color.cyan);
       g.fillOval(tester.position.x * 60 + 15, tester.position.y * 60 + 15, Tile.size.width / 2, Tile.size.height / 2);
       System.out.println(tester.position);
    }  
    
    
    
    static final Dimension TILE_SIZE = new Dimension(60,60);
    static final Dimension ROOM_SIZE = new Dimension(TILE_SIZE.width,TILE_SIZE.height);
    
    
}
