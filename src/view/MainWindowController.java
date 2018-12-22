package view;

import java.io.File;
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
			{' ','r','-','J'},
			{' ','L','-','g'}
	};
	
	@FXML
	PipeGameDisplayer pipeDisplayer;
	@FXML
	private Label stepsLabel;
	@FXML
	private Label timerLabel;
	
	
	private int port = 6100;
	
	private Date startTime, submitTime;
	private Integer stepsCounter = 0;
	
	public void handleServerSolution(String s) throws InterruptedException
	{
		String[] splited = s.split(",");
		int x = Integer.parseInt(splited[0]);
		int y = Integer.parseInt(splited[1]);
		int rotationCount = Integer.parseInt(splited[2]);
		
		for (int i = 0; i < rotationCount; i ++)
		{
			pipesRotation(x,y);
			Thread.sleep(10);
		}
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
		char PipeType = pipeDisplayer.getPipe(x, y);
		
		switch (PipeType) {
		case 's':
			pipeDisplayer.pipeBoard[x][y] = 's';
			break;
		case 'L':
			pipeDisplayer.pipeBoard[x][y] = 'r';
			pipeDisplayer.redraw();
			setStepsCounter();
			break;
		case 'r':
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
	
	public void Submit()
	{
		submitTime = new Date();
		long difference = submitTime.getTime() - startTime.getTime();
		timerLabel.setText("Timer: " + difference/1000 + " seconds");
		Communication.start(pipeDisplayer.covertGameToString(),"127.0.0.1", port);
	}
	
	public void Solve()
	{
		
	}
	
}
		
