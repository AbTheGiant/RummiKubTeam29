package model;

public class GameStateOriginator {
	GameState state;
	
	public void set(GameState state) {
        this.state = state;
        System.out.println("Originator: Setting state to " + state);
    }
 
    public Memento saveToMemento() {
        System.out.println("Originator: Saving to Memento.");
        return new Memento(this.state);
    }
 
    public void restoreFromMemento(Memento memento) {
        this.state = memento.getSavedState();
        System.out.println("Originator: State after restoring from Memento: " + state);
    }
    
    public GameState getState() {
    	return state;
    }

    public static class Memento {
        private final GameState state;

        public Memento(GameState stateToSave) {
            state = stateToSave.copy();
        }
 
        // accessible by outer class only
        private GameState getSavedState() {
            return state;
        }
    }
}