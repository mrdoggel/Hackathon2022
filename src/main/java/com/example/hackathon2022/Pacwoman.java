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
public class Pacwoman extends Moveable{
    
    boolean bildebytte = false;
    boolean blue = false;
    boolean white = false;
    Image bilde = new Image("file:pacwomanopen.png");
    Image bilde1 = new Image("file:pacwomanclosed.png");
    Image ghostopen = new Image("file:pacwomanopenghost.png");
    Image ghostclosed = new Image("file:pacwomanclosedghost.png");
    Image ghostwhiteopen = new Image("file:pacwomanopenwhite.png");
    Image ghostwhiteclosed = new Image("file:pacwomanclosedwhite.png");
      
    //Hovedkonstruktør, legger ut bilde og bestemmer størrelse
    public Pacwoman(int bredde, int høyde, Double fart) {
        
        iv = new ImageView();
        this.bredde = bredde;
        this.høyde = høyde;
        this.fart = fart;
        
    }

    public void isWhite() { white = true; }

    public void notWhite() { white = false; }
    
    public void isBlue() {
        blue = true;
    }
    
    public void notBlue() {
        blue = false;
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
        iv.setY(y*bredde+10);
               
    }
    
    //Setter bildet av pacman på spawn (5 fra tekstfil) med riktig strl og bilde
    public void placeInSpawn(int x, int y) throws Exception {
                      
        iv.setImage(bilde);
        iv.setFitWidth(bredde);
        iv.setFitHeight(høyde);
        iv.setX(x*bredde);
        iv.setY(y*bredde+10);
        retning = 10;
   
    }   
    
    public void pacwomanBlue() {
        Image ghost = new Image("file:pacwomanopenghost.png");
        iv.setImage(ghost);
    }
    
    //Metode beveger pacman automatisk
    public void movePac() {
        Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(fart), ev -> {
            
            if (bildebytte) {
                if (white) {
                    iv.setImage(ghostwhiteopen);
                    bildebytte = false;
                }else if (blue) {
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
                }else if (blue) {
                    iv.setImage(ghostclosed);
                    bildebytte = true;
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
    
    //Sjekker om det er vegg og om det er teleportering. Teleporterer om nødvendig
    public boolean isMoveable(int retning) throws Exception {
        
        int curX = 0;
        int curY;
        
        int x = (int)iv.getX()/20;
        int y = (int)iv.getY()/20;
        curY = (int)iv.getY()/20;
        if ((int)iv.getX()/20 >= 1 && (int)iv.getX()/20 <= 50) {
            curX = (int)iv.getX()/20;    
        }
            switch (retning) {
            //høyre
                case 0:
                    if (x == 50) {
                        
                        x = 0;
                        iv.setX(x*20);
                        curX = 1;
                        
                    }else return Kart.mapXY2P("map2p.txt")[curY][curX+1] != 0 && x < 50;
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
                        
                    }else return Kart.mapXY2P("map2p.txt")[curY-1][curX] != 0;
                default:
                    return false;
            }
    }     
    
}
