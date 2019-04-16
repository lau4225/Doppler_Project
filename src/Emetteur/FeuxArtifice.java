package Emetteur;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FeuxArtifice extends Source {

    //source de l'image      https://svgsilh.com/fr/image/305732.html

    //source valeurs         https://www.cirrusresearch.co.uk/blog/2011/11/noise-levels-from-fireworks-a-very-unscientific-measurement/
    public FeuxArtifice(){

        setNom("Feux artifice");
        setFrequenceEmise(360);
        setIntensiteEmise(140);
    }

}
