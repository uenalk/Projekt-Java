package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class GUI {

    private final JLabel Produktart;
    private final JLabel Attribute1;
    private final JLabel Attribute2;
    private final JLabel Belohnung;

    private final JButton btnNext;
    private final JButton btnUmlagern;
    private final JButton btnVerschrotten;
    private final JButton btnBilanz;
    private final JButton[][] btnLager1 ;
    private final JButton[][] btnLager2;
    private final int zeile=3;
    private final int spalte=4;



    int cnt =0;
    CSVListe csvData = new CSVListe();
    List<Produkte> liste = new ArrayList<>();



    public GUI()
    {
        JFrame frame = new JFrame();

        btnNext = new JButton("Nächster Eintrag");
        btnUmlagern = new JButton("Umlaagern");
        btnVerschrotten = new JButton("Verschrotten");
        btnBilanz= new JButton("Bilanz anzeigen");
        btnLager1 = new JButton[spalte][zeile];
        btnLager2 = new JButton[spalte][zeile];

        Produktart   = new JLabel();
        Attribute1 = new JLabel();
        Attribute2 = new JLabel();
        Belohnung = new JLabel();

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        panel.setLayout(new GridLayout(6,4));

        for(int y = 0; y < zeile; y++){
            for(int x = 0; x < spalte; x++){
                btnLager2[x][y] = new JButton("2");
                btnLager2[x][y].addActionListener(new hintereReiheListener());
                panel.add(btnLager2[x][y]);
            }
        }

        for(int y = 0; y < zeile; y++){
            for(int x = 0; x < spalte; x++){
                btnLager1[x][y] = new JButton("1");
                btnLager1[x][y].addActionListener(new vordereReiheListener());
                panel.add(btnLager1[x][y]);
            }
        }






        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Lagerspiel");
        frame.pack();
        frame.setVisible(true);



    }

    //Button aktion
    private class vordereReiheListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //Some code to change a specific button
        }
    }

    private class hintereReiheListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //Some code to change a specific button
        }
    }
}
