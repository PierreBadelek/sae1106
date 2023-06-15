package sae.view;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
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

                if (laby.getNbLoup() != 1){
                    Alert warn=new Alert(Alert.AlertType.WARNING);
                    warn.setContentText("Le nombre de Loup est incorrect, il doit y en avoir 1");
                    warn.show();

                } if (laby.getNbMouton() != 1){
                    Alert warn=new Alert(Alert.AlertType.WARNING);
                    warn.setContentText("Le nombre de Mouton est incorrect, il doit y en avoir 1");
                    warn.show();

                }if (laby.getNbSortie() != 1) {
                    Alert warn = new Alert(Alert.AlertType.WARNING);
                    warn.setContentText("Le nombre de Sortie est incorrect, il doit y en avoir 1");
                    warn.show();
                }
                if (laby.getNbMouton() == 1 && laby.getNbSortie() == 1 && laby.getNbLoup() == 1) {
                    this.labyC.LaunchGame();
                }

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
                    laby.prochainTour(this.labyC);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else if (event.getSource().toString().contains("Menu principal")) {
                labyC.close();
                dp.returnToWelcs();
            } else if (event.getSource().toString().contains("Parcours en profondeur")) {
                try {
                    ArrayList<Case> Chemin = laby.Parcourir(laby.getMouton(), laby.getlaSortie());
                    for (Case c: Chemin){
                        StackPane lestack = c.getLeStack();
                        for (Node n: lestack.getChildren()){
                            if (n instanceof Rectangle){
                                ((Rectangle) n).setFill(Color.LIGHTPINK);
                            }
                        }
                    }
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