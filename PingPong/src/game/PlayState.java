package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import engine.GameState;

public class PlayState extends GameState {
	
	private Player p1, p2;
	private Ball ball;
	
	public int pointsMax, points1, points2;
	
	private boolean paused;
	
	public Vortex vortex;
	
	public void reset() {
		points1 = points2 = 0;
		p1 = new Player(game, 0, KeyEvent.VK_W, KeyEvent.VK_S);
		p2 = new Player(game, game.width-3, KeyEvent.VK_UP, KeyEvent.VK_DOWN);
		vortex = null;
		ball = new Ball(game, p1, p2, this);
		paused = false;
	}
	
	public void render(Graphics g) {
		p1.render(g);
		p2.render(g);
		ball.render(g);
		if(vortex != null) vortex.render(g);
		
		g.setColor(new Color(0x88, 0x88, 0x88, 100));
		g.fillRect(0, 0, 25, 5);
		
		g.setColor(new Color(0xff, 0xff, 0xff, 100));
		for(int i = 0; i < points1; i++) {
			g.fillRect(5*i, 0, 5, 5);
		}
		
		g.setColor(new Color(0x88, 0x88, 0x88, 100));
		g.fillRect(game.width-25, 0, 25, 5);
		
		g.setColor(new Color(0xff, 0xff, 0xff, 100));
		for(int i = 0; i < points2; i++) {
			g.fillRect(game.width-25 + 5*i, 0, 5, 5);
		}
		
		if(paused) {
			g.setColor(Color.WHITE);
			g.drawString("PAUSED", game.width/2-20, 20);
		}
		
	}
	
	public void tick() {
		if(game.keyboard.isKeyClicked(KeyEvent.VK_ESCAPE)) {
			paused = !paused;
		}
		
		if(paused) return;
		
		p1.tick();
		p2.tick();
		ball.tick();
		if(vortex != null) {
			vortex.tick();
			if(vortex.remove) vortex = null;
		} else {
			if(Math.random()*100 < 1) {
				vortex = new Vortex(Math.random()*game.width, Math.random()*game.height);
			}
		}
		
		if(points1 >= pointsMax) {
			VictoryState.PLAYER = "Player 1";
			game.setState(1);
		} else if(points2 >= pointsMax) {
			VictoryState.PLAYER = "Player 2";
			game.setState(1);
		}
		
		
		
	}
	
	public void create() {
		p1 = new Player(game, 0, KeyEvent.VK_W, KeyEvent.VK_S);
		p2 = new Player(game, game.width-3, KeyEvent.VK_UP, KeyEvent.VK_DOWN);
		ball = new Ball(game, p1, p2, this);
		pointsMax = 5;
	}
	
}