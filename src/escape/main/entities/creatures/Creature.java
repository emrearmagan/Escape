package escape.main.entities.creatures;

import escape.main.Handler;
import escape.main.entities.Entity;
import escape.main.tiles.Tile;

public abstract class Creature extends Entity
{
	public static final int DEFAULT_HEALTH = 10;
	public static final float DEFAULT_SPEED = 4.0f;
	public static final int DEFAULT_WIDTH = 64, DEFAULT_HEIGHT = 64;

	protected int health;
	protected float speed;
	protected float xMove, yMove;

	public Creature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		this.health = DEFAULT_HEALTH;
		this.speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}

	public void move() {
		moveX();
		moveY();
	}

	public void moveX() {
		if (xMove > 0) {// Moving right
			int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;

			if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				x += xMove;
			}
			else {
				x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
			}
		}
		else if (xMove < 0) {// Moving left
			int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;

			if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				x += xMove;
			}
			else {
				x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
			}
		}
	}

	/////
	///////////////
	/////////////////////////////////////
	public void moveY() {
		if (yMove < 0) {// Moving up
			int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;

			if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEHEIGHT, ty) && (!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEHEIGHT, ty))) {
				y += yMove;
			}
			else {
				y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
			}
		}
		else if (yMove > 0) {// Moving down

			int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;

			if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEHEIGHT, ty) && (!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEHEIGHT, ty))) {
				y += yMove;
			}
			else {
				y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
			}
		}
	}

	protected boolean collisionWithTile(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}
}
