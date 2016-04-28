import java.util.*;
import java.text.SimpleDateFormat;

public class Response {
	private String protocol = "HTTP/1.1";
	private int error;
	private String contentType;
	private String date;
	private SimpleDateFormat dateFormat;

	public Response(int error, String fileType) {
		this.error = error;
		this.contentType = interpretContentType(fileType);

		// http://stackoverflow.com/questions/7707555/getting-date-in-http-format-in-java
		dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		this.date = dateFormat.format(Calendar.getInstance().getTime());
	}

	public String interpretContentType(String fileType) {
		String contentType = "";

		switch (fileType) {
			case ".txt":
				contentType = "text/plain";
			default:
				contentType = "text/plain";
		}

		return contentType;
	}

	public String toString() {
		String str = "";

		str += protocol + " " + error + " OK\r\n";
		str += "Content-Type: " + contentType + "\r\n";
		str += "Date: " + date;

		return str;
	}
}