package escape.main.states;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import escape.main.Handler;

public class MenuState extends State
{
	private final int lblWidth = 100, lblHeight = 24;
	private final int lblWidthFullscreen = 250, lblHeightFullscreen = 50;

	private Font font = new Font("Script MT Bold", Font.BOLD, 20);
	private Font titleFont = new Font("DejaVu Serif", Font.BOLD, 90);

	// FONT FULLSCREEN
	private Font fontFullscreen = new Font("Script MT Bold", Font.BOLD, 40);
	private Font titleFontFullscreen = new Font("DejaVu Serif", Font.BOLD, 120);
	private JPanel menuPanel;

	private JLabel lblStart;
	private JLabel lblExit;
	private JLabel lblSettings;

	private boolean initialized;

	public MenuState(Handler handler) {
		super(handler);
	}

	@Override
	public void init() {
		if (handler.getCanvas().isVisible())
			handler.getCanvas().setVisible(false);

		// CHECK IF FULLSCREEN IS SELECTED AND SET DEPENDING ON THAT THE NEW SIZE OF THE FRAME
		if (handler.getGameSaver().fullscreen) {
			handler.getFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);
		}
		else {
			handler.getFrame().setSize(handler.getGame().getWidth(), handler.getGame().getHeight());
			handler.getFrame().setLocationRelativeTo(null);
		}
		//
		// INITIALIZE LABELS

		menuPanel = new JPanel();
		menuPanel.setDoubleBuffered(true);
		menuPanel.setLayout(null);
		menuPanel.setSize(handler.getFrame().getSize());
		handler.getFrame().add(menuPanel);

		menuPanel.paint(menuPanel.getGraphics());

		lblStart = new JLabel("Start Game");
		lblStart.addMouseListener(new MouseManager());
		lblStart.addFocusListener(new FocusManager());
		lblStart.addKeyListener(new KeysManager());
		lblStart.setBounds(menuPanel.getWidth() / 2 - lblWidth, menuPanel.getHeight() / 2 - 50, getlblWidth(), getlblHeight());
		lblStart.setFont(getFont());
		menuPanel.add(lblStart);

		lblExit = new JLabel("Exit Game");
		lblExit.addMouseListener(new MouseManager());
		lblExit.addFocusListener(new FocusManager());
		lblExit.addKeyListener(new KeysManager());
		lblExit.setBounds(menuPanel.getWidth() / 2 - lblWidth, menuPanel.getHeight() / 2 + 50, getlblWidth(), getlblHeight());
		lblExit.setFont(getFont());
		menuPanel.add(lblExit);

		lblSettings = new JLabel("Settings");
		lblSettings.addMouseListener(new MouseManager());
		lblSettings.addFocusListener(new FocusManager());
		lblSettings.addKeyListener(new KeysManager());
		lblSettings.setBounds(menuPanel.getWidth() / 2 - lblWidth, menuPanel.getHeight() / 2 + 150, getlblWidth(), getlblHeight());
		lblSettings.setFont(getFont());
		menuPanel.add(lblSettings);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 100, handler.getFrame().getWidth(), 100);
		menuPanel.add(separator);

		handler.getFrame().validate();
		menuPanel.setVisible(true);
		menuPanel.repaint();

		lblStart.requestFocus();

		initialized = true;
	}

	@Override
	public void update() {

	}

	@Override
	public void render(Graphics g) {
		// SHOULD ONLY DRAW AFTER EVERYTHING IS initialized
		if (initialized) {

			g = menuPanel.getGraphics();
			// menuPanel.paintComponents(g);
			Graphics2D g2d = (Graphics2D) g;

			g2d.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 10.0f));
			g2d.setFont(getTitleFont());
			g2d.drawString("E S C A P E", menuPanel.getWidth() / 2 - 300, 100);

			g2d.drawLine(menuPanel.getWidth() - 100, menuPanel.getHeight(), menuPanel.getWidth(), menuPanel.getHeight() - 100);
			g2d.drawLine(0, menuPanel.getHeight() - 100, 100, menuPanel.getHeight());

			g2d.drawLine(handler.getFrame().getWidth() - 100, handler.getFrame().getHeight(), handler.getFrame().getWidth(), handler.getFrame().getHeight() - 100);
			g2d.drawLine(0, handler.getFrame().getHeight() - 100, 100, handler.getFrame().getHeight());

		}
	}

	private void startGame() {
		initialized = false;
		menuPanel.removeAll();
		handler.getFrame().remove(menuPanel);

		State.setCurrentState(handler.getGame().getGameState());
		handler.getGame().initState();
	}

	private void Settings() {
		initialized = false;
		menuPanel.removeAll();
		handler.getFrame().remove(menuPanel);

		State.setCurrentState(handler.getGame().getSettingState());
		handler.getGame().initState();
	}

	//
	// MOUSELISTENER FOR MENUSTATE
	private class MouseManager extends MouseAdapter
	{
		@Override
		public void mouseEntered(MouseEvent e) {
			((JComponent) e.getSource()).requestFocus();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			((JComponent) e.getSource()).requestFocus();
		}

		@Override
		public void mouseClicked(MouseEvent e) {

			JLabel l = (JLabel) e.getSource();

			switch (l.getText()) {
				case "Start Game":
					startGame();
					break;
				case "Exit Game":
					handler.getGameSaver().saveFile();
					System.exit(0);
					break;
				case "Settings":
					Settings();
					break;
			}
		}
	}

	//
	// FOCUSLISTENER
	private class FocusManager implements FocusListener
	{

		@Override
		public void focusGained(FocusEvent e) {
			((JComponent) e.getSource()).setForeground(Color.red);
		}

		@Override
		public void focusLost(FocusEvent e) {
			((JComponent) e.getSource()).setForeground(Color.BLACK);
		}
	}

	////
	// KEYLISTENER
	private class KeysManager implements KeyListener
	{

		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_DOWN:
					if (lblStart.hasFocus())
						lblExit.requestFocus();
					else
						lblSettings.requestFocus();
					break;
				case KeyEvent.VK_UP:
					if (lblSettings.hasFocus())
						lblExit.requestFocus();
					else
						lblStart.requestFocus();
					break;
				case KeyEvent.VK_ENTER:
					if (lblStart.hasFocus())
						startGame();
					else if (lblExit.hasFocus()) {
						handler.getGameSaver().saveFile();
						System.exit(0);
					}
					else
						Settings();
					break;

			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}

	}

	// GETTERS FOR FONT

	private Font getFont() {
		if (handler.getGame().getSettingState().isFullscreen())
			return fontFullscreen;
		return font;
	}

	private Font getTitleFont() {
		if (handler.getGame().getSettingState().isFullscreen())
			return titleFontFullscreen;
		return titleFont;
	}

	private int getlblWidth() {
		if (handler.getGame().getSettingState().isFullscreen())
			return lblWidthFullscreen;
		return lblWidth;
	}

	private int getlblHeight() {
		if (handler.getGame().getSettingState().isFullscreen())
			return lblHeightFullscreen;
		return lblHeight;
	}

	@Override
	public void YES_NO_CANCEL_OPTION(int selection) {
	}

}
