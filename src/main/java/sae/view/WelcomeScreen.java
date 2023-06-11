package sae.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sae.Display;

public class WelcomeScreen extends Stage {
    private Display display;
    private Spinner<Integer> coloSpin;
    private Spinner<Integer> ligneSpin;

    public WelcomeScreen(Display dp) {
        this.display = dp;
        this.coloSpin = new Spinner();
        this.ligneSpin = new Spinner();
        this.init();
    }

    public void init() {
        EventWelcomeScreen lesevent = new EventWelcomeScreen(this.display, this);

        /* Création des textes et des polices */
        Font fontTitle = new Font("montserrat", 80);
        Font fontLiCo = new Font("montserrat", 20);
        Font fontSubTitle = new Font("montserrat", 25);
        Font fontDimLab = new Font("montserrat", 20);
        Text textTitle = new Text(50, 50, "BIENVENUE");
        Text textColo = new Text("Nombre de colonnes  ");
        Text textLigne = new Text("Nombre de lignes  ");
        Text textSubTitle = new Text("Pour commencer à jouer, \n paramétrez votre partie.\n");
        Text textDimLab = new Text("Dimensions du labyrinthe :");
        Text textName = new Text("Pierre - Lilou - Riwan - William");
        Text textPassLigne = new Text("\n");

        /* Styles des textes */
        textDimLab.setStyle("-fx-font-weight: bold;");
        textTitle.setStyle("-fx-font-weight: bold;");
        textLigne.setStyle("-fx-font-weight: bold;");
        textColo.setStyle("-fx-font-weight: bold;");
        textTitle.setFont(fontTitle);
        textColo.setFont(fontLiCo);
        textLigne.setFont(fontLiCo);
        textSubTitle.setFont(fontSubTitle);
        textDimLab.setFont(fontDimLab);
        textName.setFont(fontLiCo);
        textDimLab.setUnderline(true);

        /* Boutons */
        Button CreateGame = new Button("Créer la partie");
        Button LoadGame = new Button("Charger la partie");
        CreateGame.setFont(fontDimLab);
        LoadGame.setFont(fontDimLab);


        /* Events */
        CreateGame.setOnMouseClicked(lesevent);
        LoadGame.setOnMouseClicked(lesevent);

        /* Configuration des spinners */
        SpinnerValueFactory<Integer> ValueFactoryLigne = new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 20, 10);
        SpinnerValueFactory<Integer> ValueFactoryColo = new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 20, 10);
        this.coloSpin.setEditable(true);
        this.ligneSpin.setEditable(true);
        this.coloSpin.setValueFactory(ValueFactoryColo);
        this.ligneSpin.setValueFactory(ValueFactoryLigne);

        /* Containers */
        VBox vboxMain = new VBox();
        VBox vboxTitle = new VBox();
        VBox vboxCoLi = new VBox();
        HBox hboxColo = new HBox();
        HBox hboxLigne = new HBox();
        VBox vboxButton = new VBox(10);
        Insets is50 = new Insets(50);

        /* Styles des containers */
        vboxMain.setPadding(is50);
        vboxTitle.setAlignment(Pos.CENTER);
        vboxCoLi.setAlignment(Pos.CENTER);
        hboxColo.setAlignment(Pos.CENTER);
        hboxLigne.setAlignment(Pos.CENTER);
        vboxButton.setAlignment(Pos.CENTER);

        /* Ajout des éléments aux containers */
        vboxTitle.getChildren().setAll(new Node[]{textTitle, textSubTitle});
        hboxColo.getChildren().setAll(new Node[]{textColo, this.coloSpin});
        hboxLigne.getChildren().setAll(new Node[]{textLigne, this.ligneSpin});
        vboxCoLi.getChildren().setAll(new Node[]{hboxColo, hboxLigne});
        vboxButton.getChildren().setAll(new Node[]{CreateGame, LoadGame, textName});
        vboxMain.getChildren().setAll(new Node[]{vboxTitle, textDimLab, vboxCoLi, textPassLigne, vboxButton});

        /* Configurations globales */
        Scene scene = new Scene(vboxMain, 600, 600);
        vboxMain.setStyle("-fx-background-color: #D6D6D6;");
        this.setTitle("SAE 201 - Mange moi si tu peux !");
        this.setScene(scene);
        this.show();
    }

    public Spinner<Integer> getcoloSpin() {
        return this.coloSpin;
    }

    public Spinner<Integer> getligneSpin() {
        return this.ligneSpin;
    }

    public void fermerWelcome() {
        this.close();
    }
}