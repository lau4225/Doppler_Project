package sample;

import Recepteur.*;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    public Stage fenetre;

    private static Timeline timeline = new Timeline();

    @Override
    public void start(Stage primaryStage) throws Exception{
        //SCENE1
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root);

        scene.getStylesheets().add("sample/reception.css");

        primaryStage.setTitle("Doppler Project");
        primaryStage.setWidth(screenSize.getWidth());
        primaryStage.setHeight(screenSize.getHeight());
        primaryStage.setMaximized(true);
       // primaryStage.setResizable(true);
        primaryStage.setScene(scene);

        //COMPOSANTES
        Label titre = new Label("DOPPLER AU PAYS DES BRUITS");
        Label noms = new Label("LAURIE BONNEL ET ANNABELLE DION");
        Button demarrer = new Button("DÉMARRER");
        Button guideUti = new Button("GUIDE D'UTILISATION");

        VBox centre  = new VBox(demarrer, guideUti);
        centre.setAlignment(Pos.CENTER);
        root.setCenter(centre);

        VBox top = new VBox(titre);
        top.setAlignment(Pos.TOP_CENTER);
        root.setTop(top);

        VBox bottom = new VBox(noms);
        bottom.setAlignment(Pos.BOTTOM_CENTER);
        root.setBottom(bottom);

        //SCENE2
        BorderPane root2 = new BorderPane();
        Scene scene2  = new Scene(root2, screenSize.getWidth(), screenSize.getHeight());
        scene2.getStylesheets().add("sample/reception.css");

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
        BorderPane root3 = new BorderPane();
        Scene scene3 = new Scene(root3, screenSize.getWidth(), screenSize.getHeight());
        scene3.getStylesheets().add("sample/game.css");
        Image image = new Image("sample/Mont_Bromo.jpg");
        ImageView fond = new ImageView(image);
        BackgroundSize bSize = new BackgroundSize(screenSize.getWidth(), screenSize.getHeight(), false, false, true, false);
        Background background = new Background(new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize));

        root3.setBackground(background);


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
        //vitesse sliders
        Label label = new Label("Vitesse éméteur");
        Label label2 = new Label("Vitesse récepteur");
        Slider vitesseE = new Slider(-20,20,0);
        Slider vitesseR = new Slider(-5,5,0);
        VBox sliders = new VBox(label, vitesseE, label2, vitesseR);
        sliders.setAlignment(Pos.CENTER);
        root3.setLeft(sliders);
        vitesseE.setShowTickMarks(true);
        vitesseE.setShowTickLabels(true);
        vitesseR.setShowTickMarks(true);
        vitesseR.setShowTickLabels(true);

        Rectangle result = new Rectangle(150, 75);
        result.setStroke(Color.BLACK);
        result.setStrokeWidth(2);
        result.setFill(Color.CHOCOLATE);
        Label label3 = new Label("Résultat");
        VBox resultat = new VBox(label3, result);
        //resultat.setTranslateY(800);
        resultat.setAlignment(Pos.CENTER);
        root3.setRight(resultat);

        Image image2 = new Image("sample/bonhomme.jpg"); // C'est quelle image ?? il faudra la mettre dans le package sample
        //je l'ai changé pour pouvoir runner
        ImageView flag = new ImageView(image2);
        root3.setCenter(flag);

        //TIMELINE
        //unité de vitesse
        Rectangle rectangle = new Rectangle();
        int end = 525;

        Line horizon = new Line(157,925, 1483,925);
        Circle cercle = new Circle(350,805,20); //represente doppler, a changer
        cercle.setFill(Color.GREEN);
        horizon.setStroke(Color.CHOCOLATE);
        horizon.setStrokeWidth(200);

        vitesseR.valueProperty().addListener(((observable, oldValue, newValue) -> {
            timeline.stop();
            timeline = new Timeline();

            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.setAutoReverse(true);
            KeyValue kv2 = new KeyValue(cercle.centerYProperty(),825, Interpolator.EASE_IN);
            KeyFrame kf2 = new KeyFrame(Duration.seconds(0), kv2);

            KeyValue kv1 = new KeyValue(cercle.centerYProperty(),end, Interpolator.EASE_IN);

            try{
                KeyFrame kf1 = new KeyFrame(Duration.seconds(1/ Math.abs( newValue.doubleValue())), kv1);
                timeline.getKeyFrames().addAll(kf1, kf2);
                timeline.play();

            }catch (ArithmeticException e){
                System.out.println("Exception de division par 0");
            }



        }));


        Pane group = new Pane(horizon, cercle);
        VBox vBox1 = new VBox(group);
        root3.setCenter(vBox1);


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
            String musicFile = "";
            Media audio = new Media(new File(musicFile).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(audio);
            mediaPlayer.play();
        });

        source2.setOnAction(event -> {
            //pas son
            String musicFile = "";
            Media audio = new Media(new File(musicFile).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(audio);
            mediaPlayer.play();
        });

        source3.setOnAction(event -> {
            String musicFile = "";
            Media audio = new Media(new File(musicFile).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(audio);
            mediaPlayer.play();
        });

        source4.setOnAction(event -> {
            String musicFile = "";
            Media audio = new Media(new File(musicFile).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(audio);
            mediaPlayer.play();
        });

        source5.setOnAction(event -> {
            String musicFile = "";
            Media audio = new Media(new File(musicFile).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(audio);
            mediaPlayer.play();
        });

        source6.setOnAction(event -> {
            String musicFile = "";
            Media audio = new Media(new File(musicFile).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(audio);
            mediaPlayer.play();
        });

        primaryStage.show();
    }




    
}
