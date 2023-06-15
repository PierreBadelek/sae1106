package sae.model;
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

    public void deplacement() {
        // TODO implement here
    }
}

