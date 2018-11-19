package escape.main.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;

import escape.main.Handler;
import escape.main.states.State;

public class OptionFrame extends JDialog
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Font font = new Font("Script MT Bold", Font.BOLD, 20);
	private String title;

	private JLabel lblYes;
	private JLabel lblNo;
	private JLabel lblCancel;
	private JPanel optionPanel;

	private Handler handler;

	private final int YES_OPTION = 0;
	private final int NO_OPTION = 1;
	private final int CANCEL_OPTION = 2;

	public OptionFrame(Handler handler, String title) {
		super();
		this.handler = handler;
		this.title = title;

		createOptionFrame();
	}

	public void createOptionFrame() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		setUndecorated(true);
		setSize(350, 160);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setAlwaysOnTop(true);
		setFocusable(false);

		optionPanel = new JPanel();
		optionPanel.setLayout(null);
		optionPanel.setBorder(new LineBorder(Color.DARK_GRAY, 4));
		optionPanel.setFocusable(false);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 40, this.getWidth(), 40);
		optionPanel.add(separator);

		JLabel lblTitle = new JLabel(title);
		lblTitle.setFont(font);
		lblTitle.setBounds(optionPanel.getX() / 2 + 100, 10, 250, 30);
		optionPanel.add(lblTitle);

		lblYes = new JLabel("Yes");
		lblYes.addMouseListener(new MouseManager());
		lblYes.addFocusListener(new FocusManager());
		lblYes.addKeyListener(new KeyManager());
		lblYes.setFont(font);
		lblYes.setBounds(100, 60, 50, 30);
		optionPanel.add(lblYes);

		lblNo = new JLabel("No");
		lblNo.addMouseListener(new MouseManager());
		lblNo.addFocusListener(new FocusManager());
		lblNo.addKeyListener(new KeyManager());
		lblNo.setFont(font);
		lblNo.setBounds(200, 60, 50, 30);
		optionPanel.add(lblNo);

		lblCancel = new JLabel("Cancel");
		lblCancel.addMouseListener(new MouseManager());
		lblCancel.addFocusListener(new FocusManager());
		lblCancel.addKeyListener(new KeyManager());
		lblCancel.setFont(font);
		lblCancel.setBounds(140, 100, 80, 30);
		optionPanel.add(lblCancel);

		add(optionPanel);
		setVisible(true);
		handler.getFrame().setEnabled(false);
		lblYes.requestFocus();
	}

	public void YES_OPTION() {
		this.setVisible(false);
		this.dispose();
		State.getCurrentState().YES_NO_CANCEL_OPTION(YES_OPTION);
	}

	public void NO_OPTION() {
		this.setVisible(false);
		this.dispose();
		State.getCurrentState().YES_NO_CANCEL_OPTION(NO_OPTION);
	}

	public void CANCEL_OPTION() {
		this.setVisible(false);
		this.dispose();
		State.getCurrentState().YES_NO_CANCEL_OPTION(CANCEL_OPTION);
	}

	// MOUSELISTENER FOR MENUSTATE
	private class MouseManager extends MouseAdapter
	{

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getComponent() == lblYes) {
				System.out.println("Yes");
				YES_OPTION();
			}
			else if (e.getComponent() == lblNo) {
				System.out.println("No");
				NO_OPTION();
			}
			else if (e.getComponent() == lblCancel) {
				System.out.print("Cancel");
				CANCEL_OPTION();
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			((JComponent) e.getSource()).requestFocus();
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

	private class KeyManager implements KeyListener
	{

		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					lblYes.requestFocus();
					break;
				case KeyEvent.VK_RIGHT:
					lblNo.requestFocus();
					break;
				case KeyEvent.VK_DOWN:
					lblCancel.requestFocus();
					break;
				case KeyEvent.VK_UP:
					lblYes.requestFocus();
					break;
				case KeyEvent.VK_ENTER:
					if (lblYes.hasFocus())
						YES_OPTION();
					else if (lblNo.hasFocus())
						NO_OPTION();
					else if (lblCancel.hasFocus())
						CANCEL_OPTION();
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
}
