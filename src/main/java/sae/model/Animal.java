package sae.model;
import sae.view.LabyConfig;

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
    public abstract Case deplacement(Labyrinthe l, Case c, LabyConfig labyC) throws Exception;


    }

