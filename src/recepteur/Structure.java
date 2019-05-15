package recepteur;

import emetteur.Source;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public abstract class Structure extends Application {

    //revoir pour autres structures

    @Override
    public void start(Stage primaryStage) {

        primaryStage.show();

    }

    private String nom;
    private double alpha;
    private Image image;
    private int positionX;
    private int positionY;
    private double vitesse;

    public double getVitesse() {
        return vitesse;
    }

    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public abstract double Isolation(Source source);

}
