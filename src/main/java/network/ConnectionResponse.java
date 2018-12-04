package network;

import model.GameState;

public class ConnectionResponse {
	GameState gameState;
	int playerIndex;
	

	public GameState getGameState() {
		return gameState;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	
}
