package escape.main.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener
{
	private boolean[] keys, justPressed, cantPress;

	public boolean up, down, left, right, alt, f4, ESC, enter, e;

	public KeyManager() {
		keys = new boolean[256];
		justPressed = new boolean[keys.length];
		cantPress = new boolean[keys.length];
	}

	public void tick() {
		for (int i = 0; i < keys.length; i++) {
			if (cantPress[i] && !keys[i]) {
				cantPress[i] = false;
			}
			else if (justPressed[i]) {
				cantPress[i] = true;
				justPressed[i] = false;
			}
			if (!cantPress[i] && keys[i]) {
				justPressed[i] = true;
			}
		}

		if (justPressed[KeyEvent.VK_E])
			System.out.println("E just pressed");

		e = keys[KeyEvent.VK_E];
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];

		alt = keys[KeyEvent.VK_ALT];
		f4 = keys[KeyEvent.VK_F4];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() < 0 || e.getKeyCode() > keys.length)
			return;
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() < 0 || e.getKeyCode() > keys.length)
			return;
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public boolean getJustPressed(int x) {
		return justPressed[x];
	}

}
