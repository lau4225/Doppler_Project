package Recepteur;

import Emetteur.Source;

public class Oreiller extends Structure{

    public Oreiller(){

        setNom("Oreiller");

    }

    public double Isolation(Source source){
        //manque feux d'articice
        double rep = 0;

        try {

            if (source.getFrequenceEmise() <= 80){

                //rep = source.getIntensiteEmise() - 19;
                rep = source.getIntensiteEmise()- source.getIntensiteEmise()*0;
            }
            else if (source.getFrequenceEmise()>80 && source.getFrequenceEmise()<= 100){

                //rep = source.getIntensiteEmise() - 24;
                rep = source.getIntensiteEmise()- source.getIntensiteEmise()*0.02;
            }
            else if (source.getFrequenceEmise()>100 && source.getFrequenceEmise()<= 125){

                //rep = source.getIntensiteEmise() - 34;
                rep = source.getIntensiteEmise()- source.getIntensiteEmise()*0.08;

            }
            else if (source.getFrequenceEmise()>125 && source.getFrequenceEmise()<= 160){

                //rep = source.getIntensiteEmise() - 45;
                rep = source.getIntensiteEmise()- source.getIntensiteEmise()*0.16;
            }
            else if (source.getFrequenceEmise()>160 && source.getFrequenceEmise()<= 200){

                //rep = source.getIntensiteEmise() - 54;
                rep = source.getIntensiteEmise()- source.getIntensiteEmise()*0.27;
            }
            else if (source.getFrequenceEmise()>200 && source.getFrequenceEmise()<= 250){

                //rep = source.getIntensiteEmise() - 57;
                rep = source.getIntensiteEmise()- source.getIntensiteEmise()*0.55;
            }
            else if (source.getFrequenceEmise()>250 && source.getFrequenceEmise()<= 315){

                //rep = source.getIntensiteEmise() - 24;
                rep = source.getIntensiteEmise()- source.getIntensiteEmise()*0.94;
            }
            else if (source.getFrequenceEmise()>315 && source.getFrequenceEmise()<= 400){

                //rep = source.getIntensiteEmise() - 34;
                rep = source.getIntensiteEmise()- source.getIntensiteEmise()*1.03;

            }
            else if (source.getFrequenceEmise()>400 && source.getFrequenceEmise()<= 500){

                //rep = source.getIntensiteEmise() - 45;
                rep = source.getIntensiteEmise()- source.getIntensiteEmise()*0.67;
            }
            else if (source.getFrequenceEmise()>500 && source.getFrequenceEmise()<= 630){

                //rep = source.getIntensiteEmise() - 54;
                rep = source.getIntensiteEmise()- source.getIntensiteEmise()*0.43;
            }
            else if (source.getFrequenceEmise()>630 && source.getFrequenceEmise()<= 800){

                //rep = source.getIntensiteEmise() - 57;
                rep = source.getIntensiteEmise()- source.getIntensiteEmise()*0.32;
            }
            else if (source.getFrequenceEmise()>800 && source.getFrequenceEmise()<= 1000){

                //rep = source.getIntensiteEmise() - 24;
                rep = source.getIntensiteEmise()- source.getIntensiteEmise()*0.27;
            }
            else if (source.getFrequenceEmise()>1000 && source.getFrequenceEmise()<= 1250){

                //rep = source.getIntensiteEmise() - 34;
                rep = source.getIntensiteEmise()- source.getIntensiteEmise()*0.30;

            }
            else if (source.getFrequenceEmise()>1250 && source.getFrequenceEmise()<= 1600){

                //rep = source.getIntensiteEmise() - 45;
                rep = source.getIntensiteEmise()- source.getIntensiteEmise()*0.31;
            }
            else if (source.getFrequenceEmise()>1600 && source.getFrequenceEmise()<= 2000){

                //rep = source.getIntensiteEmise() - 54;
                rep = source.getIntensiteEmise()- source.getIntensiteEmise()*0.26;
            }
            else if (source.getFrequenceEmise()>2000 && source.getFrequenceEmise()<= 2500){

                //rep = source.getIntensiteEmise() - 57;
                rep = source.getIntensiteEmise()- source.getIntensiteEmise()*0.26;
            }
            else if (source.getFrequenceEmise()>2500 && source.getFrequenceEmise()<= 3150){

                //rep = source.getIntensiteEmise() - 24;
                rep = source.getIntensiteEmise()- source.getIntensiteEmise()*0.29;
            }
            else if (source.getFrequenceEmise()>3150 && source.getFrequenceEmise()<= 4000){

                //rep = source.getIntensiteEmise() - 34;
                rep = source.getIntensiteEmise()- source.getIntensiteEmise()*0.41;

            }
            else if (source.getFrequenceEmise()>4000 && source.getFrequenceEmise()<= 5000){

                //rep = source.getIntensiteEmise() - 45;
                rep = source.getIntensiteEmise()- source.getIntensiteEmise()*0.47;
            }
            else if (source.getFrequenceEmise()>5000 && source.getFrequenceEmise()<= 6300){

                //rep = source.getIntensiteEmise() - 54;
                rep = source.getIntensiteEmise()- source.getIntensiteEmise()*0.33;
            }


        }
        catch (NullPointerException e){

        }
        if (rep<0){ rep = 0; }
        return Math.round(rep);

    }
}
