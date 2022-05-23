package com.company;

import javax.swing.text.LayeredHighlighter;

public class Lager {
    //Attributes
    private int x=4;
    private int y=3;
    private int z=2;
    private Boolean isEmpty =true;
    private int anzPaletten = x * y * z;
    private Produkte Lager [][][] = new Produkte[x][y][z];


    //Methods
    public Boolean isEmpty(int x, int y,int z){
        if(Lager[x][y][z]==null){
            isEmpty = true;
        }else isEmpty=false;
        return isEmpty;
    }

    public int getAnzPaletten(){
        return anzPaletten;
    }

    public Produkte getLagerplatz(int x, int y,int z) {
        return Lager[x][y][z];
    }

    public void insertProduct(int x, int y, int z, Produkte produkte){
        Lager[x][y][z] = produkte;
    }
}
