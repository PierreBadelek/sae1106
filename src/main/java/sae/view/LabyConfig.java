package sae.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sae.model.*;
import java.util.ArrayList;

public class LabyConfig extends Stage {
    private Labyrinthe laby;
    private sae.Display display;
    private GridPane leLabyAff;
    private EventLabyConfig EventMain;
    private Font fontTitle;
    private boolean VegetalConfig = false;
    private boolean AnimalConfig = false;
    public LabyConfig(Labyrinthe lab, sae.Display dp) throws Exception {
        super();
        this.laby = lab;
        this.display = dp;
        this.leLabyAff = new GridPane();
        this.EventMain = new EventLabyConfig(this.display,this,this.laby);
        this.fontTitle = new Font("montserrat",50);
        AfficherLeLab();
        initMapConf();
    }

    public void initMapConf() {
        this.VegetalConfig = true;
        this.AnimalConfig = false;

        /* Styles des textes */
        Label title = new Label("Configuration de la carte");
        title.setStyle("-fx-font-weight: bold;");
        title.setFont(this.fontTitle);

        Font fontText = new Font("montserrat", 20);
        Font fontSubTitle = new Font("montserrat", 25);
        Text textSubTitle = new Text("Placez les différents éléments sur la carte");
        textSubTitle.setFont(fontSubTitle);

        Case CaseAff = new Case(800,800);

        ArrayList<ElementCase> EleAffichage = new ArrayList<>();
        Rocher rocherAff = new Rocher(CaseAff);
        Marguerite MargAff = new Marguerite(CaseAff);
        Cactus CactusAff = new Cactus(CaseAff);
        Herbe herbeAff = new Herbe(CaseAff);

        EleAffichage.add(rocherAff);
        EleAffichage.add(MargAff);
        EleAffichage.add(CactusAff);
        EleAffichage.add(herbeAff);

        StackPane stackConfig = new StackPane();
        Insets is50 = new Insets(50.0);
        VBox vboxconf = new VBox();
        vboxconf.setStyle("-fx-border-width: 2px;");
        vboxconf.setPadding(new Insets(50.0, 0.0, 0.0, 0.0));
        vboxconf.setSpacing(10);

        /* Icônes avec textes */
        for (ElementCase ele : EleAffichage){
            ImageView imageView = new ImageView();
            Image image = new Image(ele.getImageURL());
            String txt = "";

            if (ele instanceof Rocher) {
                txt = "Rocher";
            }

            if (ele instanceof Herbe) {
                txt = "Herbe";
            }

            if (ele instanceof Marguerite) {
                txt = "Marguerite";
            }

            if (ele instanceof Cactus) {
                txt = "Cactus";
            }
            Text letxt = new Text(txt);
            letxt.setFont(fontText);

            HBox hboximgtxt = new HBox(10);
            hboximgtxt.getChildren().addAll(imageView, letxt);
            hboximgtxt.setAlignment(Pos.CENTER);

            imageView.setImage(image);
            imageView.setFitWidth(45.0);
            imageView.setFitHeight(45.0);
            vboxconf.getChildren().add(hboximgtxt);
        }

        stackConfig.getChildren().addAll(vboxconf);

        /* Boutons */
        Font fontDimLab = new Font("montserrat", 20);
        Button gameButton = new Button("Configuration Suivante");
        gameButton.setFont(fontDimLab);
        gameButton.setStyle("-fx-text-fill: black; -fx-font-size: 20px; -fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
        gameButton.setOnMouseClicked(this.EventMain);

        Button backConfig = new Button("Menu principal");
        backConfig.setOnMouseClicked(this.EventMain);
        backConfig.setFont(fontDimLab);
        backConfig.setStyle("-fx-text-fill: black; -fx-font-size: 20px; -fx-focus-color: transparent; -fx-faint-focus-color: transparent;");

        HBox hboxButton = new HBox(20);
        hboxButton.getChildren().addAll(gameButton, backConfig);
        hboxButton.setAlignment(Pos.CENTER);

        HBox hboxMain = new HBox(50);
        hboxMain.getChildren().addAll(this.leLabyAff, stackConfig);
        VBox vboxMain = new VBox();
        vboxMain.setPadding(is50);
        vboxMain.getChildren().addAll(title, textSubTitle, hboxMain, hboxButton);
        vboxMain.setStyle("-fx-background-color: #D6D6D6;");
        vboxMain.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vboxMain, (double)(700 + this.laby.getNbcolonne() * 50), (double)(500 + this.laby.getNbligne() * 30));
        this.setTitle("SAE 201.2 - Mange moi si tu peux !");
        this.setScene(scene);
        this.show();
    }

