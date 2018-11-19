package escape.main.graphics;

import escape.main.Handler;
import escape.main.entities.Entity;
import escape.main.tiles.Tile;

public class GameCamera
{

	private Handler handler;

	private float xOffset, yOffset;

	public GameCamera(Handler handler, float xOffset, float yOffset) {
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void checkBlankSpace() {
		if (xOffset < 0)
			xOffset = 0;
		else if (xOffset > handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getFrame().getWidth())
			xOffset = handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getFrame().getWidth();

		if (yOffset < 0)
			yOffset = 0;
		else if (yOffset > handler.getWorld().getHeight() * Tile.TILEWIDTH - handler.getFrame().getHeight())
			yOffset = handler.getWorld().getHeight() * Tile.TILEWIDTH - handler.getFrame().getHeight();

	}

	public void centerOnEntity(Entity e) {

		float lerp = 0.1f;
		xOffset += lerp * (e.getX() - handler.getCanvas().getWidth() / 2 + e.getWidth() / 2 - xOffset);
		yOffset += lerp * (e.getY() - handler.getCanvas().getHeight() / 2 + e.getHeight() / 2 - yOffset);

		// xOffset = e.getX() - handler.getCanvas().getWidth() / 2 + e.getWidth() / 2;
		// yOffset = e.getY() - handler.getCanvas().getHeight() / 2 + e.getHeight() / 2;

		checkBlankSpace();
	}

	// public void move(float xAmt, float yAmt) {
	// xOffset += xAmt;
	// yOffset += yAmt;
	//
	// checkBlankSpace();
	// }

	// GETTERS AND SETTERS
	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

}
