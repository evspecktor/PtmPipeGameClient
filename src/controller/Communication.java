package controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class Communication {
	
	private static void readInputsAndSendTo(BufferedReader in, PrintWriter out,String exitStr){ 
		try { 
			String line; 
			while(!(line=in.readLine()).equals(exitStr)){ 
				out.println(line);
				System.out.println("going to print line from input");
				System.out.println(line);
//				out.flush(); 
				}
			out.println(exitStr);
			System.out.println("sending to output");
			out.flush();
			} 
		catch (IOException e) { e.printStackTrace();} 
		} 
	
	public static void start(String inputData,StringWriter outPutData,String ip, int port){ 
		try { 
			Socket theServer=new Socket(ip, port); 
			System.out.println("connected to server");
			InputStream in = new ByteArrayInputStream(inputData.getBytes(StandardCharsets.UTF_8));
			InputStreamReader isr = new InputStreamReader(in);
//			BufferedReader userInput=new BufferedReader(new InputStreamReader(System.in));
			BufferedReader userInput=new BufferedReader(isr);
			BufferedReader serverInput=new BufferedReader(new InputStreamReader(theServer.getInputStream()));
			PrintWriter outToServer=new PrintWriter(theServer.getOutputStream());
//			PrintWriter outToScreen=new PrintWriter(System.out);
			PrintWriter outToScreen=new PrintWriter(outPutData);
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
	
	public static void main(String args[]) throws UnknownHostException{
		StringWriter output = new StringWriter();
		Communication.start("s|L\n  g\ndone",output,"127.0.0.1",6100);
		System.out.println("output: " + output +"!");
	//	String st = "p,h,l\np,h,q\n"
	}
	
	
}
