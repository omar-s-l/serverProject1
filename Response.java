import java.io.*;
import java.net.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class Response {
	private String protocol = "HTTP/1.1";
	private int error;
	private String contentType;
	private String date;
	private SimpleDateFormat dateFormat;
	private String path;
	private String fileStr;

	public Response(String path) throws IOException {
		this.path = path;
		this.fileStr = getFile();
		this.contentType = interpretContentType();
		
		// Format the date appropriately
		// http://stackoverflow.com/questions/7707555/getting-date-in-http-format-in-java
		dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		this.date = dateFormat.format(Calendar.getInstance().getTime());
	}


	public String interpretContentType() {
		String contentType = "text/plain";

		if (path.endsWith(".html"))
			contentType = "text/html";
		else if (path.endsWith(".txt"))
			contentType = "text/plain";
		else if (path.endsWith(".pdf"))
			contentType = "application/pdf";
		else if (path.endsWith(".png"))
			contentType = "image/png";
		else if (path.endsWith(".jpg") || path.endsWith(".jpeg"))
			contentType = "image/jpeg";

		return contentType;
	}

	public String getFile() throws IOException {
		String fileStr  = "";

		try {
			// http://stackoverflow.com/questions/23003142/java-read-file-and-send-in-the-server
			BufferedReader input =  new BufferedReader(new FileReader(path));
	 		String line = null;
	 		while ((line = input.readLine()) != null) {
	      		fileStr += line + "\r\n";
	  		}
		} catch (FileNotFoundException e) {
			System.out.println("Exception: " + e);
		}
		

		return fileStr;
	}

	public String toString() {
		String str = "";

		str += protocol + " " + error + " OK\r\n";
		str += "Content-Type: " + contentType + "\r\n";
		str += "Date: " + date + "\r\n\r\n";
		str += fileStr;

		return str;
	}
}