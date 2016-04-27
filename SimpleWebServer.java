import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

// Attributions: Several of these methods were taken from instructor solutions to Project0
public class SimpleWebServer {
	private final int serverNumber;
	private ServerSocket socket;
	private BufferedReader fromClientStream;

	public Server(int serverPort) {
		this.serverPort = serverPort;
	}

	public void start() throws IOException {
		socket = new ServerSocket(serverPort);
		System.out.println("Server bound and listening to port " + serverPort);
	}

	public boolean acceptFromClient() throws IOException {
		Socket clientSocket;
		try {
			clientSocket = socket.accept();
		} catch (SecurityException e) {
			System.out.println("Security manager intervened; your config is wrong. " + e);
			return false;
		} catch (IllegalArgumentException e) {
			System.out.println("Probably an invalid port number. " + e);
			return false;
		}
	}

	

	public static void main(String[] args) throws IOException {
		portNumber = Integer.parseInt(args[0].substring(7));
	}
}