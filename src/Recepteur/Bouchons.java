package Recepteur;

import Emetteur.Source;

public class Bouchons extends Structure {

    public Bouchons(){

         setNom("Bouchons");
    }

    public double Isolation(Source source){

        double rep = 0;

        try {
            if (source.getFrequenceEmise() <= 125){

                rep = source.getIntensiteEmise() - 24.4;
            }
            else if (source.getFrequenceEmise() > 125 && source.getFrequenceEmise() <= 250){

                rep = source.getIntensiteEmise() - 23.6;
            }
            else if (source.getFrequenceEmise() > 250 && source.getFrequenceEmise() <= 500){

                rep = source.getIntensiteEmise() - 25.6;
            }
            else if(source.getFrequenceEmise() > 250 && source.getFrequenceEmise() == 1000){

                rep = source.getIntensiteEmise() - 26.5;
            }
            else if (source.getFrequenceEmise() > 1000 && source.getFrequenceEmise() == 2000){

                rep = source.getIntensiteEmise() - 32.3;
            }

        }
        catch (NullPointerException e){

        }

        if (rep<0){ rep = 0; }

        return rep;
    }
}
