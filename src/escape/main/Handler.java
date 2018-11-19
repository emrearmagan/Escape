package escape.main;

import java.awt.Canvas;

import javax.swing.JFrame;

import escape.main.graphics.GameCamera;
import escape.main.input.KeyManager;
import escape.main.states.GameState;
import escape.main.states.SettingState;
import escape.main.states.State;
import escape.main.worlds.World;

public class Handler
{
	private Game game;

	public Handler(Game game) {
		this.game = game;
	}

	public Game getGame() {
		return game;
	}

	public JFrame getFrame() {
		return game.getDisplay().getFrame();
	}

	public Canvas getCanvas() {
		return game.getDisplay().getCanvas();
	}

	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}

	public GameSaver getGameSaver() {
		return game.getGameSaver();
	}

	public GameState getGameState() {
		return game.getGameState();
	}

	public State getMenuState() {
		return game.getMenuState();
	}

	public SettingState getSettingState() {
		return game.getSettingState();
	}

	public GameCamera getGameCamera() {
		return game.getGameCamera();
	}

	public World getWorld() {
		return game.getGameState().getWorld();
	}
}
