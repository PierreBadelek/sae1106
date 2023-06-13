package sae;


import javafx.application.Application;
import javafx.scene.control.Spinner;
import javafx.stage.Stage;
import sae.model.Labyrinthe;
import sae.view.LabyConfig;
import sae.view.WelcomeScreen;

public class Display extends Application {
    WelcomeScreen welcs;
    @Override
    public void start(Stage stage) {
        this.welcs = new WelcomeScreen(this);
    }

    public void returnToWelcs(){
        welcs.show();
    }

    public void openConfig(Spinner nbligne, Spinner nbcolonne) throws Exception {
        Labyrinthe l = new Labyrinthe((Integer) nbligne.getValue(), (Integer) nbcolonne.getValue());
        LabyConfig lc = new LabyConfig(l,this);
    }

    public void LoadConfig(String path) throws Exception {
        Labyrinthe l = new Labyrinthe(path);

        LabyConfig lc = new LabyConfig(l,this);
    }

    public static void main(String[] args) {
        launch();
    }
}
