package recepteur;

import emetteur.Source;

public class CacheOreilles extends Structure {

    public CacheOreilles(){

        setNom("Cache-oreilles");
    }

    public double Isolation(Source source){
        //réduit de 28dB        https://www.homedepot.ca/produit/workhorse-coquille-anti-bruit-avec-bande-rembouree-ajustable/1001009251

        double rep = source.getIntensiteEmise()-28;

        if (rep<0){ rep = 0; }

        return rep;

    }
}
