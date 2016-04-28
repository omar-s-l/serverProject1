import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Attributions: Several of these methods were taken from instructor solutions to Project0
public class SimpleWebServer {
	private final int serverPort;
	private ServerSocket socket;
	private DataOutputStream toClientStream;
	private BufferedReader fromClientStream;

	public SimpleWebServer(int serverPort) {
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

		toClientStream = new DataOutputStream(clientSocket.getOutputStream());
		fromClientStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		System.out.print("Input and output streams created for client.\n");
		return true;
	}

	public void processGetRequest() throws IOException {
		ArrayList<ArrayList<String>> lines = new ArrayList<ArrayList<String>>();
		String str = ".";
		while (!str.equals("")) {
			str = fromClientStream.readLine();

			// Separate parts of the response by white space
			String[] split = str.split("\\s+");
			ArrayList<String> wordsInLine = new ArrayList<String>();
			for (String s : split) {
				wordsInLine.add(s);
			}

			lines.add(wordsInLine);
		}

		// Print out all of the lines (for testing)
		for (ArrayList<String> s : lines) {
			System.out.println(s);
		}
	}

	public static void main(String[] args) throws IOException {
		Map<String, String> flags = Utils.parseCmdlineFlags(args);
		if (!flags.containsKey("--serverPort")) {
			System.out.println("usage: Server --serverPort=12345");
			System.exit(-1);
		}

		int serverPort = -1;
		try {
			serverPort = Integer.parseInt(flags.get("--serverPort"));
		} catch (NumberFormatException e) {
			System.out.println("Invalid port number! Must be an integer.");
			System.exit(-1);
		}

		SimpleWebServer webServer = new SimpleWebServer(serverPort);
		try {
			webServer.start();
			if (webServer.acceptFromClient()) {

				// This is where most of the work happens
				webServer.processGetRequest();
			} else {
				System.out.println("Error accepting client communication.");
			}
		} catch (IOException e) {
			System.out.println("Error communicating with client. Aborting. Details: " + e);
		}
	}
}