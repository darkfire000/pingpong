package game;

import java.awt.Color;
import java.awt.Graphics;

import engine.Sprite;

public class Vortex extends Sprite {
	
	private long lifetime, timeToShift;
	private double shiftIn;
	public double radius;
	private double gravity;
	public boolean remove;
	
	public Vortex(double x, double y) {
		super(x, y, 20, 20);
		radius = width/2;
		lifetime = 180;
		timeToShift = 10;
		gravity = 0.4;
	}
	
	public void tick() {
		timeToShift--;
		if(timeToShift <= 0) {
			if(shiftIn == 0) shiftIn = 1;
			else shiftIn = 0;
			timeToShift = 10;
		}
		
		lifetime--;
		if(lifetime == 0) {
			remove = true;
		}
	}
	
	public double getGravity() {
		return gravity;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		
//		g.drawOval((int)(x-(radius-2*timeToShift/10)), (int)(y-(radius-2*timeToShift/10)), (int)((radius-2*timeToShift/10)*2), (int)((radius-2*timeToShift)*2));
		
		for(int i = 0; i < 6; i++) {
			g.drawOval((int)(x-(radius-2*i-shiftIn)), (int)(y-(radius-2*i-shiftIn)), (int)((radius-2*i-shiftIn)*2), (int)((radius-2*i-shiftIn)*2));
		}
	}
	
}