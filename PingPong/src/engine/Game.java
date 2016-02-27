package engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game implements Runnable {
	
	private JFrame window;
	private JPanel panel;
	
	public final int width, height, scale, sWidth, sHeight;
	
	private final int fps;
	
	private Thread thread;
	private boolean running;
	
	private BufferedImage buffer;
	
	private Color bgColor;
	
	private GameState[] states;
	private int currState;
	
	public final Keyboard keyboard;
	
	public Game(int width, int height, int scale, String title) {
		this.width = width;
		this.height = height;
		this.scale = scale;
		this.sWidth = width*scale;
		this.sHeight = height*scale;
		
		keyboard = new Keyboard();
		
		fps = 32;
		
		window = new JFrame(title);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(width*scale, height*scale));
		panel.setFocusable(true);
		panel.addKeyListener(keyboard);
		
		window.setContentPane(panel);
		window.pack();
		
		buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		bgColor = Color.BLACK;
		
	}
	
	public void show() {
		window.setVisible(true);
		panel.requestFocus();
	}
	
	public void start() {
		if(states == null) {
			System.err.println("No gamestates specified");
			return;
		}
		if(running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void setGameStates(int index, GameState[] gs) {
		states = gs;
		currState = index;
		
		for(GameState g:gs) {
			g.game = this;
			g.create();
		}
	}
	
	public void resetState(int index) {
		states[index].reset();
	}
	
	public void setState(int index) {
		currState = index;
	}
	
	public void run() {
		
		long tLast = System.currentTimeMillis();
		long tNow, tDelta = 0;
		long tTarget = 1000/fps;
		
		while(running) {
			
			tick();
			render();
			
			tNow = System.currentTimeMillis();
			tDelta = tNow-tLast;
			try {
				if(tTarget-tDelta > 0) Thread.sleep(tTarget - tDelta);
				else System.out.println("FPS-Drop: " + (1000/tDelta));
			} catch (InterruptedException e) {}
			tLast = System.currentTimeMillis();
		}
		
	}
	
	private void render() {
		Graphics g = buffer.getGraphics();
		
		g.setColor(bgColor);
		g.fillRect(0, 0, width, height);
		
		states[currState].render(g);
		
		g.dispose();
		g = panel.getGraphics();
		g.drawImage(buffer, 0, 0, sWidth, sHeight, null);
		g.dispose();
		
	}
	
	private void tick() {
		states[currState].tick();
		keyboard.tick();
	}
	
	public int getFps() {
		return fps;
	}
	
}