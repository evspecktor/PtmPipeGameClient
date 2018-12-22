package view;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pipeDisplayer.setPipeBoard(pipeData);
		
		pipeDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->pipeDisplayer.requestFocus());
		
		pipeDisplayer.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				int y = (int)event.getX();
				int x = (int)event.getY();
				
				y = y / (int)pipeDisplayer.getw(); 
				x = x / (int)pipeDisplayer.geth();
				
				System.out.println("x: " + x + "y: " + y);
				
				char PipeType = pipeDisplayer.getPipe(x, y);
				
				System.out.println("PipeType: " + PipeType);
				
				switch (PipeType) {
				case 's':
					pipeDisplayer.pipeBoard[x][y] = 's';
					pipeDisplayer.redraw();
					break;
				case 'L':
					pipeDisplayer.pipeBoard[x][y] = 'r';
					pipeDisplayer.redraw();
					break;
				case 'r':
					pipeDisplayer.pipeBoard[x][y] = '7';
					pipeDisplayer.redraw();
					break;
				case '7':
					pipeDisplayer.pipeBoard[x][y] = 'J';
					pipeDisplayer.redraw();
					break;
				case 'J':
					pipeDisplayer.pipeBoard[x][y] = 'L';
					pipeDisplayer.redraw();
					break;
				case 'g':
					pipeDisplayer.pipeBoard[x][y] = 'g';
					break;
				case '-':
					pipeDisplayer.pipeBoard[x][y] = '|';
					pipeDisplayer.redraw();
					break;	
				case '|':
					pipeDisplayer.pipeBoard[x][y] = '-';
					pipeDisplayer.redraw();
					break;		
				default:
					break;
			}
		
		}
	});
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
		
	}
	
	public void Solve()
	{
		
	}
	
	public void Top10()
	{
		
	}
	
	public void Timer()
	{
		
	}
	
	public void Steps()
	{
		
	}
}
		
