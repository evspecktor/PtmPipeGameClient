package controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import model.clientGameModel;

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
			System.out.println("connection close");
	} catch (UnknownHostException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	public static void main(String args[]) throws UnknownHostException{
		long timePassed = 10;
		int steps = 15;
		char[][] pipeBoard = {
				{'s','-','-','-'},
				{' ',' ',' ','-'},
				{' ','F','-','-'},
				{' ','L','-','g'}
		};
		
		clientGameModel cgm = new clientGameModel(timePassed, steps, pipeBoard);
		try {
	         FileOutputStream fileOut = new FileOutputStream("./resources/cgm.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(cgm);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in ./resources/cgm.ser");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
		
		cgm.setTime(15);
		System.out.println("time was changed to: " + cgm.getTime());
		System.out.println("uploading from serialize");
		
		clientGameModel cgmAfter = null;
		try {
			FileInputStream fileIn = new FileInputStream("./resources/cgm.ser");
		    ObjectInputStream in = new ObjectInputStream(fileIn);
		    cgmAfter = (clientGameModel) in.readObject();
		    in.close();
		    fileIn.close();
		  } catch (IOException i) {
		    i.printStackTrace();
		    return;
		  } catch (ClassNotFoundException c) {
		    System.out.println("cgm class not found");
		    c.printStackTrace();
		    return;
		  }
		System.out.println("time after deserialized: " + cgmAfter.getTime()); 
		System.out.println("x after deserialized: " + cgmAfter.getX()); 
		System.out.println("y after deserialized: " + cgmAfter.getY()); 
		System.out.println("steps after deserialized: " + cgmAfter.getStepsCounter()); 
		System.out.println("board after deserialized: " + cgmAfter.getPipeBoard().toString()); 
//		StringWriter output = new StringWriter();
//		Communication.start("s|L\n  g\ndone",output,"127.0.0.1",6100);
//		System.out.println("output: " + output +"!");
	//	String st = "p,h,l\np,h,q\n"
	}
	
	
}
