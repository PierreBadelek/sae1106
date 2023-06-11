package sae.view;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class EventWelcomeScreen implements EventHandler{
    private sae.Display dp;
    private WelcomeScreen welcomescreen;
    public EventWelcomeScreen(sae.Display d, WelcomeScreen wc){
        this.dp = d;
        this.welcomescreen = wc;
    }


    @Override
    public void handle(Event event) {

        if (event.getSource() instanceof Button){
            Button b = (Button) event.getSource();
            if (event.getSource().toString().contains("Créer la partie")) {
                welcomescreen.fermerWelcome();
                try {
                    dp.openConfig(welcomescreen.getligneSpin(), welcomescreen.getcoloSpin());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else if (event.getSource().toString().contains("Charger la partie")){
                FileChooser fileChooser = new FileChooser();
                File fichier = fileChooser.showOpenDialog(new Stage());
                try {
                    dp.LoadConfig(fichier.getAbsolutePath());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }


            }
        }



    }
}
