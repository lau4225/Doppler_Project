package emetteur;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Source extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.show();
    }

    private String nom;
    private double frequenceEmise;
    private double vitesse;
    //intensité en décibels
    private double IntensiteEmise;
    private boolean cercleOndes;
    private Audio son;
    private Image image;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getFrequenceEmise() {
        return frequenceEmise;
    }

    public void setFrequenceEmise(double frequenceEmise) {
        this.frequenceEmise = frequenceEmise;
    }

    public double getVitesse() {
        return vitesse;
    }

    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }

    public double getIntensiteEmise() {
        return IntensiteEmise;
    }

    public void setIntensiteEmise(double intensiteEmise) {
        IntensiteEmise = intensiteEmise;
    }

    public boolean isCercleOndes() {
        return cercleOndes;
    }

    public void setCercleOndes(boolean cercleOndes) {
        this.cercleOndes = cercleOndes;
    }

    public Audio getSon() {
        return son;
    }

    public void setSon(Audio son) {
        this.son = son;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void frontOndes(){

    }

}
