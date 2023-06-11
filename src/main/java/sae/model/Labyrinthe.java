package sae.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
                        System.out.println("Lettre 'x' à la position [" + row + ", " + column + "]");
                        Rocher r = new Rocher(macase);
                        macase.ajouterElementCase(r);
                        this.lesCases.add(macase);

                    } else if (letter == 'f') {
                        System.out.println("Lettre 'f' à la position [" + row + ", " + column + "]");
                        Marguerite m = new Marguerite(macase);
                        macase.ajouterElementCase(m);
                        this.lesCases.add(macase);

                    } else if (letter == 'h') {
                        System.out.println("Lettre 'h' à la position [" + row + ", " + column + "]");
                        Herbe h = new Herbe(macase);
                        macase.ajouterElementCase(h);
                        this.lesCases.add(macase);

                    } else if (letter == 'c') {
                        System.out.println("Lettre 'c' à la position [" + row + ", " + column + "]");
                        Cactus c = new Cactus(macase);
                        macase.ajouterElementCase(c);
                        this.lesCases.add(macase);


                    } else if (letter == 'm') {
                        System.out.println("Lettre 'h' à la position [" + row + ", " + column + "]");
                        Mouton h = new Mouton(macase);
                        macase.ajouterElementCase(h);
                        this.lesCases.add(macase);

                    }
                    else if (letter == 'l') {
                        System.out.println("Lettre 'h' à la position [" + row + ", " + column + "]");
                        Loup h = new Loup(macase);
                        macase.ajouterElementCase(h);
                        this.lesCases.add(macase);

                    }

                    if (letter == 's') {
                        System.out.println("Lettre 's' trouvée à la position [" + row + ", " + column + "]");
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
                System.out.println("coordonée x y = "+ i + j );

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
        System.out.println("La taille du laby = "+ this.lesCases.size());

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

    public void prochainTour() throws Exception {
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
                    }
                }
            }

        }


    }


    public void EditCell(Case lc){
        StackPane stack = lc.getLeStack();
        stack.getChildren().remove(1);

        Rectangle rect = new Rectangle(50.0,50.0);
        rect.setFill(Color.TRANSPARENT);
        rect.setStroke(Color.BLACK);

        ImageView imageView = new ImageView();
        Image image = new Image(lc.getElement().getImageURL());
        imageView.setImage(image);
        imageView.setFitWidth(45);
        imageView.setFitHeight(45);
        stack.getChildren().addAll(imageView);


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

    public ArrayList<Case> getVoisinNord(int vision, Case maCase) throws Exception {
        ArrayList<Case> lesVoisins = new ArrayList<>();
        int i = 1;
        while (i != vision+1 ) {
            System.out.println("???55?");
            if (! (maCase.getPosX() - i <0)){
                Case voisin = TrouverCaseXY(maCase.getPosX(), maCase.getPosY()-i);
                Rectangle r =(Rectangle) voisin.getLeStack().getChildren().get(0);
                r.setFill(Color.LIGHTBLUE);


                System.out.println(voisin.getElement().toString());

                if (!(voisin.getElement() instanceof Rocher)){
                    lesVoisins.add(voisin);
                    System.out.println("Voisin Trouvé");

                    i++;
                } else {
                    if (getVoisinEst(1, maCase).size() != 0){
                       lesVoisins.add((Case) getVoisinEst(1,voisin).get(0));
                        i++;
                    }  else if (getVoisinOuest(1, maCase).size() != 0){
                        lesVoisins.add((Case) getVoisinOuest(1,voisin).get(0));
                        i++;
                    } else if (getVoisinOuest(1, voisin).size() == 0 && getVoisinEst(1, voisin).size() == 0){
                        i ++;
                    }
                }

            }
        }
        return lesVoisins;
    }

    public ArrayList<Case> getVoisinSud(int vision, Case maCase) throws Exception {
        ArrayList<Case> lesVoisins = new ArrayList<>();
        int i = 1;
        while (i != vision+1 ) {
            System.out.println("????");
            if (! (maCase.getPosX() - i <0)){
                Case voisin = TrouverCaseXY(maCase.getPosX(), maCase.getPosY()+i);
                Rectangle r =(Rectangle) voisin.getLeStack().getChildren().get(0);
                r.setFill(Color.LIGHTBLUE);


                System.out.println(voisin.getElement().toString());

                if (!(voisin.getElement() instanceof Rocher)){
                    lesVoisins.add(voisin);
                    System.out.println("Voisin Trouvé");

                    i++;
                } else {
                    if (getVoisinEst(1, voisin).size() != 0){
                        lesVoisins.add((Case) getVoisinEst(1,voisin).get(0));
                        i++;
                    }  else if (getVoisinOuest(1, voisin).size() != 0){
                        lesVoisins.add((Case) getVoisinOuest(1,voisin).get(0));
                        i++;
                    } else if (getVoisinOuest(1, voisin).size() == 0 && getVoisinEst(1, voisin).size() == 0){
                        i = vision+1;
                    }
                }

            }
        }
        return lesVoisins;
    }

    public ArrayList<Case> getVoisinEst(int vision, Case maCase) throws Exception {
        ArrayList<Case> lesVoisins = new ArrayList<>();
        int i = 1;
        while (i != vision+1 ) {
            System.out.println("????");
            if (! (maCase.getPosX() - i <0)){
                Case voisin = TrouverCaseXY(maCase.getPosX()+i, maCase.getPosY());
                Rectangle r =(Rectangle) voisin.getLeStack().getChildren().get(0);
                r.setFill(Color.LIGHTBLUE);


                System.out.println(voisin.getElement().toString());

                if (!(voisin.getElement() instanceof Rocher)){
                    lesVoisins.add(voisin);
                    System.out.println("Voisin Trouvé");

                    i++;
                } else {
                    if (getVoisinNord(1, voisin).size() != 0){
                        lesVoisins.add((Case) getVoisinNord(1,voisin).get(0));
                        i++;
                    }  else if (getVoisinSud(1, voisin).size() != 0){
                        lesVoisins.add((Case) getVoisinSud(1,voisin).get(0));
                        i++;
                    } else if (getVoisinSud(1, voisin).size() == 0 && getVoisinNord(1, voisin).size() == 0){
                        i = vision+1;
                    }
                }

            }
        }
        return lesVoisins;
    }

    public ArrayList<Case> getVoisinOuest(int vision, Case maCase) throws Exception {
        ArrayList<Case> lesVoisins = new ArrayList<>();
        int i = 1;
        while (i != vision+1 ) {
            System.out.println("????");
            if (! (maCase.getPosX() - i <0)){
                Case voisin = TrouverCaseXY(maCase.getPosX()-i, maCase.getPosY());
                Rectangle r =(Rectangle) voisin.getLeStack().getChildren().get(0);
                r.setFill(Color.LIGHTBLUE);


                System.out.println(voisin.getElement().toString());

                if (!(voisin.getElement() instanceof Rocher)){
                    lesVoisins.add(voisin);
                    System.out.println("Voisin Trouvé");

                    i++;
                } else {
                    if (getVoisinNord(1, voisin).size() != 0){
                        lesVoisins.add((Case) getVoisinNord(1,voisin).get(0));
                        i++;
                    }  else if (getVoisinSud(1, voisin).size() != 0){
                        lesVoisins.add((Case) getVoisinSud(1,voisin).get(0));
                        i++;
                    } else if (getVoisinSud(1, voisin).size() == 0 && getVoisinNord(1, voisin).size() == 0){
                        i = vision+1;
                    }
                }

            }
        }
        return lesVoisins;
    }


}

