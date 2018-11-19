package escape.main.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import escape.main.Handler;

public class SettingState extends State
{
	// SETS THE SIZE DEPENDING ON FULLSCREEN || !FULLSCREEN
	private final Font font = new Font("Script MT Bold", Font.BOLD, 20);
	private Font fontFullscreen = new Font("Script MT Bold", Font.BOLD, 40);

	private final int lblWidth = 100, lblHeight = 24;
	private final int lblWidthFullscreen = 250, lblHeightFullscreen = 50;

	//
	private JLabel lblFullscreenSelect;
	private JLabel lblSoundMusicSelect;
	private JLabel lblSoundEffectSelect;
	private JLabel lblBack;

	private boolean fullscreen = handler.getGameSaver().fullscreen;
	private boolean soundMusic = handler.getGameSaver().soundMusic;
	private boolean soundEffect = handler.getGameSaver().soundEffect;

	// ONLY IF THE USERS DOESNT WANT TO SAVE THE NEW SETTINGS
	private boolean fullscreen_default;
	private boolean soundMusic_default;
	private boolean soundEffect_default;

	private JPanel settingsPanel;

	private BufferedImage imgIsSelected;

	private boolean initialized;

	public SettingState(Handler handler) {
		super(handler);
	}

	@Override
	public void update() {
	}

	@Override
	public void render(Graphics g) {
		// SHOULD ONLY DRAW AFTER EVERYTHING IS initialized
		if (initialized) {
			g = settingsPanel.getGraphics();
			Graphics2D g2d = (Graphics2D) g;

			if (fullscreen) {
				g2d.drawImage(imgIsSelected, lblFullscreenSelect.getX() - 10, lblFullscreenSelect.getY() - 10, 50, 50, null);
			}
			if (soundMusic) {
				g2d.drawImage(imgIsSelected, lblSoundMusicSelect.getX() - 10, lblSoundMusicSelect.getY() - 10, 50, 50, null);
			}
			if (soundEffect) {
				g2d.drawImage(imgIsSelected, lblSoundEffectSelect.getX() - 10, lblSoundEffectSelect.getY() - 10, 50, 50, null);
			}

			g2d.drawLine(settingsPanel.getWidth() - 100, settingsPanel.getHeight(), settingsPanel.getWidth(), settingsPanel.getHeight() - 100);
			g2d.drawLine(0, settingsPanel.getHeight() - 100, 100, settingsPanel.getHeight());

			g2d.drawLine(handler.getFrame().getWidth() - 100, handler.getFrame().getHeight(), handler.getFrame().getWidth(), handler.getFrame().getHeight() - 100);
			g2d.drawLine(0, handler.getFrame().getHeight() - 100, 100, handler.getFrame().getHeight());

		}
	}

