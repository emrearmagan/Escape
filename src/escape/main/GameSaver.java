package escape.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GameSaver
{
	private Handler handler;
	public boolean fullscreen;
	public boolean soundMusic;
	public boolean soundEffect;
	public float playerX, playerY;

	private static String path = "." + File.separator + "Settings.txt";

	public GameSaver(Handler handler) {
		this.handler = handler;
	}

	public void saveFile() {
		BufferedWriter out = null;
		try {
			File settingsPath = new File(path);
			if (!settingsPath.exists()) {
				initFile();
			}
			// ERSTELLE EINE DATEI AUF DEM SPEICHER
			out = new BufferedWriter(new FileWriter(settingsPath));

			// HOLE DIR DIE NEUEN AKTUALISIERTEN WERTE AUS DEM SPIEL

			/////

			getNewData();

			////

			// SCHREIBE DIE WERTE IN DIE DATEI UM SIE BEIM NÄCHSTEN SPIELSTART ZU BENUTZEN
			out.write(Boolean.toString(fullscreen));
			out.newLine();

			out.write(Boolean.toString(soundMusic));
			out.newLine();

			out.write(Boolean.toString(soundEffect));
			out.newLine();

			out.write(Float.toString(playerX));
			out.newLine();

			out.write(Float.toString(playerY));
			out.newLine();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if (out != null) {
				try {
					out.close();

					System.out.println(fullscreen);
					System.out.println(soundMusic);
					System.out.println(soundEffect);

					System.out.println(playerX);
					System.out.println(playerY);

				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	public void loadFile() {
		BufferedReader in = null;
		File settingsPath = new File(path);
		try {
			if (!settingsPath.exists()) {
				initFile();
			}
			in = new BufferedReader(new FileReader(settingsPath));

			fullscreen = Boolean.parseBoolean(in.readLine());
			soundMusic = Boolean.parseBoolean(in.readLine());
			soundEffect = Boolean.parseBoolean(in.readLine());

			playerX = Float.parseFloat(in.readLine());
			playerY = Float.parseFloat(in.readLine());

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		finally {
			if (in != null) {
				try {
					in.close();

					System.out.println(fullscreen);
					System.out.println(soundMusic);
					System.out.println(soundEffect);

					System.out.println(playerX);
					System.out.println(playerY);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	private void getNewData() {
		fullscreen = handler.getSettingState().isFullscreen();
		soundMusic = handler.getSettingState().isSoundMusic();
		soundEffect = handler.getSettingState().isSoundEffect();

		playerX = handler.getGameState().getPlayer().getX();
		playerY = handler.getGameState().getPlayer().getY();
	}

	private void initFile() {
		BufferedWriter out = null;
		try {
			File settingsPath = new File(path);
			settingsPath.createNewFile();
			// ERSTELLE EINE DATEI AUF DEM SPEICHER
			out = new BufferedWriter(new FileWriter(settingsPath));

			// SCHREIBE DIE WERTE IN DIE DATEI UM SIE BEIM NÄCHSTEN SPIELSTART ZU BENUTZEN

			fullscreen = false;
			soundMusic = true;
			soundEffect = true;

			playerX = 100;
			playerY = 100;

			out.write(Boolean.toString(fullscreen));
			out.newLine();

			out.write(Boolean.toString(soundMusic));
			out.newLine();

			out.write(Boolean.toString(soundEffect));
			out.newLine();

			out.write(Float.toString(playerX));
			out.newLine();

			out.write(Float.toString(playerY));
			out.newLine();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}
