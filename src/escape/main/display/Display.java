package escape.main.display;

import java.awt.Canvas;

import javax.swing.JFrame;

import escape.main.Game;

public class Display extends Canvas
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int width, height;

	// OBJECTS
	private JFrame frame;
	//

	public Display(Game game, int width, int height) {
		super();
		this.width = width;
		this.height = height;

		createDisplay();
	}

	private void createDisplay() {
		frame = new JFrame();
		frame.setSize(width, height);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		// frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setFocusable(false);
		setVisible(false);
		frame.add(this);

		// ERST WIEDER FREIGEBEN WENN ERSTES STATE NICHT MEHR GAMESTATE SONDERN MENUStATE IST
		// frame.pack();
		frame.validate();
		frame.setVisible(true);

	}

	// GETTERS AND SETTERS
	public JFrame getFrame() {
		return frame;
	}

	public Canvas getCanvas() {
		return this;
	}

}
