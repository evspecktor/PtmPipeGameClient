package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class PipeGameDisplayer extends Canvas{
	
	char[][] pipeBoard;
	char[][] pipeBoardChars;
	
	int cCol,cRow;
	int x,y;
	double h,H,W,w;
	
	public PipeGameDisplayer()
	{
		picFileName = new SimpleStringProperty();
		pipeg = new SimpleStringProperty();
		pipes = new SimpleStringProperty();
		pipe7 = new SimpleStringProperty();
		pipeL = new SimpleStringProperty();
		pipeJ = new SimpleStringProperty();
		piper = new SimpleStringProperty();
		pipe0 = new SimpleStringProperty();
		pipe1 = new SimpleStringProperty();
		
		cCol = 0;
		cRow = 1; 
	}
	
	
	public char getPipe(int x, int y) {
		System.out.println("get pipe x: " + x + " y: " + y);
		System.out.println(pipeBoard);
		System.out.println("in Get" + pipeBoard[y][x]);
		System.out.println("in Get" + pipeBoard[x][y]);
		return pipeBoard[x][y];
	}
	
	
	public int getcRow() {
		return cRow;
	}
	
	public int getcCol() {
		return cCol;
	}

	public void setcPoition(int row, int col) {
		cRow = row;
		cCol = col;
		redraw();
	}

	private StringProperty picFileName;
	private StringProperty pipeg;
	private StringProperty pipes;
	private StringProperty pipe7;
	private StringProperty pipeJ;
	private StringProperty pipeL;
	private StringProperty piper;
	private StringProperty pipe0;
	private StringProperty pipe1;


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
	
	public String getPiper() {
		return piper.get();
	}

	public void setPiper(String pipeg) {
		this.piper.set(pipeg);
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
	
	public String getPicFileName() {
		return picFileName.get();
	}

	public void setPicFileName(String picFileName) {
		this.picFileName.set(picFileName);
	}


	public char[][] getPipeBoard() {
		return pipeBoard;
	}

	public void setPipeBoard(char[][] pipeData) {
		this.pipeBoard = pipeData;
		redraw();
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

	
	
	public void redraw()
	{
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
			Image picr = null;

			try {
				System.out.println("1");
				System.out.println(picFileName.get()+pipes.get());
				pics = new Image(new FileInputStream(picFileName.get()+pipes.get()));

				System.out.println("2");
				picg = new Image(new FileInputStream(picFileName.get()+pipeg.get()));				
				System.out.println("3");
				pic0 = new Image(new FileInputStream(picFileName.get()+pipe0.get()));
				System.out.println("4");
				pic1 = new Image(new FileInputStream(picFileName.get()+pipe1.get()));
				System.out.println("5");
				picL = new Image(new FileInputStream(picFileName.get()+pipeL.get()));
				picJ = new Image(new FileInputStream(picFileName.get()+pipeJ.get()));
				pic7 = new Image(new FileInputStream(picFileName.get()+pipe7.get()));
				picr = new Image(new FileInputStream(picFileName.get()+piper.get()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			System.out.println(picFileName.get());
			
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
					case 'r':
						if (picr == null)
						{
							gc.fillRect(w*j, h*i, w, h);
						}
						else
						{
							gc.drawImage(picr, w*j, h*i, w, h); 
						}
						break;

					default:
						break;
					}
					
					}
				
				System.out.println(pipeBoard.toString());
			}
		}
	}
}
