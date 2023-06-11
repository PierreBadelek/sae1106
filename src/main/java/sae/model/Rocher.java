package sae.model;

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