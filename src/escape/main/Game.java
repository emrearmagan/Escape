package escape.main;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import escape.main.display.Display;
import escape.main.graphics.Assets;
import escape.main.graphics.GameCamera;
import escape.main.input.KeyManager;
import escape.main.states.GameState;
import escape.main.states.InGameSettings;
import escape.main.states.MenuState;
import escape.main.states.SettingState;
import escape.main.states.State;

public class Game implements Runnable
{
	private int width, height;

	private boolean running = false;

	// OBJECTS
	private Handler handler;
	private Display display;

	// States
	private State menuState;
	private SettingState settingState;
	private GameState gameState;
	private InGameSettings inGameSettings;

	private GameSaver gameSaver;

	///
	private Thread gameThread;
	// private Graphics g;

	// INPUT
	private KeyManager keyManager;

	// GAMECAMERA
	private GameCamera gameCamera;

	public Game(int width, int height) {
		this.width = width;
		this.height = height;

		keyManager = new KeyManager();
		handler = new Handler(this);

		gameSaver = new GameSaver(handler);
		gameSaver.loadFile();

		start();

	}

	private void init() {

		display = new Display(this, width, height);
		display.getFrame().addKeyListener(keyManager);
		Assets.init();

		gameCamera = new GameCamera(handler, 0, 0);

		// STATES
		menuState = new MenuState(handler);
		settingState = new SettingState(handler);
		gameState = new GameState(handler);

		inGameSettings = new InGameSettings(handler);


		State.setCurrentState(menuState);
		initState();
	}

	public void initState() {
		State.getCurrentState().init();
	}

	private void update() {
		keyManager.tick();

		if (State.getCurrentState() != null)
			State.getCurrentState().update();

	}

	public void render() {
		// GET GRAPHICS
		BufferStrategy bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		// CLEAR SCREEN
		g.clearRect(0, 0, handler.getCanvas().getWidth(), handler.getCanvas().getHeight());

		// DRAW HERE
		if (State.getCurrentState() != null)
			State.getCurrentState().render(g);

		// END DRAWING
		g.dispose();
		bs.show();

	}

	@Override
	public void run() {
		init();

		int fps = 60;

		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double timePerTick = 1000000000.0 / fps;
		double delta = 0;
		int frames = 0;
		int updates = 0;

		while (running) {
			synchronized (this) {
				long now = System.nanoTime();
				delta += (now - lastTime) / timePerTick;
				lastTime = now;

				if (delta >= 1) {
					update();
					updates++;
					delta--;
				}
				render();
				frames++;

				if (System.currentTimeMillis() - timer > 1000) {
					timer += 1000;
					System.out.println(updates + " ups, " + frames + " fps");
					updates = 0;
					frames = 0;
				}
			}
		}
		stop();
	}

	public synchronized void start() {
		if (running)
			return;
		running = true;

		gameThread = new Thread(this, "GameThread");
		gameThread.start();
	}

	public synchronized void stop() {
		if (!running)
			return;
		running = false;

		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// GETTERS AND SETTERS

	public Display getDisplay() {
		return display;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public State getMenuState() {
		return menuState;
	}

	public SettingState getSettingState() {
		return settingState;
	}

	public GameState getGameState() {
		return gameState;
	}

	public InGameSettings getInGameSettings() {
		return inGameSettings;
	}

	public Thread getGameThread() {
		return gameThread;
	}

	public KeyManager getKeyManager() {
		return keyManager;
	}

	public GameSaver getGameSaver() {
		return gameSaver;
	}

	public GameCamera getGameCamera() {
		return gameCamera;
	}

}
