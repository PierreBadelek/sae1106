package sae.model;

/* Classe enfant de Végétal */
public class Cactus extends Vegetal {
    public Cactus(Case lc) {
        super(lc);
        this.capaciteDeplacement = 1; /* Nombres de cases que le mouton peut parcourir en l'ayant ingurgité */
        this.estMange = false;
        this.accessibilite = true;
        this.imageURL = "/cactus.png";
    }
    public String toString(){
        return "c";
    }
}