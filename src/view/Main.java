package view;
	
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	@FXML
	PipeGameDisplayer pipeDisplayerFxml;
	public BorderPane root;
	@Override
	public void start(Stage primaryStage) throws IOException {
	    //BorderPane borderPane = new BorderPane();
	    root = new BorderPane();
	    //(BorderPane)FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
	    Image image1 = new Image(new FileInputStream("./resources/Pipes/Background.png"));
	    BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);

	    root.setBackground(new Background(new BackgroundImage(image1,
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
