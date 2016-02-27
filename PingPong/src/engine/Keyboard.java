package engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	
	private boolean[] keys, pkeys;
	
	public Keyboard() {
		keys = new boolean[512];
		pkeys = new boolean[512];
	}
	
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}
	
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	
	public void tick() {
		for(int i = 0; i < keys.length; i++) {
			pkeys[i] = keys[i];
		}
	}
	
	public boolean isKeyDown(int k) {
		return keys[k];
	}
	
	public boolean isKeyClicked(int k) {
		return keys[k] && !pkeys[k];
	}
	
	public void keyTyped(KeyEvent e) {}
	
}