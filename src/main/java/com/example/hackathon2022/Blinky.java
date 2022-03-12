/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.hackathon2022;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author byreb
 */
public class Blinky extends Ghosts{
    
    int randomRetning =(int) (Math.random() * 4);
    Image bilde = new Image("file:redghost.png");
    
    //du kan anta at alt jeg skriver her gjelder for alle ghosts, siden de ahr alt det samme.
    //startkonstruktør for et spøkelse, lager bildeplaceholder
    public Blinky(int bredde, int høyde, Double fart) {
        
        iv = new ImageView();
        this.bredde = bredde;
        this.høyde = høyde;
        this.fart = fart;
        
    }    
    
    //Metode for å plassere bildet og gjøre det rett størrelse
    public void place(int x, int y) throws Exception {
                             
        iv.setImage(bilde);
        iv.setFitWidth(bredde);
        iv.setFitHeight(høyde);
        iv.setX(x*bredde);
        iv.setY(y*bredde+10);
               
    }
       
    //Setter spøkelset tilbake til spawn
    public void placeInSpawn(String tekstfil) throws Exception {
        
        for(int i = 0; i<31;i++) {
            for(int j = 0; j<28;j++) {
                if (Kart.mapXY(tekstfil)[i][j] == 6) {
                                       
                    iv.setImage(bilde);
                    iv.setFitWidth(bredde);
                    iv.setFitHeight(høyde);
                    iv.setX(j*bredde);
                    iv.setY(i*bredde+10);
                    
                }
            }
        }      
    }
    
    //Automatisk bevegelse, lagt til litt kode ulikt fra pacman kode for tilfeldig bevegelse
    public void move() {
        Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(fart), ev -> {
                    
            if (iv.getX()/20 == 12 && iv.getY()/20-0.5 == 14) {
                try {
                    flytt(3);
                    flytt(0);
                    flytt(3);
                    flytt(3);
                } catch (Exception ex) {
                }
            } else {
                try {
                    flytt(randomRetning);
                } catch (Exception ex) {
                
                }
            }                
        }));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();       
    }
    
    //samme som pacman bortsett fra randomRetning addisjon
    public void flytt(int retning) throws Exception {
        
        
            switch (retning) {
                case 0:
                    //høyre
                    if (isMoveable(0)) {
                        iv.setX(iv.getX() + bredde);
                    } else {
                        randomRetning =(int) (Math.random() * 4);
                    }
                    break;
                case 1:
                    //ned
                    if (isMoveable(1)) {
                        iv.setY(iv.getY() + høyde);
                    } else {
                        randomRetning =(int) (Math.random() * 4);
                    }
                    break;
                case 2:
                    //venstre
                    if (isMoveable(2)) {
                        iv.setX(iv.getX() - bredde);
                    } else {
                        randomRetning =(int) (Math.random() * 4);
                    }                    
                    break;
                case 3:
                    //opp
                    if (isMoveable(3)) {
                        iv.setY(iv.getY() - høyde);
                    } else {
                        randomRetning =(int) (Math.random() * 4);
                    }
                    break;
                default:
                    break;
            }
    }
    
    //sjekker om bevegelig, samme som pacman men lagt til at om spøkelse er over
    //buret kan de ikke gå inn igjen
    public boolean isMoveable(int retning) throws Exception {
        
        int curX = 0;
        int curY;
        
        int x = (int)iv.getX()/20;
        int y = (int)iv.getY()/20;
        curY = (int)iv.getY()/20;
        if ((int)iv.getX()/20 >= 1 && (int)iv.getX()/20 <= 27) {
            curX = (int)iv.getX()/20;    
        }
            switch (retning) {
            //høyre
                case 0:
                    if (x == 27) {
                        
                        x = 0;
                        iv.setX(x*20);
                        curX = 1;
                        
                    }else return Kart.mapXY("map.txt")[curY][curX+1] != 0 && x < 27;
            //ned
                case 1:
                    if (curX == 13 && curY == 11 || curX == 14 && curY == 11) {
                        return false;
                    } else return Kart.mapXY("map.txt")[curY+1][curX] != 0;

            //venstre
                case 2:
                    if (x == 0) {
                       
                        x = 27;
                        iv.setX(x*20);
                        curX = 26;
                        
                    } else return Kart.mapXY("map.txt")[curY][curX-1] != 0 && x > 0;
            //opp
                case 3:
                    return Kart.mapXY("map.txt")[curY-1][curX] != 0;
                default:
                    return false;
            }
    }
    
    //Metode for å sette bilde tilbake til orginalt
    public void live() {
        iv.setImage(bilde);
    }
    
}
