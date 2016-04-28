import java.util.*;
import java.text.SimpleDateFormat;

public class Response {
	private String protocol = "HTTP/1.1";
	private int error;
	private String contentType;
	private String date;
	private SimpleDateFormat dateFormat;
	private String path;

	public Response(String path) {
		this.path = path;
		this.contentType = interpretContentType(); 
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

	public String toString() {
		String str = "";

		str += protocol + " " + error + " OK\r\n";
		str += "Content-Type: " + contentType + "\r\n";
		str += "Date: " + date + "\r\n\r\n";

		return str;
	}
}