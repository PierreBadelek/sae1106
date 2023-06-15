package sae.model;
import sae.view.LabyConfig;

import java.util.*;

/* Classe enfant de Animal */
public class Loup extends Animal {

    public Loup(Case lacase) {
        super(lacase);
        this.imageURL = "/loup.png";
    }



    public String toString(){
        return "l";
    }
    public void manger(Mouton m) {
        // TODO implement here
    }

    @Override
    public Case deplacement(Labyrinthe l, Case c, LabyConfig labyC) throws Exception {

        int vit = this.getVitesse();
        Case nextCase = c;
        ArrayList<Case> nextChemin = l.getVoisinNord(5, c);
        boolean trouve = true;

        while (trouve) {
            Random random = new Random();
            int randomNumber = random.nextInt(100);

            if (randomNumber < 25 && l.getVoisinNord(5, c).size() != 0) {
                nextChemin = l.getVoisinNord(5, c);
                trouve = false;
                System.out.println("n" + l.getVoisinNord(5, c).size());

            } else if (randomNumber < 50 && l.getVoisinSud(5, c).size() != 0) {
                nextChemin = l.getVoisinSud(5, c);
                trouve = false;
                System.out.println("s" + l.getVoisinSud(5, c).size());

            } else if (randomNumber < 75 && l.getVoisinEst(5, c).size() != 0) {
                nextChemin = l.getVoisinEst(5, c);
                trouve = false;
                System.out.println("e" + l.getVoisinEst(5, c).size());

            } else if (randomNumber < 100 && l.getVoisinOuest(5, c).size() != 0) {
                nextChemin = l.getVoisinOuest(5, c);
                trouve = false;
                System.out.println("o" + l.getVoisinOuest(5, c).size());
            }
        }

        for (int i = 0; i <= vit; i++) {
            if (nextChemin.size() <= vit) {
                vit = nextChemin.size() - 1;
            }
            nextCase = nextChemin.get(vit);
        if (! (this.getLacase().getPosX()+vit >= l.getNbcolonne() || this.getLacase().getPosY()+1 >= l.getNbligne())){
            if (! (nextCase.getElement() instanceof Rocher)) {
                Case old = this.getLacase();
                this.changeCase(nextCase);
                nextCase.modifierElementCase(this);
                Herbe v = new Herbe(old);
                l.EditCell(old);
                l.EditCell(nextCase);
                return nextCase;
                }
            }

        }
        return nextCase;
    }
}

