package sae.model;
import java.util.*;


public abstract class Animal extends ElementCase {
    private int vision = 5;
    private int vitesse;
    public Animal(Case lacase) {
        super(lacase);
        this.vitesse = 5;

    }




    public abstract void deplacement();

}

