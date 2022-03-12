package com.example.hackathon2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


/**
 * JavaFX App
 */
public class App extends Application {
    
//Legger til variabler og diverse klasser som skal inn i scene    
        int xruter = 28;
        int yruter = 31;
        int pixstrl = 20;
        int curScore = 0;
        int p1Score = 0;
        int p2Score = 0;
        int highScore = 0;
        int intliv = 3;
        int womanLiv = 3;
        int interval = 0;
        int antMat = 0;
        int antStorMat = 0;
        int spawnX = 0;
        int spawnY = 0;
        int spawnX1 = 0;
        int spawnY1 = 0;
        Double blinkyspeed = 0.40;
        Double inkyspeed = 0.35;
        Double pinkyspeed = 0.30;
        Double clydespeed = 0.25;
        Double pacmanspeed = 0.1;
        String curScoreString = Integer.toString(curScore);
        String p1ScoreString = Integer.toString(p1Score);
        String p2ScoreString = Integer.toString(p2Score);
        String highScoreString = Integer.toString(highScore);
        String kart = "";
        boolean start = false;
        boolean gameover = false;
        boolean storMatFull = true;
        ArrayList<Circle> mat = new ArrayList<>();
        ArrayList<Circle> storMat = new ArrayList<>();
        ArrayList<ImageView> spøkels = new ArrayList<>();
        int[] highScores = new int[50];
        //Legger til pacman og pacwoman så de kan kalles i metoder
        Pacman pacman = new Pacman(pixstrl, pixstrl, pacmanspeed);
        Pacwoman pacwoman = new Pacwoman(pixstrl, pixstrl, pacmanspeed);
        //Legger til en av guttene så de også kan kalles og endres på
        Blinky blinky = new Blinky(pixstrl, pixstrl, blinkyspeed);
        Inky inky = new Inky(pixstrl, pixstrl, inkyspeed);
        Pinky pinky = new Pinky(pixstrl, pixstrl, pinkyspeed);
        Clyde clyde = new Clyde(pixstrl, pixstrl, clydespeed);
        Font font = Font.loadFont("file:Fonts/crackman.ttf", 45);
        Timeline pacmanSuperTimeline;
        Timeline superPacwomanTimeline;
        
        //Hovedmeny
        BorderPane bp2 = new BorderPane();
            VBox logo = new VBox();
                Text logoTekst = new Text("På ingen måte:");
                Image pacmanLogo = new Image("file:pacman-logo.png");
                ImageView pacLogo = new ImageView(pacmanLogo);
            VBox menu = new VBox();
                Button onePlayer = new Button("1 Spiller");
                Button twoPlayer = new Button("2 Spillere");
                Button highScorebtn = new Button("Highscores");
        //2-Player
        BorderPane bp1 = new BorderPane();
            Pane pane1 = new Pane();
                TilePane tp1 = new TilePane();
                Pane pp1 = new Pane();
                
                HBox highscorep1 = new HBox();
                HBox score1 = new HBox();
                    HBox scoretekst1 = new HBox();
                        Label cs1 = new Label("P1 SCORE:");
                    HBox scoretall1 = new HBox();
                        Label cst1 = new Label(p1ScoreString);
                HBox score2 = new HBox();
                    HBox scoretekst2 = new HBox();
                        Label cs2 = new Label("P2 SCORE:");
                    HBox scoretall2 = new HBox();
                        Label cst2 = new Label(p2ScoreString);
                        
                        HBox liv1 = new HBox();
                HBox antLiv1 = new HBox();
                    ImageView iv51 = new ImageView();
                    ImageView iv61 = new ImageView();
                    ImageView iv71 = new ImageView();

                HBox antLiv2 = new HBox();
                    ImageView iv52 = new ImageView();
                    ImageView iv62 = new ImageView();
                    ImageView iv72 = new ImageView();
        //1- Player
        BorderPane bp = new BorderPane();
            Label label = new Label("");
            Label nhs = new Label("");
            Button avslutt = new Button("Avslutt");
       
            Pane pane = new Pane();
                TilePane tp = new TilePane();
                Pane pp = new Pane();
                
            HBox highscore = new HBox();
                HBox score = new HBox();
                    HBox scoretekst = new HBox();
                        Label cs = new Label("SCORE:");
                    HBox scoretall = new HBox();
                        Label cst = new Label(curScoreString);
                HBox hscore = new HBox();
                    HBox hscoretekst = new HBox();
                        Label hs = new Label("HIGH SCORE:");
                    HBox hscoretall = new HBox();
                        Label hst = new Label(highScoreString);
                
            HBox liv = new HBox();
                HBox antLiv = new HBox();
                    ImageView iv5 = new ImageView();
                    ImageView iv6 = new ImageView();
                    ImageView iv7 = new ImageView();

            BorderPane bp3 = new BorderPane();
                VBox highScoresTab = new VBox();
                Text top1 = new Text();
                Text top2 = new Text();
                Text top3 = new Text();
                Text top4 = new Text();
                Text top5 = new Text();

