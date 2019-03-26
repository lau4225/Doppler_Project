package Recepteur;

import Emetteur.Source;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.text.DecimalFormat;

public class Personnage extends Application {

    //methode return

    @Override
    public void start(Stage primaryStage) {
         primaryStage.show();
    }

    private String nom;
    private double vitesse;
    private Image image;
    //probablement supprimer
    private Structure protectActiv;
    private Source source;

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

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


    //fréquence perçue
    //ajouter le vent
    public double frequenceCalc(double vitesseEmet, double vitesseRecep, double frequenceEmise){

        double rep = 0;
        double vE = 0;
        double vR = 0;
        double vitesseSon = 1235;

        if (vitesseEmet > 0 && vitesseRecep > 0){

            rep = (vitesseSon + vitesseRecep) / (vitesseSon + vitesseEmet)*frequenceEmise;
        }
        if (vitesseEmet < 0 && vitesseRecep < 0){

            vE =  Math.abs(vitesseEmet);
            vR = Math.abs(vitesseRecep);

            rep = ((vitesseSon - vR) / (vitesseSon - vE))*frequenceEmise;
        }
        if (vitesseEmet < 0 && vitesseRecep > 0){

            vE = Math.abs(vitesseEmet);

            rep = ((vitesseSon + vitesseRecep) / (vitesseSon - vE))*frequenceEmise;
        }
        if(vitesseEmet > 0 && vitesseRecep < 0){

            vR = Math.abs(vitesseRecep);

            rep = ((vitesseSon - vR) / (vitesseSon + vitesseEmet))*frequenceEmise;
        }


        return rep;
    }



}
