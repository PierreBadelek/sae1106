package sae.view;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sae.model.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Stack;

public class EventLabyConfig implements EventHandler{
    private sae.Display dp;

    private LabyConfig labyC;
    private Labyrinthe laby;
    public EventLabyConfig(sae.Display d, LabyConfig lC, Labyrinthe lab){
        this.dp = d;
        this.labyC = lC;
        this.laby = lab;

    }


    @Override
    public void handle(Event event) {
        if (event.getSource() instanceof Button){
            Button b = (Button) event.getSource();
            if (event.getSource().toString().contains("Lancer le jeu")) {
                this.labyC.LaunchGame();

            }else if (event.getSource().toString().contains("Configuration Suivante")) {
                this.labyC.initAnimalConfig();
            } else if (event.getSource().toString().contains("Sauvegarder")){
                try {
                    laby.afficherLab();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                DirectoryChooser dirChooser = new DirectoryChooser();
                File fichier = dirChooser.showDialog(new Stage());
                this.laby.saveLaby(fichier.getAbsolutePath());
            }else if (event.getSource().toString().contains("Retour sur la carte")){
                this.labyC.initMapConf();
            } else if (event.getSource().toString().contains("Tour suivant")){
                try {
                    laby.prochainTour();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

        }
        if (event.getSource() instanceof StackPane) {

            if (labyC.isAnimalConfig() == false && labyC.isVegetalConfig() == true){
                cellChangeTypeVegetal(event);

            } else if (labyC.isAnimalConfig() == true && labyC.isVegetalConfig() == false){
                cellChangeTypeAnimal(event);
            } else {

                StackPane stack = (StackPane) event.getSource();
                Case lc = laby.TrouverCaseStackPane(stack);
                try {
                    ArrayList<Case> voisins = laby.getVoisinNord(5,lc);
                    for (Case c : voisins){
                        System.out.println(c.getPosX() +" " + c.getPosY());
                        StackPane s = c.getLeStack();
                        for (Node n : s.getChildren()){
                            if (n instanceof Rectangle){
                                System.out.println("c");
                                ((Rectangle) n).setFill(Color.LIGHTBLUE);
                            }
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void cellChangeTypeVegetal(Event event){
        StackPane stack = (StackPane) event.getSource();
        Case lc = laby.TrouverCaseStackPane(stack);
        if (! laby.isBordure(lc)){
            if (lc.getElement() instanceof Rocher){
                Marguerite m = new Marguerite(lc);
            } else if (lc.getElement() instanceof Herbe){
                Rocher ro = new Rocher(lc);
            } else if (lc.getElement() instanceof Marguerite){
                Cactus c = new Cactus(lc);
            } else if (lc.getElement() instanceof Cactus){
                Herbe h = new Herbe(lc);
            } else {
                Herbe h = new Herbe(lc);
            }
        } else {
            lc.setSortie(true);
            if (lc.getElement() instanceof Rocher){
                Marguerite m = new Marguerite(lc);
            } else if (lc.getElement() instanceof Herbe){
                Rocher ro = new Rocher(lc);
            } else if (lc.getElement() instanceof Marguerite){
                Cactus c = new Cactus(lc);
            } else if (lc.getElement() instanceof Cactus){
                Herbe h = new Herbe(lc);
            } else {
                Herbe h = new Herbe(lc);
            }
        }

        labyC.EditCell(lc);
    }

    public void cellChangeTypeAnimal(Event event){
        StackPane stack = (StackPane) event.getSource();
        Case lc = laby.TrouverCaseStackPane(stack);
        if (lc.getElement() instanceof Vegetal){
            Loup l = new Loup(lc);
        } else if (lc.getElement() instanceof Loup){
            Mouton m = new Mouton(lc);
        } else if (lc.getElement() instanceof Mouton){
            cellChangeTypeVegetal(event);
        }
        labyC.EditCell(lc);
    }
}