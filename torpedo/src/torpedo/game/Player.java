package torpedo.game;

import java.util.ArrayList;

public class Player{
	
	/**
	 * Name of the player.
	 */
	private String name;
	
	/**
	 * Flag to know whose turn in multiplayer mode.
	 */
	private boolean turnFlag;
	
	/**
	 * Flag to know if the player is the server.
	 */
	private boolean serverFlag;
	
	/**
	 * Array of the the player's ships.
	 */
	private ArrayList<Ship> ships;
	
	/**
	 * Create a player.
	 * @param name
	 * @param serverFlag
	 */
	public Player(String name, boolean serverFlag) {
		
		this.name = name;
		this.serverFlag = serverFlag;
		
	}
	
	/**
	 * Getter of the name.
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Check if the player is on turn.
	 * @return flag of the player's turn
	 */
	public boolean playerIsActive() {
		return turnFlag;
	}
	
	/**
	 * Set the players turn.
	 * @param set turnFlag true if the player is on turn
	 */
	public void setPlayerActive(boolean turn) {
		this.turnFlag = turn;
	}
	
	/**
	 * Check if the player is server.
	 * @return true if the player is server
	 */
	public boolean isServer() {
		return serverFlag;
	}
}