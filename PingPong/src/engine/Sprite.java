package engine;

import java.awt.Graphics;

public abstract class Sprite {
	
	public double x, y, width, height;
	
	public Sprite(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public Sprite() {}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
}