import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Request { 
	
	private String method;
	private String path; 
	//private ArrayList<ArrayList<String>> lines;


	Request(ArrayList<ArrayList<String>> lines) {
		this.method = lines.get(0).get(0);
		this.path = lines.get(0).get(1);
	}

<<<<<<< Updated upstream

	public String getMethod(){
		return this.method;
	}


	public String getPath(){
=======
	public String getMethod() {
		return this.method;
	}

	public String getPath() {
>>>>>>> Stashed changes
		return this.path; 
	}

}
