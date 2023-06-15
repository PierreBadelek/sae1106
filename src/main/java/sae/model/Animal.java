package sae.model;
import java.util.*;

/* Classe parente de Loup et Mouton */
public abstract class Animal extends ElementCase {
    private int vision = 5;
    private int vitesse;
    public Animal(Case lacase) {
        super(lacase);
        this.vitesse = 2;
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }
}

