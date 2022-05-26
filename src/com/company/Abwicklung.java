package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Abwicklung extends Component {
    private CSVListe p;
    private Lager la;
    private  List<Produkte> l ;
    private int belohnung;
    private Boolean naechsterEintrag;


    public Abwicklung() {
        p = new CSVListe();
        la = new Lager();
        l= p.getList();
        belohnung=0;
    }

    //Muss überprüft werden
    public Boolean naechsterEintrag(int i,int x, int y, int z){
        if(Objects.equals(l.get(i).getAuftragsart(), "Einlagerung")){

            if(la.isEmpty(x,y,z)){
                if(Objects.equals(l.get(i).getName(),"Holz")){
                    if((z == 0 && !la.isEmpty(x, y, 1)||(z==1 && !la.isEmpty(x, y, 0)))){
                        naechsterEintrag=false;
                        System.out.println("Einlagerung nicht möglich ");
                        JOptionPane.showMessageDialog(null,"Einlagerung nicht möglich","Fehler: Einlagerung",JOptionPane.ERROR_MESSAGE);

                    }else {
                        la.insertProduct(x, y, 0, l.get(i));
                        la.insertProduct(x, y, 1, l.get(i));
                        System.out.println("Einlagerung erfolgreich");
                        naechsterEintrag=true;
                        belohnung += l.get(i).getBelohnung();
                    }

                }else if (Objects.equals(l.get(i).getName(),"Stein")){

                    if(Objects.equals(l.get(i).getAttribute2(),"Schwer"))
                    {
                        if(!la.isEmpty(x,0,z)||(y!=0)){
                            naechsterEintrag=false;
                            if(!la.isEmpty(x,0,z)){
                                JOptionPane.showMessageDialog(null,"Einlagerung nicht möglich, da die Palette nicht leer ist!","Fehler: Einlagerung",JOptionPane.ERROR_MESSAGE);

                            }
                            else if ((y!=0)){
                                JOptionPane.showMessageDialog(null,"Einlagerung nicht möglich, da schwere Steine nur für die unteren Paletten geeignet sind!","Fehler: Einlagerung",JOptionPane.ERROR_MESSAGE);
                            }

                        }else{
                            la.insertProduct(x, y, z, l.get(i));
                            System.out.println("Einlagerung erfolgreich");
                            naechsterEintrag=true;
                            belohnung += l.get(i).getBelohnung();
                        }

                    }else if (Objects.equals(l.get(i).getAttribute2(),"Mittel")){
                        if((y==0 && !la.isEmpty(x,y,z))||y==1 && !la.isEmpty(x,y,z)||y==2){
                            naechsterEintrag=false;
                            if(y==0 && !la.isEmpty(x,y,z)){
                                JOptionPane.showMessageDialog(null,"Einlagerung nicht möglich, da die unteren Paletten besetzt sind","Fehler: Einlagerung",JOptionPane.ERROR_MESSAGE);
                            }else if(y==1 && !la.isEmpty(x,y,z)){
                                JOptionPane.showMessageDialog(null,"Einlagerung nicht möglich, da die mittleren Paletten besetzt sind","Fehler: Einlagerung",JOptionPane.ERROR_MESSAGE);
                            }else if(y==2){
                                JOptionPane.showMessageDialog(null,"Einlagerung nicht möglich, da mittelschwere Paletten nicht oben platziert werden dürfen","Fehler: Einlagerung",JOptionPane.ERROR_MESSAGE);
                            }

                        }else{
                            la.insertProduct(x, y, z, l.get(i));
                            System.out.println("Einlagerung erfolgreich");
                            naechsterEintrag=true;
                            belohnung += l.get(i).getBelohnung();
                        }

                    }

                }else if(Objects.equals(l.get(i).getName(),"Papier")){
                    la.insertProduct(x, y, z, l.get(i));
                    System.out.println("Einlagerung erfolgreich");
                    naechsterEintrag=true;
                    belohnung += l.get(i).getBelohnung();
                }

            }else JOptionPane.showMessageDialog(null,"Einlagerung nicht möglich, da die Palette nicht leer ist","Fehler: Einlagerung",JOptionPane.ERROR_MESSAGE); naechsterEintrag=false;



        }else if(Objects.equals(l.get(i).getAuftragsart(),"Auslagerung")){

            if(la.isEmpty(x,y,z)){
                JOptionPane.showMessageDialog(null,"Auslagerung nicht möglich, da das Lager leer ist","Fehler: Auslagerung",JOptionPane.ERROR_MESSAGE);
                naechsterEintrag=false;
            }
            else{
                if(la.foundProduct(l.get(i), x,y,z)){
                    naechsterEintrag=true;
                    if (Objects.equals(la.getSearchedName(), "Holz")) {
                        if(z==0){
                            System.out.printf("Auslagerung möglich an Stelle x: %d, y: %d, z: %d, dabei muss auch an Stelle x: %d, y: %d, z: %d ausgelagert werden \n",x,y,0,x,y,1);
                            la.insertProduct(x, y, 1, null);
                            la.insertProduct(x, y, 0, null);
                            belohnung+=l.get(i).getBelohnung();
                        }else if (z==1) {
                        System.out.printf("Auslagerung möglich an Stelle x: %d, y: %d, z: %d, dabei muss auch an Stelle x: %d, y: %d, z: %d ausgelagert werden \n",x,y,1,x,y,0);
                        la.insertProduct(x, y, 1, null);
                        la.insertProduct(x, y, 0, null);
                            belohnung+=l.get(i).getBelohnung();
                        }
                    }
                    else {
                        if(z==1){
                            if(la.isEmpty(x,y,0)){
                                naechsterEintrag=true;
                                la.insertProduct(x, y, z, null);
                                belohnung+=l.get(i).getBelohnung();
                            }
                            else naechsterEintrag=false; JOptionPane.showMessageDialog(null,"Auslagerung nicht möglich, da ein anderes Produkt das aktuelle Produkt blockiert","Fehler: Auslagerung",JOptionPane.ERROR_MESSAGE);
                        }
                    }

                }else JOptionPane.showMessageDialog(null,"Auslagerung nicht möglich, da das Produkt nicht gefunden wurde","Fehler: Auslagerung",JOptionPane.ERROR_MESSAGE); naechsterEintrag=false;
            }
        }
        return naechsterEintrag;
    }

    public Integer getBelohnung(){
        return belohnung;
    }

    public void verschrotten(int x, int y, int z){
        if(la.isEmpty(x,y,z))
        {
            JOptionPane.showMessageDialog(null,"Prdoukt kann nicht verschrottet werden, da die Palette leer ist.","Fehler: Verschrotten",JOptionPane.ERROR_MESSAGE);
        }else{
            la.insertProduct(x,y,z,null);
            JOptionPane.showMessageDialog(null, "Das ausgewählte Produkt wurde für 300 Geldeinheiten verschrottet.", "Info: Verschrotten", JOptionPane.INFORMATION_MESSAGE);
            belohnung -= 300;
        }

    }




    public void umlagern (int x, int y, int z,int zielX, int zielY, int zielZ){ //Muss gemacht werden
        //Produkt auswählen
        if(la.isEmpty(x,y,z)){
            JOptionPane.showMessageDialog(null,"Die Palette ist leer.","Fehler: Umlagern",JOptionPane.ERROR_MESSAGE);
        }else {
            //Diagonal innerhalb gleicher Höhe von einzel Produkten
            if (z == 0) {
                if (zielZ == 1) {
                    if (!la.isEmpty(zielX, zielY, z)) {
                        JOptionPane.showMessageDialog(null, "Ein anderes Produkt blockiert, um umlagern zu können.", "Fehler: Umlagern", JOptionPane.ERROR_MESSAGE);
                    } else {
                        la.tauscheProdukt(x,y,z,zielX,zielY,zielZ);
                    }
                }
                else{
                    la.tauscheProdukt(x,y,z,zielX,zielY,zielZ);
                }
            }
            //Hintere Palette gleicher Höhe
            else if(z==1){
                if(zielZ==1){
                    if(!la.isEmpty(zielX,zielY,0)){
                        JOptionPane.showMessageDialog(null, "Ein anderes Produkt blockiert, um umlagern zu können.", "Fehler: Umlagern", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        la.tauscheProdukt(x,y,z,zielX,zielY,zielZ);
                    }
                }
                else{

                }
            }

        }
    }




}
