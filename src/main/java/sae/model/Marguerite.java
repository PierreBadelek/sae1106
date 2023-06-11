package sae.model;

public class Marguerite extends Vegetal {

    public Marguerite(Case lc) {
        super(lc);
        this.capaciteDeplacement = 4; /* Nombres de cases que le mouton peut parcourir en l'ayant ingurgité */
        this.estMange = false;
        this.accessibilite = true;
        this.imageURL = "/fleur.png";
    }



    public String toString(){

        return "f";
    }
}