package escape.main.graphics;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class ImageLoader
{

	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(0);
		}
		return null;
	}

}
