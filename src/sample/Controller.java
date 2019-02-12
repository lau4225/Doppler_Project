package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    BorderPane gameOn;

    @FXML
    Button demarrer;

    //Ne fonctionne pas :(
    public void changeScene(ActionEvent event) throws IOException {
        Parent gameView = FXMLLoader.load(getClass().getResource("game.fxml"));
        Scene newScene = new Scene(gameView);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
        stage.show();


    }
    @FXML
    BorderPane acceuil;

    @FXML
    VBox vBoxStart;
    public void guideUtilisation(){
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
        acceuil.setCenter(vBox);
        retour.setOnAction(event -> acceuil.setCenter(vBoxStart));
    }
}
