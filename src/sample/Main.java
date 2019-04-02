package sample;

import Emetteur.*;
import Recepteur.*;
import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Insets;
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

    private static Timeline timeline = new Timeline();
    SequentialTransition seqTrans = new SequentialTransition();
    SequentialTransition seqFade = new SequentialTransition();
    public static double frequenceRep;
    public static int entier;

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
        Image image = new Image("sample/Mont_Bromo.jpg"); //ratio 2 880 X 1920 = 1,5
        scene3.getStylesheets().add("sample/game.css");
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
        MenuItem source4 = new MenuItem("Feux d'artifice");
        MenuItem source5 = new MenuItem("Marteau piqueur");
        MenuItem source6 = new MenuItem("Tondeuse");
        MenuItem structure1 = new MenuItem("Bouchons");
        MenuItem structure2 = new MenuItem("Cache Oreilles");
        MenuItem structure3 = new MenuItem("Mur");
        MenuItem structure4 = new MenuItem("Oreiller");

        structure.getItems().addAll(structure1, structure2, structure3, structure4);
        source.getItems().addAll(source1, source2, source4, source5, source6);
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
        Label label4 = new Label("Vitesse vent");
        Slider vitesseE = new Slider(0,0,0);
        Slider vitesseR = new Slider(-20,20,0);
        Slider vent = new Slider(-80, 80, 0);
        VBox sliders = new VBox(label, vitesseE, label2, vitesseR, label4, vent);
        sliders.setAlignment(Pos.CENTER);
        root3.setLeft(sliders);
        vitesseE.setShowTickMarks(true);
        vitesseE.setShowTickLabels(true);
        vitesseR.setShowTickMarks(true);
        vitesseR.setShowTickLabels(true);
        vitesseR.setMajorTickUnit(5);
        vitesseR.setMinorTickCount(0);
        vent.setShowTickMarks(true);
        vent.setMajorTickUnit(20);
        vent.setMinorTickCount(0);
        vent.setShowTickLabels(true);

        //Améliorer l'apparence
        Label label3 = new Label("Résultats");

        Label intesitePercue = new Label("Intensité perçue : ");
        Label intensiteValue = new Label("0");
        Label intensiteUnit = new Label(" dB");
        HBox intensite = new HBox(intesitePercue, intensiteValue, intensiteUnit);

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

        Label vitesseVent = new Label("Vitesse vent : ");
        Label vitesseVentValue = new Label("0");
        Label vitesseVentUnit = new Label(" km/h");
        HBox ventH = new HBox(vitesseVent, vitesseVentValue, vitesseVentUnit);

        VBox vBoxResultats = new VBox(intensite, hBoxFreq, hBoxEmise, hBoxEmetteur, hBoxRecepteur, ventH);

        VBox resultat = new VBox(label3, vBoxResultats);
        resultat.setAlignment(Pos.CENTER);
        root3.setRight(resultat);
        root3.setPadding(new Insets(0,10,0,10));

        //IMAGES
        Image image1 = new Image("Emetteur/ambulance.png");
        Image image3 = new Image("Emetteur/avion.png");
        Image image4 = new Image("Emetteur/marteau-piqueur.png");
        Image image5 = new Image("Emetteur/tondeuse.png");
        Image image2 = new Image("Emetteur/feux_artifices.png");
        Image image6 = new Image("Recepteur/dopp_auditif.png");
        Image image7 = new Image("Recepteur/dopp_bouchons.png");
        Image image8 = new Image("Recepteur/dopp_oreiller.png");
        Image ventD = new Image("sample/ventDroite.png");
        Image ventG = new Image("sample/ventGauche.png");
        ImageView imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(300);

        //TIMELINE
        Rectangle rectangle = new Rectangle();

        Line horizon = new Line(0,
                (screenSize.getHeight()- (screenSize.getHeight()/8)),
                 screenSize.getWidth()-275,
                (screenSize.getHeight()-((screenSize.getHeight()/8))));
        horizon.setStroke(Color.CHOCOLATE);
        horizon.setStrokeWidth(screenSize.getHeight()/7);
        imageViewDoppler.setX(350);
        imageViewDoppler.setY(screenSize.getHeight()- ((screenSize.getHeight()/8)+ (imageViewDoppler.getFitHeight())));   //rogner l'images de doppler le plus petit possible
        imageView.setX(imageViewDoppler.getX()+450);                                                                                                                  //faire dememe pour les autres aussi
        imageView.setY(screenSize.getHeight()- ((screenSize.getHeight()/8)+ (imageView.getFitHeight())));

        int doppDepart = (int) (screenSize.getHeight()- ((screenSize.getHeight()/8)+ (imageViewDoppler.getFitHeight())))-100;  //rendu ici !!!!!
        int doppFin = doppDepart+100;

        vitesseR.valueProperty().addListener(((observable, oldValue, newValue) -> {

            vitesseRecepteurValue.setText(String.valueOf(Math.round(vitesseR.getValue())));

            timeline.stop();
            timeline = new Timeline();

            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.setAutoReverse(true);
            KeyValue kv2 = new KeyValue(imageViewDoppler.yProperty(),doppDepart, Interpolator.EASE_IN);
            KeyFrame kf2 = new KeyFrame(Duration.seconds(0), kv2);

            KeyValue kv1 = new KeyValue(imageViewDoppler.yProperty(),doppFin, Interpolator.EASE_IN);
            try{
                KeyFrame kf1 = new KeyFrame(Duration.seconds(5/ Math.abs( newValue.doubleValue())), kv1);
                timeline.getKeyFrames().addAll(kf1, kf2);
                timeline.play();

                frequenceRep = doppler.frequenceCalc(vitesseE.getValue(), vitesseR.getValue(), doppler.getSource().getFrequenceEmise(), vent.getValue());
                frequenceValue.setText((String.valueOf(Math.round(frequenceRep))));
            }
            catch (ArithmeticException e){
                System.out.println("division par 0");
            }
            catch (NullPointerException e){

            }
        }));


        vitesseE.valueProperty().addListener(((observable, oldValue, newValue) -> {

            int transValue;
            int retransValue;

            if (vitesseE.getValue() < 0){
                imageView.setScaleX(-1);
                transValue = 200;
                retransValue = -200;

            }
            else {
                transValue = -200;
                retransValue = 200;
                imageView.setScaleX(1);
            }

            vitesseEmetteurValue.setText(String.valueOf(Math.round(vitesseE.getValue())));
            seqFade.stop();
            seqTrans.stop();
            imageView.setTranslateX(0);

            TranslateTransition trans  = new TranslateTransition(
                    Duration.seconds(entier/ Math.abs( newValue.doubleValue())), imageView);
            trans.setByX(transValue);

            TranslateTransition resetTrans = new TranslateTransition(Duration.millis(1), imageView);
            resetTrans.setByX(retransValue);

            seqTrans = new SequentialTransition(trans, resetTrans);
            seqTrans.setCycleCount(Timeline.INDEFINITE);
            seqTrans.play();


            FadeTransition fade = new FadeTransition(
                    Duration.seconds(entier/ Math.abs( newValue.doubleValue())), imageView);
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

                frequenceRep = doppler.frequenceCalc(vitesseE.getValue(), vitesseR.getValue(), doppler.getSource().getFrequenceEmise(), vent.getValue());
                frequenceValue.setText(String.valueOf(Math.round(frequenceRep)));
            }
            catch (ArithmeticException e){
                System.out.println("division par 0");
            }
        }));

        vent.valueProperty().addListener((observable, oldValue, newValue) -> {

            if (vent.getValue() < 0){

                fond.setImage(ventG);
                Background background2 = new Background(new BackgroundImage(ventG,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        bSize));

                root3.setBackground(background2);
            }
            else if (vent.getValue() > 0){

                fond.setImage(ventD);
                Background background3 = new Background(new BackgroundImage(ventD,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        bSize));

                root3.setBackground(background3);
            }

            frequenceRep = doppler.frequenceCalc(vitesseE.getValue(), vitesseR.getValue(), doppler.getSource().getFrequenceEmise(), vent.getValue());
            frequenceValue.setText(String.valueOf(Math.round(frequenceRep)));
            vitesseVentValue.setText(String.valueOf(Math.round(vent.getValue())));
        });


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
           frequenceValue.setText("0");
           frequenceEmiseValue.setText("0");
           vitesseEmetteurValue.setText("0");
           vitesseRecepteurValue.setText("0");
           intensiteValue.setText("0");
           vitesseVentValue.setText("0");

           vitesseE.setValue(0);
           vitesseR.setValue(0);
           vitesseE.setMin(0);
           vitesseE.setMax(0);
           vent.setValue(0);

           root3.setBackground(background);

           imageView.setImage(null);
           imageViewDoppler.setImage(dopplerImage);

        });

        structure1.setOnAction(event -> {
            Bouchons bouchons = new Bouchons();
            doppler.setStructure(bouchons);
            imageViewDoppler.setImage(image7);


           intensiteValue.setText(String.valueOf(doppler.getStructure().Isolation(doppler.getSource())));

        });

        structure2.setOnAction(event -> {
            CacheOreilles cacheOreilles = new CacheOreilles();
            doppler.setStructure(cacheOreilles);
            imageViewDoppler.setImage(image6);

            intensiteValue.setText(String.valueOf(doppler.getStructure().Isolation(doppler.getSource())));
        });

        structure3.setOnAction(event -> {
            Mur mur = new Mur();
            doppler.setStructure(mur);


            intensiteValue.setText(String.valueOf(doppler.getStructure().Isolation(doppler.getSource())));
        });

        structure4.setOnAction(event -> {
            Oreiller oreiller = new Oreiller();
            doppler.setStructure(oreiller);
            imageViewDoppler.setImage(image8);

            intensiteValue.setText(String.valueOf(doppler.getStructure().Isolation(doppler.getSource())));
        });

        source1.setOnAction(event -> {
            Ambulance ambulance = new Ambulance();
            doppler.setSource(ambulance);
            imageView.setImage(image1);
            ambulance.setImage(image1);
            intensiteValue.setText(String.valueOf(ambulance.getIntensiteEmise()));

            vitesseE.setMin(-150);
            vitesseE.setMax(150);
            vitesseE.setMajorTickUnit(50);
            vitesseE.setMinorTickCount(0);

            entier = 50;

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
            intensiteValue.setText(String.valueOf(avion.getIntensiteEmise()));

            vitesseE.setMin(-1000);
            vitesseE.setMax(1000);
            vitesseE.setMajorTickUnit(500);
            vitesseE.setMinorTickCount(0);

            entier = 400;

            frequenceEmiseValue.setText(String.valueOf(doppler.getSource().getFrequenceEmise()));

          /*  String musicFile = "";
            Media audio = new Media(new File(musicFile).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(audio);
            mediaPlayer.play();*/

            frequenceRep = doppler.frequenceCalc(vitesseE.getValue(), vitesseR.getValue(), avion.getFrequenceEmise(), vent.getValue());
        });

        source4.setOnAction(event -> {
            FeuxArtifice feuxArtifice = new FeuxArtifice();
            doppler.setSource(feuxArtifice);
            imageView.setImage(image2);
            intensiteValue.setText(String.valueOf(feuxArtifice.getIntensiteEmise()));
            imageView.setY(100);
            imageView.setX(1000);

            vitesseE.setMin(0);
            vitesseE.setMax(0);
            vitesseE.setMajorTickUnit(1);
            vitesseE.setMinorTickCount(0);

            FadeTransition fade = new FadeTransition(Duration.seconds(1.5), imageView);
            fade.setFromValue(1.0);
            fade.setToValue(0);
            fade.setCycleCount(Timeline.INDEFINITE);
            fade.play();

            frequenceEmiseValue.setText(String.valueOf(doppler.getSource().getFrequenceEmise()));

           /* String musicFile = "";
            Media audio = new Media(new File(musicFile).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(audio);
            mediaPlayer.play();*/

           frequenceRep = doppler.frequenceCalc(vitesseE.getValue(), vitesseR.getValue(), feuxArtifice.getFrequenceEmise(), vent.getValue());
        });

        source5.setOnAction(event -> {
            Marteau marteau = new Marteau();
            doppler.setSource(marteau);
            imageView.setImage(image4);
            marteau.setImage(image4);
            intensiteValue.setText(String.valueOf(marteau.getIntensiteEmise()));

            vitesseE.setMin(-5);
            vitesseE.setMax(5);
            vitesseE.setMajorTickUnit(1);
            vitesseE.setMinorTickCount(0);

            entier = 5;

            frequenceEmiseValue.setText(String.valueOf(doppler.getSource().getFrequenceEmise()));

            String musicFile = "";
            Media audio = new Media(new File(musicFile).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(audio);
            mediaPlayer.play();

            frequenceRep = doppler.frequenceCalc(vitesseE.getValue(), vitesseR.getValue(), marteau.getFrequenceEmise(), vent.getValue());
        });

        source6.setOnAction(event -> {
            Tondeuse tondeuse = new Tondeuse();
            doppler.setSource(tondeuse);
            imageView.setImage(image5);
            tondeuse.setImage(image5);
            intensiteValue.setText(String.valueOf(tondeuse.getIntensiteEmise()));

            vitesseE.setMin(-5);
            vitesseE.setMax(5);
            vitesseE.setMajorTickUnit(1);
            vitesseE.setMinorTickCount(0);

            entier = 5;

            frequenceEmiseValue.setText(String.valueOf(doppler.getSource().getFrequenceEmise()));

            String musicFile = "";
            Media audio = new Media(new File(musicFile).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(audio);
            mediaPlayer.play();

            frequenceRep = doppler.frequenceCalc(vitesseE.getValue(), vitesseR.getValue(), tondeuse.getFrequenceEmise(), vent.getValue());
        });

        primaryStage.show();
    }




}
