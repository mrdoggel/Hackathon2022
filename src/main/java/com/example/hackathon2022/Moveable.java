/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.hackathon2022;

import javafx.scene.image.ImageView;

/**
 *
 * @author byreb
 */
public abstract class Moveable {
    
    //variabler som arves til alle movables
    int retning;
    int bredde;
    int høyde;
    Double fart;
    ImageView iv;
           
    public void flytt() {
        
    }
    
}
