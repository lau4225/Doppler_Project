package sample;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.naming.ldap.StartTlsResponse;

public class Controller {

    BorderPane gameOn = new BorderPane();


    @FXML
    BorderPane acceuil;
    public void changeScene(){
    }


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
