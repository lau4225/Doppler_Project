package Recepteur;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Personnage extends Application {

    //methode return

    @Override
    public void start(Stage primaryStage) {
         primaryStage.show();
    }

    private String nom;
    private double vitesse;
    private Image image;
    private Structure protectActiv;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getVitesse() {
        return vitesse;
    }

    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Structure getProtectActiv() {
        return protectActiv;
    }

    public void setProtectActiv(Structure protectActiv) {
        this.protectActiv = protectActiv;
    }

    public void Marcher(){

    }

    public Structure Protection(Structure protection){

        return protection;
    }

    //intensité
    public void Entendre(){

    }

    //fréquence perçue
    //wtf
    public double frequenceCalc(double vitesseEmet, double vitesseRecep, double frequenceEmise, boolean sens){

        double rep = 0;
        double vitesseSon = 0;

        rep = ((vitesseSon - vitesseRecep) / (vitesseSon - vitesseEmet))*frequenceEmise;

        return rep;
    }



}
