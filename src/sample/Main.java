package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    public Stage fenetre;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("acceuil.fxml"));
        Scene scene = new Scene(root, 700, 500);

        scene.getStylesheets().add("sample/style.css");

        primaryStage.setTitle("Doppler Project");
        //primaryStage.setMaximized(true);
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);

        primaryStage.show();
    }




}
