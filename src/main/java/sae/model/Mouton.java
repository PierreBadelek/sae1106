package sae.model;
import java.util.*;

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

    @Override
    public void deplacement() {
        // TODO implement here
    }




}

