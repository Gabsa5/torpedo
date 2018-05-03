package torpedo.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetworkClient extends Network implements SocketObserver{
	
	/**
	 * Class of the socket and the I/O stream objects.
	 */
	private Socket socket = null;
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	
	/**
	 * Constructor of NetworkServer with socketObserver input.
	 * @param socketObserver
	 */
	public NetworkClient(SocketObserver socketObserver) {
		super(socketObserver);
	}
	
	/**
	 * Connect to the given ip adress.
	 */
	@Override
	public void connect (String ip) {
		disconnect();
		try {
			socket = new Socket(ip, 8768);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			out.flush();
			
			Thread rec = new Thread(new ReceiverThread());
			rec.start();
			super.setConnection(true);
		}
		catch(UnknownHostException u) {
			disconnect();
			System.err.println("Client connection error: unknown host");
		}
		catch(IOException e) {
			disconnect();
			System.err.println("Client connection error: error getting I/O for connection");
		}
		
	}
	
	/**
	 * Disconnect the network.
	 */
	@Override
	public void disconnect() {
		try {
			if(out != null)
				out.close();
			if(in != null)
				in.close();
			if(socket != null)
				socket.close();
			super.setConnection(false);
		} catch(IOException e) {
			System.err.println("Error while closing the connection");
		}
	}
	
	/**
	 * Send object.
	 */
	@Override
	public void send(Object o) {
		if (out == null)
			return;
		System.out.println("Sending: " + o + " to server");
		
	}
	
	private class ReceiverThread implements Runnable{
		
		public void run() {
			System.out.println("Waiting for indices...");
			try {
				while (true) {
					onMessage(in.readObject());
				}
				} catch (Exception e) {
					System.out.println(e.getMessage());
					System.err.println("Server disconnected!");
				} finally {
					disconnect();
				}
			}
		}
	}