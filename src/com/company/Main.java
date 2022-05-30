package com.company;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main extends JFrame {

    public static void main(String[] args) {
        new GUI();

        /*CSVListe p = new CSVListe();
        List<Produkte> l;
        l= p.getList();
        Scanner sc = new Scanner(System.in);
        Abwicklung ab = new Abwicklung();
        int x,y,z,x1,y1,z1;
        int aktion;

        for (int i=0;i<l.size()-1;i++){

            System.out.println(l.get(i).getName());
            System.out.println(l.get(i).getAuftragsart());
            System.out.println(l.get(i).getAttribute1());
            System.out.println(l.get(i).getAttribute2());

            System.out.print("Aktion: ");
            aktion= sc.nextInt();

            if(aktion==0){
                System.out.println(" ");
                System.out.print("x: ");
                x= sc.nextInt();
                System.out.print("y: ");
                y= sc.nextInt();
                System.out.print("z: ");
                z= sc.nextInt();

                ab.naechsterEintrag(i,x,y,z);
                System.out.println(" ");
                System.out.printf("Belohnung: %d \n", ab.getBelohnung());
                System.out.println(" ");
            }


            if(aktion==1){
                System.out.println("Verschrotten ");
                System.out.print("x: ");
                x= sc.nextInt();
                System.out.print("y: ");
                y= sc.nextInt();
                System.out.print("z: ");
                z= sc.nextInt();

                ab.verschrotten(x,y,z);
                System.out.println(" ");
                System.out.printf("Belohnung: %d \n", ab.getBelohnung());
                System.out.println(" ");
            }

            if(aktion==2){
                System.out.println("Umlagern ");
                System.out.print("x: ");
                x= sc.nextInt();
                System.out.print("y: ");
                y= sc.nextInt();
                System.out.print("z: ");
                z= sc.nextInt();
                System.out.print("Gewünschtes x: ");
                x1= sc.nextInt();
                System.out.print("Gewünschtes y: ");
                y1= sc.nextInt();
                System.out.print("Gewünschtes z: ");
                z1= sc.nextInt();

                ab.umlagern(x,y,z,x1,y1,z1);
                System.out.println(" ");
                System.out.printf("Belohnung: %d \n", ab.getBelohnung());
                System.out.println(" ");
            }
        }*/
    }

}
