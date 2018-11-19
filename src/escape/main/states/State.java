package escape.main.states;

import java.awt.Graphics;

import escape.main.Handler;

public abstract class State
{
	protected Handler handler;

	private static State currentState = null;

	public State(Handler handler) {
		this.handler = handler;
	}

	// METHODS
	public abstract void init();

	public abstract void update();

	public abstract void render(Graphics g);

	public abstract void YES_NO_CANCEL_OPTION(int selection);

	//
	// GETTERS AND SETTERS
	public void setState(State state) {
		currentState = state;
	}

	public State getState() {
		return currentState;
	}

	public static State getCurrentState() {
		return currentState;
	}

	public static void setCurrentState(State currentState) {
		State.currentState = currentState;
	}

}
