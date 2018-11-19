package escape.main.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import escape.main.Handler;

public class InGameSettings extends State
{
	private int x = 0, y = 0;
	private boolean[][] options = new boolean[3][2];

	public InGameSettings(Handler handler) {
		super(handler);
	}

	@Override
	public void init() {

		for (int x = 0; x < options.length; x++) {
			for (int y = 0; y < options[x].length; y++)
				options[x][y] = false;

		}
		options[2][1] = true;
	}

	private void exit() {
		// DETECT WHAT THE USER SELECTED AND REACT TO IT BEFOR CLOSING THE STATE

		// SELECTED SAVE GAME
		if (options[0][0]) {
			handler.getGameSaver().saveFile();
			handler.getGame().getGameState().setGameSaved(true);

			//// STILL NEED TO TELL THE USER THAT THE GAME JUST SAVED
		}

		// SELECTED EXIT GAME
		if (options[0][1]) {
			if (handler.getGame().getGameState().isGameSaved()) {
				// DELETE GAME HERE//////////////////////////////////////////////////////////////////////////////////////////
				setCurrentState(handler.getGame().getMenuState());
				handler.getGame().initState();
			}
			else
				new OptionFrame(handler, "Save Game ?");
		}

		// SELECTED NEW GAME
		if (options[1][0]) {
			if (handler.getGame().getGameState().isGameSaved()) {
				// START NEW GAME HERE//////////////////////////////////////////////////////////////////////////////////////////
			}
			else
				new OptionFrame(handler, "Save Game ?");
		}

		// SELECTED SETTINGS
		if (options[1][1]) {
			setCurrentState(handler.getGame().getSettingState());
			handler.getGame().initState();
		}

		// SELECTED BACK
		if (options[2][0] || options[2][1]) {
			State.setCurrentState(handler.getGame().getGameState());
			x = 0;
			y = 0;
		}
	}

	@Override
	public void update() {
		// KEYMANAGER iNPUT DETECTION
		if (handler.getKeyManager().getJustPressed(KeyEvent.VK_LEFT)) {
			System.out.println("Left");
			for (int x = 0; x < options.length; x++) {
				for (int y = 0; y < options[x].length; y++)
					if (options[x][y]) {
						try {
							options[x][y - 1] = true;
							options[x][y] = false;
							return;
						} catch (IndexOutOfBoundsException ex) {
							options[x][y] = true;
							return;
						}

					}
			}
		}
		if (handler.getKeyManager().getJustPressed(KeyEvent.VK_RIGHT)) {
			System.out.println("right");
			for (int x = 0; x < options.length; x++) {
				for (int y = 0; y < options[x].length; y++)
					if (options[x][y]) {
						try {
							options[x][y + 1] = true;
							options[x][y] = false;
							return;
						} catch (IndexOutOfBoundsException ex) {
							options[x][y] = true;
							return;
						}
					}
			}
		}
		if (handler.getKeyManager().getJustPressed(KeyEvent.VK_UP)) {
			System.out.println("up");
			for (int x = 0; x < options.length; x++) {
				for (int y = 0; y < options[x].length; y++)
					if (options[x][y]) {
						try {
							options[x - 1][y] = true;
							options[x][y] = false;
							return;
						} catch (Exception ex) {
							options[x][y] = true;
							return;
						}
					}
			}
		}

		if (handler.getKeyManager().getJustPressed(KeyEvent.VK_DOWN)) {
			System.out.println("down");
			for (int x = 0; x < options.length; x++) {
				for (int y = 0; y < options[x].length; y++)
					if (options[x][y]) {
						try {
							options[x + 1][y] = true;
							options[x][y] = false;
							return;
						} catch (Exception ex) {
							options[x][y] = true;
							return;
						}
					}
			}
		}

		if (handler.getKeyManager().getJustPressed(KeyEvent.VK_ENTER)) {
			exit();
		}

		if (handler.getKeyManager().getJustPressed(KeyEvent.VK_ESCAPE)) {
			State.setCurrentState(handler.getGame().getGameState());
			x = 0;
			y = 0;
		}

		////
		// TO RENDER THE GAMESETTINGS PANEL IN "REAL TIME"
		if (x <= handler.getCanvas().getWidth() / 2 - 120) {
			x += 12;
		}
		if (y <= 300) {
			y += 5;
		}

	}

	@Override
	public void render(Graphics g) {
		handler.getGame().getGameState().render(g);

		Graphics2D g2d = (Graphics2D) g;

		// RENDER THE OPTIONPANEL
		g2d.fillRoundRect(handler.getCanvas().getWidth() / 2 + 100, 10, x, y, 20, 20);
		g2d.fill3DRect(handler.getCanvas().getWidth() / 2 + 120, 30, x - 40, y - 40, true);

		// RENDER THE OPTIONTEXT DEPENDING ON SELECTED OR NOT
		g2d.setFont(new Font("Script MT Bold", Font.ITALIC, 25));
		if (options[0][0]) {
			g2d.setColor(Color.RED);
			g2d.drawString("Save Game", handler.getCanvas().getWidth() / 2 + 170, 100);
		}
		else {
			g2d.setColor(Color.WHITE);
			g2d.drawString("Save Game", handler.getCanvas().getWidth() / 2 + 170, 100);
		}

		if (options[0][1]) {
			g2d.setColor(Color.RED);
			g2d.drawString("Exit Game", handler.getCanvas().getWidth() / 2 + 370, 100);
		}
		else {
			g2d.setColor(Color.WHITE);
			g2d.drawString("Exit Game", handler.getCanvas().getWidth() / 2 + 370, 100);
		}

		if (options[1][0]) {
			g2d.setColor(Color.RED);
			g2d.drawString("New Game", handler.getCanvas().getWidth() / 2 + 170, 200);
		}
		else {
			g2d.setColor(Color.WHITE);
			g2d.drawString("New Game", handler.getCanvas().getWidth() / 2 + 170, 200);
		}

		if (options[1][1]) {
			g2d.setColor(Color.RED);
			g2d.drawString("Settings", handler.getCanvas().getWidth() / 2 + 370, 200);
		}
		else {
			g2d.setColor(Color.WHITE);
			g2d.drawString("Settings", handler.getCanvas().getWidth() / 2 + 370, 200);
		}

		if (options[2][0]) {
			g2d.setColor(Color.RED);
			g2d.drawString("Back", handler.getCanvas().getWidth() / 2 + 300, 270);
		}
		else if (options[2][1]) {
			g2d.setColor(Color.RED);
			g2d.drawString("Back", handler.getCanvas().getWidth() / 2 + 300, 270);
		}
		else {
			g2d.setColor(Color.WHITE);
			g2d.drawString("Back", handler.getCanvas().getWidth() / 2 + 300, 270);
		}

	}

	@Override
	public void YES_NO_CANCEL_OPTION(int selection) {
		handler.getFrame().setEnabled(true);
		handler.getFrame().requestFocus();

		if (selection == 1) { // NO SELECTED
			// DELETE SOMEHOW GAMESTATE///////////////////////////////////////////////////////////////////////////
			handler.getGame().getGameState().setGameStarted(false);
		}

		if (selection == 2) { // CANCEL SELECTED
			return;
		}

		if (selection == 0) {// YES SELECTED
			handler.getGameSaver().saveFile();
			setCurrentState(handler.getGame().getMenuState());
			handler.getGame().initState();
		}

		setCurrentState(handler.getGame().getMenuState());
		handler.getGame().initState();

	}

}
