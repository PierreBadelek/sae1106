package sae.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sae.view.LabyConfig;

import java.io.FileWriter;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class Labyrinthe {

    private int nbcolonne;
    private int nbligne;
    private ArrayList<Case> lesCases;

    public Labyrinthe(int nbl, int nbc) {
        this.nbcolonne = nbc;
        this.nbligne = nbl;
        genererLab();
    }

    public Labyrinthe(String path) throws Exception {
        loadLaby(path);
    }

    public void loadLaby(String filePath) throws Exception {
        if (filePath.length() <= 1){
            throw new Exception("Le chemin du dossier n'est pas correcte");
        }
        this.lesCases = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                char[] letters = line.toCharArray();

                for (int i = 0; i < letters.length; i++) {
                    char letter = letters[i];
                    int row = i ;
                    int column = lineNumber;

                    this.nbcolonne = row+1;
                    this.nbligne = column+1;
                    //System.out.println("Coordonnées de la case :  "+this.nbcolonne + ' '+this.nbligne);
                    Case macase = new Case(row,column);
                    if (letter == 'x') {
                        Rocher r = new Rocher(macase);
                        macase.ajouterElementCase(r);
                        this.lesCases.add(macase);

                    } else if (letter == 'f') {
                        Marguerite m = new Marguerite(macase);
                        macase.ajouterElementCase(m);
                        this.lesCases.add(macase);

                    } else if (letter == 'h') {
                        Herbe h = new Herbe(macase);
                        macase.ajouterElementCase(h);
                        this.lesCases.add(macase);

                    } else if (letter == 'c') {
                        Cactus c = new Cactus(macase);
                        macase.ajouterElementCase(c);
                        this.lesCases.add(macase);


                    } else if (letter == 'm') {
                        Mouton h = new Mouton(macase);
                        macase.ajouterElementCase(h);
                        this.lesCases.add(macase);

                    }
                    else if (letter == 'l') {
                        Loup h = new Loup(macase);
                        macase.ajouterElementCase(h);
                        this.lesCases.add(macase);

                    }

                    if (letter == 's') {
                        Herbe h = new Herbe(macase);
                        macase.ajouterElementCase(h);
                        this.lesCases.add(macase);
                        macase.setSortie(true);

                    }

                }

                lineNumber++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public int getNbcolonne(){
        return this.nbcolonne;
    }

    public int getNbligne(){
        return this.nbligne;
    }

    public void genererLab() {

        System.out.println(this.nbcolonne);
        System.out.println(this.nbligne);
        this.lesCases = new ArrayList<>();
        for (int i = 0; i <= this.nbcolonne-1 ; i++) {
            for (int j = 0; j <= this.nbligne-1 ; j++) {

                this.lesCases.add(new Case(i, j));
            }
        }
        initCase();
    }

    public void initCase() {
        for (Case macase : this.lesCases) {

            if (macase.getPosX() == 0) {
                Rocher r = new Rocher(macase);
                macase.ajouterElementCase(r);
            } else if (macase.getPosX() == this.nbcolonne - 1) {
                Rocher r = new Rocher(macase);
                macase.ajouterElementCase(r);
            } else if (macase.getPosY() == 0) {
                Rocher r = new Rocher(macase);
                macase.ajouterElementCase(r);
            } else if (macase.getPosY() == this.nbligne - 1) {
                Rocher r = new Rocher(macase);
                macase.ajouterElementCase(r);
            } else {
                Herbe h = new Herbe(macase);
                macase.ajouterElementCase(h);
            }
        }

    }


    public void afficherLab() throws Exception {


        for (int i = 0; i <= this.getNbligne()-1 ; i++) {
            for (int j = 0; j <= this.getNbcolonne()-1 ; j++) {
                Case lc = (Case) this.TrouverCaseXY(j, i);

                if (lc.getPosX() == nbcolonne-1) {

                    System.out.print(lc.getElement().toString() +"\n ");
                } else {
                    System.out.print(lc.getElement().toString() + "\t");
                }



            }
        }

    }

    public ArrayList getlesCases(){
        return this.lesCases;
    }

    public Case TrouverCaseXY(int x, int y) throws Exception {
        if (x < 0 || y < 0){
            throw new Exception("Les entrées x et Y sont invalide");

        }
        for (Case macase : this.lesCases) {
            if (macase.getPosX() == x && macase.getPosY() == y){
                return macase;
            }
        }
        return null;
    }

    public Case TrouverCaseStackPane(StackPane stack){
        for (Case macase : this.lesCases) {
            if (macase.getLeStack() == stack){
                return macase;
            }
        }
        return null;
    }

    public void prochainTour(LabyConfig labyC) throws Exception {
        ArrayList<Case> Claim = new ArrayList<Case>();
        for (Case c : this.lesCases){
            if (! Claim.contains(c)){

                if (c.getElement() instanceof Animal){
                    Animal a = (Animal) c.getElement();

                    if (a instanceof Loup){
                        System.out.println("Loup "+a.getLacase().getPosX() + " et " + a.getLacase().getPosY());
                        Case nextCase = this.TrouverCaseXY(a.getLacase().getPosX(),a.getLacase().getPosY()+1);
                        if (! (a.getLacase().getPosX()+1 >= nbcolonne || a.getLacase().getPosY()+1 >= nbligne)){
                            if (! (nextCase.getElement() instanceof Rocher)){
                                Case old = a.getLacase();
                                a.changeCase(nextCase);
                                nextCase.modifierElementCase(a);
                                new Herbe(old);
                                EditCell(old);
                                EditCell(nextCase);
                                Claim.add(nextCase);

                            }
                        }

                    } else if (a instanceof Mouton){
                        System.out.println("Mouton "+ a.getLacase().getPosX() + " et " + a.getLacase().getPosY());
                        int vitesse = a.getVitesse();
                        Case nextCase = this.TrouverCaseXY(a.getLacase().getPosX()+vitesse,a.getLacase().getPosY());

                        Vegetal laplante = (Vegetal) nextCase.getElement();
                        a.setVitesse(laplante.getCapaciteDeplacement());

                        if (! (a.getLacase().getPosX()+vitesse >= nbcolonne || a.getLacase().getPosY()+1 >= nbligne)){
                            if (! (nextCase.getElement() instanceof Rocher)){
                                if (nextCase.getSortie()){
                                    labyC.sheepWin();
                                }
                                Case old = a.getLacase();
                                a.changeCase(nextCase);
                                nextCase.modifierElementCase(a);

                                Herbe h = new Herbe(old);
                                h.manger();
                                EditCell(old);
                                EditCell(nextCase);
                                Claim.add(nextCase);


                            }
                        }
                    }

                } else if(c.getElement() instanceof Vegetal){
                    Vegetal v = (Vegetal) c.getElement();
                    v.checkRepousse();
                    if (v.estMange){
                        System.out.println(v.getCptRepousse());
                    }
                    EditCell(c);
                }
            }

        }


    }


    public void EditCell(Case lc){
        StackPane stack = lc.getLeStack();


        if (lc.getElement() instanceof Vegetal){

            Vegetal veg = (Vegetal) lc.getElement();
            if (veg.estMange() == false){
                stack.getChildren().remove(1);
                ImageView imageView = new ImageView();
                Image image = new Image(lc.getElement().getImageURL());
                imageView.setImage(image);
                imageView.setFitWidth(45);
                imageView.setFitHeight(45);
                stack.getChildren().addAll(imageView);
            } else {
                try {
                    stack.getChildren().remove(1);
                } catch (Exception e) {
                    System.out.println("Silence ça pousse");
                }
            }
        } else {
            stack.getChildren().remove(1);
            ImageView imageView = new ImageView();
            Image image = new Image(lc.getElement().getImageURL());
            imageView.setImage(image);
            imageView.setFitWidth(45);
            imageView.setFitHeight(45);
            stack.getChildren().addAll(imageView);
        }

    }

    public void saveLaby(String path) {
        String name = path+"/LabyData.txt";

        try {
            FileWriter writer = new FileWriter(name);

            for (int i = 0; i <= this.getNbligne()-1 ; i++) {
                for (int j = 0; j <= this.getNbcolonne()-1 ; j++) {
                    Case lc = (Case) this.TrouverCaseXY(j,i);

                    if (lc.getSortie()){
                        if (lc.getPosX() == nbcolonne-1) {
                            writer.write("s\n");
                        } else {
                            writer.write("s");
                        }
                    } else {
                        if (lc.getPosX() == nbcolonne-1) {
                            writer.write(lc.getElement().toString() +"\n");
                        } else {
                            writer.write(lc.getElement().toString());
                        }
                    }



                }
            }

            writer.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isBordure(Case macase) {


    if (macase.getPosX() == 0) {
        return true;
    } else if (macase.getPosX() == this.nbcolonne - 1) {
        return true;
    } else if (macase.getPosY() == 0) {
        return true;
    } else if (macase.getPosY() == this.nbligne - 1) {
        return true;
    } else {
        return false;
    }
    }

    public boolean isVoisin(Case lacase, ArrayList<Case> lesVoisins){
        for (Case c: lesVoisins){
            if (c == lacase){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Case> getVoisinNord(int vision, Case maCase) throws Exception {
        ArrayList<Case> lesVoisins = new ArrayList<>();
        int i = 1;
        int inc = 1;
        Case v = TrouverCaseXY(maCase.getPosX(), maCase.getPosY()-i);
        if (v.getElement() instanceof Rocher) { return lesVoisins;};
        while (i != vision+1 ) {

            if (! (maCase.getPosY() - inc <0 )){
                System.out.println("cc = " + maCase.getPosX() + maCase.getPosY());


                Case voisin = TrouverCaseXY(maCase.getPosX(), maCase.getPosY()-inc);
                Rectangle r =(Rectangle) voisin.getLeStack().getChildren().get(0);


                System.out.println(voisin.getElement().toString());

                if (!(voisin.getElement() instanceof Rocher)){
                    lesVoisins.add(voisin);
                    System.out.println("Voisin Trouvé");

                    i++;
                    inc++;
                } else {
                    voisin = lesVoisins.get(lesVoisins.size()-1);
                    if (getVoisinEst(1, voisin).size() != 0 && !(isVoisin(getVoisinEst(1, voisin).get(0), lesVoisins))){
                        System.out.println("aled");
                        lesVoisins.add((Case) getVoisinEst(1,lesVoisins.get(lesVoisins.size()-1)).get(0));
                        maCase = lesVoisins.get(lesVoisins.size()-1);

                        i++;
                        inc = 1;

                    }  else if (getVoisinOuest(1, voisin).size() != 0 ){
                        lesVoisins.add((Case) getVoisinOuest(1,lesVoisins.get(lesVoisins.size()-1)).get(0));
                        maCase = lesVoisins.get(lesVoisins.size()-1);

                        i++;
                        inc = 1;


                    } else if (getVoisinOuest(1, voisin).size() == 0 && getVoisinEst(1, voisin).size() == 0){
                        i = vision+1;


                    }
                }

            } else {
                i ++;
            }
        }
        return lesVoisins;
    }

    public ArrayList<Case> getVoisinSud(int vision, Case maCase) throws Exception {
        ArrayList<Case> lesVoisins = new ArrayList<>();
        int i = 1;
        int inc = 1;
        Case v = TrouverCaseXY(maCase.getPosX(), maCase.getPosY()+i);
        if (v.getElement() instanceof Rocher) { return lesVoisins;};
        while (i != vision+1 ) {

            if (! (maCase.getPosY() + inc > nbligne )){


                Case voisin = TrouverCaseXY(maCase.getPosX(), maCase.getPosY()+inc);
                Rectangle r =(Rectangle) voisin.getLeStack().getChildren().get(0);


                System.out.println(voisin.getElement().toString());

                if (!(voisin.getElement() instanceof Rocher)){
                    lesVoisins.add(voisin);
                    System.out.println("Voisin Trouvé");

                    i++;
                    inc++;
                } else {
                    voisin = lesVoisins.get(lesVoisins.size()-1);
                    if (getVoisinEst(1, voisin).size() != 0){
                        lesVoisins.add((Case) getVoisinEst(1,lesVoisins.get(lesVoisins.size()-1)).get(0));
                        maCase = lesVoisins.get(lesVoisins.size()-1);

                        i++;
                        inc = 1;

                    }  else if (getVoisinOuest(1, voisin).size() != 0){
                        lesVoisins.add((Case) getVoisinOuest(1,lesVoisins.get(lesVoisins.size()-1)).get(0));
                        maCase = lesVoisins.get(lesVoisins.size()-1);

                        i++;
                        inc = 1;


                    } else if (getVoisinOuest(1, voisin).size() == 0 && getVoisinEst(1, voisin).size() == 0){
                        i = vision+1;


                    }
                }

            } else {
                i ++;
            }
        }
        return lesVoisins;

    }

    public ArrayList<Case> getVoisinEst(int vision, Case maCase) throws Exception {
        ArrayList<Case> lesVoisins = new ArrayList<>();
        int i = 1;
        int inc = 1;
        Case v = TrouverCaseXY(maCase.getPosX()+i, maCase.getPosY());
        if (v.getElement() instanceof Rocher) { return lesVoisins;};
        while (i != vision+1 ) {

            if (! (maCase.getPosX() + inc > nbcolonne )){


                Case voisin = TrouverCaseXY(maCase.getPosX()+inc, maCase.getPosY());
                Rectangle r =(Rectangle) voisin.getLeStack().getChildren().get(0);


                System.out.println(voisin.getElement().toString());

                if (!(voisin.getElement() instanceof Rocher)){
                    lesVoisins.add(voisin);
                    System.out.println("Voisin Trouvé");

                    i++;
                    inc++;
                } else {
                    voisin = lesVoisins.get(lesVoisins.size()-1);
                    if (getVoisinNord(1, voisin).size() != 0){
                        lesVoisins.add((Case) getVoisinNord(1,lesVoisins.get(lesVoisins.size()-1)).get(0));
                        maCase = lesVoisins.get(lesVoisins.size()-1);
                        inc = 1;
                        i++;
                    }  else if (getVoisinSud(1, voisin).size() != 0){
                        lesVoisins.add((Case) getVoisinSud(1,lesVoisins.get(lesVoisins.size()-1)).get(0));
                        maCase = lesVoisins.get(lesVoisins.size()-1);
                        i++;
                        inc = 1;

                    } else if (getVoisinNord(1, voisin).size() == 0 && getVoisinSud(1, voisin).size() == 0){
                        i = vision+1;
                    }
                }

            } else {
                i ++;
            }
        }
        return lesVoisins;

    }

    public ArrayList<Case> getVoisinOuest(int vision, Case maCase) throws Exception {
        ArrayList<Case> lesVoisins = new ArrayList<>();
        int i = 1;
        int inc = 1;
        Case v = TrouverCaseXY(maCase.getPosX()-i, maCase.getPosY());
        if (v.getElement() instanceof Rocher) { return lesVoisins;};
        while (i != vision+1 ) {

            if (! (maCase.getPosX() + inc > nbcolonne )){


                Case voisin = TrouverCaseXY(maCase.getPosX()-inc, maCase.getPosY());
                Rectangle r =(Rectangle) voisin.getLeStack().getChildren().get(0);


                System.out.println(voisin.getElement().toString());

                if (!(voisin.getElement() instanceof Rocher)){
                    lesVoisins.add(voisin);
                    System.out.println("Voisin Trouvé");

                    i++;
                    inc++;
                } else {
                    voisin = lesVoisins.get(lesVoisins.size()-1);
                    if (getVoisinNord(1, voisin).size() != 0){
                        lesVoisins.add((Case) getVoisinNord(1,lesVoisins.get(lesVoisins.size()-1)).get(0));
                        maCase = lesVoisins.get(lesVoisins.size()-1);
                        inc = 1;
                        i++;
                    }  else if (getVoisinSud(1, voisin).size() != 0){
                        lesVoisins.add((Case) getVoisinSud(1,lesVoisins.get(lesVoisins.size()-1)).get(0));
                        maCase = lesVoisins.get(lesVoisins.size()-1);
                        i++;
                        inc = 1;

                    } else if (getVoisinNord(1, voisin).size() == 0 && getVoisinSud(1, voisin).size() == 0){
                        i = vision+1;
                    }
                }

            } else {
                i ++;
            }
        }
        return lesVoisins;
    }

    public ArrayList<Case> getAllVoisins(Case lc, int vision) throws Exception {
        ArrayList<Case> vNord = getVoisinNord(vision,lc);
        ArrayList<Case> vSud = getVoisinSud(vision,lc);
        ArrayList<Case> vEst = getVoisinEst(vision,lc);
        ArrayList<Case> vOuest = getVoisinOuest(vision,lc);

        ArrayList<Case> vAll = new ArrayList<>();
        for (Case c : vNord){
            vAll.add(c);
        }
        for (Case c : vEst){
            vAll.add(c);
        }
        for (Case c : vOuest){
            vAll.add(c);
        }
        for (Case c : vSud){
            vAll.add(c);
        }

        return vAll;
    }

    public int getNbLoup() {
        int count = 0;
        for (Case c : this.lesCases) {
            if (c.getElement() instanceof Loup){
                count++;
            }
        }
        return count;
    }

    public int getNbMouton() {
        int count = 0;
        for (Case c : this.lesCases) {
            if (c.getElement() instanceof Mouton){
                count++;
            }
        }
        return count;
    }

    public int getNbSortie() {
        int count = 0;
        for (Case c : this.lesCases) {
            if (c.getSortie()){
                count++;
            }
        }
        return count;
    }

}