	@Override
	public void init() {
		fullscreen_default = fullscreen;
		soundMusic_default = soundMusic;
		soundEffect_default = soundEffect;

		try {
			imgIsSelected = ImageIO.read(SettingState.class.getResource("/Icons/isSelectedIcon.png"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (handler.getCanvas().isVisible())
			handler.getCanvas().setVisible(false);

		settingsPanel = new JPanel(new GridBagLayout());
		settingsPanel.setDoubleBuffered(true);
		settingsPanel.setLayout(null);
		handler.getFrame().add(settingsPanel);

		settingsPanel.paintComponents(settingsPanel.getGraphics());

		JLabel lblSettings = new JLabel("<HTML><U>Settings</U></HTML>");
		lblSettings.setFont(getFont());
		lblSettings.setBounds((handler.getFrame().getWidth() / 2 - getlblWidth()), ((handler.getFrame().getHeight() / 2) - 220), getlblWidth(), getlblHeight());
		settingsPanel.add(lblSettings);

		JLabel lblFullScreen = new JLabel("Fullscreen: ");
		lblFullScreen.setFont(getFont());
		lblFullScreen.setBounds((handler.getFrame().getWidth() / 2 - getlblWidth() - getlblWidth()), ((handler.getFrame().getHeight() / 2) - 150), getlblWidth() + 100, getlblHeight());
		settingsPanel.add(lblFullScreen);

		JLabel lblSoundMusic = new JLabel("Play Sound Music:  ");
		lblSoundMusic.setFont(getFont());
		lblSoundMusic.setBounds((handler.getFrame().getWidth() / 2 - getlblWidth() - getlblWidth()), ((handler.getFrame().getHeight() / 2) - 100), getlblWidth() + 100, getlblHeight());
		settingsPanel.add(lblSoundMusic);

		JLabel lblSoundEffect = new JLabel("Play Sound Effects:  ");
		lblSoundEffect.setFont(getFont());
		lblSoundEffect.setBounds((handler.getFrame().getWidth() / 2 - getlblWidth() - getlblWidth()), ((handler.getFrame().getHeight() / 2) - 50), getlblWidth() + 100, getlblHeight());
		settingsPanel.add(lblSoundEffect);

		//
		// SELECTABLE LABELS
		lblBack = new JLabel("Back");
		lblBack.addMouseListener(new MouseListener());
		lblBack.addFocusListener(new Focus());
		lblBack.addKeyListener(new Keys());
		lblBack.setFont(getFont());
		lblBack.setBounds(((handler.getFrame().getWidth() / 2 - getlblWidth()) - getlblWidth()), handler.getFrame().getHeight() - 300, getlblWidth(), getlblHeight());
		settingsPanel.add(lblBack);

		lblFullscreenSelect = new JLabel();
		lblFullscreenSelect.setBounds(lblFullScreen.getX() + 250, ((handler.getFrame().getHeight() / 2) - 150), 30, 30);
		lblFullscreenSelect.addMouseListener(new MouseListener());
		lblFullscreenSelect.addKeyListener(new Keys());
		lblFullscreenSelect.addFocusListener(new Focus());
		lblFullscreenSelect.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		settingsPanel.add(lblFullscreenSelect);

		lblSoundMusicSelect = new JLabel();
		lblSoundMusicSelect.setBounds(lblSoundMusic.getX() + 250, ((handler.getFrame().getHeight() / 2) - 100), 30, 30);
		lblSoundMusicSelect.addMouseListener(new MouseListener());
		lblSoundMusicSelect.addKeyListener(new Keys());
		lblSoundMusicSelect.addFocusListener(new Focus());
		lblSoundMusicSelect.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		settingsPanel.add(lblSoundMusicSelect);

		lblSoundEffectSelect = new JLabel();
		lblSoundEffectSelect.setBounds(lblSoundEffect.getX() + 250, ((handler.getFrame().getHeight() / 2) - 50), 30, 30);
		lblSoundEffectSelect.addMouseListener(new MouseListener());
		lblSoundEffectSelect.addKeyListener(new Keys());
		lblSoundEffectSelect.addFocusListener(new Focus());
		lblSoundEffectSelect.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		settingsPanel.add(lblSoundEffectSelect);

		handler.getFrame().validate();
		settingsPanel.setVisible(true);
		settingsPanel.repaint();

		lblBack.requestFocus();

		initialized = true;
	}

	private void backClick() {
		if (fullscreen_default != fullscreen || soundMusic_default != soundMusic || soundEffect_default != soundEffect)
			new OptionFrame(handler, "Save Settings ?");
		else {

			// IF THE GAME ALREADY STARTED GO BACK TO IT OTHERWIES GO BACK TO THE MENUSTATE
			if (handler.getGame().getGameState().isGameStarted())
				State.setCurrentState(handler.getGame().getGameState());
			else {
				State.setCurrentState(handler.getGame().getMenuState());
			}

			handler.getGameSaver().saveFile();
			initialized = false;
			settingsPanel.removeAll();
			handler.getFrame().remove(settingsPanel);
			handler.getGame().initState();
		}
	}

	@Override
	public void YES_NO_CANCEL_OPTION(int selection) {
		handler.getFrame().setEnabled(true);
		handler.getFrame().requestFocus();

		// DETECT THE SELECTION OF THE OPTIONFRAME
		if (selection == 1) { // NO SELECTED
			fullscreen = fullscreen_default;
			soundMusic = soundMusic_default;
			soundEffect = soundEffect_default;
		}
		else if (selection == 2) { // CANCEL SELECTED
			lblBack.requestFocus();
			return;
		}

		// IF THE GAME ALREADY STARTED GO BACK TO IT OTHERWIES GO BACK TO THE MENUSTATE
		if (handler.getGame().getGameState().isGameStarted())
			State.setCurrentState(handler.getGame().getGameState());
		else {
			State.setCurrentState(handler.getGame().getMenuState());
		}

		handler.getGameSaver().saveFile();

		initialized = false;
		settingsPanel.removeAll();
		handler.getFrame().remove(settingsPanel);

		handler.getGame().initState();
	}

	//
	// MOUSELISTENER FOR MENUSTATE
	private class MouseListener extends MouseAdapter
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

			//
			// SELECTABLE LABELS
			if (e.getComponent() == lblFullscreenSelect) {
				fullscreen = !fullscreen;
				settingsPanel.repaint();
			}

			else if (e.getComponent() == lblSoundMusicSelect) {
				soundMusic = !soundMusic;
				settingsPanel.repaint();
			}

			else if (e.getComponent() == lblSoundEffectSelect) {
				soundEffect = !soundEffect;
				settingsPanel.repaint();
			}

			else if (e.getComponent() == lblBack) {
				backClick();
			}
		}
	}

	//
	// FOCUSLISTENER
	private class Focus implements FocusListener
	{

		@Override
		public void focusGained(FocusEvent e) {
			((JComponent) e.getSource()).setForeground(Color.red);

			if (e.getComponent() == lblFullscreenSelect || e.getComponent() == lblSoundMusicSelect || e.getComponent() == lblSoundEffectSelect) {
				((JComponent) e.getSource()).setBorder(new MatteBorder(1, 1, 1, 1, Color.RED));
				settingsPanel.repaint();
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
			((JComponent) e.getSource()).setForeground(Color.BLACK);

			if (e.getComponent() == lblFullscreenSelect || e.getComponent() == lblSoundMusicSelect || e.getComponent() == lblSoundEffectSelect) {
				((JComponent) e.getSource()).setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
				settingsPanel.repaint();
			}
		}
	}

	//
	// KEYLISTENER
	private class Keys implements KeyListener
	{

		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_DOWN:
					if (lblFullscreenSelect.hasFocus())
						lblSoundMusicSelect.requestFocus();
					else if (lblSoundMusicSelect.hasFocus())
						lblSoundEffectSelect.requestFocus();
					else if (lblSoundEffectSelect.hasFocus())
						lblBack.requestFocus();
					break;

				case KeyEvent.VK_UP:
					if (lblBack.hasFocus())
						lblSoundEffectSelect.requestFocus();
					else if (lblSoundEffectSelect.hasFocus())
						lblSoundMusicSelect.requestFocus();
					else if (lblSoundMusicSelect.hasFocus())
						lblFullscreenSelect.requestFocus();
					break;

				case KeyEvent.VK_ENTER:
					if (lblFullscreenSelect.hasFocus()) {
						fullscreen = !fullscreen;
						settingsPanel.repaint();
					}
					else if (lblSoundMusicSelect.hasFocus()) {
						soundMusic = !soundMusic;
						settingsPanel.repaint();
					}
					else if (lblSoundEffectSelect.hasFocus()) {
						soundEffect = !soundEffect;
						settingsPanel.repaint();
					}
					else if (lblBack.hasFocus())
						backClick();
					break;
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
		}

	}

	//
	// GETTERS AND SETTERS
	public boolean isFullscreen() {
		return fullscreen;
	}

	public boolean isSoundMusic() {
		return soundMusic;
	}

	public boolean isSoundEffect() {
		return soundEffect;
	}

	private int getlblWidth() {
		if (isFullscreen())
			return lblWidthFullscreen;
		return lblWidth;
	}

	private int getlblHeight() {
		if (isFullscreen())
			return lblHeightFullscreen;
		return lblHeight;
	}

	private Font getFont() {
		if (handler.getGame().getSettingState().isFullscreen())
			return fontFullscreen;
		return font;
	}

}
