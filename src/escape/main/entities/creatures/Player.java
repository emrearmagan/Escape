package escape.main.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import escape.main.Handler;
import escape.main.graphics.Animation;
import escape.main.graphics.Assets;

public class Player extends Creature
{
	private Animation animRight, animLeft, animDown, animUp;

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT);

		bounds.x = 25;
		bounds.y = 32;

		bounds.width = 16;
		bounds.height = 32;

		animRight = new Animation(50, Assets.walksRight);
		animLeft = new Animation(50, Assets.walksLeft);
		animDown = new Animation(50, Assets.playerFront);
		animUp = new Animation(50, Assets.playerBack);
	}

	@Override
	public void update() {
		// ANIMATION
		animRight.update();
		animLeft.update();
		animDown.update();
		animUp.update();

		// MOVEMENT
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);

		g.setColor(Color.red);
		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), (int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
	}

	private void getInput() {
		xMove = 0;
		yMove = 0;

		if (handler.getKeyManager().up)
			yMove = -speed;
		if (handler.getKeyManager().down)
			yMove = speed;
		if (handler.getKeyManager().left)
			xMove = -speed;
		if (handler.getKeyManager().right)
			xMove = speed;
		if (handler.getKeyManager().e) {
			System.out.println(handler.getGameCamera().getxOffset());
			System.out.println(handler.getGameCamera().getyOffset());
		}
	}

	private BufferedImage getCurrentAnimationFrame() {
		if (xMove < 0) {
			return animLeft.getCurrentFrames();
		}
		else if (xMove > 0) {
			return animRight.getCurrentFrames();
		}
		else if (yMove < 0) {
			return animUp.getCurrentFrames();
		}
		else {
			return animDown.getCurrentFrames();
		}
	}
}
