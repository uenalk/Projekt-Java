package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Abwicklung extends Component {
    private final Lager la;
    private final List<Produkte> l ;
    private int belohnung;
    private boolean einlagerungErfolgreich;
    private boolean auslagerungErfolgreich;


    public Abwicklung() {
        CSVListe p = new CSVListe();
        la = new Lager();
        l= p.getList();
        belohnung=0;
    }
    public Boolean auslagerungErfolgreich(){
        return auslagerungErfolgreich;
    }

    public Boolean einlagerungErfolgreich(){
        return einlagerungErfolgreich;
    }

    public void naechsterEintrag(int i,int x, int y, int z){

            if (Objects.equals(l.get(i).getAuftragsart(), "Einlagerung")) {

                if (!la.isEmpty(x, y, z)) {
                    JOptionPane.showMessageDialog(null, "Einlagerung nicht möglich, da die Palette nicht leer ist", "Fehler: Einlagerung", JOptionPane.ERROR_MESSAGE);
                    einlagerungErfolgreich = false;


                } else {
                    if (Objects.equals(l.get(i).getName(), "Holz")) {
                        einlagernHolz(i, x, y, z);
                    } else if (Objects.equals(l.get(i).getName(), "Stein")) {
                        einlagernStein(i, x, y, z);
                    } else if (Objects.equals(l.get(i).getName(), "Papier")) {
                        einlagernPapier(i, x, y, z);
                    }
                }


            } else if (Objects.equals(l.get(i).getAuftragsart(), "Auslagerung")) {

                if (la.isEmpty(x, y, z)) {
                    JOptionPane.showMessageDialog(null, "Auslagerung nicht möglich, da die Palette leer ist", "Fehler: Auslagerung", JOptionPane.ERROR_MESSAGE);
                    auslagerungErfolgreich = false;
                } else {
                    if (!la.foundProduct(l.get(i), x, y, z)) {
                        JOptionPane.showMessageDialog(null, "Auslagerung nicht möglich, da das Produkt nicht gefunden wurde", "Fehler: Auslagerung", JOptionPane.ERROR_MESSAGE);
                        auslagerungErfolgreich = false;
                    } else {
                        if (Objects.equals(la.getSearchedName(), "Holz")) {
                            auslagernHolz(i, x, y, z);
                        } else {
                            auslagernPapierStein(i, x, y, z);
                        }
                    }
                }
            }
        }


    public Integer getBelohnung(){
        return belohnung;
    }

    public void verschrotten(int i,int x, int y, int z){
        if(la.isEmpty(x,y,z))
        {
            JOptionPane.showMessageDialog(null,"Prdoukt kann nicht verschrottet werden, da die Palette leer ist.","Fehler: Verschrotten",JOptionPane.ERROR_MESSAGE);
        }else{
            if(Objects.equals(l.get(i).getName(), "Holz")){
                la.insertProduct(x,y,1,null);
                la.insertProduct(x,y,1,null);
                JOptionPane.showMessageDialog(null, "Das ausgewählte Produkt wurde für 300 Geldeinheiten verschrottet.", "Info: Verschrotten", JOptionPane.INFORMATION_MESSAGE);
                belohnung -= 300;
            }else{
            la.insertProduct(x,y,z,null);
            JOptionPane.showMessageDialog(null, "Das ausgewählte Produkt wurde für 300 Geldeinheiten verschrottet.", "Info: Verschrotten", JOptionPane.INFORMATION_MESSAGE);
            belohnung -= 300;}
        }

    }




    public void umlagern (int x, int y, int z,int zX, int zY, int zZ){ //Muss gemacht werden
        //Produkt auswählen
        if(la.isEmpty(x,y,z)){
            JOptionPane.showMessageDialog(null,"Die Palette ist leer.","Fehler: Umlagern",JOptionPane.ERROR_MESSAGE);
        }else {
            //Aktueles Produkt Papier
            if(Objects.equals(la.getProdukt(x, y, z).getName(), "Papier")){
                aktProduktPapier(x,y,z,zX,zY,zZ);
            }


            //Aktuelles Produkt Holz
            else if(Objects.equals(la.getProdukt(x, y, z).getName(), "Holz")){
                aktProduktHolz(x,y,z,zX,zY,zZ);

            }


            //Aktuelles Produkt Stein
            else if(Objects.equals(la.getProdukt(x, y, z).getName(), "Stein")) {
                aktProduktStein(x, y, z, zX, zY, zZ);

            }
        }
    }


    //Einlagern/Auslagern der Produkte
    public void einlagernHolz(int i, int x, int y, int z){
        if((z == 0 && !la.isEmpty(x, y, 1)||(z==1 && !la.isEmpty(x, y, 0)))){
            System.out.println("Einlagerung nicht möglich ");
            einlagerungErfolgreich=false;
            JOptionPane.showMessageDialog(null,"Einlagerung nicht möglich, da der Platz nicht ausreicht","Fehler: Einlagerung",JOptionPane.ERROR_MESSAGE);

        }else {
            la.insertProduct(x, y, 0, l.get(i));
            la.insertProduct(x, y, 1, l.get(i));
            System.out.println("Einlagerung erfolgreich");
            einlagerungErfolgreich=true;
            auslagerungErfolgreich=false;
            belohnung += l.get(i).getBelohnung();
        }
    }

    public void einlagernStein(int i,int x, int y, int z){
        if(Objects.equals(l.get(i).getAttribute2(),"Schwer"))
        {
            if(!la.isEmpty(x,0,z)||(y!=0)){
                if(!la.isEmpty(x,0,z)){
                    JOptionPane.showMessageDialog(null,"Einlagerung nicht möglich, da der Stein zu schwer ist!","Fehler: Einlagerung",JOptionPane.ERROR_MESSAGE);
                    einlagerungErfolgreich=false;

                }
                else if ((y!=0)){
                    JOptionPane.showMessageDialog(null,"Einlagerung nicht möglich, da schwere Steine nur für die unteren Paletten geeignet sind!","Fehler: Einlagerung",JOptionPane.ERROR_MESSAGE);
                    einlagerungErfolgreich=false;
                }

            }else{
                la.insertProduct(x, y, z, l.get(i));
                System.out.println("Einlagerung erfolgreich");
                einlagerungErfolgreich=true;
                auslagerungErfolgreich=false;
                belohnung += l.get(i).getBelohnung();
            }

        }else if (Objects.equals(l.get(i).getAttribute2(),"Mittel")){
            if((y==0 && !la.isEmpty(x,y,z))||y==1 && !la.isEmpty(x,y,z)||y==2){
                if(y==0 && !la.isEmpty(x,y,z)){
                    JOptionPane.showMessageDialog(null,"Einlagerung nicht möglich, da die unteren Paletten besetzt sind","Fehler: Einlagerung",JOptionPane.ERROR_MESSAGE);einlagerungErfolgreich=false;
                }else if(y==1 && !la.isEmpty(x,y,z)){
                    JOptionPane.showMessageDialog(null,"Einlagerung nicht möglich, da die mittleren Paletten besetzt sind","Fehler: Einlagerung",JOptionPane.ERROR_MESSAGE);einlagerungErfolgreich=false;
                }else if(y==2){
                    JOptionPane.showMessageDialog(null,"Einlagerung nicht möglich, da mittelschwere Paletten nicht oben platziert werden dürfen","Fehler: Einlagerung",JOptionPane.ERROR_MESSAGE);einlagerungErfolgreich=false;
                }

            }else{
                la.insertProduct(x, y, z, l.get(i));
                System.out.println("Einlagerung erfolgreich");
                einlagerungErfolgreich=true;
                auslagerungErfolgreich=false;
                belohnung += l.get(i).getBelohnung();
            }

        }else if (Objects.equals(l.get(i).getAttribute2(),"Leicht")){
            la.insertProduct(x, y, z, l.get(i));
            System.out.println("Einlagerung erfolgreich");
            einlagerungErfolgreich=true;
            auslagerungErfolgreich=false;
            belohnung += l.get(i).getBelohnung();
        }
    }

    public void einlagernPapier(int i, int x, int y, int z){
        la.insertProduct(x, y, z, l.get(i));
        System.out.println("Einlagerung erfolgreich");
        einlagerungErfolgreich=true;
        auslagerungErfolgreich=false;
        belohnung += l.get(i).getBelohnung();
    }

    public void auslagernHolz(int i, int x, int y, int z){
        if(z==0){
            la.insertProduct(x, y, 1, null);
            la.insertProduct(x, y, 0, null);
            belohnung+=l.get(i).getBelohnung();
            System.out.println("Auslagern erfolgreich");
            auslagerungErfolgreich=true;
            einlagerungErfolgreich=false;
        }else if (z==1) {
            la.insertProduct(x, y, 1, null);
            la.insertProduct(x, y, 0, null);
            belohnung+=l.get(i).getBelohnung();
            System.out.println("Auslagern erfolgreich");
            auslagerungErfolgreich=true;
            einlagerungErfolgreich=false;
        }
    }

    public void auslagernPapierStein(int i, int x, int y, int z){
        if(z==1){
            if(la.isEmpty(x,y,0)){
                la.insertProduct(x, y, z, null);
                belohnung+=l.get(i).getBelohnung();
                System.out.println("Auslagern erfolgreich");
                auslagerungErfolgreich=true;
                einlagerungErfolgreich=false;
            }
            else{
                JOptionPane.showMessageDialog(null,"Auslagerung nicht möglich, da ein anderes Produkt das aktuelle Produkt blockiert","Fehler: Auslagerung",JOptionPane.ERROR_MESSAGE);auslagerungErfolgreich=false;
            }

        } else {
            la.insertProduct(x,y,z,null); belohnung+=l.get(i).getBelohnung();System.out.println("Auslagern erfolgreich");
            auslagerungErfolgreich=true;
            einlagerungErfolgreich=false;
        }
    }

    public void aktProduktPapier(int x, int y, int z, int zX,int zY, int zZ){
            //Zielprodukt Papier
            if(Objects.equals(la.getProdukt(zX, zY, zZ).getName(), "Papier")){
                uberprufenProduktWieBeiPapier(x,y,z,zX,zY,zZ);
            }

            //Zielprodukt Stein
            if(Objects.equals(la.getProdukt(zX, zY, zZ).getName(), "Stein")){
                if(Objects.equals(la.getProdukt(zX, zY, zZ).getAttribute2(), "Schwer")){
                    if(y!=0){
                        //Stein zu schwer, kann  nicht getauscht werden
                        JOptionPane.showMessageDialog(null,"Stein zu schwer, kann  nicht getauscht werden","Fehler: Umlagerung",JOptionPane.ERROR_MESSAGE);
                    }else{
                        uberprufenProduktWieBeiPapier(x,y,z,zX,zY,zZ);
                    }
                }else if(Objects.equals(la.getProdukt(zX, zY, zZ).getAttribute2(), "Mittel")){
                    if(y==2){
                        //Fehler Produkt zu schwer
                        JOptionPane.showMessageDialog(null,"Prdoukt zu schwer","Fehler: Umlagerung",JOptionPane.ERROR_MESSAGE);

                    }else{
                        uberprufenProduktWieBeiPapier(x,y,z,zX,zY,zZ);
                    }
                }else{
                    uberprufenProduktWieBeiPapier(x,y,z,zX,zY,zZ);
                }
            }

            //Zielprodukt Holz
            if(Objects.equals(la.getProdukt(zX, zY, zZ).getName(), "Holz")){
                if(z==1&&zZ==1){
                    if(!la.isEmpty(x,y,0)){
                        //Vor dem Zielprodukt ist ein anderes Produkt
                        JOptionPane.showMessageDialog(null,"Vor dem Zielprodukt ist ein anderes Produkt","Fehler: Umlagerung",JOptionPane.ERROR_MESSAGE);
                    }else{
                        //tausche
                        la.tauscheProdukt(x,y,z,zX,zY,zZ);
                        la.tauscheProdukt(x,y,0,zX,zY,0);
                        System.out.println("Erfolgreich umgelagert");
                        belohnung-=100;
                    }
                }else if(z==0&&zZ==1){
                    if(!la.isEmpty(x,y,1)){
                        //Hinter dem Produkt befindet sich noch ein Produkt
                        JOptionPane.showMessageDialog(null,"Hinter dem Produkt befindet sich noch ein Produkt","Fehler: Umlagerung",JOptionPane.ERROR_MESSAGE);
                    }else{
                        //tausche
                        la.tauscheProdukt(x,y,z,zX,zY,zZ);
                        la.tauscheProdukt(x,y,0,zX,zY,1);
                        System.out.println("Erfolgreich umgelagert");
                        belohnung-=100;
                    }
                }else if(z==1&&zZ==0){
                    if(!la.isEmpty(x,y,0)){
                        //Vor dem ausgewählten Produkt ist ein anderes Produkt
                        JOptionPane.showMessageDialog(null,"Vor dem ausgewählten Produkt ist ein anderes Produkt","Fehler: Umlagerung",JOptionPane.ERROR_MESSAGE);
                    }else{
                        //tausche
                        la.tauscheProdukt(x,y,z,zX,zY,zZ);
                        la.tauscheProdukt(x,y,0,zX,zY,1);
                        System.out.println("Erfolgreich umgelagert");
                        belohnung-=100;
                    }
                }else if(z==0 && zZ==0){
                    //tausche
                    if(!la.isEmpty(x,y,1)){
                        //Hinter dem ausgewählten Produkt befindet sich noch ein Produkt
                        JOptionPane.showMessageDialog(null,"Hinter dem Produkt befindet sich noch ein Produkt","Fehler: Umlagerung",JOptionPane.ERROR_MESSAGE);
                    }else{
                        //tausche
                        la.tauscheProdukt(x,y,z,zX,zY,zZ);
                        la.tauscheProdukt(x,y,0,zX,zY,1);
                        System.out.println("Erfolgreich umgelagert");
                        belohnung-=100;
                    }
                }
            }


    }

    public void aktProduktHolz(int x, int y, int z, int zX,int zY, int zZ){
        //Zielprodukt Papier
        if(Objects.equals(la.getProdukt(zX, zY, zZ).getName(), "Papier")){
            uberprufenProduktBeiHolz(x,y,z,zX,zY,zZ);
        }

        //Zielprodukt Stein
        if(Objects.equals(la.getProdukt(zX, zY, zZ).getName(), "Stein")){
            if(Objects.equals(la.getProdukt(zX, zY, zZ).getAttribute2(), "Schwer")){
                if(y!=0){
                    //Stein zu schwer, kann  nicht getauscht werden
                    JOptionPane.showMessageDialog(null,"Produkt zu schwer","Fehler: Umlagerung",JOptionPane.ERROR_MESSAGE);
                }else{
                    uberprufenProduktBeiHolz(x,y,z,zX,zY,zZ);
                }
            }else if(Objects.equals(la.getProdukt(zX, zY, zZ).getAttribute2(), "Mittel")){
                if(y==2){
                    //Fehler Produkt zu schwer
                    JOptionPane.showMessageDialog(null,"Produkt zu schwer","Fehler: Umlagerung",JOptionPane.ERROR_MESSAGE);
                }else{
                    uberprufenProduktBeiHolz(x,y,z,zX,zY,zZ);
                }
            }else{
                uberprufenProduktBeiHolz(x,y,z,zX,zY,zZ);
            }
        }

        //Zielprodukt Holz
        if(Objects.equals(la.getProdukt(zX, zY, zZ).getName(), "Holz")){
            la.tauscheProdukt(x,y,1,zX,zY,1);
            la.tauscheProdukt(x,y,0,zX,zY,0);
            System.out.println("Erfolgreich umgelagert");
            belohnung-=100;

        }
    }

    public void aktProduktStein(int x, int y, int z, int zX,int zY, int zZ){
        //Zielprodukt Papier
        if(Objects.equals(la.getProdukt(zX, zY, zZ).getName(), "Papier")){
            if(Objects.equals(la.getProdukt(x, y, z).getAttribute2(), "Schwer")){
                if(zY!=0){
                    //Der Stein ist viel zu schwer für eine höhere Ebene
                    JOptionPane.showMessageDialog(null,"Produkt zu schwer","Fehler: Umlagerung",JOptionPane.ERROR_MESSAGE);
                }else{
                    uberprufenProduktWieBeiPapier(x,y,z,zX,zY,zZ);
                }
            }else if(Objects.equals(la.getProdukt(x, y, z).getAttribute2(), "Mittel")){
                if(y==2){
                    //Der Stein ist viel zu schwer für eine höhere Ebene
                    JOptionPane.showMessageDialog(null,"Produkt zu schwer","Fehler: Umlagerung",JOptionPane.ERROR_MESSAGE);
                }else{
                    uberprufenProduktWieBeiPapier(x,y,z,zX,zY,zZ);
                }
            }else{
                uberprufenProduktWieBeiPapier(x,y,z,zX,zY,zZ);}
        }

        //Zielprodukt Stein
        if(Objects.equals(la.getProdukt(zX, zY, zZ).getName(), "Stein")){
            uberprufenProduktWieBeiPapier(x,y,z,zX,zY,zZ);
        }

        //Zielprodukt Holz
        if(Objects.equals(la.getProdukt(zX, zY, zZ).getName(), "Holz")){
            if(Objects.equals(la.getProdukt(x, y, z).getAttribute2(), "Schwer")){
                if(zY!=0){
                    //Der Stein ist viel zu schwer für eine höhere Ebene
                    JOptionPane.showMessageDialog(null,"Produkt zu schwer","Fehler: Umlagerung",JOptionPane.ERROR_MESSAGE);
                }else{
                    uberprufenHolzZuStein(x,y,z,zX,zY,zZ);
                }
            }else if(Objects.equals(la.getProdukt(x, y, z).getAttribute2(), "Mittel")){
                if(y==2){
                    //Der Stein ist viel zu schwer für eine höhere Ebene
                    JOptionPane.showMessageDialog(null,"Produkt zu schwer","Fehler: Umlagerung",JOptionPane.ERROR_MESSAGE);
                }else{
                    uberprufenHolzZuStein(x,y,z,zX,zY,zZ);
                }
            }else{
                uberprufenHolzZuStein(x,y,z,zX,zY,zZ);}

        }

    }

    public void uberprufenHolzZuStein(int x, int y, int z, int zX,int zY, int zZ){
        if(z==1&&zZ==1){
            if(!la.isEmpty(x,y,0)){
                //Das ausgewählte Produkt wird von einem anderen Produkt blockiert
                JOptionPane.showMessageDialog(null,"Das ausgewählte Produkt wird von einem anderen Produkt blockiert","Fehler: Umlagerung",JOptionPane.ERROR_MESSAGE);
            }else{
                la.tauscheProdukt(x,y,z,zX,zY,zZ);
                la.tauscheProdukt(x,y,0,zX,zY,0);
                System.out.println("Erfolgreich umgelagert");
                belohnung-=100;
            }
        }else if(z==1&&zZ==0){
            if(!la.isEmpty(x,y,0)){
                //Das ausgewählte Produkt wird von einem anderen Produkt blockiert
                JOptionPane.showMessageDialog(null,"Das ausgewählte Produkt wird von einem anderen Produkt blockiert","Fehler: Umlagerung",JOptionPane.ERROR_MESSAGE);
            }else{
                la.tauscheProdukt(x,y,z,zX,zY,zZ);
                la.tauscheProdukt(x,y,0,zX,zY,1);
                System.out.println("Erfolgreich umgelagert");
                belohnung-=100;
            }
        }else if(z==0&&zZ==1){
            if(!la.isEmpty(x,y,1)){
                //Hinter dem Prdoukt befindet sich ein anderes Produkt
                JOptionPane.showMessageDialog(null,"Hinter dem Produkt befindet sich ein anderes Produkt und Holz braucht 2 Paletten platz für das Umlagern","Fehler: Umlagerung",JOptionPane.ERROR_MESSAGE);
            }
            else{
                la.tauscheProdukt(x,y,z,zX,zY,zZ);
                la.tauscheProdukt(x,y,1,zX,zY,0);
                System.out.println("Erfolgreich umgelagert");
                belohnung-=100;
            }
        }else if(z==0&&zZ==0){
            if(!la.isEmpty(x,y,1)){
                //Hinter dem ausgewählten Prdoukt befindet sich ein anderes Produkt
                JOptionPane.showMessageDialog(null,"Hinter dem Produkt befindet sich ein anderes Produkt und Holz braucht 2 Paletten platz für das Umlagern","Fehler: Umlagerung",JOptionPane.ERROR_MESSAGE);
            }else{
                la.tauscheProdukt(x,y,x,zX,zY,zZ);
                la.tauscheProdukt(x,y,1,zX,zY,1);
                System.out.println("Erfolgreich umgelagert");
                belohnung-=100;
            }
        }
    }




    public void uberprufenProduktBeiHolz(int x, int y, int z, int zX,int zY, int zZ){
        if(z==1&&zZ==1){
            if(!la.isEmpty(zX,zY,0)){
                //Vor dem Zielprodukt ist ein anderes Produkt
                JOptionPane.showMessageDialog(null,"Das ausgewählte Produkt wird von einem anderen Produkt blockiert","Fehler: Umlagerung",JOptionPane.ERROR_MESSAGE);
            }else{
                //tausche
                la.tauscheProdukt(x,y,z,zX,zY,zZ);
                la.tauscheProdukt(x,y,0,zX,zY,0);
                System.out.println("Erfolgreich umgelagert");
                belohnung-=100;
            }
        }else if(z==0&&zZ==1){
            if(!la.isEmpty(zX,zY,0)){
                //Vor dem Zielprodukt ist ein anderes Produkt
                JOptionPane.showMessageDialog(null,"Das ausgewählte Produkt wird von einem anderen Produkt blockiert","Fehler: Umlagerung",JOptionPane.ERROR_MESSAGE);
            }else{
                //tausche
                la.tauscheProdukt(x,y,z,zX,zY,zZ);
                la.tauscheProdukt(x,y,1,zX,zY,0);
                System.out.println("Erfolgreich umgelagert");
                belohnung-=100;
            }
        }else if(z==1&&zZ==0){
            if(!la.isEmpty(zX,zY,1)){
                //Hinter dem Zielprodukt befindet sich ein anderes Produkt
                JOptionPane.showMessageDialog(null,"Hinter dem Produkt befindet sich ein anderes Produkt und Holz braucht 2 Paletten platz für das Umlagern","Fehler: Umlagerung",JOptionPane.ERROR_MESSAGE);
            }else{
                //tausche
                la.tauscheProdukt(x,y,z,zX,zY,zZ);
                la.tauscheProdukt(x,y,0,zX,zY,1);
                System.out.println("Erfolgreich umgelagert");
                belohnung-=100;
            }
        }else if(z==0 && zZ==0){
            //tausche
            if(!la.isEmpty(zX,zY,1)){
                //Hinter dem ausgewählten Produkt befindet sich noch ein Produkt
                JOptionPane.showMessageDialog(null,"Hinter dem Produkt befindet sich ein anderes Produkt und Holz braucht 2 Paletten platz für das Umlagern","Fehler: Umlagerung",JOptionPane.ERROR_MESSAGE);
            }else{
                //tausche
                la.tauscheProdukt(x,y,z,zX,zY,zZ);
                la.tauscheProdukt(x,y,1,zX,zY,1);
                System.out.println("Erfolgreich umgelagert");
                belohnung-=100;
            }
        }

    }

    public void uberprufenProduktWieBeiPapier(int x, int y, int z, int zX,int zY, int zZ){
        if(z==1&&zZ==1){
            if(!la.isEmpty(zX,zY,0)){
                //Vor dem Zielprodukt ist ein anderes Produkt
                JOptionPane.showMessageDialog(null,"Das ausgewählte Produkt wird von einem anderen Produkt blockiert","Fehler: Umlagerung",JOptionPane.ERROR_MESSAGE);
            }else{
                //tausche
                la.tauscheProdukt(x,y,z,zX,zY,zZ);
                System.out.println("Erfolgreich umgelagert");
                belohnung-=100;
            }
        }else if(z==0&&zZ==1){
            if(x==zX&&y==zY){
                //tausche
                la.tauscheProdukt(x,y,z,zX,zY,zZ);
                System.out.println("Erfolgreich umgelagert");
                belohnung-=100;
            }else{
                if(!la.isEmpty(zX,zY,0)){
                    //Vor dem Zielprodukt ist ein anderes Produkt
                    JOptionPane.showMessageDialog(null,"Das ausgewählte Produkt wird von einem anderen Produkt blockiert","Fehler: Umlagerung",JOptionPane.ERROR_MESSAGE);
                }else{
                    //tausche
                    la.tauscheProdukt(x,y,z,zX,zY,zZ);
                    System.out.println("Erfolgreich umgelagert");
                    belohnung-=100;
                }
            }

        }else if(z==1&&zZ==0){
            if(x==zX&&y==zY){
                //Tausche
                la.tauscheProdukt(x,y,z,zX,zY,zZ);
                System.out.println("Erfolgreich umgelagert");
                belohnung-=100;
            }else{
                if(!la.isEmpty(x,y,0)){
                    //Aktuelle produkt wird blockiert
                    JOptionPane.showMessageDialog(null,"Das ausgewählte Produkt wird von einem anderen Produkt blockiert","Fehler: Umlagerung",JOptionPane.ERROR_MESSAGE);
                }else{
                    //tausche
                    la.tauscheProdukt(x,y,z,zX,zY,zZ);
                    System.out.println("Erfolgreich umgelagert");
                    belohnung-=100;
                }
            }
        }else if(z==0 && zZ==0) {
            //tausche
            la.tauscheProdukt(x,y,z,zX,zY,zZ);
            System.out.println("Erfolgreich umgelagert");
            belohnung-=100;
        }
    }




    }
