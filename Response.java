import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
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
	private byte[] file;

	//OMAR's TODO TRY/CATCH ERRORSTATUS METHOD SIGNATURE 


	public Response(String path) throws FileNotFoundException {
		this.path = path;
		// this.fileStr = getFileStr();
		try {

		// http://stackoverflow.com/questions/858980/file-to-byte-in-java
		Path nioPath = Paths.get(path);
		this.file = Files.readAllBytes(nioPath);
		this.contentType = interpretContentType();

		} catch FileNotFoundException e {
			System.out.println("The requested file was not found" + e);
			error = 404;
		}

		
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

	public String getFileStr() throws IOException {
		String fileStr  = "";

		try {
			// http://stackoverflow.com/questions/23003142/java-read-file-and-send-in-the-server
			BufferedReader input =  new BufferedReader(new FileReader(path));
	 		String line = null;
	 		while ((line = input.readLine()) != null) {
	      		fileStr += line + "\r\n";
	      	error = 200;
	  		}
		} catch (FileNotFoundException e) {
			System.out.println("Exception: " + e);
		}
		
		return fileStr;
	}

	public byte[] getFile() {
		return file;
	}

	public String toString() {
		String str = "";

		str += protocol + " " + error + " OK\r\n";
		str += "Content-Type: " + contentType + "\r\n";
		str += "Date: " + date + "\r\n\r\n";
		//str += fileStr;

		return str;
	}
}