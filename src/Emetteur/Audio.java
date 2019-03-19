package Emetteur;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class Audio extends Application {

    //volume properties bind avec le déplacement de la source
    //on démarre le son quand?
    @Override
    public void start(Stage primaryStage) {
        primaryStage.show();
    }

    String musicFile = "0477.mp3";
    Media audio = new Media(new File(musicFile).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(audio);
    //mediaplayer.play;

    //je crois qu'il faut créer les média directement quand l'utilisateur crée les sources
}
