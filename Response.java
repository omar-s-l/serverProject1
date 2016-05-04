import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.*;
import java.text.SimpleDateFormat;

public class Response {
	private String path;
	private String method;
	private String protocol = "HTTP/1.1";
	private int error;
	private String contentType;
	private String date;
	private SimpleDateFormat dateFormat;
	private byte[] file;

	//OMAR's TODO TRY/CATCH ERRORSTATUS METHOD SIGNATURE 

	// Constructor
	public Response(String path, String method) throws IOException {
		this.path = path;
		this.method = method;
		contentType = "text/html";

		try {
			// Read the file into a byte stream
			// http://stackoverflow.com/questions/858980/file-to-byte-in-java
			Path nioPath = Paths.get(path);
			System.out.println(1);
			this.file = Files.readAllBytes(nioPath);
			System.out.println(2);
			this.contentType = interpretContentType();
			System.out.println(3);
			this.error = 200;

		} catch (Exception e) {
			System.out.println("The requested file was not found: " + e);
			error = 404;
		}
		
		// Format the date appropriately
		// http://stackoverflow.com/questions/7707555/getting-date-in-http-format-in-java
		dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		this.date = dateFormat.format(Calendar.getInstance().getTime());
	}
	
	// Methods
	public String getMethod() {
		return method;
	}

	public byte[] getFile() {
		return file;
	}

	public String getContentType() {
		return contentType;
	}

	public int getError() {
		return error;
	}

	public String interpretContentType() {
		if (path.endsWith(".html"))
			this.contentType = "text/html";
		else if (path.endsWith(".txt"))
			this.contentType = "text/plain";
		else if (path.endsWith(".pdf"))
			this.contentType = "application/pdf";
		else if (path.endsWith(".png"))
			this.contentType = "image/png";
		else if (path.endsWith(".jpg") || path.endsWith(".jpeg"))
			this.contentType = "image/jpeg";

		return contentType;
	}

	public String toString() {
		String str = "";

		str += protocol + " " + error + " OK\r\n";
		str += "Content-Type: " + contentType + "\r\n";
		str += "Date: " + date + "\r\n\r\n";
		
		return str;
	}
}