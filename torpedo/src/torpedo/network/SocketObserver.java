package torpedo.network;

/**
 * SocketObserver interface for sending message and getting the connection status
 *
 */
public interface SocketObserver {
	
	/**
	 * Message data which is going on the socket.
	 * @param data
	 */
	void onMessage(Object data);
	
	/**
	 * Getter of the status of disconnection.
	 * @param isClosed
	 */
    void onDisconnectionStatus(boolean isClosed);

}
