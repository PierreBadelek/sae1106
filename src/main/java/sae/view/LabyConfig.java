package sae.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;

import javafx.scene.text.Text;
import javafx.stage.Stage;
import sae.model.*;

import java.nio.file.attribute.FileStoreAttributeView;
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

        Label title = new Label("Configuration de la carte");
        title.setStyle("-fx-font-weight: bold;");
        title.setFont(fontTitle);


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

        Rectangle rect = new Rectangle(300.0,500.0);
        rect.setFill(Color.TRANSPARENT);
        rect.setStroke(Color.BLACK);

        Insets is50 = new Insets( 50);

        VBox vboxconf = new VBox();
        vboxconf.setPadding(new Insets(120,0,0,50));
        vboxconf.setSpacing(25);

        for (ElementCase ele : EleAffichage){
            ImageView imageView = new ImageView();
            Image image = new Image(ele.getImageURL());
            imageView.setImage(image);
            imageView.setFitWidth(45);
            imageView.setFitHeight(45);
            vboxconf.getChildren().add(imageView);

        }


        stackConfig.getChildren().addAll(rect,vboxconf);

        Button gameButton = new Button("Configuration Suivante");
        gameButton.setOnMouseClicked(EventMain);

        // hbox vbox
        HBox hboxMain = new HBox();
        hboxMain.getChildren().addAll(this.leLabyAff,stackConfig);

        VBox vboxMain = new VBox();


        vboxMain.setPadding(is50);



        //vboxMain.getChildren().setAll(vboxTitle,textDimLab,vboxCoLi,textPassLigne,vboxButton);

        vboxMain.getChildren().addAll(title,hboxMain,gameButton);
        Scene scene = new Scene(vboxMain,700+laby.getNbcolonne()*50, 500+laby.getNbligne()*30);

        this.setTitle("SAE 201.2 - Mange moi si tu peux !");
        this.setScene(scene);
        this.show();
    }

    public void initAnimalConfig(){
        this.VegetalConfig = false;
        this.AnimalConfig = true;
        Label title = new Label("Configuration des animaux");
        title.setStyle("-fx-font-weight: bold;");
        title.setFont(fontTitle);

        HBox hboxMain = new HBox();


        Case CaseAff = new Case(800,800);
        ArrayList<ElementCase> EleAffichage = new ArrayList<>();
        Loup loupAff = new Loup(CaseAff);
        Mouton moutonAff = new Mouton(CaseAff);
        EleAffichage.add(loupAff);
        EleAffichage.add(moutonAff);
        StackPane stackConfig = new StackPane();
        Rectangle rect = new Rectangle(300.0,500.0);
        rect.setFill(Color.TRANSPARENT);
        rect.setStroke(Color.BLACK);
        VBox vboxconf = new VBox();
        vboxconf.setPadding(new Insets(120,0,0,50));
        vboxconf.setSpacing(25);

        for (ElementCase ele : EleAffichage){
            ImageView imageView = new ImageView();
            Image image = new Image(ele.getImageURL());
            imageView.setImage(image);
            imageView.setFitWidth(45);
            imageView.setFitHeight(45);
            vboxconf.getChildren().add(imageView);

        }
        stackConfig.getChildren().addAll(rect,vboxconf);

        Button gameButton = new Button("Lancer le jeu");
        gameButton.setOnMouseClicked(EventMain);
        Button saveButton = new Button("Sauvegarder");
        saveButton.setOnMouseClicked(EventMain);
        Button goBackButton = new Button("Retour sur la carte");
        goBackButton.setOnMouseClicked(EventMain);

        hboxMain.getChildren().addAll(this.leLabyAff,stackConfig,goBackButton);
        VBox vboxMain = new VBox();
        vboxMain.setPadding(new Insets(50));


        vboxMain.getChildren().addAll(title,hboxMain,gameButton,saveButton);
        Scene sceneGame = new Scene(vboxMain,750+laby.getNbcolonne()*50, 500+laby.getNbligne()*30);

        this.setScene(sceneGame);

    }
    public void AfficherLeLab() throws Exception {
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

        Label title = new Label("Le jeu est lancé !");
        title.setStyle("-fx-font-weight: bold;");
        title.setFont(fontTitle);
        title.setPadding(new Insets(0,0,30,0));

        Button nextTour = new Button("Tour suivant ->");
        nextTour.setOnMouseClicked(this.EventMain);
        nextTour.setFont(new Font("montserrat",30));
        // hbox vbox
        HBox hboxMain = new HBox();
        hboxMain.getChildren().addAll(this.leLabyAff);

        VBox vboxMain = new VBox();

        vboxMain.setPadding(new Insets(50));


        vboxMain.getChildren().addAll(title,hboxMain,nextTour);
        vboxMain.setAlignment(Pos.TOP_CENTER);
        hboxMain.setAlignment(Pos.CENTER);
        Scene sceneGame = new Scene(vboxMain,700+laby.getNbcolonne()*40, 500+laby.getNbligne()*30);



        this.setScene(sceneGame);

    }


    public boolean isAnimalConfig() {
        return AnimalConfig;
    }

    public boolean isVegetalConfig() {
        return VegetalConfig;
    }
}