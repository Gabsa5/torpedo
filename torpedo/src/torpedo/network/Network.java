package torpedo.network;


public abstract class Network implements SocketObserver{
	/**
	 * Status flag of the connection.
	 */
	private boolean connected;
	
	/**
     * A SocketObserver interface to notice the controller.
     */
    private SocketObserver socketobserver;
	
	
	/**
	 * Create new Network class.
	 * @param socketobserver
	 */
	public Network(SocketObserver socketobserver){
		this.socketobserver = socketobserver;
		connected = false;
	}
	
	/**
	 * Connect to the given IP.
	 * @param ip
	 */
	public abstract void connect(String ip);
	
	/**
	 * Disconnect the network.
	 */
	public abstract void disconnect();
	
	/**
	 * Send object.
	 * @param obj
	 */
	public abstract void send(Object obj);
	
	/**
	 * Check if the connection is working.
	 * @return true if the connection is working.
	 */
	public boolean isConnected() {
		return connected;
	}
	
	/**
	 * Set the status of the connection.
	 * @param connected true if it is working.
	 */
	public void setConnection(boolean connected) {
		this.connected = connected;
	}
	
}