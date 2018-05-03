package torpedo.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkServer extends Network implements SocketObserver{
	
	/**
	 * Classes of the server and client socket.
	 */
	private ServerSocket serverSocket = null;
	private Socket clientSocket = null;
	/**
	 * Readable and writeable stream objects.
	 */
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	
	/**
	 * Constructor of NetworkServer with socketObserver input.
	 * @param socketObserver
	 */
	public NetworkServer(SocketObserver socketObserver) {
		super(socketObserver);
	}
	
	/**
	 * Connect to the given ip adress.
	 */
	@Override
	public void connect(String ip) {
		disconnect();
		try {
			serverSocket = new ServerSocket(8768);
			
			Thread rec = new Thread(new ReceiverThread());
			rec.start();
		} catch(IOException e) {
			System.err.println("Port 8768 is not working.");
		}
	}
	
	/**
	 * Send object.
	 */
	@Override
	public void send(Object obj) {
		if(out == null)
		return;
		System.out.println("Sending data to client");
		try {
			out.writeObject(obj);
			out.flush();
		}catch(IOException e) {
			System.err.println("Send error");
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Disconnect the network.
	 */
	@Override
	public void disconnect() {
		try{
			if(out != null)
				out.close();
			if(in != null)
				in.close();
			if(clientSocket != null)
				clientSocket.close();
			if(serverSocket != null)
				serverSocket.close();
			super.setConnection(false);
		}catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * Class to build network connection from server side.
	 */
	private class ReceiverThread implements Runnable{
		
		public void run() {
			try {
				System.out.println("Waiting for Client");
				clientSocket = serverSocket.accept();
				System.out.println("Client connected");
			}catch (IOException e) {
				System.err.println("Accept failed");
				disconnect();
				return;
			}
			
			try {
				out = new ObjectOutputStream(clientSocket.getOutputStream());
				in = new ObjectInputStream(clientSocket.getInputStream());
				out.flush();
			}catch(IOException e) {
				System.err.println("Error while getting streams.");
				disconnect();
				return;
			}
			
			try {
				while(true) {
					onMessage(in.readObject());
				}
			}catch (Exception e) {
				System.err.println("Client disconnected.");
			}finally {
				disconnect();
			}
		}
	}
}