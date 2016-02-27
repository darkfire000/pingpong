package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import engine.GameState;

public class StartState extends GameState {
	
	public StartState() {
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
		g.drawString("PRESS ENTER TO START", 5, 20);
		
	}
	
	public void reset() {
		
	}
	
}