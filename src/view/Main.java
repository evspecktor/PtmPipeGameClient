package view;
	
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	@FXML
	PipeGameDisplayer pipeDisplayerFxml;
	public BorderPane root;
	MainWindowController mwc;
	Image image = null;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
	    root = new BorderPane();
	    mwc = new MainWindowController();
//	    MediaPlayer mediaPlayer1,mediaPlayer2;
//		String musicFile = "./resources/Theme1/song1.mp3";     // For example
//    	Media sound = new Media(new File(musicFile).toURI().toString());
//    	mediaPlayer1 = new MediaPlayer(sound);
//    	String musicFile2 = "./resources/Theme2/song2.mp3";     // For example
//    	Media sound2 = new Media(new File(musicFile2).toURI().toString());
//    	mediaPlayer2 = new MediaPlayer(sound2);
	    if (mwc.getChosenTheme() == 1)
	    {
	    	System.out.println("im in chosen theme1");
	    	image = new Image(new FileInputStream("./resources/Theme1/Background.jpg"));
//	    	mediaPlayer2.stop();
//	    	mediaPlayer1.play();
	    }
	    else
	    {
	    	System.out.println("im in chosen theme2");
	    	image = new Image(new FileInputStream("./resources/Theme2/Background.jpg"));
//	    	System.out.println("stopping media player1");
//	    	mediaPlayer1.stop();
//	    	System.out.println("starting media player2");
//	    	mediaPlayer2.play();
//	    	System.out.println("media player2 started");
	    }
	    
	    BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);

	    root.setBackground(new Background(new BackgroundImage(image,
	            BackgroundRepeat.REPEAT,
	            BackgroundRepeat.REPEAT,
	            BackgroundPosition.CENTER,
	            bSize)));
	    root.setCenter((BorderPane)FXMLLoader.load(getClass().getResource("MainWindow.fxml")));
	    
	    Scene scene = new Scene(root,600,400);
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
