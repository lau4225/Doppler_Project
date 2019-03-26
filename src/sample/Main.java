package sample;

import Emetteur.*;
import Recepteur.*;
import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
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
import java.text.DecimalFormat;

public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    private static Timeline timeline = new Timeline();
    SequentialTransition seqTrans = new SequentialTransition();
    SequentialTransition seqFade = new SequentialTransition();
    public static double frequenceRep;

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
        Image image = new Image("sample/Mont_Bromo.jpg");
        scene3.getStylesheets().add("sample/game.css");
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
        Image dopplerImage = new Image("Recepteur/doppler.png");
        doppler.setImage(dopplerImage);
        ImageView imageViewDoppler = new ImageView(dopplerImage);
        imageViewDoppler.setPreserveRatio(true);
        imageViewDoppler.setFitHeight(300);

        //COMPOSANTES
        //vitesse sliders
        Label label = new Label("Vitesse éméteur");
        Label label2 = new Label("Vitesse récepteur");
        Slider vitesseE = new Slider(0,0,0);
        Slider vitesseR = new Slider(-20,20,0);
        VBox sliders = new VBox(label, vitesseE, label2, vitesseR);
        sliders.setAlignment(Pos.CENTER);
        root3.setLeft(sliders);
        vitesseE.setShowTickMarks(true);
        vitesseE.setShowTickLabels(true);
        vitesseR.setShowTickMarks(true);
        vitesseR.setShowTickLabels(true);
        vitesseR.setMajorTickUnit(5);
        vitesseR.setMinorTickCount(0);

       /* Rectangle result = new Rectangle(150, 75);
        result.setStroke(Color.BLACK);
        result.setStrokeWidth(2);
        result.setFill(Color.CHOCOLATE);*/

        //Améliorer l'apparence
        Label label3 = new Label("Résultats");

        Label frequence = new Label("Fréquence perçue : ");
        Label frequenceValue = new Label("0");
        Label frequenceUnit = new Label(" Hz");
        HBox hBoxFreq = new HBox(frequence, frequenceValue, frequenceUnit);

        Label frequenceEmise = new Label("Fréquence émise : ");
        Label frequenceEmiseValue = new Label("0");
        Label frequenceEmiseUnit = new Label(" Hz");
        HBox hBoxEmise = new HBox(frequenceEmise, frequenceEmiseValue, frequenceEmiseUnit);

        Label vitesseEmetteur = new Label("Vitesse source : ");
        Label vitesseEmetteurValue = new Label(String.valueOf(vitesseE.getValue()));
        Label vitesseEmetteurUnit = new Label(" km/h");
        HBox hBoxEmetteur = new HBox(vitesseEmetteur, vitesseEmetteurValue, vitesseEmetteurUnit);

        Label vitesseRecepteur = new Label("Vitesse Doppler : ");
        Label vitesseRecepteurValue = new Label(String.valueOf(vitesseR.getValue()));
        Label vitesseRecepteurUnit = new Label(" km/h");
        HBox hBoxRecepteur = new HBox(vitesseRecepteur, vitesseRecepteurValue, vitesseRecepteurUnit);

        VBox vBoxResultats = new VBox(hBoxFreq, hBoxEmise, hBoxEmetteur, hBoxRecepteur);

        VBox resultat = new VBox(label3, vBoxResultats);
        resultat.setAlignment(Pos.CENTER);
        root3.setRight(resultat);

        //marche pas changer pour les background
        Image image2 = new Image("sample/téléchargement.png");
        ImageView flag = new ImageView(image2);
        root3.setCenter(flag);

        //IMAGES
        Image image1 = new Image("Emetteur/ambulance.png");
        Image image3 = new Image("Emetteur/avion.png");
        Image image4 = new Image("Emetteur/marteau-piqueur.png");
        Image image5 = new Image("Emetteur/tondeuse.png");
        ImageView imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(300);

        //TIMELINE
        Rectangle rectangle = new Rectangle();

        Line horizon = new Line(157,925, 1483,925);
        horizon.setStroke(Color.CHOCOLATE);
        horizon.setStrokeWidth(200);
        imageViewDoppler.setX(350);
        imageViewDoppler.setY(650);
        imageView.setX(900);
        imageView.setY(600);

        vitesseR.valueProperty().addListener(((observable, oldValue, newValue) -> {

            //ajouter une condition de faire une réflexion de l'image si valeur négative de la vitesse

            vitesseRecepteurValue.setText(String.valueOf(Math.round(vitesseR.getValue())));

            timeline.stop();
            timeline = new Timeline();

            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.setAutoReverse(true);
            KeyValue kv2 = new KeyValue(imageViewDoppler.yProperty(),600, Interpolator.EASE_IN);
            KeyFrame kf2 = new KeyFrame(Duration.seconds(0), kv2);

            KeyValue kv1 = new KeyValue(imageViewDoppler.yProperty(),700, Interpolator.EASE_IN);
            try{
                KeyFrame kf1 = new KeyFrame(Duration.seconds(1/ Math.abs( newValue.doubleValue())), kv1);
                timeline.getKeyFrames().addAll(kf1, kf2);
                timeline.play();

                frequenceRep = doppler.frequenceCalc(vitesseE.getValue(), vitesseR.getValue(), doppler.getSource().getFrequenceEmise());
                frequenceValue.setText((String.valueOf(Math.round(frequenceRep))));
            }
            catch (ArithmeticException e){
                System.out.println("division par 0");
            }
            catch (NullPointerException e){

            }
        }));


        vitesseE.valueProperty().addListener(((observable, oldValue, newValue) -> {

            //la source avance encore

            vitesseEmetteurValue.setText(String.valueOf(Math.round(vitesseE.getValue())));
            seqFade.stop();
            seqTrans.stop();
            imageView.setTranslateX(0);

            TranslateTransition trans  = new TranslateTransition(
                    Duration.seconds(20/ Math.abs( newValue.doubleValue())), imageView);
            trans.setByX(-200);

            TranslateTransition resetTrans = new TranslateTransition(Duration.millis(1), imageView);
            resetTrans.setByX(200);

            seqTrans = new SequentialTransition(trans, resetTrans);
            seqTrans.setCycleCount(Timeline.INDEFINITE);
            seqTrans.play();


            FadeTransition fade = new FadeTransition(
                    Duration.seconds(20/ Math.abs( newValue.doubleValue())), imageView);
            fade.setFromValue(1.0);
            fade.setToValue(0);

            FadeTransition resetFade = new FadeTransition(
                    Duration.millis(1), imageView);
            resetFade.setFromValue(0);
            resetFade.setToValue(1);

            seqFade = new SequentialTransition(fade, resetFade);
            seqFade.setCycleCount(Timeline.INDEFINITE);
            seqFade.play();

            try{

                frequenceRep = doppler.frequenceCalc(vitesseE.getValue(), vitesseR.getValue(), doppler.getSource().getFrequenceEmise());
                frequenceValue.setText(String.valueOf(Math.round(frequenceRep)));
            }
            catch (ArithmeticException e){
                System.out.println("division par 0");
            }
        }));


        Pane group = new Pane(horizon, imageViewDoppler, imageView);
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
            Ambulance ambulance = new Ambulance();
            doppler.setSource(ambulance);
            imageView.setImage(image1);
            ambulance.setImage(image1);

            vitesseE.setMin(-200);
            vitesseE.setMax(200);
            vitesseE.setMajorTickUnit(50);
            vitesseE.setMinorTickCount(0);

            frequenceEmiseValue.setText(String.valueOf(doppler.getSource().getFrequenceEmise()));


           /* String musicFile = "";
            Media audio = new Media(new File(musicFile).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(audio);
            mediaPlayer.play();*/


        });

        source2.setOnAction(event -> {
            //pas son
            Avion avion = new Avion();
            doppler.setSource(avion);
            imageView.setImage(image3);
            avion.setImage(image3);

            vitesseE.setMin(-1000);
            vitesseE.setMax(1000);
            vitesseE.setMajorTickUnit(200);
            vitesseE.setMinorTickCount(0);

            frequenceEmiseValue.setText(String.valueOf(doppler.getSource().getFrequenceEmise()));

            String musicFile = "";
            Media audio = new Media(new File(musicFile).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(audio);
            mediaPlayer.play();

            frequenceRep = doppler.frequenceCalc(vitesseE.getValue(), vitesseR.getValue(), avion.getFrequenceEmise());
        });

        source3.setOnAction(event -> {
            String musicFile = "";
            Media audio = new Media(new File(musicFile).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(audio);
            mediaPlayer.play();
        });

        source4.setOnAction(event -> {
            FeuxArtifice feuxArtifice = new FeuxArtifice();
            doppler.setSource(feuxArtifice);

            vitesseE.setMin(0);
            vitesseE.setMax(0);
            vitesseE.setMajorTickUnit(0);
            vitesseE.setMinorTickCount(0);

            frequenceEmiseValue.setText(String.valueOf(doppler.getSource().getFrequenceEmise()));

            String musicFile = "";
            Media audio = new Media(new File(musicFile).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(audio);
            mediaPlayer.play();

           frequenceRep = doppler.frequenceCalc(vitesseE.getValue(), vitesseR.getValue(), feuxArtifice.getFrequenceEmise());
        });

        source5.setOnAction(event -> {
            Marteau marteau = new Marteau();
            doppler.setSource(marteau);
            imageView.setImage(image4);
            marteau.setImage(image4);

            vitesseE.setMin(-5);
            vitesseE.setMax(5);
            vitesseE.setMajorTickUnit(1);
            vitesseE.setMinorTickCount(0);

            frequenceEmiseValue.setText(String.valueOf(doppler.getSource().getFrequenceEmise()));

            String musicFile = "";
            Media audio = new Media(new File(musicFile).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(audio);
            mediaPlayer.play();

            frequenceRep = doppler.frequenceCalc(vitesseE.getValue(), vitesseR.getValue(), marteau.getFrequenceEmise());
        });

        source6.setOnAction(event -> {
            Tondeuse tondeuse = new Tondeuse();
            doppler.setSource(tondeuse);
            imageView.setImage(image5);
            tondeuse.setImage(image5);

            vitesseE.setMin(-5);
            vitesseE.setMax(5);
            vitesseE.setMajorTickUnit(1);
            vitesseE.setMinorTickCount(0);

            frequenceEmiseValue.setText(String.valueOf(doppler.getSource().getFrequenceEmise()));

            String musicFile = "";
            Media audio = new Media(new File(musicFile).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(audio);
            mediaPlayer.play();

            frequenceRep = doppler.frequenceCalc(vitesseE.getValue(), vitesseR.getValue(), tondeuse.getFrequenceEmise());
        });

        primaryStage.show();
    }




}
