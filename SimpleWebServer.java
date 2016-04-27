public class SimpleWebServer {
	private final int serverNumber;
	private ServerSocket socket;
	private BufferedReader fromClientStream;

	public Server(int serverPort) {
		this.serverPort = serverPort;
	}

	public void start() {
		socket = new ServerSocket(serverPort);
	}

	public static void main(String[] args) throws IOException {
		portNumber = Integer.parseInt(args[0].substring(7));
	}
}