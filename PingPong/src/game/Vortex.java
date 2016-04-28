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
	
	private int type;
	
	private static final int TYPE_NORMAL = 0;
	private static final int TYPE_STRONG = 1;
	private static final int TYPE_WEAK = 2;
	
	public Vortex(double x, double y) {
		super(x, y, 20, 20);
		radius = width/2;
		lifetime = 160 + (int)(Math.random()*60-30);
		timeToShift = 10;
		
		type = (int)(Math.random()*3);
		switch(type) {
		case TYPE_NORMAL:
			gravity = 0.4;
			break;
		case TYPE_STRONG:
			gravity = 0.6;
			break;
		case TYPE_WEAK:
			gravity = 0.2;
			radius = 15;
			break;
		}
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
		switch(type) {
		case TYPE_NORMAL:
			g.setColor(Color.BLUE);
			break;
		case TYPE_STRONG:
			g.setColor(Color.RED);
			break;
		case TYPE_WEAK:
			g.setColor(Color.CYAN);
			break;
		}
		
//		g.drawOval((int)(x-(radius-2*timeToShift/10)), (int)(y-(radius-2*timeToShift/10)), (int)((radius-2*timeToShift/10)*2), (int)((radius-2*timeToShift)*2));
		
		for(int i = 0; i < 6; i++) {
			g.drawOval((int)(x-(radius-2*i-shiftIn)), (int)(y-(radius-2*i-shiftIn)), (int)((radius-2*i-shiftIn)*2), (int)((radius-2*i-shiftIn)*2));
		}
	}
	
}