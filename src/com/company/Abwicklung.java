package com.company;

import java.util.*;

public class Abwicklung {
    private CSVListe p;
    private Lager lager;
    private  List<Produkte> l ;
    private int belohnung;
    private Boolean erfolreich;


    public Abwicklung() {
        p = new CSVListe();
        lager = new Lager();
        l= p.getList();
        belohnung=0;
    }

    //Muss überprüft werden
    public Boolean naechsterEintrag(int i,int x, int y, int z){
        if(Objects.equals(l.get(i).getAuftragsart(), "Einlagerung")){
            if(lager.isEmpty(x,y,z)){
                if(Objects.equals(l.get(i).getName(),"Holz")){
                    if((z == 0 && !lager.isEmpty(x, y, 1)||(z==1 && !lager.isEmpty(x, y, 0)))){
                        erfolreich=true;
                        System.out.println("Einlagerung nicht möglich ");

                    }else {
                        lager.insertProduct(x, y, 0, l.get(i));
                        lager.insertProduct(x, y, 1, l.get(i));
                        System.out.println("Einlagerung erfolgreich");
                        erfolreich=true;
                        belohnung += l.get(i).getBelohnung();

                    }
                }else if (Objects.equals(l.get(i).getName(),"Stein")){
                    if(Objects.equals(l.get(i).getAttribute2(),"Schwer"))
                    {
                        if(!lager.isEmpty(x,0,z)||(y!=0)){
                            System.out.println("Einlagerung geht nicht, weil Stein zu schwer bzw. kein platz");
                            erfolreich=true;
                        }else{
                            lager.insertProduct(x, y, z, l.get(i));
                            System.out.println("Einlagerung erfolgreich");
                            erfolreich=true;
                            belohnung += l.get(i).getBelohnung();
                        }

                    }else if (Objects.equals(l.get(i).getAttribute2(),"Mittel")){
                        if((y==0 && !lager.isEmpty(x,y,z))||y==1 && !lager.isEmpty(x,y,z)||y==2){
                            System.out.println("Einlagerung geht nicht, weil Stein zu schwer bzw. kein platz");
                            erfolreich=true;

                        }else{
                            lager.insertProduct(x, y, z, l.get(i));
                            System.out.println("Einlagerung erfolgreich");
                            erfolreich=true;
                            belohnung += l.get(i).getBelohnung();
                        }

                    }

                } else if(Objects.equals(l.get(i).getName(),"Papier")){
                    lager.insertProduct(x, y, z, l.get(i));
                    System.out.println("Einlagerung erfolgreich");
                    erfolreich=true;
                    belohnung += l.get(i).getBelohnung();
                }
            }else System.out.println("Platz besetzt");
        }else if(Objects.equals(l.get(i).getAuftragsart(),"Auslagerung")){  //Auslagerung muss angepasst werden
            if(lager.isEmpty(x,y,z)){
                System.out.println("Palette leer");
            }else{
                if(Objects.equals(l.get(i).getName(),"Holz")){
                lager.insertProduct(x, y, 1, null);
                lager.insertProduct(x, y, 0,null);
                System.out.println("Auslagerung erfolgreich");
                erfolreich=true;
            }else{
                lager.insertProduct(x, y, z, null);
                System.out.println("Auslagerung erfolgreich");
                erfolreich=true;
            }}


        }
        return erfolreich;
    }

    public void umlagern (int i, int x, int y, int z){
        //Produkt auswählen
        if(lager.isEmpty(x,y,z)){
            System.out.println("Das Feld ist leer");
        }else{
            //Produkt lagern

        }

        //Produkt lagern

    }




}
