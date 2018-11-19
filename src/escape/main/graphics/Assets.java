package escape.main.graphics;

import java.awt.image.BufferedImage;

public class Assets
{

	// 64 IST UM 1PIXEL ZU GROSS !!!!!!!!!!!!!!!!!!!!!!!!!!!
	private static final int width = 64, height = 64;

	// TILES
	public static BufferedImage gras, dirt, stone, wood, water;

	// TILES MAIN ISLAND
	public static BufferedImage grasMainIsland;

	// PLAYERS
	public static BufferedImage[] walksRight = new BufferedImage[8];
	public static BufferedImage[] walksLeft = new BufferedImage[8];
	public static BufferedImage[] playerFront = new BufferedImage[8];
	public static BufferedImage[] playerBack = new BufferedImage[8];

	public static void init() {
		///// FILE SEPERATOR FUNKTIONIERT NICHT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// SpriteSheet Playersheet = new SpriteSheet(ImageLoader.loadImage(File.separator + "Entities" + File.separator + "Player" + File.separator + "Player.png"));
		SpriteSheet Playersheet = new SpriteSheet(ImageLoader.loadImage("/Entities/Player/Player.png"));
		// SpriteSheet TileSheet = new SpriteSheet(ImageLoader.loadImage(File.separator + "Tiles" + File.separator + "Tiles.png"));
		SpriteSheet TileSheet = new SpriteSheet(ImageLoader.loadImage("/Tiles/Tiles.png"));

		SpriteSheet TileSheetMainIsland = new SpriteSheet(ImageLoader.loadImage("/Tiles/MainIsland/GrasTileMainIsland.png"));

		// PLAYER
		playerFront[0] = Playersheet.crop(0, width * 4, width, height);
		playerFront[1] = Playersheet.crop(0, width * 4, width, height);
		playerFront[2] = Playersheet.crop(0, width * 4, width, height);
		playerFront[3] = Playersheet.crop(0, width * 4, width, height);
		playerFront[4] = Playersheet.crop(0, width * 4, width, height);
		playerFront[5] = Playersheet.crop(0, width * 4, width, height);
		playerFront[6] = Playersheet.crop(0, width * 4, width, height);
		playerFront[7] = Playersheet.crop(0, width * 4, width, height);

		playerBack[0] = Playersheet.crop(width, width * 4, width, height);
		playerBack[1] = Playersheet.crop(width, width * 4, width, height);
		playerBack[2] = Playersheet.crop(width, width * 4, width, height);
		playerBack[3] = Playersheet.crop(width, width * 4, width, height);
		playerBack[4] = Playersheet.crop(width, width * 4, width, height);
		playerBack[5] = Playersheet.crop(width, width * 4, width, height);
		playerBack[6] = Playersheet.crop(width, width * 4, width, height);
		playerBack[7] = Playersheet.crop(width, width * 4, width, height);

		walksRight[0] = Playersheet.crop(0, 0, width, height);
		walksRight[1] = Playersheet.crop(width, 0, width, height);
		walksRight[2] = Playersheet.crop(width * 2, 0, width, height);
		walksRight[3] = Playersheet.crop(width * 3, 0, width, height);
		walksRight[4] = Playersheet.crop(0, width, width, height);
		walksRight[5] = Playersheet.crop(width, width, width, height);
		walksRight[6] = Playersheet.crop(width * 2, width, width, height);
		walksRight[7] = Playersheet.crop(width * 3, width, width, height);

		walksLeft[0] = Playersheet.crop(width * 3, width * 2, width, height);
		walksLeft[1] = Playersheet.crop(width * 2, width * 2, width, height);
		walksLeft[2] = Playersheet.crop(width, width * 2, width, height);
		walksLeft[3] = Playersheet.crop(0, width * 2, width, height);
		walksLeft[4] = Playersheet.crop(0 * 2, width * 3, width, height);
		walksLeft[5] = Playersheet.crop(width, width * 3, width, height);
		walksLeft[6] = Playersheet.crop(0 * 2, width * 3, width, height);

		// MAIN ISLAND TILES
		grasMainIsland = TileSheetMainIsland.crop(0, 0, width, height);

		// TILES
		// gras = TileSheet.crop(0, 0, width, width);
		dirt = TileSheet.crop(width, 0, width, height);
		// stone = TileSheet.crop(width * 2 + 3, 0, width, height);
		// wood = TileSheet.crop(width * 3 + 3, 0, width, height);
		// water = TileSheet.crop(width * 4 + 3, 0, width, height);

	}

}
