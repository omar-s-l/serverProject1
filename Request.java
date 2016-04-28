import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Request { 
	
	private String method;
	private String path; 
	//private ArrayList<ArrayList<String>> lines;

	Request(ArrayList<ArrayList<String>> lines) {
		this.method = lines.get(0).get(0);
		this.path = "www" + lines.get(0).get(1);
	}

	public String getMethod(){
		return this.method;
	}


	public String getPath(){
		return this.path; 
	}
}
