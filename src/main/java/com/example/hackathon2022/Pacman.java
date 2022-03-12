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
 * @author marti
 */
public class Pacman extends Moveable{
    
    boolean bildebytte = false;
    boolean blue = false;
    boolean white = false;
    Image bilde = new Image("file:pacmanopen.png");
    Image bilde1 = new Image("file:pacmanclosed.png");
    Image ghostopen = new Image("file:pacmanopenghost.png");
    Image ghostclosed = new Image("file:pacmanclosedghost.png");
    Image ghostwhiteclosed = new Image("file:pacmanclosedwhite.png");
    Image ghostwhiteopen = new Image("file:pacmanopenwhite.png");
    //Hovedkonstruktør, legger ut bilde og bestemmer størrelse
    public Pacman(int bredde, int høyde, Double fart) {
        
        iv = new ImageView();
        this.bredde = bredde;
        this.høyde = høyde;
        this.fart = fart;
        
    }
    
    public void isBlue() {
        blue = true;
    }
    
    public void notBlue() {
        blue = false;
    }

    public void isWhite() {
        white = true;
    }

    public void notWhite() {
        white = false;
    }
    
    //Retning som brukes i automatisk pacman bevegelse
    public void setRetning(int retning) {
        this.retning = retning;
    }
    
    //Metode om jeg manuelt trenger å flytte pacman
    public void place(int x, int y) throws Exception {
                             
        iv.setImage(bilde);
        iv.setFitWidth(bredde);
        iv.setFitHeight(høyde);
        iv.setX(x*bredde);
        iv.setY(y*høyde+10);
               
    }
    
    //Setter bildet av pacman på spawn (5 fra tekstfil) med riktig strl og bilde
    public void placeInSpawn(int x, int y) throws Exception {
                                                   
        iv.setImage(bilde);
        iv.setFitWidth(bredde);
        iv.setFitHeight(høyde);
        iv.setX(x*bredde);
        iv.setY(y*høyde+10);
                                        
    }    
    
    public void placeInSpawn2P(int x, int y) throws Exception {
                                       
        iv.setImage(bilde);
        iv.setFitWidth(bredde);
        iv.setFitHeight(høyde);
        iv.setX(x*bredde);
        iv.setY(y*høyde+10);
        retning = 10;
   
    }   
    
    //Metode beveger pacman automatisk
    public void movePac() {
        Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(fart), ev -> {
            
            if (bildebytte) {
                if (blue) {
                    iv.setImage(ghostopen);
                } else {
                    iv.setImage(bilde);
                    bildebytte = false; 
                }
            } else {
                if (blue) {
                    iv.setImage(ghostclosed);
                } else {
                    iv.setImage(bilde1);
                    bildebytte = true;
                }
            }        
            
            try {
                flytt(retning);
            } catch (Exception ex) {
                
            }
                                 
        }));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();       
    }
    
    public void movePac2P() {
        Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(fart), ev -> {
            
            if (bildebytte) {
                if (white) {
                    iv.setImage(ghostwhiteopen);
                    bildebytte = false;
                } else if (blue) {
                    iv.setImage(ghostopen);
                    bildebytte = false;
                } else {
                    iv.setImage(bilde);
                    bildebytte = false; 
                }
            } else {
                if (white) {
                    iv.setImage(ghostwhiteclosed);
                    bildebytte = true;
                } else if (blue) {
                    iv.setImage(ghostclosed);
                    bildebytte = true;
                } else {
                    iv.setImage(bilde1);
                    bildebytte = true;
                }
            }        
            
            try {
                flytt2P(retning);
            } catch (Exception ex) {
                
            }
                                 
        }));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();       
    }
    
    //Metode om isMoveable metode returnerer true gå et hakk
    public void flytt(int retning) throws Exception {
        
        
            switch (retning) {
                case 0:
                    //høyre
                    if (isMoveable(0)) {
                        iv.setX(iv.getX() + bredde);
                    }
                    break;
                case 1:
                    //ned
                    if (isMoveable(1)) {
                        iv.setY(iv.getY() + høyde);
                    }
                    break;
                case 2:
                    //venstre
                    if (isMoveable(2)) {
                        iv.setX(iv.getX() - bredde);
                    }                    
                    break;
                case 3:
                    //opp
                    if (isMoveable(3)) {
                        iv.setY(iv.getY() - høyde);
                    }
                    break;
                default:
                    break;
            }
    }
    
    //Metode om isMoveable metode returnerer true gå et hakk
    public void flytt2P(int retning) throws Exception {
        
        
            switch (retning) {
                case 0:
                    //høyre
                    if (isMoveable2P(0)) {
                        iv.setX(iv.getX() + bredde);
                    }
                    break;
                case 1:
                    //ned
                    if (isMoveable2P(1)) {
                        iv.setY(iv.getY() + høyde);
                    }
                    break;
                case 2:
                    //venstre
                    if (isMoveable2P(2)) {
                        iv.setX(iv.getX() - bredde);
                    }                    
                    break;
                case 3:
                    //opp
                    if (isMoveable2P(3)) {
                        iv.setY(iv.getY() - høyde);
                    }
                    break;
                default:
                    break;
            }
    }
    
    public void pacmanBlue() {
        iv.setImage(ghostopen);
        blue = true;
    }
    
    //Sjekker om det er vegg og om det er teleportering. Teleporterer om nødvendig
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
                    return Kart.mapXY("map.txt")[curY+1][curX] != 0;

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
    
    //Sjekker om det er vegg og om det er teleportering. Teleporterer om nødvendig
    public boolean isMoveable2P(int retning) throws Exception {
        
        int curX = 0;
        int curY = 0;
        
        int x = (int)iv.getX()/20;
        int y = (int)iv.getY()/20;
        //if ((int)iv.getX()/20 >= 1 && (int)iv.getX()/20 <= 50) {
            curX = (int)iv.getX()/20;    
        //}
        //if ((int)iv.getY()/20 >= 1 && (int)iv.getY()/20 <= 30) {
            curY = (int)iv.getY()/20;
        //}
            switch (retning) {
            //høyre
                case 0:
                    if (x == 50) {
                        
                        x = 0;
                        iv.setX(x*20);
                        curX = 1;
                        
                    }else return Kart.mapXY2P("map2p.txt")[curY][curX+1] != 0 && x < 51;
            //ned
                case 1:
                    if (y == 30) {
                        
                        y = 0;
                        iv.setY(y*20+10);
                        curY = 1;
                        
                    }else return Kart.mapXY2P("map2p.txt")[curY+1][curX] != 0 && y < 31;

            //venstre
                case 2:
                    if (x == 0) {
                       
                        x = 50;
                        iv.setX(x*20);
                        curX = 49;
                        
                    } else return Kart.mapXY2P("map2p.txt")[curY][curX-1] != 0 && x > 0;
            //opp
                case 3:
                    if (y == 0) {
                        
                        y = 30;
                        iv.setY(y*20+10);
                        curY = 29;
                        
                    }else return Kart.mapXY2P("map2p.txt")[curY-1][curX] != 0 && y > 0;
                default:
                    return false;
            }
    }
}
