package escape.main.graphics;

import java.awt.image.BufferedImage;

public class Animation
{

	private int speed, index;
	private long lastTime, timer;
	private BufferedImage[] frames;

	public Animation(int speed, BufferedImage[] frames) {
		this.speed = speed;
		this.frames = frames;
		lastTime = System.currentTimeMillis();
		timer = 0;
		index = 0;
	}

	public void update() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();

		if (timer > speed) {
			index++;
			timer = 0;
			if (index >= frames.length)
				index = 0;
		}
	}

	public BufferedImage getCurrentFrames() {
		return frames[index];
	}

}
