import java.util.Calendar;
import java.util.Date;

public class Response {
	private String protocol = "HTTP/1.1";
	private int error;
	private String contentType;
	private Date date;

	public Response(int error, String contentType) {
		this.error = error;
		this.contentType = contentType;
		this.date = Calendar.getInstance().getTime();
	}

	public String toString() {
		String str = "";

		str += protocol + " " + error + " OK\r\n";
		str += "Content-Type: " + contentType + "\r\n";
		str += "Date: " + date;

		return str;
	}
}