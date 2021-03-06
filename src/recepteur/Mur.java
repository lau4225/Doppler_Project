package recepteur;

import emetteur.Source;

public class Mur extends Structure {

    public Mur(){

        setNom("Mur");
    }

    public double Isolation(Source source){

        double rep = 0;

        try {

            if (source.getFrequenceEmise() <= 125){

                rep = source.getIntensiteEmise()- source.getIntensiteEmise()*0.15;
            }
            else if (source.getFrequenceEmise()>125 && source.getFrequenceEmise()<= 250){

                rep = source.getIntensiteEmise()- source.getIntensiteEmise()*0.66;
            }
            else if (source.getFrequenceEmise()>250 && source.getFrequenceEmise()<= 500){

                rep = source.getIntensiteEmise()- source.getIntensiteEmise()*1.07;

            }
            else if (source.getFrequenceEmise()>500 && source.getFrequenceEmise()<= 1000){

                rep = source.getIntensiteEmise()- source.getIntensiteEmise()*1.06;
            }
            else if (source.getFrequenceEmise()>1000 && source.getFrequenceEmise()<= 2000){

                rep = source.getIntensiteEmise()- source.getIntensiteEmise()*0.97;
            }
            else if (source.getFrequenceEmise()>2000 && source.getFrequenceEmise()<= 4000){

                rep = source.getIntensiteEmise()- source.getIntensiteEmise()*0.86;
            }

        }
        catch (NullPointerException e){

        }

        if (rep<0){ rep = 0; }

        return Math.round(rep);
    }
}
