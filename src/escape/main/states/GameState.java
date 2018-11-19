package escape.main.states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import escape.main.Handler;
import escape.main.entities.creatures.Player;
import escape.main.worlds.World;

public class GameState extends State
{

	private Player player;
	private boolean gameStarted;
	private boolean gameSaved;

	private float playerX = 0, playerY = 0;
	private World world;

	public GameState(Handler handler) {
		super(handler);
		player = new Player(handler, playerX, playerY);
		world = new World(handler, "res/Worlds/World1.txt");
	}

	@Override
	public void update() {
		world.update();
		player.update();

		if (handler.getGame().getKeyManager().alt && handler.getGame().getKeyManager().f4)
			new OptionFrame(handler, "Save game ? ");

		if (handler.getKeyManager().getJustPressed(KeyEvent.VK_ESCAPE)) {
			State.setCurrentState(handler.getGame().getInGameSettings());
			handler.getGame().initState();
		}
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
		player.render(g);
	}

	@Override
	public void init() {
		// try {
		// Thread.sleep(500);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		if (handler.getGameSaver().fullscreen) {
			handler.getFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);
		}
		else {
			handler.getFrame().setSize(handler.getGame().getWidth(), handler.getGame().getHeight());
			handler.getFrame().setLocationRelativeTo(null);
		}

		handler.getFrame().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		handler.getFrame().requestFocus();
		handler.getCanvas().setSize(handler.getFrame().getWidth(), handler.getFrame().getHeight());
		handler.getCanvas().setVisible(true);

		gameStarted = true;
		gameSaved = false;
	}

	@Override
	public void YES_NO_CANCEL_OPTION(int selection) {
		handler.getFrame().setEnabled(true);
		handler.getFrame().requestFocus();

		if (selection == 0) {
			handler.getGameSaver().saveFile();
		}

		else if (selection == 2) {
			return;
		}

		setCurrentState(handler.getGame().getMenuState());
		handler.getGame().initState();
	}

	public boolean isGameStarted() {
		return gameStarted;
	}

	public void setGameStarted(boolean gameStarted) {
		this.gameStarted = gameStarted;
	}

	public boolean isGameSaved() {
		return gameSaved;
	}

	public void setGameSaved(boolean gameSaved) {
		this.gameSaved = gameSaved;
	}

	public Player getPlayer() {
		return player;
	}

	public World getWorld() {
		return world;
	}

}
