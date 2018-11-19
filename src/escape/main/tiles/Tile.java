package escape.main.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile
{
	public static Tile[] tiles = new Tile[256];
	// public static Tile grassTile = new GrassTile(10);
	public static Tile dirtTile = new DirtTile(2);
	// public static Tile rockTile = new RockTile(2);
	// public static Tile woodTile = new WoodTile(3);
	// public static Tile waterTile = new WaterTile(4);

	// TILES MAIN ISLAND
	public static Tile grassTileMainIsland = new GrassTileMainIsland(0);

	// CLASS
	public static final int TILEWIDTH = 32, TILEHEIGHT = 32;
	protected BufferedImage texture;
	protected final int id;

	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;

		tiles[id] = this;
	}

	public void tick() {

	}

	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);

	}

	public boolean isSolid() {
		return false;
	}

	public int getId() {
		return id;
	}
}
