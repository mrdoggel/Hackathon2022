/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.hackathon2022;

import javafx.scene.image.Image;

/**
 *
 * @author byreb
 */
public class Ghosts extends Moveable{
    
    //Metode for å skifte bilde på spøkelsene, arv
    public void dieBlue() {
        Image ghost = new Image("file:ghost.png");
        iv.setImage(ghost);
    }
    
    //Metode for å skifte bilde på spøkelsene, arv
    public void dieWhite() {
        Image ghost1 = new Image("file:ghost1.png");
        iv.setImage(ghost1);

    }
    
    //Metode for å sette retning på spøkelsene. skriver det her så det blir arv
    public void setRetning(int retning) {
        this.retning = retning;
    }
    
    //gammel kode som jeg ikke vil fjerne i tilfelle jeg glemmer hvordan jeg gjorde noe
    /*public void moveGhost() throws InterruptedException {
        Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(0.5+level), ev -> {
            if (iv1.getX()/20 == 12 && iv1.getY()/20-0.5 == 14) {
                moveGhost1(3);
                moveGhost1(0);
                moveGhost1(3);
                moveGhost1(3);
            }
            try {
                if (isMoveable1(randomRetning)) {
                    moveGhost1(randomRetning);
                } else {
                    randomRetning =(int) (Math.random() * 4);
                }
            } catch (Exception ex) {
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play(); 
        Timeline timeline2 = new Timeline(new KeyFrame(javafx.util.Duration.seconds(0.45+level), ev -> {
            if (iv2.getX()/20 == 13 && iv2.getY()/20-0.5 == 14) {
                moveGhost2(3);
                moveGhost2(3);
                moveGhost2(3);
            }
            try {
                if (isMoveable2(randomRetning2)) {
                    moveGhost2(randomRetning2);
                } else {
                    randomRetning2 =(int) (Math.random() * 4);
                }
            } catch (Exception ex) {
            }           
        }));
        timeline2.setDelay(javafx.util.Duration.seconds(15));  
        timeline2.setCycleCount(Animation.INDEFINITE);
        timeline2.play();
        Timeline timeline3 = new Timeline(new KeyFrame(javafx.util.Duration.seconds(0.30+level), ev -> {
            if (iv3.getX()/20 == 14 && iv3.getY()/20-0.5 == 14) {
                moveGhost3(3);
                moveGhost3(3);
                moveGhost3(3);
            }
            try {
                if (isMoveable3(randomRetning3)) {
                    moveGhost3(randomRetning3);
                } else {
                    randomRetning3 =(int) (Math.random() * 4);
                }
            } catch (Exception ex) {
            }
            
        }));
        timeline3.setDelay(javafx.util.Duration.seconds(30));
        timeline3.setCycleCount(Animation.INDEFINITE);
        timeline3.play();
        Timeline timeline4 = new Timeline(new KeyFrame(javafx.util.Duration.seconds(0.25+level), ev -> {
            if (iv4.getX()/20 == 15 && iv4.getY()/20-0.5 == 14) {
                moveGhost4(3);
                moveGhost4(2);
                moveGhost4(3);
                moveGhost4(3);
            }
            try {
                if (isMoveable4(randomRetning4)) {
                    moveGhost4(randomRetning4);
                } else {
                    randomRetning4 =(int) (Math.random() * 4);
                }
            } catch (Exception ex) {
            }
        }));
        timeline4.setDelay(javafx.util.Duration.seconds(45));
        timeline4.setCycleCount(Animation.INDEFINITE);
        timeline4.play();
    } */   
    
}
