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
import java.util.Date;
import java.util.ResourceBundle;

import controller.Communication;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;


public class MainWindowController implements Initializable{
	
	char[][] pipeData= {
			{'s','-','-','7'},
			{' ',' ',' ','|'},
			{' ','F','-','J'},
			{' ','L','-','g'}
	};
	
	
	@FXML
	PipeGameDisplayer pipeDisplayer;
	@FXML
	private Label stepsLabel;
	@FXML
	private Label timerLabel;
	
	private StringWriter inputFromServer = new StringWriter();
	
	private int port = 6100;
	//private String ip = "127.0.0.1";
	private String ip = "10.0.0.3";
	
	private Date startTime, submitTime;
	private Integer stepsCounter = 0;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		startTime = new Date();
		timerLabel.setText("Timer: ");
		stepsLabel.setText("Steps: " + stepsCounter);
		
		pipeDisplayer.setPipeBoard(pipeData);
		
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
			String line = buf.readLine(); 
			int i =0;
			while(line != null)
			{ 
				if ( line.startsWith("Steps"))
				{
					int counter = Integer.parseInt(line.substring(7));
					setStepsCounter(counter);
				}
				
				pipeData[i] = line.toCharArray(); 
				line = buf.readLine();
				i++;
			} 
			
			//need to set pipes into array (there is a code like that in the server) OR create model, serealize to XML all the data and load it
			//char[][] pipeDataLoaded = char[][]
			

			pipeDisplayer.setPipeBoard(pipeData);
		}
	}
	
	
	public void SaveLevel()
	{
		
	}
	
	public void LoadServerConfiguration()
	{
		
	}
	
	public void ChooseTheme()
	{
		
	}
	
	public void Submit() throws InterruptedException
	{
		submitTime = new Date();
		long difference = submitTime.getTime() - startTime.getTime();
		timerLabel.setText("Timer: " + difference/1000 + " seconds");
		
		Communication.start(pipeDisplayer.covertGameToString(),inputFromServer,ip, port);
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
	
	public void Solve() throws InterruptedException
	{
		Communication.start(pipeDisplayer.covertGameToString(),inputFromServer,ip, port);
		System.out.println("evg shaming: "+ inputFromServer.toString());
		handleServerSolution(inputFromServer.toString());
	}
	
}
		
