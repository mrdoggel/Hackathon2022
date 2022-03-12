/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.hackathon2022;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author marti
 */
public class Kart {
    
    final int høyde;
    final int bredde;
    final String kart;
    
    //startkonstruktør
    public Kart(int høyde, int bredde, String kart) {
        this.høyde = høyde;
        this.bredde = bredde;
        this.kart = kart;
    }
         //prøvde meg frem med hva som var best for metode for å lage kart, fjerner ikke i tilfelle jeg får behov for det
    public /*ArrayList<*/Rectangle/*>*/ make(int i, int j) throws Exception {
        
        //ArrayList<Rectangle> tab = new ArrayList();
        
                int tall = mapXY(kart)[i][j];
                if (tall == 0) { 
                    Rectangle r = new Rectangle(bredde, høyde);
                    r.setFill(Color.BLUE); 
                    //tab.add(r);  
                    return r;
                } else {
                    Rectangle r1 = new Rectangle(bredde, høyde);
                    r1.setFill(Color.BLACK);
                    //tab.add(r);
                    return r1;
                }                 
        //return tab;    
    }
    
     public /*ArrayList<*/Rectangle/*>*/ make2P(int i, int j) throws Exception {
        
        //ArrayList<Rectangle> tab = new ArrayList();
        
                int tall = mapXY2P(kart)[i][j];
                if (tall == 0) { 
                    Rectangle r = new Rectangle(bredde, høyde);
                    r.setFill(Color.BLUE); 
                    //tab.add(r);  
                    return r;
                } else {
                    Rectangle r1 = new Rectangle(bredde, høyde);
                    r1.setFill(Color.BLACK);
                    //tab.add(r);
                    return r1;
                }                 
        //return tab;    
    }
    
    //metode for å gå igjennom map.txt og skrive til 2d-tabell single player
    public static int[][] mapXY(String tekstfil) throws Exception {
        
        int xnr = 0;
        int ynr = 0;
        int[][] tab = new int[31][28];
        
        File fil = new File(tekstfil);
        try (Scanner leser = new Scanner(fil)) {
            while (leser.hasNextInt()) {                    
                tab[ynr][xnr++] = leser.nextInt();
                if(xnr == 28) {
                    ynr++;
                    xnr = 0;
                }
            }
            leser.close();
        }
        return tab;       
    } 
    
    //metode for å gå igjennom map.txt og skrive til 2d-tabell 2- player
    public static int[][] mapXY2P(String tekstfil) throws Exception {
        
        int xnr = 0;
        int ynr = 0;
        int[][] tab = new int[31][51];
        
        File fil = new File(tekstfil);
        try (Scanner leser = new Scanner(fil)) {
            while (leser.hasNextInt()) {                    
                tab[ynr][xnr++] = leser.nextInt();
                if(xnr == 51) {
                    ynr++;
                    xnr = 0;
                }
            }
            leser.close();
        }
        return tab;       
    }
}
    
