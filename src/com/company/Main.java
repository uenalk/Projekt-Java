package com.company;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)  {
        CSVListe p = new CSVListe();
        List<Produkte> l;
        l= p.getList();
        Scanner sc = new Scanner(System.in);
        Abwicklung ab = new Abwicklung();
        int x,y,z;

        for (int i=0;i<l.size()-1;i++){

            System.out.println(l.get(i).getName());
            System.out.println(l.get(i).getAuftragsart());
            System.out.println(l.get(i).getAttribute1());
            System.out.println(l.get(i).getAttribute2());
            System.out.println(" ");

            System.out.print("x: ");
            x= sc.nextInt();
            System.out.print("y: ");
            y= sc.nextInt();
            System.out.print("z: ");
            z= sc.nextInt();

            ab.naechsterEintrag(i,x,y,z);

        }
    }

}
