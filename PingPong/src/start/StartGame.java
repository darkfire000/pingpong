package start;

import engine.Game;
import engine.GameState;
import game.PlayState;
import game.StartState;
import game.VictoryState;

public class StartGame {
	
	public static void main(String[] args) {
		
		Game g = new Game(160, 100, 6, "");
		g.setGameStates(2, new GameState[]{
				new PlayState(), new VictoryState(), new StartState()
		});
		g.show();
		g.start();
		
	}
	
}