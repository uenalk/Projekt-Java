package com.company;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Main {

    public static void main(String[] args)  {
        //Grafik
        //new GUI();

        CSVListe p =new CSVListe();
        List<Produkte> l ;
        l= p.getList();
        Lager lager = new Lager();


        int x,y,z;
        int i =2;
        Scanner sc = new Scanner(System.in);
        System.out.print("X koordinate: ");
        x=sc.nextInt();
        System.out.print("y koordinate: ");
        y=sc.nextInt();
        System.out.print("z koordinate: ");
        z=sc.nextInt();

        if(Objects.equals(l.get(i).getAuftragsart(), "Einlagerung")){
            if(lager.isEmpty(x,y,z)){
                if(Objects.equals(l.get(i).getName(),"Holz")){
                    if((z == 0 && !lager.isEmpty(x, y, 1)||(z==1 && !lager.isEmpty(x, y, 0)))){
                        System.out.println("Einlagerung geht nicht, weil platz reicht nicht aus");
                    }else {
                        lager.insertProduct(x, y, z, l.get(i));
                        System.out.println("Einlagerung erfolgreich");
                    }
                }else if (Objects.equals(l.get(i).getName(),"Stein")){                      //Stein muss bearbeitet werden
                    if(Objects.equals(l.get(i).getAttribute2(),"Schwer"))
                    {
                        if(!lager.isEmpty(x,0,z)||(y!=0)){
                            System.out.println("Einlagerung geht nicht, weil Stein zu schwer bzw. kein platz");
                        }else{
                            lager.insertProduct(x, y, z, l.get(i));
                            System.out.println("Einlagerung erfolgreich");
                        }

                    }else if (Objects.equals(l.get(i).getAttribute2(),"Mittel")){
                        if((y==0 && !lager.isEmpty(x,y,z))||y==1 && !lager.isEmpty(x,y,z)||y==2){
                            System.out.println("Einlagerung geht nicht, weil Stein zu schwer bzw. kein platz");
                        }else{
                            lager.insertProduct(x, y, z, l.get(i));
                            System.out.println("Einlagerung erfolgreich");
                        }

                    }

                }else{
                    lager.insertProduct(x, y, z, l.get(i));
                    System.out.println("Einlagerung erfolgreich");
                    //Belohnung muss gut geschrieben werden
                }

                lager.insertProduct(x, y, z, l.get(i));
                System.out.println("Einlagerung erfolgreich");

            }else System.out.println("Platz besetzt");
        }
        else if (Objects.equals(l.get(i).getAuftragsart(), "Auslagerung")){
            if(!lager.isEmpty(x,y,z)){
                lager.insertProduct(x,y,z,null);
                System.out.println("Auslagerung erfolgreich");
            }else System.out.println("Gibt nichts zum Auslagern");
        }


    }
}
