package sae.model;

public class Herbe extends Vegetal {

    public Herbe(Case lc) {
        super(lc);
        this.capaciteDeplacement = 2; /* Nombre de cases que le mouton peut parcourir en l'ayant ingurgité */
        this.estMange = false;
        this.accessibilite = true;
        this.imageURL = "/herbe.png";
    }
    public String toString(){
        return "h";
    }
}