    public void initAnimalConfig(){
        this.VegetalConfig = false;
        this.AnimalConfig = true;

        /* Styles des textes */
        Label title = new Label("Configuration des animaux");
        title.setStyle("-fx-font-weight: bold;");
        title.setFont(fontTitle);

        Font fontText = new Font("montserrat", 20);
        Font fontSubTitle = new Font("montserrat", 25);
        Text textSubTitle = new Text("Placez un loup et un mouton");
        textSubTitle.setFont(fontSubTitle);

        HBox hboxMain = new HBox();

        Case CaseAff = new Case(200,200);

        ArrayList<ElementCase> EleAffichage = new ArrayList<>();
        Loup loupAff = new Loup(CaseAff);
        Mouton moutonAff = new Mouton(CaseAff);

        EleAffichage.add(loupAff);
        EleAffichage.add(moutonAff);

        StackPane stackConfig = new StackPane();

        VBox vboxconf = new VBox();
        vboxconf.setStyle("-fx-border-width: 2px;");
        vboxconf.setPadding(new Insets(50.0, 0.0, 0.0, 0.0));
        vboxconf.setSpacing(10);

        /* Icônes avec textes */
        for (ElementCase ele : EleAffichage){
            ImageView imageView = new ImageView();
            Image image = new Image(ele.getImageURL());
            String txt = "";

            if (ele instanceof Loup) {
                txt = "Loup";
            }

            if (ele instanceof Mouton) {
                txt = "Mouton";
            }

            Text letxt = new Text(txt);
            letxt.setFont(fontText);

            HBox hboximgtxt = new HBox(10);
            hboximgtxt.getChildren().addAll(imageView, letxt);
            hboximgtxt.setAlignment(Pos.CENTER);

            imageView.setImage(image);
            imageView.setFitWidth(45.0);
            imageView.setFitHeight(45.0);
            vboxconf.getChildren().add(hboximgtxt);
        }

        stackConfig.getChildren().addAll(vboxconf);

        /* Boutons */
        Font fontDimLab = new Font("montserrat", 20);
        Button gameButton = new Button("Lancer le jeu");
        gameButton.setOnMouseClicked(EventMain);
        gameButton.setFont(fontDimLab);
        gameButton.setStyle("-fx-text-fill: black; -fx-font-size: 20px; -fx-focus-color: transparent; -fx-faint-focus-color: transparent;");

        Button saveButton = new Button("Sauvegarder");
        saveButton.setOnMouseClicked(EventMain);
        saveButton.setFont(fontDimLab);
        saveButton.setStyle("-fx-text-fill: black; -fx-font-size: 20px; -fx-focus-color: transparent; -fx-faint-focus-color: transparent;");

        Button goBackButton = new Button("Retour sur la carte");
        goBackButton.setOnMouseClicked(EventMain);
        goBackButton.setFont(fontDimLab);
        goBackButton.setStyle("-fx-text-fill: black; -fx-font-size: 20px; -fx-focus-color: transparent; -fx-faint-focus-color: transparent;");

        HBox hboxButton = new HBox(20);
        hboxButton.getChildren().addAll(saveButton, gameButton,goBackButton);
        hboxButton.setAlignment(Pos.CENTER);

        hboxMain.getChildren().addAll(this.leLabyAff,stackConfig);
        VBox vboxMain = new VBox(10);
        vboxMain.setPadding(new Insets(50));
        vboxMain.setStyle("-fx-background-color: #D6D6D6;");
        vboxMain.setAlignment(Pos.CENTER);
        vboxMain.getChildren().addAll(title,textSubTitle,hboxMain,hboxButton);

        Scene sceneGame = new Scene(vboxMain,700+laby.getNbcolonne()*50, 500+laby.getNbligne()*30);
        this.setScene(sceneGame);
    }
    public void AfficherLeLab() throws Exception {
        this.leLabyAff.setPadding(new Insets(20));
        for (int i = 0; i <= this.laby.getNbcolonne() - 1; i++) {
            for (int j = 0; j <= this.laby.getNbligne() - 1; j++) {

                Rectangle rect = new Rectangle(50.0,50.0);
                rect.setFill(Color.TRANSPARENT);
                rect.setStroke(Color.BLACK);
                StackPane stack = new StackPane();
                Case lc = (Case) laby.TrouverCaseXY(i,j);

                ImageView imageView = new ImageView();

                Image image = new Image(lc.getElement().getImageURL());
                imageView.setImage(image);
                imageView.setFitWidth(45);
                imageView.setFitHeight(45);
                stack.getChildren().addAll(rect, imageView);

                this.leLabyAff.add(stack,i,j);

                stack.setOnMouseClicked(this.EventMain);
                lc.setLeStack(stack);
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

    public void LaunchGame() {
        this.VegetalConfig = false;
        this.AnimalConfig = false;

        /* Textes */
        Label title = new Label("Partie lancée !");
        title.setStyle("-fx-font-weight: bold;");
        title.setFont(fontTitle);
        title.setPadding(new Insets(0,0,30,0));

        /* Boutons */
        Button nextTour = new Button("Tour suivant ->");
        nextTour.setFont(new Font("monserrat",20));
        nextTour.setStyle("-fx-text-fill: black; -fx-font-size: 20px; -fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
        nextTour.setOnMouseClicked(this.EventMain);

        HBox hboxMain = new HBox();
        hboxMain.getChildren().addAll(this.leLabyAff);
        VBox vboxMain = new VBox();
        vboxMain.setPadding(new Insets(50));
        vboxMain.getChildren().addAll(title,hboxMain,nextTour);
        vboxMain.setAlignment(Pos.TOP_CENTER);
        hboxMain.setAlignment(Pos.CENTER);
        vboxMain.setStyle("-fx-background-color: #D6D6D6;");

        Scene sceneGame = new Scene(vboxMain,700+laby.getNbcolonne()*40, 500+laby.getNbligne()*30);
        this.setScene(sceneGame);
    }

    public void sheepWin(){
        Alert sheepWin = new Alert(Alert.AlertType.NONE);

        sheepWin.setTitle("Fin de partie");
        sheepWin.setHeaderText("Le mouton a gagné !");
        sheepWin.setContentText("Le loup n'a pas réussi à manger le mouton.");

        Image sheep = new Image("/mouton.png");
        ImageView imageSheep = new ImageView(sheep);
        DialogPane dialogPane = sheepWin.getDialogPane();
        dialogPane.setGraphic(imageSheep);

        /* HBox Statistiques */
        HBox hboxStats = new HBox(5);
        Text text1 = new Text("Statistiques : ");

        Image cactus = new Image("/cactus.png");
        ImageView imageCactus = new ImageView(cactus);
        Text text3 = new Text(":  X");
        imageCactus.setFitWidth(20);
        imageCactus.setFitHeight(20);

        Image herb = new Image("/herbe.png");
        ImageView imageHerb = new ImageView(herb);
        Text text4 = new Text(":  X");
        imageHerb.setFitWidth(20);
        imageHerb.setFitHeight(20);

        Image marguerite = new Image("/fleur.png");
        ImageView imageMarguerite = new ImageView(marguerite);
        Text text5 = new Text(":  X");
        imageMarguerite.setFitWidth(20);
        imageMarguerite.setFitHeight(20);

        hboxStats.getChildren().addAll(text1, imageCactus, text3, imageMarguerite, text4, imageHerb, text5);

        /* HBox Tours */
        HBox hboxTours = new HBox(5);
        Text text2 = new Text("Nombre de tours : ");
        Image clock = new Image("/clock.png");
        ImageView imageClock = new ImageView(clock);
        Text text6 = new Text(" X");
        hboxTours.getChildren().addAll(imageClock,text2,text6);

        /* VBox avec les deux HBox précédentes */
        VBox finalInfos = new VBox(20);
        finalInfos.getChildren().addAll(hboxStats,hboxTours);

        /* Affichage */
        dialogPane.setExpandableContent(finalInfos);
        sheepWin.initModality(Modality.APPLICATION_MODAL);
        addCloseEventHandler(sheepWin);
        sheepWin.showAndWait();
    }

    public void wolfWin(){
        Alert wolfWin = new Alert(Alert.AlertType.NONE);

        wolfWin.setTitle("Fin de partie");
        wolfWin.setHeaderText("Le loup a gagné !");
        wolfWin.setContentText("Le mouton a été mangé par le loup.");

        Image wolf = new Image("/loup.png");
        ImageView imageView = new ImageView(wolf);
        DialogPane dialogPane = wolfWin.getDialogPane();
        dialogPane.setGraphic(imageView);

        /* HBox Statistiques */
        HBox hboxStats = new HBox(5);
        Text text1 = new Text("Statistiques : ");
        Image sheep = new Image("/mouton.png");
        ImageView imageSheep = new ImageView(sheep);
        imageSheep.setFitWidth(20);
        imageSheep.setFitHeight(20);

        Image cactus = new Image("/cactus.png");
        ImageView imageCactus = new ImageView(cactus);
        Text text3 = new Text(":  X");
        imageCactus.setFitWidth(20);
        imageCactus.setFitHeight(20);

        Image herb = new Image("/herbe.png");
        ImageView imageHerb = new ImageView(herb);
        Text text4 = new Text(":  X");
        imageHerb.setFitWidth(20);
        imageHerb.setFitHeight(20);

        Image marguerite = new Image("/fleur.png");
        ImageView imageMarguerite = new ImageView(marguerite);
        Text text5 = new Text(":  X");
        imageMarguerite.setFitWidth(20);
        imageMarguerite.setFitHeight(20);

        hboxStats.getChildren().addAll(imageSheep, text1, imageCactus, text3, imageMarguerite, text4, imageHerb, text5);

        /* HBox Tours */
        HBox hboxTours = new HBox(5);
        Text text2 = new Text("Nombre de tours : ");
        Image clock = new Image("/clock.png");
        ImageView imageClock = new ImageView(clock);
        Text text6 = new Text(" X");
        hboxTours.getChildren().addAll(imageClock,text2,text6);

        /* VBox avec les deux HBox précédentes */
        VBox finalInfos = new VBox(20);
        finalInfos.getChildren().addAll(hboxStats,hboxTours);

        /* Affichage */
        dialogPane.setExpandableContent(finalInfos);
        wolfWin.initModality(Modality.APPLICATION_MODAL);
        addCloseEventHandler(wolfWin);
        wolfWin.showAndWait();
    }

    /* Event pour la fermeture des alertes de fin de partie */
    public void addCloseEventHandler(Alert alert) {
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getScene().getWindow().setOnCloseRequest(event -> {
            alert.hide();
        });
    }

    public boolean isAnimalConfig() {
        return AnimalConfig;
    }

    public boolean isVegetalConfig() {
        return VegetalConfig;
    }
}