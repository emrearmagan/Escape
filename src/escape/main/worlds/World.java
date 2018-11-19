package escape.main.worlds;

import java.awt.Graphics;

import escape.main.Handler;
import escape.main.tiles.Tile;
import escape.main.utils.Utils;

public class World
{
	private Handler handler;

	private int width, height;
	private int[][] tiles;

	public World(Handler handler, String path) {
		this.handler = handler;
		loadWorld(path);
	}

	public void update() {

	}

	public void render(Graphics g) {
		int xStart = (int) Math.max(0, (handler.getGameCamera().getxOffset() / Tile.TILEWIDTH));
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getCanvas().getWidth()) / Tile.TILEWIDTH + 1);

		int yStart = (int) Math.max(0, (handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT));
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getCanvas().getHeight()) / Tile.TILEHEIGHT + 1);

		for (int y = yStart; y < yEnd; y++) {
			for (int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
	}

	public Tile getTile(int x, int y) {
		// JUST TO PREVENT ANY ERRORS IF THE PLAYER GOES SOMEHOW OUT OF THE MAP
		if (x < 0 || y < 0 || x >= width || y >= height) {
			return Tile.grassTileMainIsland;
		}

		Tile t = Tile.tiles[tiles[x][y]];
		if (t == null)
			return Tile.dirtTile;
		return t;
	}

	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");

		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);

		tiles = new int[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				try {
					tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 2]);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
