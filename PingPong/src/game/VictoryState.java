package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import engine.GameState;

public class VictoryState extends GameState {
	
	public static String PLAYER;
	
	public VictoryState() {
		PLAYER = "Player 1";
	}
	
	public void create() {
		
	}
	
	public void tick() {
		if(game.keyboard.isKeyClicked(KeyEvent.VK_ENTER) || game.keyboard.isKeyClicked(KeyEvent.VK_SPACE)) {
			game.resetState(0);
			game.setState(0);
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, game.width, game.height);
		
		g.setColor(Color.WHITE);
		g.drawString(PLAYER + " is victorious", 20, 10);
		
	}
	
	public void reset() {
		
	}
	
}