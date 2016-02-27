package game;

import java.awt.Color;
import java.awt.Graphics;

import engine.Game;
import engine.Sprite;

public class Player extends Sprite {

	private Game game;
	
	private int kUp, kDown;
	
	private double speed;
	
	public boolean freeze;
	
	public Player(Game game, int x, int kUp, int kDown) {
		super(x, 0, 3, 20);
		this.y = game.height/2-height/2;
		this.game = game;
		this.kUp = kUp;
		this.kDown = kDown;
		
		speed = 3.5;
	}
	
	public void tick() {
		if(freeze) return;
		
		if(game.keyboard.isKeyDown(kUp)) {
			y -= speed;
		} else if(game.keyboard.isKeyDown(kDown)) {
			y += speed;
		}
		
		if(y < 0) y = 0;
		else if(y + height > game.height) y = game.height -height;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect((int)x, (int)y, (int)width, (int)height);
	}
	
}