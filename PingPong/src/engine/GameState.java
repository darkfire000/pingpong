package engine;

import java.awt.Graphics;

public abstract class GameState {
	
	public Game game;
	
	public abstract void reset();
	
	public abstract void render(Graphics g);
	
	public abstract void tick();
	
	public abstract void create();
	
}