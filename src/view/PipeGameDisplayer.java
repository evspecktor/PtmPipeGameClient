package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.clientGameModel;

public class PipeGameDisplayer extends Canvas{
	
	char[][] pipeBoard;
	int x,y;
	
	double h,H,W,w;
	
	boolean onGame = true;
	
	public PipeGameDisplayer()
	{
		theme1Path = new SimpleStringProperty();
		theme2Path = new SimpleStringProperty();
		pipeg = new SimpleStringProperty();
		pipes = new SimpleStringProperty();
		pipe7 = new SimpleStringProperty();
		pipeL = new SimpleStringProperty();
		pipeJ = new SimpleStringProperty();
		pipeF = new SimpleStringProperty();
		pipe0 = new SimpleStringProperty();
		pipe1 = new SimpleStringProperty();
		Background = new SimpleStringProperty();
		picSuccess = new SimpleStringProperty();
		picFail = new SimpleStringProperty();
		picNoConnection = new SimpleStringProperty();
		pipeEmpty = new SimpleStringProperty();

	}
	

	private StringProperty theme1Path;
	private StringProperty theme2Path;
	private StringProperty pipeg;
	private StringProperty pipes;
	private StringProperty pipe7;
	private StringProperty pipeJ;
	private StringProperty pipeL;
	private StringProperty pipeF;
	private StringProperty pipe0;
	private StringProperty pipe1;
	private StringProperty Background;
	private StringProperty picSuccess;
	private StringProperty picFail;
	private StringProperty picNoConnection;
	private StringProperty pipeEmpty;
	
	


	public String covertGameToString()
	{
	    String s = "";
	    for (int i=0; i < pipeBoard.length; i++) {
	        for (int j=0; j < pipeBoard[i].length; j++) {
	            s += pipeBoard[i][j];
	        }
	        s += "\n";
	    }
	    s += "done";
	    
	    System.out.println("string to server: " + s.toString());
	    return s.toString();
	}
	

	public char getPipe(int x, int y) {
		System.out.println("get pipe x: " + x + " y: " + y);
		System.out.println(pipeBoard);
		return pipeBoard[x][y];
	}
	
	public char[][] getPipeBoard()
	{
		return pipeBoard;
		
	}
	
	public String getBackground() {
		return Background.get();
	}

	public void setBackground(String background) {
		this.Background.set(background);
	}

	public String getPicNoConnection() {
		return picNoConnection.get();
	}

	public void setPicNoConnection(String picNoConnection) {
		this.picNoConnection.set(picNoConnection);
	}
	
	public String getPicFail() {
		return picFail.get();
	}

	public void setPicFail(String picFail) {
		this.picFail.set(picFail);
	}
	
	public String getPicSuccess() {
		return picSuccess.get();
	}

	public void setPicSuccess(String picSuccess) {
		this.picSuccess.set(picSuccess);
	}
	
	public String getPipeEmpty() {
		return pipeEmpty.get();
	}

	public void setPipeEmpty(String pipeEmpty) {
		this.pipeEmpty.set(pipeEmpty);
	}
	
	public String getPipeJ() {
		return pipeJ.get();
	}

	public void setPipeJ(String pipeg) {
		this.pipeJ.set(pipeg);
	}
	
	public String getPipe7() {
		return pipe7.get();
	}

	public void setPipe7(String pipeg) {
		this.pipe7.set(pipeg);
	}
	
	public String getPipeL() {
		return pipeL.get();
	}

	public void setPipeL(String pipeg) {
		this.pipeL.set(pipeg);
	}
	
	public String getPipeF() {
		return pipeF.get();
	}

	public void setPipeF(String pipeg) {
		this.pipeF.set(pipeg);
	}
	
	public String getPipe0() {
		return pipe0.get();
	}

	public void setPipe0(String pipeg) {
		this.pipe0.set(pipeg);
	}

	public String getPipe1() {
		return pipe1.get();
	}

	public void setPipe1(String pipeg) {
		this.pipe1.set(pipeg);
	}
	
	public String getPipes() {
		return pipes.get();
	}

	public void setPipes(String pipeg) {
		this.pipes.set(pipeg);
	}
	
	public String getPipeg() {
		return pipeg.get();
	}

	public void setPipeg(String pipeg) {
		this.pipeg.set(pipeg);
	}
	
	public String getTheme1Path() {
		return theme1Path.get();
	}

	public void setTheme1Path(String theme1Path) {
		this.theme1Path.set(theme1Path);
	}
	
	public String getTheme2Path() {
		return theme2Path.get();
	}

	public void setTheme2Path(String theme2Path) {
		this.theme2Path.set(theme2Path);
	}

	public void setPipeBoard(char[][] pipeData) {
		this.pipeBoard = pipeData;
	}
	
	public double getw() {
		return w;
	}

	public void setw(double w) {
		this.w = w;
	}

	public double geth() {
		return h;
	}

	public void seth(double h) {
		this.h = h;
	}

	
	@Override
  	public boolean isResizable() {
      return true;
    }
	
	public void redrawSuccess() throws FileNotFoundException, InterruptedException
	{
		System.out.println("redrawSuccess");
		W = getWidth(); 
		H = getHeight();
		
		GraphicsContext gc = getGraphicsContext2D();
		
		Image picSuccess = null;
		picSuccess = new Image(new FileInputStream(theme2Path.get()+this.picSuccess.get()));
		System.out.println("redrawSuccess2");
		gc.clearRect(0, 0, W, H);
		gc.drawImage(picSuccess,0,0,W,H);
		onGame = false;
	}
		
