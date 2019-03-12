package Emetteur;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Audio extends Application {

    //trouver les bruits et les ajouter avec la méthode dans le powerpoint
    @Override
    public void start(Stage primaryStage) {
        primaryStage.show();
    }

    private String musicFile;
    private Media audio;
    private MediaPlayer mediaPlayer;
}
