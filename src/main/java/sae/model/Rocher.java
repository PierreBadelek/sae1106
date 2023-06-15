package sae.model;

/* Classe d'un rocher */
public class Rocher extends ElementCase {
    protected boolean accessibilite;

    public Rocher(Case lc) {
        super(lc);
        this.accessibilite = false;
        this.imageURL = "/rocher.png";
    }

    public String toString(){
        return "x";
    }
}