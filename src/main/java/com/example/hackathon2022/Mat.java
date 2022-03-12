/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.hackathon2022;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author byreb
 */
public class Mat extends Objekt{
    
    Color farge;
    
    //startkonstruktør for ny mat
    public Mat(int radius, int x, int y) {
        
        farge = Color.WHITE;
        sirkel = new Circle(x*20+10, y*20+20, radius, farge);
        this.radius = radius;
        this.x = x;
        this.y = y;
          
    }
        //Metode for å se om inntatte koordinater kræsjer med sirkels posisjon
        public void sjekkKrasj(int x, int y) {
        
        if (this.x == x && this.y == y) {
            this.sirkel.setVisible(false);
        }
        
    }
      
}
