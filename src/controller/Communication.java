package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Communication {
	
	private void readInputsAndSendTo(BufferedReader in, PrintWriter out,String exitStr){ 
		try { 
			String line; 
			while(!(line=in.readLine()).equals(exitStr)){ 
				out.println(line); 
//				out.flush(); 
				}
			out.println(exitStr);
			out.flush();
			} 
		catch (IOException e) { e.printStackTrace();} 
		} 
	
	public void start(String ip, int port){ 
		try { 
			Socket theServer=new Socket(ip, port); 
			System.out.println("connected to server");
			BufferedReader userInput=new BufferedReader(new InputStreamReader(System.in)); 
			BufferedReader serverInput=new BufferedReader(new InputStreamReader(theServer.getInputStream()));
			PrintWriter outToServer=new PrintWriter(theServer.getOutputStream()); 
			PrintWriter outToScreen=new PrintWriter(System.out);
			// correspond according to a well-defined protocol 
			readInputsAndSendTo(userInput,outToServer,"done"); 
			readInputsAndSendTo(serverInput,outToScreen,"done");
			userInput.close(); 
			serverInput.close(); 
			outToServer.close(); 
			outToScreen.close(); 
			theServer.close();
	} catch (UnknownHostException e) {e.printStackTrace();} catch (IOException e) {e.printStackTrace();} 
	}
}
