package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import controller.Communication;
import controller.ConfigParser;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import model.clientGameModel;


public class MainWindowController implements Initializable{
	
	private char[][] defaultPipeData= {
			{'s','-','-','7'},
			{' ',' ',' ','|'},
			{' ','F','-','J'},
			{' ','L','-','g'}
	};
	
	clientGameModel gameModel = new clientGameModel(defaultPipeData);
	
	@FXML
	PipeGameDisplayer pipeDisplayer;
	@FXML
	private Label stepsLabel;
	@FXML
	private Label timerLabel;
	
	private StringWriter inputFromServer = new StringWriter();
	
	
	ConfigParser CP = null;
	
	private Date startTime, submitTime;
	private Integer stepsCounter = 0;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		startTime = new Date();
		timerLabel.setText("Timer: ");
		stepsLabel.setText("Steps: " + stepsCounter);
		
		pipeDisplayer.setPipeBoard(gameModel.getPipeBoard());
		
		pipeDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->pipeDisplayer.requestFocus());
		
		pipeDisplayer.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				int y = (int)event.getX();
				int x = (int)event.getY();
				
				y = y / (int)pipeDisplayer.getw(); 
				x = x / (int)pipeDisplayer.geth();
				
				pipesRotation(x, y);
				}
	});
}
	
	private void pipesRotation(int x, int y)
	{
		System.out.println("--inside pipesRotation--");
		char PipeType = pipeDisplayer.getPipe(x, y);
		
		switch (PipeType) {
		case 's':
			pipeDisplayer.pipeBoard[x][y] = 's';
			break;
		case 'L':
			pipeDisplayer.pipeBoard[x][y] = 'F';
			pipeDisplayer.redraw();
			setStepsCounter();
			break;
		case 'F':
			pipeDisplayer.pipeBoard[x][y] = '7';
			pipeDisplayer.redraw();
			setStepsCounter();
			break;
		case '7':
			pipeDisplayer.pipeBoard[x][y] = 'J';
			pipeDisplayer.redraw();
			setStepsCounter();
			break;
		case 'J':
			pipeDisplayer.pipeBoard[x][y] = 'L';
			pipeDisplayer.redraw();
			setStepsCounter();
			break;
		case 'g':
			pipeDisplayer.pipeBoard[x][y] = 'g';
			break;
		case '-':
			pipeDisplayer.pipeBoard[x][y] = '|';
			pipeDisplayer.redraw();
			setStepsCounter();
			break;	
		case '|':
			pipeDisplayer.pipeBoard[x][y] = '-';
			pipeDisplayer.redraw();
			setStepsCounter();
			break;		
		default:
			break;
	}


	}
	
	private void handleServerSolution(String s) throws InterruptedException
	{
		if(s != null)
		{
			if (s.equals("done"))
			{
				System.out.println("WOHOOOO! :) ");
				return;
			}
			
			BufferedReader bufReader = new BufferedReader(new StringReader(s));
			String line;
			try {
				while( !(line=bufReader.readLine()).equals("done"))
				{	
					int x = Integer.parseInt(line.split(",")[0]);
					int y = Integer.parseInt(line.split(",")[1]);
					int rotationCount = Integer.parseInt(line.split(",")[2]);
										
					for (int i = 0; i < rotationCount-1; i ++)
					{
						System.out.println("i: " + i );
						pipesRotation(y,x);
						Thread.sleep(10);
					}
			
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}

	private void setStepsCounter(int x)
	{
		stepsCounter = x;
		stepsLabel.setText("Steps: " + stepsCounter);
	}
	
	private void setStepsCounter()
	{
		stepsCounter++;
		stepsLabel.setText("Steps: " + stepsCounter);
	}

	public void LoadLevel() throws IOException
	{
		FileChooser fc = new FileChooser();
		
		fc.setTitle("choose file");
		fc.setInitialDirectory(new File("./resources"));

		File chosen = fc.showOpenDialog(null);
		if (chosen != null)
		{
			InputStream is = new FileInputStream(chosen); 
			BufferedReader buf = new BufferedReader(new InputStreamReader(is)); 

			gameModel = new clientGameModel(setPipesIntoArray(buf));
			
			pipeDisplayer.setPipeBoard(gameModel.getPipeBoard());
		}
	}
	
	private char[][] setPipesIntoArray(BufferedReader reader) {
		System.out.println("---set Pipes Into Array---");
		ArrayList<String> listOfString = new ArrayList<String>();
		int numOfLines = 0;			
		
		try {
			String line = null;
			while (( line = reader.readLine()) != null)
			{
					
				numOfLines++;
				System.out.println("Filling the list of string");
				listOfString.add(line);
			}	 
		} catch (IOException e) {
			System.out.println("exception?");
			e.printStackTrace();
			
		}
		System.out.println("!");
		char[][] pipes = null;
		int lengthOfLine = 0;
		if (listOfString != null)
		{
			if (listOfString.isEmpty())
			{
				System.out.println("client sent empty game");
			}
			else
			{
				lengthOfLine = listOfString.get(0).length();
				System.out.println("New array size is: x is :" + lengthOfLine + ", y is: " + (numOfLines)); //numOfLines-1 since done is also a line
				if (lengthOfLine > 0 && numOfLines > 0)
				{
					pipes = new char[lengthOfLine][numOfLines];
					//listOfString.remove(listOfString.size()-1);
					int lineNumber = 0;
					for (String line:listOfString) {
						for ( int i = 0; i < line.length(); i++)
						{
							int x = i; 
							int y = lineNumber;
							char p = line.charAt(i);
							//Pipe pipe = new Pipe(p, x,y);
							System.out.println("X : " + x + ", Y : " + y + ", Pipe type: " + p);
							pipes[x][y] = p;
						}
						lineNumber++;
					}
				
				}
					System.out.println("---set Pipes Into Array Done---");
			
			}
		}
		return pipes;
	}

	public void SaveLevel()
	{
		
	}
	
	public void LoadServerConfiguration() throws ParserConfigurationException, SAXException, IOException
	{
		FileChooser fc = new FileChooser();
		
		fc.setTitle("choose file");
		fc.setInitialDirectory(new File("./resources"));

		File chosen = fc.showOpenDialog(null);
		if (chosen != null)
		{
			CP = new ConfigParser(chosen);
		}
		
	}
	
	public void ChooseTheme()
	{
		
	}
	
	private void connectToServer() throws ParserConfigurationException, SAXException, IOException
	{
		if (CP == null)
		{
			CP = new ConfigParser();
		}
		Communication.start(pipeDisplayer.covertGameToString(),inputFromServer,CP.getServerIp(), CP.getPort());
	}
	
	public void Submit() throws InterruptedException, ParserConfigurationException, SAXException, IOException
	{
		submitTime = new Date();
		long difference = submitTime.getTime() - startTime.getTime();
		timerLabel.setText("Timer: " + difference/1000 + " seconds");
		
		connectToServer();
		System.out.println("input from server: " + inputFromServer + "!");
		if (inputFromServer.toString().equals("done\n"))
		{
			System.out.println("WOHOOOO! :) ");
			return;
		}
		else
		{
			System.out.println("Try again ");
		}
	}
	
	public void Solve() throws InterruptedException, ParserConfigurationException, SAXException, IOException
	{
		connectToServer();
		System.out.println("evg shaming: "+ inputFromServer.toString());
		handleServerSolution(inputFromServer.toString());
	}
	
}
		
