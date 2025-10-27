package org.toast.states;

public class GameStateManager {
	
	private static State currentState = null;
	private static State lastState = null;
	
	public static void setState(State state) {
		if(currentState != null)
			lastState = currentState;
		currentState = state;
	}
	
	public static State getState() {
		return currentState;
	}
	
	public static State getLastState() {
		return lastState;
	}
	
	
	
	
}
