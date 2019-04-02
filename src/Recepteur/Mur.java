package Recepteur;

import Emetteur.Source;

public class Mur extends Structure {

    public Mur(){

        setNom("Mur");
    }

    public double Isolation(Source source){
        //manque feux d'articice
        double rep = 0;

        try {

            if (source.getFrequenceEmise() <= 125){

                rep = source.getIntensiteEmise() - 19;
            }
            else if (source.getFrequenceEmise()>125 && source.getFrequenceEmise()<= 250){

                rep = source.getIntensiteEmise() - 24;
            }
            else if (source.getFrequenceEmise()>250 && source.getFrequenceEmise()<= 500){

                rep = source.getIntensiteEmise() - 34;
            }
            else if (source.getFrequenceEmise()>500 && source.getFrequenceEmise()<= 1000){

                rep = source.getIntensiteEmise() - 45;
            }
            else if (source.getFrequenceEmise()>1000 && source.getFrequenceEmise()<= 2000){

                rep = source.getIntensiteEmise() - 54;
            }
            else if (source.getFrequenceEmise()>2000 && source.getFrequenceEmise()<= 4000){

                rep = source.getIntensiteEmise() - 57;
            }

        }
        catch (NullPointerException e){

        }

        return rep;
    }
}
