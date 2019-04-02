package Emetteur;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Avion extends Source {

    public Avion(){

        //fréqunece à vérifier
        setNom("Avion");
        setFrequenceEmise(2500);
        setIntensiteEmise(200);
    }

}