	public void redrawFail() throws FileNotFoundException
	{
		W = getWidth(); 
		H = getHeight();
		
		GraphicsContext gc = getGraphicsContext2D();
			
		Image picFail = null;
		picFail = new Image(new FileInputStream(theme2Path.get()+this.picFail.get()));
		gc.clearRect(0, 0, W, H);
		gc.drawImage(picFail,0,0,W,H); 
		onGame = false;		
	}
	
	public void redrawNoConnection() throws FileNotFoundException
	{
		W = getWidth(); 
		H = getHeight();
		
		GraphicsContext gc = getGraphicsContext2D();
			
		Image picNoConnection = null;
		picNoConnection = new Image(new FileInputStream(theme2Path.get()+this.picNoConnection.get()));
		gc.clearRect(0, 0, W, H);
		gc.drawImage(picNoConnection,0,0,W,H); 
		onGame = false;
	}
	
	public void redraw(int chosenTheme)
	{
		onGame = true;
		if (pipeBoard != null)
		{
			
			W = getWidth(); 
			H = getHeight();
			w = W / pipeBoard[0].length;
			h = H / pipeBoard.length;
			
			GraphicsContext gc = getGraphicsContext2D();
			
			Image picg = null;
			Image pics = null;
			Image pic1 = null;
			Image pic0 = null;
			Image picL = null;
			Image picJ = null;
			Image pic7 = null;
			Image picF = null;
			Image pipeEmpty = null;
			
			switch (chosenTheme) {
			case 1:
				try {
					pics = new Image(new FileInputStream(theme1Path.get()+pipes.get()));
					pics = new Image(new FileInputStream(theme1Path.get()+pipes.get()));
					picg = new Image(new FileInputStream(theme1Path.get()+pipeg.get()));				
					pic0 = new Image(new FileInputStream(theme1Path.get()+pipe0.get()));
					pic1 = new Image(new FileInputStream(theme1Path.get()+pipe1.get()));
					picL = new Image(new FileInputStream(theme1Path.get()+pipeL.get()));
					picJ = new Image(new FileInputStream(theme1Path.get()+pipeJ.get()));
					pic7 = new Image(new FileInputStream(theme1Path.get()+pipe7.get()));
					picF = new Image(new FileInputStream(theme1Path.get()+pipeF.get()));
					pipeEmpty = new Image(new FileInputStream(theme1Path.get()+this.pipeEmpty.get()));
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				try {
					pics = new Image(new FileInputStream(theme2Path.get()+pipes.get()));
					pics = new Image(new FileInputStream(theme2Path.get()+pipes.get()));
					picg = new Image(new FileInputStream(theme2Path.get()+pipeg.get()));				
					pic0 = new Image(new FileInputStream(theme2Path.get()+pipe0.get()));
					pic1 = new Image(new FileInputStream(theme2Path.get()+pipe1.get()));
					picL = new Image(new FileInputStream(theme2Path.get()+pipeL.get()));
					picJ = new Image(new FileInputStream(theme2Path.get()+pipeJ.get()));
					pic7 = new Image(new FileInputStream(theme2Path.get()+pipe7.get()));
					picF = new Image(new FileInputStream(theme2Path.get()+pipeF.get()));
					pipeEmpty = new Image(new FileInputStream(theme2Path.get()+this.pipeEmpty.get()));
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			default:
				break;
			}
			
			gc.clearRect(0, 0, W, H);
			char pipe;
			
			for (int i = 0; i < pipeBoard.length ; i++ )
			{
				for ( int j = 0 ; j < pipeBoard[i].length; j++)
				{
					pipe = pipeBoard[i][j];
					
					System.out.println("redraew i: " + i + " j: " + j);
					
					switch (pipe) {
					case 's':
						if (pics == null)
						{
							gc.fillRect(w*j, h*i, w, h);
						}
						else
						{
							gc.drawImage(pics, w*j, h*i, w, h); 
						}
						break;
					case 'g':
						if (picg == null)
						{
							gc.fillRect(w*j, h*i, w, h);
						}
						else
						{
							gc.drawImage(picg, w*j, h*i, w, h); 
						}
						break;
					case '-':
						if (pic0 == null)
						{
							gc.fillRect(w*j, h*i, w, h);
						}
						else
						{
							gc.drawImage(pic0, w*j, h*i, w, h); 
						}
						break;
					case '|':
						if (pic1 == null)
						{
							gc.fillRect(w*j, h*i, w, h);
						}
						else
						{
							gc.drawImage(pic1, w*j, h*i, w, h); 
						}
						break;
					case '7':
						if (pic7 == null)
						{
							gc.fillRect(w*j, h*i, w, h);
						}
						else
						{
							gc.drawImage(pic7, w*j, h*i, w, h); 
						}
						break;
					case 'L':
						if (picL == null)
						{
							gc.fillRect(w*j, h*i, w, h);
						}
						else
						{
							gc.drawImage(picL, w*j, h*i, w, h); 
						}
						break;
					case 'J':
						if (picJ == null)
						{
							gc.fillRect(w*j, h*i, w, h);
						}
						else
						{
							gc.drawImage(picJ, w*j, h*i, w, h); 
						}
						break;
					case 'F':
						if (picF == null)
						{
							gc.fillRect(w*j, h*i, w, h);
						}
						else
						{
							gc.drawImage(picF, w*j, h*i, w, h); 
						}
						break;

					default:
						if (pipeEmpty == null)
					{
						gc.fillRect(w*j, h*i, w, h);
					}
					else
					{
						gc.drawImage(pipeEmpty, w*j, h*i, w, h); 
					}
						break;
					}
					
					}
				
			}
		}
	}
}
