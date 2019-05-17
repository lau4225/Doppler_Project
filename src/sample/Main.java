package sample;

import emetteur.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import recepteur.*;
import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.*;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
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
    public MediaPlayer mediaPlayer;
    @Override
    public void start(Stage primaryStage) throws Exception{

        //SCENE1
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds(); //1920x1040  écran à l'école
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
        titre.setScaleX(3); titre.setScaleY(3);
        titre.setTranslateY(50);

        DropShadow ds = new DropShadow(20,10,10, Color.BLACK);
        titre.setEffect(ds);
        titre.setPadding(new Insets(10));

        VBox top = new VBox(titre);
        top.setAlignment(Pos.TOP_CENTER);
        root.setTop(top);

        noms.setScaleY(1.5); noms.setScaleX(1.5);
        noms.setEffect(ds);
        noms.setPadding(new Insets(10));
        VBox bottom = new VBox(noms);
        bottom.setAlignment(Pos.BOTTOM_CENTER);
        root.setBottom(bottom);
        root.setPadding(new Insets(20));

        Image allSources = new Image("sample/all_sources.png");
        Image allCovers = new Image("sample/all_covers.png");
        ImageView accueil1 = new ImageView(allSources);
        ImageView accueil2 = new ImageView(allCovers);
        accueil1.setPreserveRatio(true);
        accueil2.setPreserveRatio(true);
        accueil1.setFitHeight(450);
        accueil2.setFitHeight(450);
        root.setLeft(accueil1);
        root.setRight(accueil2);
        root.getLeft().setTranslateY(125);
        root.getRight().setTranslateY(125);
        root.getLeft().setTranslateX(100);
        root.getRight().setTranslateX(-100);

        //SCENE2
        BorderPane root2 = new BorderPane();
        Scene scene2  = new Scene(root2, screenSize.getWidth(), screenSize.getHeight());
        scene2.getStylesheets().add("sample/guideUti.css");

        //INSTRUCTIONS
        Button retour = new Button("Retour");
        Button next = new Button("→");
        final int[] current = {0};
        Label index = new Label(" " + (current[0]+1) + " / 7 ");
        Button previous = new Button("←");
        HBox pages = new HBox(previous, index, next);
        pages.setSpacing(10);
        VBox controle = new VBox(pages, retour);
        controle.setSpacing(10);

        root2.setBottom(controle);

        pages.setAlignment(Pos.CENTER);
        controle.setAlignment(Pos.CENTER);

        Image[] tabPages = {new Image("sample/-debut.JPG"), new Image("sample/-source.png"), new Image("sample/-mouvement.png"),
                new Image("sample/-structure.png"),  new Image("sample/-mute.png"), new Image("sample/-reinitialiser.png"),
                new Image("sample/-quitter.png")};
        ImageView currentPage = new ImageView(tabPages[0]);
        currentPage.setFitHeight(400);currentPage.setPreserveRatio(true);
        DropShadow dropShadow3 = new DropShadow(20,10,10, Color.BLACK);
        currentPage.setEffect(dropShadow3);
        root2.setCenter(currentPage);

        String tabInstructions[] = {"Bienvenue dans l’application Doppler au pays des bruits. Cette application est une simulation \n" +
                                    "simplifiée de l’effet Doppler qui a été conçue pour prévenir les dangers auxquels un observateur \n" +
                                    "peut être exposé. Le programme a été réalisé afin d’illustrer les répercussions d’une intensité \n" +
                                    "trop élevée sur l’audition humaine. \n\n",


                                    "En cliquant sur le bouton « Démarrer », une nouvelle simulation commence.\n \n" +
                                    "Au début de la simulation, il est recommandé de sélectionner une source afin que les méthodes de \n" +
                                            "calculs soient exploitées. \n \n",


                "Utilisez les barres de défilement pour changer les vitesses des trois variables: \n" +
                        "-Le personnage de Doppler\n" +
                        "-La source sonore\n" +
                        "-Le vent \n \n" +
                        "Observez et écoutez les résultats des calculs ainsi que la flèche indiquant si le \n" +
                        "niveau sonore représente un danger pour l'oreille humaine",


                "Ajoutez une structure protectrice pour les oreilles de Doppler\n \n" +
                        "Voyez comment celle-ci peux influencer le niveau de danger sonore",


                "Cliquez sur l'icône du son pour mettre la simulation en mode silencieux ou bruyant",


                "Cliquez sur « Réinitialiser la partie » pour remettre les variables à leur valeur \n" +
                        "de départ en enlever la source sonore",


                "Cliquez sur « Quitter » pour revenir à la page de démarrage tout en gardant \nla simulation active"};

        Label info1 = new Label("À partir de seulement 80 dB \n" +
                                     "est le niveau auquel les dommages \n" +
                                     "à l’audition commencent. Cette \n" +
                                     "intensité est l’équivalent du bruit \n" +
                                     "moyen de la circulation en ville. ");
        Label info2 = new Label("Les effets d’une exposition de huit \n" +
                                     "heures par jour deviennent \n" +
                                     "dangereux à long terme pour un \n" +
                                     "observateur. En revanche, les effets \n" +
                                     "d’une intensité de 120 dB et plus \n" +
                                     "sont immédiats. Cette intensité est \n" +
                                     "le seuil de douleur de l’audition \n" +
                                     "humaine. ");
        root2.setLeft(info1); root2.setRight(info2);
        info1.autosize();info2.autosize();
        info1.setEffect(dropShadow3);info2.setEffect(dropShadow3);
        info1.setTextAlignment(TextAlignment.JUSTIFY);info2.setTextAlignment(TextAlignment.JUSTIFY);
        info1.setPadding(new Insets(5));info2.setPadding(new Insets(5));
        info1.setAlignment(Pos.CENTER);info2.setAlignment(Pos.CENTER);
        info1.setTranslateY(150);info2.setTranslateY(150);

        Label instructions = new Label(tabInstructions[0]);
        instructions.setMaxWidth(screenSize.getWidth()-400);
        instructions.setMinHeight(100);
        instructions.autosize(); instructions.setPadding(new Insets(5));
        instructions.setTextAlignment(TextAlignment.JUSTIFY);

        instructions.setEffect(dropShadow3);
        instructions.setAlignment(Pos.CENTER);
        VBox box = new VBox(instructions);
        root2.setTop(box);
        box.setAlignment(Pos.CENTER);
        root2.setPadding(new Insets(8));

        next.setOnAction(event -> {

            if (current[0] == 6){ }
            else { current[0]++;
            currentPage.setImage(tabPages[current[0]]);
            instructions.setText(tabInstructions[current[0]]);
            index.setText(" " + (current[0]+1) + " / 7 ");
            root2.setLeft(null); root2.setRight(null);
            }
        });

        previous.setOnAction(event -> {
            if (current[0] == 0){ root2.setLeft(info1); root2.setRight(info2); }
            else { current[0]--;
                currentPage.setImage(tabPages[current[0]]);
                instructions.setText(tabInstructions[current[0]]);
                index.setText(" " + (current[0]+1) + " / 7 ");
                if (current[0] == 0){ root2.setLeft(info1); root2.setRight(info2); }
            }
        });

        //SCENE3
        BorderPane root3 = new BorderPane();
        Scene scene3 = new Scene(root3, screenSize.getWidth(), screenSize.getHeight());
        Image image = new Image("sample/Mont_Bromo.jpg"); //ratio 2 880 X 1920 = 1,5
        ImageView fond = new ImageView(image);
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
        MenuItem source3 = new MenuItem("Feux d'artifice");
        MenuItem source4 = new MenuItem("Marteau piqueur");
        MenuItem source5 = new MenuItem("Tondeuse");
        MenuItem structure1 = new MenuItem("Bouchons");
        MenuItem structure2 = new MenuItem("Cache Oreilles");
        MenuItem structure3 = new MenuItem("Mur");
        MenuItem structure4 = new MenuItem("Oreiller");
        MenuItem structure5 = new MenuItem("Aucun");


        structure.getItems().addAll(structure1, structure2, structure3, structure4, structure5);
        source.getItems().addAll(source1, source2, source3, source4, source5);
        option.getItems().addAll(quitter, reinitialiser);
        menuBar.getMenus().addAll(source, structure, option);

        //DOPPLER
        Personnage doppler = new Personnage();
        doppler.setNom("Doppler");
        Image dopplerImage = new Image("recepteur/doppler.png");
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

        DropShadow dropShadow1 = new DropShadow(10,-5,5, Color.DARKGRAY);
        Rectangle rectA =  new Rectangle(180,200);
        rectA.setEffect(dropShadow1);
        rectA.setFill(Color.DEEPSKYBLUE);

        Group groupVitesses = new Group(rectA, sliders);
        groupVitesses.setTranslateY((screenSize.getHeight()/2) - rectA.getHeight());

        root3.setLeft(groupVitesses);
        Label label3 = new Label("Résultats");
        label3.setAlignment(Pos.CENTER);

        Label intensitePercue = new Label("Intensité perçue : ");
        Label intensiteValue = new Label("0");
        Label intensiteUnit = new Label(" dB");
        HBox intensite = new HBox(intensitePercue, intensiteValue, intensiteUnit);
        intensite.setAlignment(Pos.CENTER);

        Label frequence = new Label("Fréquence perçue : ");
        Label frequenceValue = new Label("0");
        Label frequenceUnit = new Label(" Hz");
        HBox hBoxFreq = new HBox(frequence, frequenceValue, frequenceUnit);
        hBoxFreq.setAlignment(Pos.CENTER);

        Label frequenceEmise = new Label("Fréquence émise : ");
        Label frequenceEmiseValue = new Label("0");
        Label frequenceEmiseUnit = new Label(" Hz");
        HBox hBoxEmise = new HBox(frequenceEmise, frequenceEmiseValue, frequenceEmiseUnit);
        hBoxEmise.setAlignment(Pos.CENTER);

        Label vitesseEmetteur = new Label("Vitesse source : ");
        Label vitesseEmetteurValue = new Label(String.valueOf(vitesseE.getValue()));
        Label vitesseEmetteurUnit = new Label(" km/h");
        HBox hBoxEmetteur = new HBox(vitesseEmetteur, vitesseEmetteurValue, vitesseEmetteurUnit);
        hBoxEmetteur.setAlignment(Pos.CENTER);

        Label vitesseRecepteur = new Label("Vitesse Doppler : ");
        Label vitesseRecepteurValue = new Label(String.valueOf(vitesseR.getValue()));
        Label vitesseRecepteurUnit = new Label(" km/h");
        HBox hBoxRecepteur = new HBox(vitesseRecepteur, vitesseRecepteurValue, vitesseRecepteurUnit);
        hBoxRecepteur.setAlignment(Pos.CENTER);

        Label vitesseVent = new Label("Vitesse vent : ");
        Label vitesseVentValue = new Label("0");
        Label vitesseVentUnit = new Label(" km/h");
        HBox ventH = new HBox(vitesseVent, vitesseVentValue, vitesseVentUnit);
        ventH.setAlignment(Pos.CENTER);

        VBox vBoxResultats = new VBox(intensite, hBoxFreq, hBoxEmise, hBoxEmetteur, hBoxRecepteur, ventH);

        VBox resultat = new VBox(label3, vBoxResultats);
        resultat.setAlignment(Pos.CENTER_LEFT);
        resultat.setTranslateX(25);
        DropShadow dropShadow = new DropShadow(10,5,5, Color.DARKGRAY);
        Rectangle rect =  new Rectangle(310,180);
        label3.setTranslateX((rect.getWidth()/4)+10);
        rect.setEffect(dropShadow);
        rect.setFill(Color.DEEPSKYBLUE);
        rect.setTranslateX(15);

        //SILENCIEUX
        Image soundOn = new Image("sample/soundON.png");
        Image soundOff = new Image("sample/mute.png");
        ImageView bruit = new ImageView(soundOn);
        ImageView mute = new ImageView(soundOff);
        Button silencieux = new Button();
        silencieux.setGraphic(bruit);
        mute.setFitHeight(28);mute.setPreserveRatio(true);
        bruit.setFitHeight(28);bruit.setPreserveRatio(true);
        silencieux.setOnAction((event)-> {
            try{
            if (mediaPlayer.getMedia()!=null){
                if (mediaPlayer.isMute()) {
                    silencieux.setGraphic(bruit);
                    mediaPlayer.setMute(false);
                }
                else if (!mediaPlayer.isMute()){ silencieux.setGraphic(mute);mediaPlayer.setMute(true); }
            }}catch (NullPointerException e){

            }
        });
        Group groupResult = new Group(rect, resultat);
        Group droite = new Group(groupResult, silencieux);
        DropShadow dropShadow2 = new DropShadow(10, Color.WHITE);
        silencieux.setEffect(dropShadow2);
        silencieux.setTranslateY(-37); silencieux.setTranslateX(rect.getWidth());
        groupResult.setTranslateY((screenSize.getHeight()/2)-(rect.getHeight()));

        Pane pane = new Pane(droite);
        root3.setRight(pane);
        root3.setPadding(new Insets(0,5,0,5));

        //IMAGES
        Image image1 = new Image("emetteur/ambulance.png");
        Image image3 = new Image("emetteur/avion.png");
        Image image4 = new Image("emetteur/marteau-piqueur.png");
        Image image5 = new Image("emetteur/tondeuse.png");
        Image image2 = new Image("emetteur/feux_artifices.png");
        Image image6 = new Image("recepteur/dopp_auditif.png");
        Image image7 = new Image("recepteur/dopp_bouchons.png");
        Image image8 = new Image("recepteur/dopp_oreiller.png");
        Image image9 = new Image("recepteur/dopp_murs.png");
        Image ventD = new Image("sample/ventDroite.png");
        Image ventG = new Image("sample/ventGauche.png");

        ImageView imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(300);
        ImageView imageView1 = new ImageView();
        imageView1.setPreserveRatio(true);
        imageView1.setFitHeight(300);

        //Charte de Décibels
        Rectangle tige = new Rectangle(250, 50);
        Polygon pointe = new Polygon(1450,850, 1450,950, 1550,900);
        Line line = new Line(1200, 875, 1200, 925);
        line.setStrokeWidth(5);
        tige.setX(1200);
        tige.setY(875);

        double widthFleche = 1550 - 1200;
        double heightFleche = 950 - 850;
        double debutFleche = 1200;
        Stop[] stops = new Stop[]{
                new Stop(0, Color.GREEN),
                new Stop(0.5, Color.YELLOW),
                new Stop(1, Color.RED),
        };

        LinearGradient gradient = new LinearGradient(
                0,0,
                1,1,
                true,
                CycleMethod.NO_CYCLE,
                stops);

        tige.setFill(gradient);
        pointe.setFill(Color.RED);
        Group fleche = new Group(tige, pointe, line);

        //TIMELINE
        Rectangle rectangle = new Rectangle();

        Line horizon = new Line(0,
                (screenSize.getHeight()- (screenSize.getHeight()/8)),
                  screenSize.getWidth()- 100,                                 //À ARRANGER ICI PLUS TARD, utiliser les proportions et rectamgles semblables
                (screenSize.getHeight()-((screenSize.getHeight()/8))));
        horizon.setStroke(Color.CHOCOLATE);
        horizon.setStrokeWidth(screenSize.getHeight()/7);
        imageViewDoppler.setX(screenSize.getWidth()/12);
        imageViewDoppler.setY(screenSize.getHeight()- ((screenSize.getHeight()/8)+ (imageViewDoppler.getFitHeight())));

        if (screenSize.getHeight()<1040){
            fleche.setScaleY(screenSize.getHeight()/1080);
            fleche.setTranslateY((screenSize.getHeight()-950) - (heightFleche/2));
        }
        if (screenSize.getWidth()<1920) {
            fleche.setScaleX(screenSize.getWidth()/1920);
            fleche.setTranslateX((screenSize.getWidth()-1550) - (widthFleche/2) - 100);
            debutFleche = tige.getX();
        }

        int doppDepart = (int) (screenSize.getHeight()- ((screenSize.getHeight()/8)+ (imageViewDoppler.getFitHeight())))-100;
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
                frequenceValue.setText("0");
            }
            catch (NullPointerException e){

            }
            if (Math.round(vitesseR.getValue())==0){
                imageViewDoppler.setX(screenSize.getWidth()/12);
                imageViewDoppler.setY(screenSize.getHeight()- ((screenSize.getHeight()/8)+ (imageViewDoppler.getFitHeight())));
                timeline.stop();
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
            catch (ArithmeticException e){ frequenceValue.setText("0"); }
            catch (NullPointerException e){}
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

            try {
                frequenceRep = doppler.frequenceCalc(vitesseE.getValue(), vitesseR.getValue(), doppler.getSource().getFrequenceEmise(), vent.getValue());

            }catch(NullPointerException e){

            }

            frequenceValue.setText(String.valueOf(Math.round(frequenceRep)));
            vitesseVentValue.setText(String.valueOf(Math.round(vent.getValue())));
        });


        Pane group = new Pane(horizon, imageViewDoppler, imageView, fleche, imageView1);
        VBox vBox1 = new VBox(group);
        root3.setCenter(vBox1);

        //SONS AUDIO
        String[] musicFiles =  {"src/sample/ambulance.mp3","src/sample/avion.mp3", "src/sample/fireworks.mp3",
                "src/sample/marteaupiqueur.mp3", "src/sample/tondeuse.mp3"};

        Media[] audios = {new Media(new File(musicFiles[0]).toURI().toString()),
                new Media(new File(musicFiles[1]).toURI().toString()),
                new Media(new File(musicFiles[2]).toURI().toString()),
                new Media(new File(musicFiles[3]).toURI().toString()),
                new Media(new File(musicFiles[4]).toURI().toString())};


        //ONACTION
        guideUti.setOnAction(event -> {                             //A FAIRE ICI LES INSTRUCTIONS
            primaryStage.setScene(scene2);
        });

        retour.setOnAction(event -> {
            primaryStage.setScene(scene);
            current[0] = 0;
            currentPage.setImage(tabPages[current[0]]);
            instructions.setText(tabInstructions[current[0]]);
            index.setText(" " + (current[0]+1) + " / 7 ");
            root2.setLeft(info1); root2.setRight(info2);
        });

        demarrer.setOnAction(event -> {
            primaryStage.setScene(scene3);
            try{ if (mediaPlayer.getMedia()!=null){
                        silencieux.setGraphic(bruit);
                        mediaPlayer.setMute(false); }
            }catch (NullPointerException e){ } });

        quitter.setOnAction(event -> {
            primaryStage.setScene(scene);
        try{ if (mediaPlayer.getMedia()!=null){
                silencieux.setGraphic(mute); mediaPlayer.setMute(true); }
            }catch (NullPointerException e){ }
        });

        double finalDebutFleche = debutFleche;
        reinitialiser.setOnAction(event -> {
           frequenceEmiseValue.setText("0");
           vitesseEmetteurValue.setText("0");
           vitesseRecepteurValue.setText("0");
           intensiteValue.setText("0");
           vitesseVentValue.setText("0");
           line.setStartX(finalDebutFleche);
           line.setEndX(finalDebutFleche);


           vitesseE.setValue(0);
           vitesseR.setValue(0);
           vitesseE.setMin(0);
           vitesseE.setMax(0);
           vent.setValue(0);
           doppler.setStructure(null);

           root3.setBackground(background);

           imageView.setImage(null);
           imageView1.setImage(null);
           imageViewDoppler.setImage(dopplerImage);
           frequenceValue.setText("0");

           try { mediaPlayer.stop(); }catch (NullPointerException e){ }

        });

        structure1.setOnAction(event -> {
            Bouchons bouchons = new Bouchons();
            doppler.setStructure(bouchons);
            imageViewDoppler.setImage(image7);
            protection(doppler, intensiteValue, line);
        });

        structure2.setOnAction(event -> {
            CacheOreilles cacheOreilles = new CacheOreilles();
            doppler.setStructure(cacheOreilles);
            imageViewDoppler.setImage(image6);
            protection(doppler, intensiteValue, line);
        });

        structure3.setOnAction(event -> {
            Mur mur = new Mur();
            doppler.setStructure(mur);
            imageViewDoppler.setImage(image9);
            protection(doppler, intensiteValue, line);
        });

        structure4.setOnAction(event -> {
            Oreiller oreiller = new Oreiller();
            doppler.setStructure(oreiller);
            imageViewDoppler.setImage(image8);
            protection(doppler, intensiteValue, line);
        });

        structure5.setOnAction(event -> {
            doppler.setStructure(null);
            imageViewDoppler.setImage(dopplerImage);
            doppler.Decibels(line, doppler.getSource().getIntensiteEmise());

            try{
                intensiteValue.setText(String.valueOf(doppler.getSource().getIntensiteEmise()));
            }catch (NullPointerException e){
                System.out.println("null pointer strusture 5 aucun");
            }

            try { mediaPlayer.setVolume(1); }catch (Exception e){ }
        });

        source1.setOnAction(event -> {
            reinitialiser.fire();
            Ambulance ambulance = new Ambulance();
            doppler.setSource(ambulance);
            imageView.setImage(image1);
            imageView1.setImage(null);
            ambulance.setImage(image1);
            intensiteValue.setText(String.valueOf(ambulance.getIntensiteEmise()));
            frequenceValue.setText(String.valueOf(ambulance.getFrequenceEmise()));

            imageView.setX(imageViewDoppler.getX()+450);
            imageView.setY(screenSize.getHeight()- (horizon.getStrokeWidth()+ (imageView.getFitHeight())));

            vitesseE.setMin(-150);
            vitesseE.setMax(150);
            vitesseE.setMajorTickUnit(50);
            vitesseE.setMinorTickCount(0);

            entier = 50;

            doppler.Decibels(line, doppler.getSource().getIntensiteEmise());
            frequenceEmiseValue.setText(String.valueOf(doppler.getSource().getFrequenceEmise()));

            sound(audios[0]);silencieux.setGraphic(bruit);

            frequenceRep = doppler.frequenceCalc(vitesseE.getValue(), vitesseR.getValue(), ambulance.getFrequenceEmise(), vent.getValue());

        });

        source2.setOnAction(event -> {
            reinitialiser.fire();
            Avion avion = new Avion();
            doppler.setSource(avion);
            imageView.setImage(image3);
            imageView1.setImage(null);
            avion.setImage(image3);
            intensiteValue.setText(String.valueOf(avion.getIntensiteEmise()));
            frequenceValue.setText(String.valueOf(avion.getFrequenceEmise()));

            imageView.setY(screenSize.getHeight()/15);
            imageView.setX(screenSize.getWidth() - (rect.getWidth()*2 + 350));


            vitesseE.setMin(-1000);
            vitesseE.setMax(1000);
            vitesseE.setMajorTickUnit(500);
            vitesseE.setMinorTickCount(0);

            entier = 400;

            doppler.Decibels(line, doppler.getSource().getIntensiteEmise());
            frequenceEmiseValue.setText(String.valueOf(doppler.getSource().getFrequenceEmise()));

            sound(audios[1]);silencieux.setGraphic(bruit);

            frequenceRep = doppler.frequenceCalc(vitesseE.getValue(), vitesseR.getValue(), avion.getFrequenceEmise(), vent.getValue());
        });

        source3.setOnAction(event -> {
            reinitialiser.fire();
            FeuxArtifice feuxArtifice = new FeuxArtifice();
            doppler.setSource(feuxArtifice);
            imageView.setImage(null);
            intensiteValue.setText(String.valueOf(feuxArtifice.getIntensiteEmise()));
            frequenceValue.setText(String.valueOf(feuxArtifice.getFrequenceEmise()));
            imageView1.setImage(image2);

            imageView1.setY((screenSize.getHeight()/15)-20);
            imageView1.setX(screenSize.getWidth() - (rect.getWidth()*2.5 + 350));

            vitesseE.setMin(0);
            vitesseE.setMax(0);
            vitesseE.setMajorTickUnit(1);
            vitesseE.setMinorTickCount(0);

            FadeTransition fade = new FadeTransition(Duration.seconds(1.5), imageView1);
            fade.setFromValue(1.0);
            fade.setToValue(0);
            fade.setCycleCount(Timeline.INDEFINITE);
            fade.play();

            doppler.Decibels(line, doppler.getSource().getIntensiteEmise());
            frequenceEmiseValue.setText(String.valueOf(doppler.getSource().getFrequenceEmise()));


            sound(audios[2]);silencieux.setGraphic(bruit);

           frequenceRep = doppler.frequenceCalc(vitesseE.getValue(), vitesseR.getValue(), feuxArtifice.getFrequenceEmise(), vent.getValue());
        });

        source4.setOnAction(event -> {
            reinitialiser.fire();
            Marteau marteau = new Marteau();
            doppler.setSource(marteau);
            imageView.setImage(image4);
            imageView1.setImage(null);
            marteau.setImage(image4);
            intensiteValue.setText(String.valueOf(marteau.getIntensiteEmise()));
            frequenceValue.setText(String.valueOf(marteau.getFrequenceEmise()));

            imageView.setX(imageViewDoppler.getX()+450);
            imageView.setY(screenSize.getHeight()- (horizon.getStrokeWidth()+ (imageView.getFitHeight())));

            vitesseE.setMin(-5);
            vitesseE.setMax(5);
            vitesseE.setMajorTickUnit(1);
            vitesseE.setMinorTickCount(0);

            entier = 5;

            doppler.Decibels(line, doppler.getSource().getIntensiteEmise());
            frequenceEmiseValue.setText(String.valueOf(doppler.getSource().getFrequenceEmise()));

            sound(audios[3]);silencieux.setGraphic(bruit);

            frequenceRep = doppler.frequenceCalc(vitesseE.getValue(), vitesseR.getValue(), marteau.getFrequenceEmise(), vent.getValue());
        });

        source5.setOnAction(event -> {
            reinitialiser.fire();
            Tondeuse tondeuse = new Tondeuse();
            doppler.setSource(tondeuse);
            imageView.setImage(image5);
            imageView1.setImage(null);
            tondeuse.setImage(image5);
            intensiteValue.setText(String.valueOf(tondeuse.getIntensiteEmise()));
            frequenceValue.setText(String.valueOf(tondeuse.getFrequenceEmise()));

            imageView.setX(imageViewDoppler.getX()+450);
            imageView.setY(screenSize.getHeight()- (horizon.getStrokeWidth()+ (imageView.getFitHeight())));

            vitesseE.setMin(-5);
            vitesseE.setMax(5);
            vitesseE.setMajorTickUnit(1);
            vitesseE.setMinorTickCount(0);

            entier = 5;

            doppler.Decibels(line, doppler.getSource().getIntensiteEmise());
            frequenceEmiseValue.setText(String.valueOf(doppler.getSource().getFrequenceEmise()));

            sound(audios[4]);silencieux.setGraphic(bruit);

            frequenceRep = doppler.frequenceCalc(vitesseE.getValue(), vitesseR.getValue(), tondeuse.getFrequenceEmise(), vent.getValue());
        });

        primaryStage.show();
    }

    public void protection(Personnage doppler,Label intensiteValue,Line line){
        if (doppler.getSource()==null){ intensiteValue.setText("0"); }
        else { doppler.Decibels(line, doppler.getStructure().Isolation(doppler.getSource()));
            intensiteValue.setText(String.valueOf(doppler.getStructure().Isolation(doppler.getSource())));
            volume(Double.parseDouble(intensiteValue.getText()), mediaPlayer, doppler.getSource().getIntensiteEmise());}
    }

    public void sound(Media media){
        try { mediaPlayer.stop(); }catch (NullPointerException e){}
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(1);
        mediaPlayer.play();
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    public void volume(double intensitePercue, MediaPlayer mediaPlayer, double intensiteInitiale){
        if (intensitePercue == 0){ try { mediaPlayer.setVolume(0); }catch (Exception e){ } }
        else { mediaPlayer.setVolume((intensitePercue/intensiteInitiale)); }
    }
}
