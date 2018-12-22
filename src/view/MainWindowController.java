package view;

import java.io.File;
import java.io.StringWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;  

import java.util.Date;
import java.util.ResourceBundle;

import controller.Communication;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
	
	public void handleServerSolution(String s) throws InterruptedException
	{
		if(s != null)
		{
			if (s.equals("done"))
			{
				System.out.println("WOHOOOO! :) ");
				return;
			}
			System.out.println("solve me!!" + s + "!");
			String[] line = s.split("\n");
			String[] splited;
		
			//for each line, do rotation
			for (String string : line) {
				System.out.println("line:" + string+"!");
				
				if (string.equals("done\n"))
				{
					System.out.println("done!!");
					break;
				}
				splited = string.split(",");
				
				System.out.println("x :" + splited[0] + "!");
				System.out.println("y :" + splited[1] + "!");
				System.out.println("rotationCount :" + splited[2] + "!");
				
				int x = Integer.parseInt(splited[0]);
				int y = Integer.parseInt(splited[1]);
				int rotationCount = Integer.parseInt(splited[2]);
				
				System.out.println("x : " + x);
				System.out.println("y : " + y);
				System.out.println("rotationCount : " + rotationCount);
				
				
				for (int i = 0; i < rotationCount-1; i ++)
				{
					System.out.println("i: " + i );
					pipesRotation(y,x);
					Thread.sleep(10);
				}
		
				
			}
			
			
			
				}
		System.out.println("Server sent null");
	}
	
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
	
	private void setStepsCounter()
	{
		stepsCounter++;
		stepsLabel.setText("Steps: " + stepsCounter);
	}

	public void LoadLevel()
	{
		FileChooser fc = new FileChooser();
		
		fc.setTitle("choose file");
		fc.setInitialDirectory(new File("./reasources"));

		File chosen = fc.showOpenDialog(null);
		if (chosen != null)
		{
			System.out.println(chosen.getName());
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
		
