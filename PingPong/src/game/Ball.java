package game;

import java.awt.Color;
import java.awt.Graphics;

import engine.Game;
import engine.Sprite;

public class Ball extends Sprite {
	
	private Game game;
	
	private double speedx, speedy, speedyMax, speedxMax, speedxMin;
	private Player p1, p2;
	private PlayState ps;
	
	private int countDown, countDownMax;
	private boolean doReset;
	
	public Ball(Game game, Player p1, Player p2, PlayState ps) {
		super(0, 0, 4, 4);
		this.game = game;
		x = game.width/2-width/2;
		y = game.height/2-height/2;
		
		speedyMax = 3;
		speedxMax = 4;
		speedxMin = 2.5;
		
		this.p1 = p1;
		this.p2 = p2;
		this.ps = ps;

		reset();
		
		countDown = countDownMax = game.getFps();
		
	}
	
	public void tick() {
		if(countDown > 0) {
			countDown--;
			p1.freeze = true;
			p2.freeze = true;
			return;
		}
		if(doReset) {
			doReset = false;
			reset();
			return;
		}

		p1.freeze = false;
		p2.freeze = false;
		
		x += speedx;
		y += speedy;
		
		if(y < 0) {
			y = 0;
			speedy *= -1;
		} else if(y+height > game.height) {
			y = game.height-height;
			speedy *= -1;
		}
		
		if(x < p1.x+p1.width && y+height > p1.y && y < p1.y+p1.height) {
			x = p1.x+p1.width;
			speedx *= -1;
			speedx += Math.random()*3-1.5;
			speedy += Math.random()*4-2;
		}
		
		if(x+width > p2.x && y+height > p2.y && y < p2.y+p2.height) {
			x = p2.x-width;
			speedx *= -1;
			speedx += Math.random()*3-1.5;
			speedy += Math.random()*4-2;
		}

		if(speedy < -speedyMax) speedy = -speedyMax;
		else if(speedy > speedyMax) speedy = speedyMax;

		if(Math.abs(speedx) > speedxMax) speedx = speedx / Math.abs(speedx) * speedxMax;
		if(Math.abs(speedx) < speedxMin) speedx = speedx / Math.abs(speedx) * speedxMin;
		
		
		if(x < 0) {
			ps.points2++;
			countDown = countDownMax;
			doReset = true;
		} else if(x+width > game.width) {
			ps.points1++;
			countDown = countDownMax;
			doReset = true;
		}
		
	}
	
	private void reset() {
		x = game.width/2-width/2;
		y = game.height/2-height/2;
		
		if(Math.random() < 0.5) speedx = -2.8;
		else speedx = 2.8;

		speedy = (int)(Math.random()*6-3);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		if(countDown > 0) g.setColor(Color.RED);
		g.fillRect((int)Math.rint(x), (int)Math.rint(y), (int)Math.rint(width), (int)Math.rint(height));
	}
	
}