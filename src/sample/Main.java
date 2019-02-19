package sample;

import Recepteur.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    public Stage fenetre;

    @Override
    public void start(Stage primaryStage) throws Exception{

        BorderPane root = new BorderPane();

        //SCENE1
        //BASE
        Scene scene = new Scene(root);
        scene.getStylesheets().add("sample/style.css");

        primaryStage.setTitle("Doppler Project");
        //primaryStage.setMaximized(true);
        primaryStage.setMinWidth(700);
        primaryStage.setMinHeight(500);
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);

        //COMPOSANTES
        Label titre = new Label("DOPPLER AU PAYS DES BRUITS");
        titre.setAlignment(Pos.TOP_CENTER);
        Label noms = new Label("LAURIE BONNEL ET ANNABELLE DION");
        noms.setAlignment(Pos.BOTTOM_CENTER);
        Button demarrer = new Button("DÉMARRER");
        demarrer.setAlignment(Pos.CENTER);
        Button guideUti = new Button("GUIDE D'UTILISATION");
        guideUti.setAlignment(Pos.CENTER);
        root.setTop(titre);
        root.setBottom(noms);
        VBox centre  = new VBox(demarrer, guideUti);
        centre.setAlignment(Pos.CENTER);
        root.setCenter(centre);

        //SCENE2
        //BASE
        BorderPane root2 = new BorderPane();

        Scene scene2  = new Scene(root2, 700,500);
        scene2.getStylesheets().add("sample/style.css");

        //COMPOSANTES
        Button retour = new Button("Retour");
        Label instructions = new Label("INSTRUCTIONS À ÉCRIRE ICI" +
                "D\n" +
                "D\n" +
                "D\n" +
                "D\n" +
                "D\n" +
                "D\n" +
                "D\n" +
                "D\n" +
                "D\n");
        VBox vBox = new VBox(instructions, retour);
        vBox.setAlignment(Pos.CENTER);
        root2.setCenter(vBox);

        //SCENE3
        //BASE
        BorderPane root3 = new BorderPane();
        Scene scene3 = new Scene(root3, 700, 500);

        //CREATION DU MENU
        MenuBar menuBar = new MenuBar();
        root3.setTop(menuBar);
        Menu source  = new Menu("Sources");
        Menu structure = new Menu("Structures");
        Menu option = new Menu("Options");
        MenuItem quitter = new MenuItem("Quitter");
        MenuItem reinitialiser = new MenuItem("Réinitialiser la partie");
        MenuItem source1 = new MenuItem("Ambulance");
        MenuItem source2 = new MenuItem("Avion");
        MenuItem source3 = new MenuItem("Chauteuse d'opéra");
        MenuItem source4 = new MenuItem("Feux d'artifice");
        MenuItem source5 = new MenuItem("Marteau piqueur");
        MenuItem source6 = new MenuItem("Tondeuse");
        MenuItem structure1 = new MenuItem("Bouchons");
        MenuItem structure2 = new MenuItem("Cache Oreilles");
        MenuItem structure3 = new MenuItem("Mur");
        MenuItem structure4 = new MenuItem("Oreiller");

        structure.getItems().addAll(structure1, structure2, structure3, structure4);
        source.getItems().addAll(source1, source2, source3, source4, source5, source6);
        option.getItems().addAll(quitter, reinitialiser);
        menuBar.getMenus().addAll(source, structure, option);

        //DOPPLER
        Personnage doppler = new Personnage();
        doppler.setNom("Doppler");

        //COMPOSANTES
        Label label = new Label("Vitesse éméteur");
        Label label2 = new Label("Vitesse récepteur");
        Slider vitesseE = new Slider(0,20,0);
        Slider vitesseR = new Slider(0,100,0);
        VBox sliders = new VBox(label, vitesseE, label2, vitesseR);
        sliders.setAlignment(Pos.CENTER_LEFT);
        root3.setLeft(sliders);
        vitesseE.setShowTickMarks(true);
        vitesseE.setShowTickLabels(true);
        vitesseR.setShowTickMarks(true);
        vitesseR.setShowTickLabels(true);
        //bind marche pas
       // vitesseR.valueProperty().bind(doppler.setVitesse());
        //vitesseE.valueProperty().bind();

        Rectangle result = new Rectangle(50, 20);
        result.setStroke(Color.BLACK);
        result.setFill(Color.WHITE);
        Label label3 = new Label("Résultat");
        VBox resultat = new VBox(label3, result);
        resultat.setAlignment(Pos.BOTTOM_RIGHT);
        root3.setBottom(resultat);
        /*result.setX(700);
        result.setY(500);*/


        //ONACTION
        guideUti.setOnAction(event -> {
            primaryStage.setScene(scene2);
        });

        retour.setOnAction(event -> {
            primaryStage.setScene(scene);
        });

        demarrer.setOnAction(event -> {
            primaryStage.setScene(scene3);
        });

        quitter.setOnAction(event -> {
            primaryStage.setScene(scene);
        });

        reinitialiser.setOnAction(event -> {

        });

        structure1.setOnAction(event -> {
            Bouchons bouchons = new Bouchons();
            doppler.setProtectActiv(bouchons);
        });

        structure2.setOnAction(event -> {
            CacheOreilles cacheOreilles = new CacheOreilles();
            doppler.setProtectActiv(cacheOreilles);
        });

        structure3.setOnAction(event -> {
            Mur mur = new Mur();
            doppler.setProtectActiv(mur);
        });

        structure4.setOnAction(event -> {
            Oreiller oreiller = new Oreiller();
            doppler.setProtectActiv(oreiller);
        });

        source1.setOnAction(event -> {
                //ajuster la vitesse du slider
        });

        source2.setOnAction(event -> {

        });

        source3.setOnAction(event -> {

        });

        source4.setOnAction(event -> {

        });

        source5.setOnAction(event -> {

        });

        source6.setOnAction(event -> {

        });

        primaryStage.show();
    }




}