    @Override
    public void start(Stage stage) throws Exception {
        
//Legg til posisjonering og styling på panes og diverse
        bp3.setCenter(highScoresTab);
        bp3.setStyle("-fx-background-color: black; -fx-background-image: url('https://c4.wallpaperflare.com/wallpaper/948/812/920/pac-man-wallpaper-preview.jpg'); -fx-background-repeat: no-repeat; -fx-background-position: top center;");
        highScoresTab.getChildren().addAll(top1,top2,top3,top4,top5);
        highScoresTab.setAlignment(Pos.CENTER);
        highScoresTab.setTranslateY(150);
        top1.setFont(font);
        top2.setFont(font);
        top3.setFont(font);
        top4.setFont(font);
        top5.setFont(font);
        top1.setFill(Color.YELLOW);
        top2.setFill(Color.YELLOW);


        bp2.setCenter(menu);
        bp2.setTop(logo);
        bp2.setStyle("-fx-background-color: black; -fx-background-image: url('https://c4.wallpaperflare.com/wallpaper/948/812/920/pac-man-wallpaper-preview.jpg'); -fx-background-repeat: no-repeat; -fx-background-position: top center;");
            logo.setAlignment(Pos.CENTER);
            logo.getChildren().addAll(logoTekst, pacLogo);
            logoTekst.setFont(font);
            logoTekst.setFill(Color.web("#fcc73f"));
            //logoTekst.setStroke(Color.web("#fcc73f"));
            menu.getChildren().addAll(onePlayer, twoPlayer, highScorebtn);
            menu.setAlignment(Pos.CENTER);
            onePlayer.setStyle("-fx-font-size: 2em; -fx-padding: 10px; -fx-border-insets: 5px; -fx-background-insets: 5px; -fx-background-color: #FFFF00");
            onePlayer.setMinWidth(200);
            twoPlayer.setStyle("-fx-font-size: 2em; -fx-padding: 10px; -fx-border-insets: 5px; -fx-background-insets: 5px; -fx-background-color: #FFFF00");
            twoPlayer.setMinWidth(200);
            highScorebtn.setStyle("-fx-font-size: 2em; -fx-padding: 10px; -fx-border-insets: 5px; -fx-background-insets: 5px; -fx-background-color: #FFFF00");
            highScorebtn.setMinWidth(200);

        bp1.setCenter(pane1);
            pane1.getChildren().addAll(tp1, pp1);      
                tp1.setMinWidth(51*pixstrl);
                tp1.setMinHeight(31*pixstrl);
                tp1.setStyle("-fx-background-color:black");
                tp1.setPadding(new Insets(10, 0, 0, 0));
                pp1.setPrefSize(51*pixstrl,31*pixstrl);
        bp1.setTop(highscorep1);
            highscorep1.getChildren().addAll(score1, score2);
            highscorep1.setStyle("-fx-background-color:black");
                score1.getChildren().addAll(scoretekst1, scoretall1);
                score2.getChildren().addAll(scoretekst2, scoretall2);
                score2.setTranslateX(51*20-300);
                    scoretekst1.getChildren().add(cs1);
                        cs1.setPadding(new Insets(10,0,0,0));
                        cs1.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                        cs1.setTextFill(Color.web("#FFFFFF"));
                        cs1.setTranslateX(30);
                    scoretall1.getChildren().add(cst1);
                        cst1.setPadding(new Insets(10,0,0,0));
                        cst1.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                        cst1.setTextFill(Color.web("#FFFFFF"));
                        cst1.setTranslateX(35);
                    scoretekst2.getChildren().add(cs2);   
                        cs2.setPadding(new Insets(10,0,0,0));
                        cs2.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                        cs2.setTextFill(Color.web("#FFFFFF"));
                        cs2.setTranslateX(30);    
                    scoretall2.getChildren().add(cst2);
                        cst2.setPadding(new Insets(10,0,0,0));
                        cst2.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                        cst2.setTextFill(Color.web("#FFFFFF"));
                        cst2.setTranslateX(35);
                        
        bp1.setBottom(liv1);
            liv1.getChildren().addAll(antLiv2, antLiv1);
            liv1.setStyle("-fx-background-color:black");   
            
        bp.setCenter(pane);
            pane.getChildren().addAll(tp, pp);      
                tp.setMinWidth(xruter*pixstrl);
                tp.setMinHeight(yruter*pixstrl);
                tp.setStyle("-fx-background-color:black");
                tp.setPadding(new Insets(10, 0, 0, 0));
                pp.setPrefSize(xruter*pixstrl,yruter*pixstrl);
        bp.setTop(highscore);
            highscore.getChildren().addAll(score, hscore);
            highscore.setStyle("-fx-background-color:black");
                score.getChildren().addAll(scoretekst, scoretall);

                    scoretekst.getChildren().add(cs);   
                        cs.setPadding(new Insets(10,0,0,0));
                        cs.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                        cs.setTextFill(Color.web("#FFFFFF"));
                        cs.setTranslateX(30);
                    scoretall.getChildren().add(cst);
                        cst.setPadding(new Insets(10,0,0,0));
                        cst.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                        cst.setTextFill(Color.web("#FFFFFF"));
                        cst.setTranslateX(35);
                hscore.getChildren().addAll(hscoretekst, hscoretall);
                    hscoretekst.getChildren().add(hs);  
                        hs.setPadding(new Insets(10,0,0,0));
                        hs.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                        hs.setTextFill(Color.web("#FFFFFF"));
                        hs.setTranslateX(260); 
                    hscoretall.getChildren().add(hst);
                        hst.setPadding(new Insets(10,0,0,0));
                        hst.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                        hst.setTextFill(Color.web("#FFFFFF"));
                        hst.setTranslateX(265);
                        
                        highScore = hentHighScore();
                        String hsString = Integer.toString(highScore);
                        hst.setText(hsString);
                        
        bp.setBottom(liv);
            liv.getChildren().add(antLiv);
            liv.setStyle("-fx-background-color:black");   
                                    
//Legg til scene på stage
        var sceneMenu = new Scene(bp2, xruter*pixstrl, yruter*pixstrl+54);
        
        stage.setScene(sceneMenu); 
        stage.setTitle("Pacman Menu");
        stage.setResizable(false);
        stage.show();
        String musicFile = "intro.mp3";
        AudioClip clip = new AudioClip(new File(musicFile).toURI().toString());
        clip.play();

             
        //om spilleren velger single-player
        onePlayer.setOnAction(e -> {
            
            var sceneOnePlayer = new Scene(bp, xruter*pixstrl, yruter*pixstrl+54);
            stage.setScene(sceneOnePlayer);
            addKeyHandler(sceneOnePlayer);
            stage.setTitle("Pacman");
            stage.show();
            kart = "map.txt";
                       
            
            //Legg til kartet, legg til Mat og i arraylist, legg til stormat og finn spawns
            Kart map = new Kart(20, 20, kart);
            for(int i = 0; i<31;i++) {  
                for(int j = 0; j<28;j++) {
                    try {
                        tp.getChildren().add(map.make(i, j));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    //legg til mat og legg i arraylist
                    try {
                        if (Kart.mapXY(kart)[i][j] == 1){
                            Mat maten = new Mat(5, j, i);
                            pp.getChildren().add(maten.sirkel);
                            mat.add(maten.sirkel);
                            antMat++;
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    //legg til stor mat og legg i arraylist
                    try {
                        if (Kart.mapXY(kart)[i][j] == 2){
                            StorMat storMaten = new StorMat(8, j, i);
                            pp.getChildren().add(storMaten.sirkel);
                            storMat.add(storMaten.sirkel);
                            antStorMat++;
                        }   } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    try {
                        if (Kart.mapXY(kart)[i][j] == 5) {
                            
                            spawnX = j;
                            spawnY = i;
                            
                        }   } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }                                
            
            //Legg til liv        
            leggTilLiv(intliv);  
            
        });
        //om spiller velger 2- Player
        twoPlayer.setOnAction(e -> {
            
            xruter = 51;
            yruter = 31;
            var sceneTwoPlayer = new Scene(bp1, xruter*pixstrl, yruter*pixstrl+54);
            mat.clear();
            storMat.clear();
            antMat = 0;
            antStorMat = 0;
            stage.setScene(sceneTwoPlayer);
            addKeyHandler1(sceneTwoPlayer);
            stage.setTitle("Pacman vs Pacwoman");
            stage.show();
            kart = "map2p.txt";
            //Legg til kartet
            Kart map = new Kart(20, 20, kart);
            for(int i = 0; i<31;i++) {  
                for(int j = 0; j<51;j++) {
                    try {
                        tp1.getChildren().add(map.make2P(i, j));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    //Legg til Mat og legg inn i ArrayList 
                    try {
                        if (Kart.mapXY2P(kart)[i][j] == 1){
                            Mat maten = new Mat(5, j, i);
                            pp1.getChildren().add(maten.sirkel);
                            mat.add(maten.sirkel);
                            antMat++;
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    //les inn stor mat og legg i arraylist
                    try {
                        if (Kart.mapXY2P(kart)[i][j] == 2){
                            StorMat storMaten = new StorMat(8, j, i);
                            pp1.getChildren().add(storMaten.sirkel);
                            storMat.add(storMaten.sirkel);
                            antStorMat++;
                        }   } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    //pacman spawns
                    try {
                        if (Kart.mapXY2P(kart)[i][j] == 5){
                            spawnX = j;
                            spawnY = i;
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    //pacwoman spawns
                    try {
                        if (Kart.mapXY2P(kart)[i][j] == 6){
                            spawnX1 = j;
                            spawnY1 = i;
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
            //Legg til liv for spiller 1
            leggTilLiv3(intliv);

            //Legg til liv for spiller 2
            leggTilLiv2(womanLiv);
        });

        highScorebtn.setOnAction(e -> {
            var sceneHighScore = new Scene(bp3, xruter*pixstrl, yruter*pixstrl);
            stage.setScene(sceneHighScore);
            stage.setTitle("Highscores");
            stage.show();
            try {
                getHighScores();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });
    }
    
    

    public static void main(String[] args) {
        
        launch();
                
    }
    
    //Metode vinnerskjerm / taperskjerm
    public void gameOver(int vinntap) {
        bp.getChildren().clear();
        pp.getChildren().clear();
        tp.getChildren().clear();
        bp.setCenter(null);
        bp.setCenter(label);
        bp.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        label.setTranslateX(25);
        if (vinntap == 0) { //tap
            label.setFont(Font.font ("Verdana", FontWeight.BOLD, 25));
            label.setTextFill(Color.RED);
            label.setVisible(true);
            label.setText("GAME OVER");
            gameover = true;
            endScore();
        } else if (vinntap == 1){
            label.setFont(Font.font ("Verdana", FontWeight.BOLD, 25));
            label.setTextFill(Color.GREEN);
            label.setVisible(true);
            label.setText("LEVEL COMPLETE");
            gameover = true;
            endScore();
        }
    }
    public void gameOverP2(int vinntap) {
        bp1.getChildren().clear();
        pp1.getChildren().clear();
        tp1.getChildren().clear();
        bp1.setCenter(null);
        bp1.setCenter(label);
        bp1.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        if (vinntap == 1) { //tap
            label.setFont(Font.font ("Verdana", FontWeight.BOLD, 25));
            label.setTextFill(Color.GREEN);
            label.setVisible(true);
            label.setText("PLAYER 1 WINS");
            gameover = true;
            endScore2P();
        } else if (vinntap == 2){
            label.setFont(Font.font ("Verdana", FontWeight.BOLD, 25));
            label.setTextFill(Color.GREEN);
            label.setVisible(true);
            label.setText("PLAYER 2 WINS");
            gameover = true;
            endScore2P();
    }
    }
    
    //Metode vis score på endscreen
    public void endScore() {
        bp.setLeft(score);
        bp.setRight(hscore);
        bp.setTop(nhs);
        cs.setTextFill(Color.WHITE);
        cst.setTextFill(Color.WHITE);
        hs.setTextFill(Color.WHITE);
        hst.setTextFill(Color.WHITE);
        cs.setTranslateX(xruter*pixstrl/2-50);
        cs.setTranslateY(400);
        cst.setTranslateX(xruter*pixstrl/2+5-50);
        cst.setTranslateY(400);
        hs.setTranslateX(-xruter*pixstrl/2+60);
        hs.setTranslateY(210);
        hst.setTranslateX(-xruter*pixstrl/2+5+60);
        hst.setTranslateY(210);
        nhs.setTranslateX(xruter*pixstrl/2-125);
        nhs.setTranslateY(100);
        nhs.setFont(Font.font ("Verdana", FontWeight.BOLD, 25));
        nhs.setTextFill(Color.GOLD);
        if (curScore > highScore) {
            highScore = curScore;
            nhs.setText("NEW HIGHSCORE!");
            nyHighScore(highScore);
        }
    }
    
    //Metode vis score på endscreen
    public void endScore2P() {
        VBox scoreContainer = new VBox();
        HBox newHighScore = new HBox();
        HBox scores = new HBox();
        String hsString = Integer.toString(this.p1Score);
        String hsString1 = Integer.toString(this.p2Score);
        Label p1Score = new Label("P1 score: " +hsString + "      ");
        Label p2Score = new Label("P2 score: "+hsString1);
        Label p1hScore = new Label("NEW HIGHSCORE!        ");
        Label p2hScore = new Label("NEW HIGHSCORE!");

        bp1.setBottom(scoreContainer);
        scoreContainer.getChildren().addAll(newHighScore, scores);
        scoreContainer.setAlignment(Pos.CENTER);
        newHighScore.getChildren().addAll(p1hScore, p2hScore);
        newHighScore.setAlignment(Pos.CENTER);
        newHighScore.setTranslateY(-150);
        p1hScore.setVisible(false);
        p2hScore.setVisible(false);
        scores.getChildren().addAll(p1Score, p2Score);
        scores.setAlignment(Pos.CENTER);
        scores.setTranslateY(-100);
        p1Score.setTextFill(Color.YELLOW);
        p2Score.setTextFill(Color.YELLOW);
        p1Score.setFont(Font.font ("Verdana", FontWeight.BOLD, 25));
        p2Score.setFont(Font.font ("Verdana", FontWeight.BOLD, 25));
        p1hScore.setTextFill(Color.GREEN);
        p2hScore.setTextFill(Color.GREEN);
        p1hScore.setFont(Font.font ("Verdana", FontWeight.BOLD, 25));
        p2hScore.setFont(Font.font ("Verdana", FontWeight.BOLD, 25));
        if (this.p1Score > highScore) {
            highScore = this.p1Score;
            nyHighScore(this.p1Score);
            p1hScore.setVisible(true);
        } else if (this.p2Score > highScore) {
            highScore = this.p2Score;
            nyHighScore(this.p2Score);
            p2hScore.setVisible(true);
        }
    }

    public void getHighScores() throws FileNotFoundException {
        File fil = new File("highscore.txt");
        int temp = 0;
        int highest = 0;
        try (Scanner leser = new Scanner(fil)) {
            while (leser.hasNextInt()) {
                temp = leser.nextInt();
                if (temp > highest) {
                    highest = temp;
                }
            }
        }
        String hsString = Integer.toString(highest);
        String hsString2 = Integer.toString(temp);
        top1.setText("Top 1: " +hsString);
        top2.setText("Top 2: " +hsString2);
    }

    public int hentHighScore() throws FileNotFoundException {
        File fil = new File("highscore.txt");
        int temp = 0;
        int highest = 0;
        try (Scanner leser = new Scanner(fil)) {
            while (leser.hasNextInt()) {
                temp = leser.nextInt();
                if (temp < highest) {
                    highest = temp;
                }
            }
        }
        return highest;
    }

    //Metode lag ny highscore på dokument
    public void nyHighScore(int highscore) {
        
            try {
                try (FileWriter skriver = new FileWriter("highscore.txt", true)) {
                    skriver.write(" ");
                    skriver.write(String.valueOf(highscore));
                }
                
            } catch (IOException ex) {
                
            }             
    }

    //Metode hent highscore fra dokument
    public int hentHighScore2(int sistHS) throws FileNotFoundException {
        int highsco;
        int highest = sistHS;
        int temp;
        File fil = new File("highscore.txt");
        try (Scanner leser = new Scanner(fil)) {
            while (leser.hasNextInt()) {
                highsco = leser.nextInt();
                temp = highsco;
                if (temp > highest) {
                    highest = temp;
                }

            }
        }
        return highest;
    }
    
    //Legg til liv nederst på skjermen
    public void leggTilLiv(int liv) {
        
        Image pacmanopen = new Image("file:pacmanopen.png");
        int livnr = 0;
        
            switch (liv) {
                case 1:
                    iv5.setImage(pacmanopen);
                    iv5.setFitWidth(15);
                    iv5.setFitHeight(15);
                    iv5.setX(10*livnr);
                    iv5.setY(300);
                    iv5.setRotate(0);
                    antLiv.getChildren().add(iv5);
                    break;
                case 2:
                    iv5.setImage(pacmanopen);
                    iv5.setFitWidth(15);
                    iv5.setFitHeight(15);
                    iv5.setX(10*livnr);
                    iv5.setY(300);
                    iv5.setRotate(0);
                    antLiv.getChildren().add(iv5);
                    iv6.setImage(pacmanopen);
                    iv6.setFitWidth(15);
                    iv6.setFitHeight(15);
                    iv6.setX(30);
                    iv6.setY(300);
                    iv6.setRotate(0);
                    antLiv.getChildren().add(iv6);
                    break;
                case 3:
                    iv5.setImage(pacmanopen);
                    iv5.setFitWidth(15);
                    iv5.setFitHeight(15);
                    iv5.setX(10*livnr);
                    iv5.setY(300);
                    iv5.setRotate(0);
                    antLiv.getChildren().add(iv5);
                    iv6.setImage(pacmanopen);
                    iv6.setFitWidth(15);
                    iv6.setFitHeight(15);
                    iv6.setX(30);
                    iv6.setY(300);
                    iv6.setRotate(0);
                    antLiv.getChildren().add(iv6);
                    iv7.setImage(pacmanopen);
                    iv7.setFitWidth(15);
                    iv7.setFitHeight(15);
                    iv7.setX(50);
                    iv7.setY(300);
                    iv7.setRotate(0);
                    antLiv.getChildren().add(iv7);
                    break;
                default:
                    break;
            }
            
    }   
    
    //Legg til liv nederst på skjermen for PacWoman
    public void leggTilLiv2(int liv) {
        
        Image pacwomanopen = new Image("file:pacwomanopen.png");
        int livnr = 0;
        
            switch (liv) {
                case 1:
                    iv52.setImage(pacwomanopen);
                    iv52.setFitWidth(15);
                    iv52.setFitHeight(15);
                    iv52.setX(10*livnr);
                    iv52.setY(300);
                    iv52.setRotate(0);
                    antLiv2.getChildren().add(iv52);
                    break;
                case 2:
                    iv52.setImage(pacwomanopen);
                    iv52.setFitWidth(15);
                    iv52.setFitHeight(15);
                    iv52.setX(10*livnr);
                    iv52.setY(300);
                    iv52.setRotate(0);
                    antLiv2.getChildren().add(iv52);
                    iv62.setImage(pacwomanopen);
                    iv62.setFitWidth(15);
                    iv62.setFitHeight(15);
                    iv62.setX(30);
                    iv62.setY(300);
                    iv62.setRotate(0);
                    antLiv2.getChildren().add(iv62);
                    break;
                case 3:
                    iv52.setImage(pacwomanopen);
                    iv52.setFitWidth(15);
                    iv52.setFitHeight(15);
                    iv52.setX(10*livnr);
                    iv52.setY(300);
                    iv52.setRotate(0);
                    antLiv2.getChildren().add(iv52);
                    iv62.setImage(pacwomanopen);
                    iv62.setFitWidth(15);
                    iv62.setFitHeight(15);
                    iv62.setX(30);
                    iv62.setY(300);
                    iv62.setRotate(0);
                    antLiv2.getChildren().add(iv62);
                    iv72.setImage(pacwomanopen);
                    iv72.setFitWidth(15);
                    iv72.setFitHeight(15);
                    iv72.setX(50);
                    iv72.setY(300);
                    iv72.setRotate(0);
                    antLiv2.getChildren().add(iv72);
                    break;
                default:
                    break;
            }
            
    }   
    
    //Legg til liv nederst på skjermen
    public void leggTilLiv3(int liv) {
        
        Image pacmanopen = new Image("file:pacmanopen.png");
        
            switch (liv) {
                case 1:
                    iv51.setImage(pacmanopen);
                    iv51.setFitWidth(15);
                    iv51.setFitHeight(15);
                    iv51.setX(500);
                    iv51.setY(300);
                    iv51.setRotate(180);
                    antLiv1.getChildren().add(iv51);
                    antLiv1.setTranslateX(51*pixstrl-60);
                    break;
                case 2:
                    iv51.setImage(pacmanopen);
                    iv51.setFitWidth(15);
                    iv51.setFitHeight(15);
                    iv51.setX(500);
                    iv51.setY(300);
                    iv51.setRotate(180);
                    antLiv1.getChildren().add(iv51);
                    iv61.setImage(pacmanopen);
                    iv61.setFitWidth(15);
                    iv61.setFitHeight(15);
                    iv61.setX(50);
                    iv61.setY(300);
                    iv61.setRotate(180);
                    antLiv1.getChildren().add(iv61);
                    antLiv1.setTranslateX(51*pixstrl-75);
                    break;
                case 3:
                    iv51.setImage(pacmanopen);
                    iv51.setFitWidth(15);
                    iv51.setFitHeight(15);
                    iv51.setX(500);
                    iv51.setY(300);
                    iv51.setRotate(180);
                    antLiv1.getChildren().add(iv51);
                    iv61.setImage(pacmanopen);
                    iv61.setFitWidth(15);
                    iv61.setFitHeight(15);
                    iv61.setX(500);
                    iv61.setY(300);
                    iv61.setRotate(180);
                    antLiv1.getChildren().add(iv61);
                    iv71.setImage(pacmanopen);
                    iv71.setFitWidth(15);
                    iv71.setFitHeight(15);
                    iv71.setX(500);
                    iv71.setY(300);
                    iv71.setRotate(180);
                    antLiv1.getChildren().add(iv71);
                    antLiv1.setTranslateX(51*pixstrl-90);
                    break;
                default:
                    break;
            }
            
    }   
    
    //Legg til eventhandler på arrowkeys og legg til pacmanstyring
    private void addKeyHandler(Scene scene) {
           
    scene.setOnKeyPressed(ke -> {
        KeyCode keyCode = ke.getCode();
        if (keyCode.equals(KeyCode.UP)) { 
            pacman.setRetning(3);
            if (start == false) {
                if (intliv == 3) {
                    spawnMoveables();
                    sjekkAlt();
                    moveAll();
                }
                altSynlig();
                pacman.iv.setRotate(270);
                start = true;
            } else {              
                pacman.iv.setRotate(270);
            }           
        }
        if (keyCode.equals(KeyCode.RIGHT)) {
            pacman.setRetning(0);
            if (start == false) {
                if (intliv == 3) {
                    spawnMoveables();
                    sjekkAlt();
                    moveAll();
                }
                altSynlig();
                pacman.iv.setRotate(0);
                start = true;
            } else {              
                pacman.iv.setRotate(0);
            }           
        }
        if (keyCode.equals(KeyCode.DOWN)) {
            pacman.setRetning(1);
            if (start == false) {
                if (intliv == 3) {
                    spawnMoveables();
                    sjekkAlt();
                    moveAll();
                }
                altSynlig();
                pacman.iv.setRotate(90);
                start = true;
            } else {              
                pacman.iv.setRotate(90);
            }          
        }
        if (keyCode.equals(KeyCode.LEFT)) {
            pacman.setRetning(2);
            if (start == false) {
                if (intliv == 3) {
                    spawnMoveables();
                    sjekkAlt();
                    moveAll();
                }
                altSynlig();
                pacman.iv.setRotate(180);
                start = true;
            } else {              
                pacman.iv.setRotate(180);
            }          
        }
    });       
    }    
    
    //Legg til eventhandler på wasd og legg til pacwomanstyring
    private void addKeyHandler1(Scene scene) {
           
    scene.setOnKeyPressed(ke -> {
        KeyCode keyCode = ke.getCode();
        if (keyCode.equals(KeyCode.W)) { 
            pacwoman.setRetning(3);
            if (start == false) {
                if (intliv == 3) {
                    spawnMoveables2P();
                    sjekkAlt2P();
                    moveAll2P();
                }
                altSynlig2P();
                pacwoman.iv.setRotate(270);
                start = true;
            } else {              
                pacwoman.iv.setRotate(270);
            }           
        }
        if (keyCode.equals(KeyCode.D)) {
            pacwoman.setRetning(0);
            if (start == false) {
                if (intliv == 3) {
                    spawnMoveables2P();
                    sjekkAlt2P();
                    moveAll2P();
                }
                altSynlig2P();
                pacwoman.iv.setRotate(0);
                start = true;
            } else {              
                pacwoman.iv.setRotate(0);
            }           
        }
        if (keyCode.equals(KeyCode.S)) {
            pacwoman.setRetning(1);
            if (start == false) {
                if (intliv == 3) {
                    spawnMoveables2P();
                    sjekkAlt2P();
                    moveAll2P();
                }
                altSynlig2P();
                pacwoman.iv.setRotate(90);
                start = true;
            } else {              
                pacwoman.iv.setRotate(90);
            }          
        }
        if (keyCode.equals(KeyCode.A)) {
            pacwoman.setRetning(2);
            if (start == false) {
                if (intliv == 3) {
                    spawnMoveables2P();
                    sjekkAlt2P();
                    moveAll2P();
                }
                altSynlig2P();
                pacwoman.iv.setRotate(180);
                start = true;
            } else {              
                pacwoman.iv.setRotate(180);
            }          
        }
        
        if (keyCode.equals(KeyCode.UP)) { 
            pacman.setRetning(3);
            if (start == false) {
                if (intliv == 3) {
                    spawnMoveables2P();
                    sjekkAlt2P();
                    moveAll2P();
                }
                altSynlig2P();
                pacman.iv.setRotate(270);
                start = true;
            } else {              
                pacman.iv.setRotate(270);
            }           
        }
        if (keyCode.equals(KeyCode.RIGHT)) {
            pacman.setRetning(0);
            if (start == false) {
                if (intliv == 3) {
                    spawnMoveables2P();
                    sjekkAlt2P();
                    moveAll2P();
                }
                altSynlig2P();
                pacman.iv.setRotate(0);
                start = true;
            } else {              
                pacman.iv.setRotate(0);
            }           
        }
        if (keyCode.equals(KeyCode.DOWN)) {
            pacman.setRetning(1);
            if (start == false) {
                if (intliv == 3) {
                    spawnMoveables2P();
                    sjekkAlt2P();
                    moveAll2P();
                }
                altSynlig2P();
                pacman.iv.setRotate(90);
                start = true;
            } else {              
                pacman.iv.setRotate(90);
            }          
        }
        if (keyCode.equals(KeyCode.LEFT)) {
            pacman.setRetning(2);
            if (start == false) {
                if (intliv == 3) {
                    spawnMoveables2P();
                    sjekkAlt2P();
                    moveAll2P();
                }
                altSynlig2P();
                pacman.iv.setRotate(180);
                start = true;
            } else {              
                pacman.iv.setRotate(180);
            }          
        }
    });
    }
    
    //sett alt synlig
    public void altSynlig() {
        pacman.iv.setVisible(true);
        blinky.iv.setVisible(true);
        inky.iv.setVisible(true);
        pinky.iv.setVisible(true);
        clyde.iv.setVisible(true);
    }
    
    public void altSynlig2P() {
        pacman.iv.setVisible(true);
        pacwoman.iv.setVisible(true);
    }
    
    //begeg alt (spøkelsene har delay fra en til annen)
    public void moveAll() {
        pacman.movePac();
        blinky.move();
        inky.move();
        pinky.move();
        clyde.move();
    }
    
    public void moveAll2P() {
        pacman.movePac2P();
        pacwoman.movePac();
    }
    
    //spawn alle ting som rører seg samtidig
    public void spawnMoveables() {
        try {
            pacman.placeInSpawn(spawnX, spawnY);
            blinky.placeInSpawn(kart);
            spøkels.add(blinky.iv);
            inky.placeInSpawn(kart);
            spøkels.add(inky.iv);
            pinky.placeInSpawn(kart);
            spøkels.add(pinky.iv);
            clyde.placeInSpawn(kart);
            spøkels.add(clyde.iv);
            pp.getChildren().addAll(pacman.iv, blinky.iv, inky.iv, pinky.iv, clyde.iv);
        } catch (Exception ex) {
            
        }
    }     
    
    //spawn alle ting som rører seg samtidig og legg inn i pane
    public void spawnMoveables2P() {
        try {
            pacman.placeInSpawn2P(spawnX, spawnY);
            pacwoman.placeInSpawn(spawnX1, spawnY1);
            pp1.getChildren().addAll(pacman.iv, pacwoman.iv);
            pacman.iv.getX();
        } catch (Exception ex) {
            
        }
    } 
    
    //når en stormat blir plukket opp, skift bilde, sjekk om pacman er på spøkelse isåfall fjern den og gi poeng
    public void superPac() {
            interval = 80;       
            Timeline timeline1 = new Timeline(new KeyFrame(javafx.util.Duration.seconds(0.20), ev1 -> {
                if(interval > 0){
                    sjekkKrasj();
                    killBlue();
                    interval--;
                    if (interval < 10 && interval % 2 == 0) {
                        killWhite();
                    } else if (interval < 10 && interval % 2 != 0) {
                        killBlue();
                    } 
                } else {
                restore();
                }
            }));
            timeline1.setCycleCount(Animation.INDEFINITE);
            if (!gameover && interval > 0) {
                timeline1.play();
            }else {
                timeline1.stop();
            }
        }
    
    //når en stormat blir plukket opp, skift bilde, sjekk om pacman kræsjer i pacwoman eller omvendt isåfall fjern og gi poeng
    public void superPacman() {
        if (superPacwomanTimeline != null) {
            superPacwomanTimeline.stop();
            pacman.notBlue();
            pacman.notWhite();
        }
        interval = 80;       
           pacmanSuperTimeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(0.10), ev1 -> {
                if(interval > 0){
                    pacwoman.isBlue();
                    if(sjekkKrasj2P()) {
                        try {
                            pacmanSuperTimeline.stop();
                            pacwoman.notBlue();
                            pacwoman.notWhite();
                            pacwoman.placeInSpawn(spawnX1, spawnY1);
                            womanLiv--;
                            fjernLivWoman();
                            p2Score += 100;
                            String csString = Integer.toString(p2Score);
                            cst2.setText(csString);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    
                    interval--;
                    if (interval < 10) {
                        pacwoman.isWhite();
                    }
                } else {
                pacwoman.notBlue();
                pacwoman.notWhite();
                }
            }));
            pacmanSuperTimeline.setCycleCount(Animation.INDEFINITE);
            if (!gameover && interval > 0) {
                pacmanSuperTimeline.play();
            }else {
                pacmanSuperTimeline.stop();
            }
        }
    
    //når en stormat blir plukket opp, skift bilde, sjekk om pacwoman kræsjer i pacman isåfall fjern og gi poeng
    public void superPacwoman() {
        if (pacmanSuperTimeline != null) {
            pacmanSuperTimeline.stop();
            pacwoman.notBlue();
            pacwoman.notWhite();
        }
        interval = 80;
        superPacwomanTimeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(0.10), ev1 -> {
            if (interval > 0) {
                pacman.isBlue();
                if (sjekkKrasj2P()) {
                    try {
                        superPacwomanTimeline.stop();
                        pacman.notBlue();
                        pacman.notWhite();
                        pacman.placeInSpawn2P(spawnX, spawnY);
                        intliv--;
                        fjernLivMan();
                        p1Score += 100;
                        String csString = Integer.toString(p1Score);
                        cst1.setText(csString);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                interval--;
                if (interval < 10 ) {
                    pacman.isWhite();
                }
            } else {
                pacman.notBlue();
                pacman.notWhite();
            }
        }));
        superPacwomanTimeline.setCycleCount(Animation.INDEFINITE);
        if (!gameover && interval > 0) {
            superPacwomanTimeline.play();
        } else {
            superPacwomanTimeline.stop();
        }
    }
    
    //sett sårbar spøkelse bilde
    public void killBlue() {
        blinky.dieBlue();
        inky.dieBlue();
        pinky.dieBlue();
        clyde.dieBlue();
    }
    
    //sett 10 sekunder igjen av superpac bilde
    public void killWhite() {
        blinky.dieWhite();
        inky.dieWhite();
        pinky.dieWhite();
        clyde.dieWhite();
    }
    
    //sett vanlig bilde tilbake
    public void restore() {
        blinky.live();
        inky.live();
        pinky.live();
        clyde.live();
    }
    
    //Sjekker alle sjekkene under på kartet hvert 10. millisekund
    public void sjekkAlt() {
        
        Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(0.1), ev -> {
            
                sjekkMat();
                sjekkStorMat();
                if (interval == 0) {
                    sjekkDød();
                }
                sjekkVinst();            
            
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();    
    }
    
    //Sjekker alle sjekkene under på kartet hvert 10. millisekund
    public void sjekkAlt2P() {
        
        Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(0.1), ev -> {
            
                sjekkMatMan();
                sjekkMatWoman();
                sjekkStorMatMan();
                sjekkStorMatWoman();
                sjekkVinst2P(); 
                if (sjekkKrasj2P()) {
                    
                }
            
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();    
    }
    
    //Sjekker om pacman er på mat og isåfall fjerner den
    public void sjekkMat() {
            
        for (int i = 0; i<mat.size(); i++) {
            if (pacman.iv.getX() == mat.get(i).getCenterX()-10 
            && pacman.iv.getY() == mat.get(i).getCenterY()-10 
            && mat.get(i).isVisible() == true ) {
                mat.get(i).setVisible(false);
                mat.remove(i);
                curScore+=10;
                String csString = Integer.toString(curScore);
                cst.setText(csString);
                antMat--;
            }
                            
        }
                                          
    }      
    
    //Sjekker om pacman er på mat og isåfall fjerner den
    public void sjekkMatWoman() {
            
        for (int i = 0; i<mat.size(); i++) {
            if (pacwoman.iv.getX() == mat.get(i).getCenterX()-10 
            && pacwoman.iv.getY() == mat.get(i).getCenterY()-10 
            && mat.get(i).isVisible() == true ) {
                mat.get(i).setVisible(false);
                mat.remove(i);
                p1Score+=10;
                String csString = Integer.toString(p1Score);
                cst1.setText(csString);
                antMat--;
            }
                            
        }
                                          
    } 
    
    //Sjekker om pacman er på mat og isåfall fjerner den
    public void sjekkMatMan() {
            
        for (int i = 0; i<mat.size(); i++) {
            if (pacman.iv.getX() == mat.get(i).getCenterX()-10 
            && pacman.iv.getY() == mat.get(i).getCenterY()-10 
            && mat.get(i).isVisible() == true ) {
                mat.get(i).setVisible(false);
                mat.remove(i);
                p2Score+=10;
                String csString = Integer.toString(p2Score);
                cst2.setText(csString);
                antMat--;
            }
                            
        }
                                          
    } 
    
    //Sjekker om pacman er på en stor mat og isåfall fjerner den
    public void sjekkStorMat() {
        
        for (int i = 0; i<storMat.size(); i++) {
            if (pacman.iv.getX() == storMat.get(i).getCenterX()-10
            && pacman.iv.getY() == storMat.get(i).getCenterY()-10 
            && storMat.get(i).isVisible() == true ) {
                storMat.get(i).setVisible(false);
                storMat.remove(i);
                curScore+=50;
                String csString = Integer.toString(curScore);
                cst.setText(csString);
                superPac();
                storMatFull = false;
                antStorMat--;
            } 
        }
    }
    
    //Sjekker om pacwoman er på en stor mat og isåfall fjerner den
    public void sjekkStorMatWoman() {
        
        for (int i = 0; i<storMat.size(); i++) {
            if (pacwoman.iv.getX() == storMat.get(i).getCenterX()-10
            && pacwoman.iv.getY() == storMat.get(i).getCenterY()-10 
            && storMat.get(i).isVisible() == true ) {
                storMat.get(i).setVisible(false);
                storMat.remove(i);
                p1Score+=50;
                String csString = Integer.toString(p1Score);
                cst1.setText(csString);
                superPacwoman();
                storMatFull = false;
                antStorMat--;
            } 
        }
    }
    
    //Sjekker om pacman er på en stor mat og isåfall fjerner den
    public void sjekkStorMatMan() {
        
        for (int i = 0; i<storMat.size(); i++) {
            if (pacman.iv.getX() == storMat.get(i).getCenterX()-10
            && pacman.iv.getY() == storMat.get(i).getCenterY()-10 
            && storMat.get(i).isVisible() == true ) {
                storMat.get(i).setVisible(false);
                storMat.remove(i);
                p2Score+=50;
                String csString = Integer.toString(p2Score);
                cst2.setText(csString);
                superPacman();
                storMatFull = false;
                antStorMat--;
            } 
        }
    }
    
    //Sjekker om pacman kræsjer med en sårbar ghost
    public boolean sjekkKrasj() {
        for (int i = 0; i<spøkels.size(); i++) {
                    if (pacman.iv.getX() == spøkels.get(i).getX() 
                        && pacman.iv.getY() == spøkels.get(i).getY() 
                        && spøkels.get(i).isVisible() == true ) {
                            spøkels.get(i).setVisible(false);
                            curScore += 100;
                            String csString = Integer.toString(curScore);
                            cst.setText(csString);
                            return true;
                    }
                }
        return false;
    }
    
    //Sjekker om pacman og pacwoman kræsjer
    public boolean sjekkKrasj2P() {
        if (pacman.iv.getX() == pacwoman.iv.getX() && pacman.iv.getY() == pacwoman.iv.getY()) {
            return true;
        }
        return false;
    }
    
    //Sjekker om pacman kræsjer i spøkelse mens de ikke er sårbare
    public void sjekkDød() {
        for (int i = 0; i<spøkels.size(); i++) {
            if (pacman.iv.getX() == spøkels.get(i).getX() 
            && pacman.iv.getY() == spøkels.get(i).getY() 
            && spøkels.get(i).isVisible() == true) {                
                start = false;
                intliv--;  
                død();
                switch (intliv) {
                    case 2:
                        iv7.setVisible(false);
                        break;
                    case 1:
                        iv7.setVisible(false);
                        iv6.setVisible(false);
                        break;
                    case 0:
                        gameOver(0);
                        break;
                    default:
                        break;
                }
            }
        }
    }
    
    public void fjernLivWoman() {
        switch (womanLiv) {
                    case 2:
                        iv72.setVisible(false);
                        break;
                    case 1:
                        iv72.setVisible(false);
                        iv62.setVisible(false);
                        break;
                    case 0:
                        gameOverP2(2);
                        break;
                    default:
                        break;
                }
    }
    
    public void fjernLivMan() {
        switch (intliv) {
                    case 2:
                        iv51.setVisible(false);
                        break;
                    case 1:
                        iv51.setVisible(false);
                        iv61.setVisible(false);
                        break;
                    case 0:
                        gameOverP2(1);
                        break;
                    default:
                        break;
                }
    }
    
    //Setter alt til spawn
    public void død() {
        try {
            pacman.placeInSpawn(spawnX, spawnY);
            blinky.placeInSpawn(kart);
            inky.placeInSpawn(kart);
            pinky.placeInSpawn(kart);
            clyde.placeInSpawn(kart);
        } catch (Exception ex) {
                    
        }
    }
    
    //sjekker om kartet er ferdig
    public boolean sjekkVinst() {
        
        if (antMat == 0 && antStorMat == 0 && start == true) {
            gameOver(1);
            return true;
        } else {
            return false;
        }    
    }
    
        //sjekker om kartet er ferdig i vs mode
    public void sjekkVinst2P() {
        
        if (antMat == 0 && antStorMat == 0 && start == true) {
            if (p1Score > p2Score) {
                gameOverP2(1);
            }
            else {
                gameOverP2(2);
            }
            
        } else {
           
        }    
    }
}
    
    
    