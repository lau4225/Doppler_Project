package Recepteur;

import Emetteur.Source;

public class Bouchons extends Structure {

    public Bouchons(){

         setNom("Bouchons");
    }

    public double Isolation(Source source){
        //manque feux d'articice

        double rep = 0;

        if (source.getFrequenceEmise() <= 125){

            rep = source.getIntensiteEmise() - 24.4;
        }
        else if (source.getFrequenceEmise() == 1000){

            rep = source.getIntensiteEmise() - 26.5;
        }
        else if (source.getFrequenceEmise() > 2000){

            rep = source.getIntensiteEmise() - 32.3;
        }

        return rep;

    }
}
