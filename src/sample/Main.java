package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    public Stage fenetre;

    @Override
    public void start(Stage primaryStage) throws Exception{

        BorderPane root = new BorderPane();

        //SCENE1
        Scene scene = new Scene(root);

        primaryStage.setTitle("Doppler Project");
        primaryStage.setMaximized(true);
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);

        Label titre = new Label("DOPPLER AU PAYS DES BRUITS");
        titre.setAlignment(Pos.TOP_CENTER);
        Label noms = new Label("LAURIE BONNEL ET ANNABELLE DION");
        noms.setAlignment(Pos.BOTTOM_CENTER);
        Button demarrer = new Button("DÉMARRER");
        demarrer.setAlignment(Pos.CENTER);
        Button guideUti = new Button("GUIDE D'UTILISATION");
        guideUti.setAlignment(Pos.CENTER);
        root.setTop(titre);
        root.setBottom(noms);
        VBox centre  = new VBox(demarrer, guideUti);
        centre.setAlignment(Pos.CENTER);
        root.setCenter(centre);

        //SCENE2
        BorderPane root2 = new BorderPane();

        Scene scene2  = new Scene(root2, 700, 500);

        Button retour = new Button("Retour");
        Label instructions = new Label("INSTRUCTIONS À ÉCRIRE ICI" +
                "D\n" +
                "D\n" +
                "D\n" +
                "D\n" +
                "D\n" +
                "D\n" +
                "D\n" +
                "D\n" +
                "D\n");
        VBox vBox = new VBox(instructions, retour);
        vBox.setAlignment(Pos.CENTER);
        root2.setCenter(vBox);

        //ONACTION
        guideUti.setOnAction(event -> {
            primaryStage.setScene(scene2);
        });

        retour.setOnAction(event -> {
            primaryStage.setScene(scene);
        });

        primaryStage.show();
    }




}
