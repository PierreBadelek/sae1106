package sae.model;
import sae.view.LabyConfig;

import java.util.*;

/* Classe enfant de Animal */
public class Mouton extends Animal {

    public Mouton(Case lacase) {
        super(lacase);
        this.imageURL = "/mouton.png";
    }

    public String toString(){
        return "m";
    }
    public void manger(Vegetal veg) {
        // TODO implement here
    }

    public Case deplacement(Labyrinthe l, Case c, LabyConfig labyC) throws Exception {
        boolean doitfuir = l.isChasse();

        System.out.println("Mouton "+ this.getLacase().getPosX() + " et " + this.getLacase().getPosY());
        int vitesse = this.getVitesse();
        Case nextCase = c;
        ArrayList <Case> nextChemin = l.getVoisinNord(5,c) ;
        boolean trouve = true;
        boolean sortie = false;
        for (Case test : l.getAllVoisins(this.getLacase(),5)){
            if (test.getSortie() && !(sortie) && doitfuir ){
                labyC.sheepWin();
                trouve = false;
                nextCase = test;
                nextChemin = new ArrayList<Case>();
                nextChemin.add(test);
                sortie = true;
            }
        }

        /* Pour trouver une direction aléatoire dans laquelle se déplacer */
        while (trouve) {
            Random random = new Random();
            int randomNumber = random.nextInt(100);

            if (randomNumber < 25 && l.getVoisinNord(5,c).size() != 0) {
                nextChemin = l.getVoisinNord(5,c);
                trouve = false;
                System.out.println("n"+l.getVoisinNord(5,c).size());

            } else if (randomNumber < 50 && l.getVoisinSud(5,c).size() != 0) {
                nextChemin = l.getVoisinSud(5,c);
                trouve = false;
                System.out.println("s"+ l.getVoisinSud(5,c).size());

            } else if (randomNumber < 75 && l.getVoisinEst(5,c).size() != 0){
                nextChemin = l.getVoisinEst(5,c);
                trouve = false;
                System.out.println("e" + l.getVoisinEst(5,c).size());

            } else if (randomNumber < 100 && l.getVoisinOuest(5,c).size() != 0){
                nextChemin = l.getVoisinOuest(5,c);
                trouve = false;
                System.out.println("o" + l.getVoisinOuest(5,c).size());
            }
        }

         /* Déplacement */
        for (int i = 0; i <= vitesse; i++){
            if (nextChemin.size()<=vitesse){
                vitesse = nextChemin.size()-1;
            }
            nextCase = nextChemin.get(vitesse);
            /* Vérification pour savoir si l'on est sur la sortie */
            if (!(nextCase.getElement() instanceof Rocher) && nextCase.getSortie() && !(sortie) && doitfuir )  {
                labyC.sheepWin();
                sortie = true;
                break;
            }
        }

        Vegetal laplante = (Vegetal) nextCase.getElement();
        this.setVitesse(laplante.getCapaciteDeplacement());

        if (! (this.getLacase().getPosX()+vitesse >= l.getNbcolonne() || this.getLacase().getPosY()+1 >= l.getNbligne())){
            if (! (nextCase.getElement() instanceof Rocher)&& !(sortie)  ){
                if (nextCase.getSortie() && doitfuir){
                    labyC.sheepWin();
                    sortie = true;
                }
                Case old = this.getLacase();
                this.changeCase(nextCase);
                nextCase.modifierElementCase(this);

                Random random2 = new Random();

                /* Repousses des plantes avec probabilité */
                int randomNumber2 = random2.nextInt(100); // Génère un nombre aléatoire entre 0 et 99
                Vegetal v;
                v = new Herbe(old);
                if (randomNumber2 < 50) {
                    v = new Herbe(old);
                } else if (randomNumber2 < 75) {
                    v = new Cactus(old);
                } else {
                    v = new Marguerite(old);
                }

                v.manger();
                l.EditCell(old);
                l.EditCell(nextCase);
                return nextCase;
            }
        }
        return nextCase;
    }
}