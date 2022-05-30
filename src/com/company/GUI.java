package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class GUI extends Component {
    private final JButton[][][] btnLager ;
    private final JButton btnNaechsterEintrag;
    private final JButton btnUmlagern;
    private final JButton btnVerschrotten;
    private final JButton btnBilanz;
    private final JButton btnAuftragBearbeiten, btnAuftragAblehnen;

    private final Label lblBelohnung;
    private final Label lblProduktname;
    private final Label lblAttribut1;
    private final Label lblAttribut2;
    private final Label lblAuftragsart;
    private final Label lblWert;

    private final JPanel panel,panelAuftrag;
    private final JButton btnFachA,btnFachB,btnFachC;

    int i;
    CSVListe p = new CSVListe();
    List<Produkte> l;
    Abwicklung aw = new Abwicklung();
    Lager lager;
    int belohnung;
    boolean btnBearbeitenIsPressed;
    boolean btnUmlagernIsPressed;
    boolean btnVerschrottenIsPressed;
    boolean btnFachAIsPressed, btnFachBISPressed, btnFachCIsPressed;
    boolean btnFlaecheIsPressed;


    public GUI()
    {
        btnFlaecheIsPressed=false;
        btnFachAIsPressed=false;
        btnFachBISPressed=false;
        btnFachCIsPressed=false;
        btnBearbeitenIsPressed=false;
        btnUmlagernIsPressed=false;
        btnVerschrottenIsPressed=false;

        //Windows
        JFrame frame = new JFrame();
        frame.setTitle("Lagerspiel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLayout(new BorderLayout(10,10));
        frame.setVisible(true);


        //Objekt erzeugung
        btnNaechsterEintrag = new JButton("Nächster Eintrag");
        btnUmlagern = new JButton("Umlagern");
        btnVerschrotten = new JButton("Verschrotten");
        btnBilanz = new JButton("Bilanz");
        btnFachA = new JButton("Fach A");
        btnFachB= new JButton("Fach B");
        btnFachC = new JButton("Fach C");
        btnAuftragBearbeiten = new JButton("Auftrag bearbeiten");
        btnAuftragAblehnen = new JButton("Auftrag ablehnen");

        lblBelohnung = new Label("Belohnung: ");
        lblProduktname = new Label("Produktart: ");
        lblAttribut1 = new Label("1.Eigenschaft:");
        lblAttribut2 = new Label("2.Eigenschaft: ");
        lblAuftragsart = new Label("Auftragsart: ");
        lblWert = new Label("Wert: ");

        btnLager = new JButton[4][3][2];
        lager = new Lager();

        panel = new JPanel();
        panelAuftrag = new JPanel();
        panelAuftrag.setLayout(new GridLayout(0,2));
        panelAuftrag.add(btnAuftragBearbeiten);
        panelAuftrag.add(btnAuftragAblehnen);


        //Panels Außen
        JPanel belohnung = new JPanel();
        JPanel infos = new JPanel();
        JPanel buttonsAktion = new JPanel();
        JPanel panel5 = new JPanel();

        //Hinzufügen der Elemente

        belohnung.setLayout(new GridLayout(1,2));
        infos.setLayout(new GridLayout(6,2));
        buttonsAktion.setLayout(new GridLayout(0,4));

        belohnung.add(lblBelohnung);
        belohnung.add(btnBilanz);

        infos.add(lblProduktname);
        infos.add(lblAuftragsart);
        infos.add(lblAttribut1);
        infos.add(lblAttribut2);
        infos.add(lblWert);
        infos.add(panelAuftrag);


        buttonsAktion.add(btnNaechsterEintrag);
        buttonsAktion.add(btnUmlagern);
        buttonsAktion.add(btnVerschrotten);
        buttonsAktion.add(panel);
        panel.setLayout(new GridLayout(3,0));
        panel.add(btnFachA);
        panel.add(btnFachB);
        panel.add(btnFachC);


        //Eigenschaften
        lblBelohnung.setText("Belohnung: "+aw.getBelohnung());
        belohnung.setBackground(Color.gray);
        btnBilanz.setBackground(Color.gray);
        panel5.setBackground(Color.blue);

        panel5.setLayout(new BorderLayout());

        belohnung.setPreferredSize(new Dimension(100,50));
        infos.setPreferredSize(new Dimension(300,100));
        buttonsAktion.setPreferredSize(new Dimension(100,100));
        panel5.setPreferredSize(new Dimension(100,100));

        //------------- sub panels --------------------

        //Objekt erzeugung
        JPanel oben = new JPanel();
        JPanel unten = new JPanel();
        JPanel links = new JPanel();
        JPanel rechts = new JPanel();
        JPanel mainInhalt = new JPanel();


        //Eigenschaften der Elemente
        oben.setBackground(Color.white);
        unten.setBackground(Color.white);
        links.setBackground(Color.white);
        rechts.setBackground(Color.white);
        mainInhalt.setBackground(Color.white);

        panel5.setLayout(new BorderLayout());
        oben.setPreferredSize(new Dimension(50,50));
        unten.setPreferredSize(new Dimension(50,50));
        links.setPreferredSize(new Dimension(50,50));
        rechts.setPreferredSize(new Dimension(50,50));
        mainInhalt.setPreferredSize(new Dimension(50,50));

        links.setLayout(new GridLayout(0,1));
        mainInhalt.setLayout(new GridLayout(6,4));

        //Buttons erzegung
        String x1,y1;
        for(Integer j=2; j>-1;j--){
            for(Integer i=0;i<4;i++){
                x1=i.toString();
                y1=j.toString();
                btnLager[i][j][1]=new JButton(" ");
                btnLager[i][j][1].addActionListener(this::ButtonPressed);
                btnLager[i][j][1].setName(x1+y1+1);
                mainInhalt.add(btnLager[i][j][1]);
            }
        }

        String x0,y0;
        for(Integer j=2; j>-1;j--){
            for(Integer i=0;i<4;i++){
                x0=i.toString();
                y0=j.toString();
                btnLager[i][j][0]=new JButton(" ");
                btnLager[i][j][0].addActionListener(this::ButtonPressed);
                btnLager[i][j][0].setName(x0+y0+0);
                mainInhalt.add(btnLager[i][j][0]);
            }
        }


        panel5.add(oben,BorderLayout.NORTH);
        panel5.add(unten,BorderLayout.SOUTH);
        panel5.add(links,BorderLayout.WEST);
        panel5.add(rechts,BorderLayout.EAST);
        panel5.add(mainInhalt,BorderLayout.CENTER);

        //------------- sub panels --------------------

        frame.add(belohnung,BorderLayout.NORTH);
        frame.add(infos,BorderLayout.WEST);
        frame.add(buttonsAktion,BorderLayout.SOUTH);
        frame.add(panel5,BorderLayout.CENTER);

        //Startzustände
        btnBilanz.setEnabled(false);
        btnAuftragBearbeiten.setEnabled(false);
        btnVerschrotten.setEnabled(false);
        btnAuftragBearbeiten.setEnabled(false);
        btnAuftragAblehnen.setEnabled(false);
        btnNaechsterEintrag.setEnabled(false);
        btnUmlagern.setEnabled(false);

        for(Integer j=2; j>-1;j--){
            for(Integer i=0;i<4;i++){
                btnLager[i][j][1].setEnabled(false);
            }
        }

        for(Integer j=2; j>-1;j--){
            for(Integer i=0;i<4;i++){
                btnLager[i][j][0].setEnabled(false);
            }
        }


        //Button action
        btnNaechsterEintrag.addActionListener(this::nextEintrag);
        btnFachA.addActionListener(this::pressedFachA);
        btnFachB.addActionListener(this::pressedFachB);
        btnFachC.addActionListener(this::pressedFachC);
        btnAuftragAblehnen.addActionListener(this::pressedAblehnen);
        btnAuftragBearbeiten.addActionListener(this::pressedBearbeiten);
        btnUmlagern.addActionListener(this::pressedUmlagern);
        btnVerschrotten.addActionListener(this::pressedVerschrotten);



    }

    private void pressedFachC(ActionEvent e) {
        btnNaechsterEintrag.setEnabled(true);
        btnUmlagern.setEnabled(true);
        btnVerschrotten.setEnabled(true);
        btnFachC.setBorderPainted(true);
        btnFachB.setBorderPainted(false);
        btnFachA.setBorderPainted(false);
    }

    private void pressedFachB(ActionEvent e) {
        btnNaechsterEintrag.setEnabled(true);
        btnUmlagern.setEnabled(true);
        btnVerschrotten.setEnabled(true);

        btnFachA.setBorderPainted(false);
        btnFachC.setBorderPainted(false);
        btnFachB.setBorderPainted(true);
    }

    private void pressedVerschrotten(ActionEvent e) {
        btnVerschrottenIsPressed=true;
        for(Integer j=2; j>-1;j--){
            for(Integer i=0;i<4;i++){
                btnLager[i][j][1].setEnabled(true);
            }
        }

        for(Integer j=2; j>-1;j--){
            for(Integer i=0;i<4;i++){
                btnLager[i][j][0].setEnabled(true);
            }
        }



    }

    private void pressedUmlagern(ActionEvent e) {
        btnUmlagernIsPressed=true;
    }

    private void pressedBearbeiten(ActionEvent e) {
        btnAuftragAblehnen.setBorderPainted(true);
        btnAuftragAblehnen.setEnabled(true);
        btnAuftragBearbeiten.setEnabled(true);
        btnVerschrotten.setEnabled(false);
        btnUmlagern.setEnabled(false);
        btnBearbeitenIsPressed=true;

        lblProduktname.setText("Produktart: " + l.get(i).getName());
        lblAuftragsart.setText("Auftragsart: " + l.get(i).getAuftragsart());
        lblAttribut1.setText("1.Eigenschaft: " + l.get(i).getAttribute1());
        lblAttribut2.setText("2.Eigenschaft: " + l.get(i).getAttribute2());
        lblWert.setText("Wert: "+ l.get(i).getBelohnung());

        for(Integer j=2; j>-1;j--){
            for(Integer i=0;i<4;i++){
                btnLager[i][j][1].setEnabled(true);
            }
        }

        for(Integer j=2; j>-1;j--){
            for(Integer i=0;i<4;i++){
                btnLager[i][j][0].setEnabled(true);
            }
        }



    }

    private void pressedAblehnen(ActionEvent e) {
        belohnung = aw.getBelohnung()-l.get(i).getBelohnung();
       lblBelohnung.setText("Belohnung: "+ belohnung);
       JOptionPane.showMessageDialog(null,"Als Vertragsstrafe wird die Belohnung vom Kontostand abgezogen","Vertragsstrafe",JOptionPane.INFORMATION_MESSAGE);


        btnFachA.setBorderPainted(true);
        btnFachC.setBorderPainted(true);
        btnFachB.setBorderPainted(true);

        btnUmlagern.setEnabled(false);
        btnVerschrotten.setEnabled(false);


        btnNaechsterEintrag.setEnabled(false);
        btnNaechsterEintrag.setBorderPainted(true);

        btnAuftragBearbeiten.setEnabled(false);
        btnAuftragBearbeiten.setBorderPainted(true);

        btnAuftragAblehnen.setEnabled(false);
        btnAuftragAblehnen.setBorderPainted(true);

        for(Integer j=2; j>-1;j--){
            for(Integer i=0;i<4;i++){
                btnLager[i][j][1].setEnabled(false);
            }
        }

        for(Integer j=2; j>-1;j--){
            for(Integer i=0;i<4;i++){
                btnLager[i][j][0].setEnabled(false);
            }
        }

        i++;

        lblProduktname.setText("Produktart: ");
        lblAttribut1.setText("1.Eigenschaft:");
        lblAttribut2.setText("2.Eigenschaft: ");
        lblAuftragsart.setText("Auftragsart: ");

    }

    private void pressedFachA(ActionEvent e) {
        btnNaechsterEintrag.setEnabled(true);
        btnUmlagern.setEnabled(true);
        btnVerschrotten.setEnabled(true);

        btnFachB.setBorderPainted(false);
        btnFachC.setBorderPainted(false);
        btnFachA.setBorderPainted(true);
    }


    public void nextEintrag(ActionEvent e) {
        l=p.getList();
        if(l!=null) {
            lblProduktname.setText("Produktart: " + l.get(i).getName());
            lblAuftragsart.setText("Auftragsart: " + l.get(i).getAuftragsart());
            lblAttribut1.setText("1.Eigenschaft: " + l.get(i).getAttribute1());
            lblAttribut2.setText("2.Eigenschaft: " + l.get(i).getAttribute2());
            lblWert.setText("Wert: "+ l.get(i).getBelohnung());
            btnAuftragBearbeiten.setEnabled(true);
            btnAuftragAblehnen.setEnabled(true);
            btnNaechsterEintrag.setEnabled(true);

        }


    }

    public void ButtonPressed(ActionEvent e) {
        btnFlaecheIsPressed=true;
        var name = ((JButton) e.getSource()).getName();
        int index = Integer.parseInt(name);
        int x = index / 100;
        int y = (index / 10) - (x * 10);
        int z;
        if (x != 0) {
            z = (index % (100 * x)) - (y * 10);
        } else z = index - (y * 10);

        if(btnBearbeitenIsPressed){
            aw.naechsterEintrag(i,x,y,z);

                if (Objects.equals(l.get(i).getAuftragsart(), "Einlagerung")) {
                    if (aw.einlagerungErfolgreich()) {
                        if (Objects.equals(l.get(i).getName(), "Holz")) {
                            btnLager[x][y][1].setText(l.get(i).getName() + " " + l.get(i).getAttribute1() + " " + l.get(i).getAttribute2());
                            btnLager[x][y][0].setText(l.get(i).getName() + " " + l.get(i).getAttribute1() + " " + l.get(i).getAttribute2());
                        } else
                            btnLager[x][y][z].setText(l.get(i).getName() + " " + l.get(i).getAttribute1() + " " + l.get(i).getAttribute2());

                        for (Integer j = 2; j > -1; j--) {
                            for (Integer i = 0; i < 4; i++) {
                                btnLager[i][j][1].setEnabled(false);
                            }
                        }

                        for (Integer j = 2; j > -1; j--) {
                            for (Integer i = 0; i < 4; i++) {
                                btnLager[i][j][0].setEnabled(false);
                            }
                        }
                        btnFachA.setEnabled(true);
                        btnFachB.setEnabled(true);
                        btnFachC.setEnabled(true);

                        btnFachA.setBorderPainted(true);
                        btnFachC.setBorderPainted(true);
                        btnFachB.setBorderPainted(true);


                        btnNaechsterEintrag.setEnabled(false);
                        btnNaechsterEintrag.setBorderPainted(true);

                        btnAuftragBearbeiten.setEnabled(false);
                        btnAuftragBearbeiten.setBorderPainted(true);

                        btnAuftragAblehnen.setEnabled(false);
                        btnAuftragAblehnen.setBorderPainted(true);
                        i++;
                    } else {
                        for (Integer j = 2; j > -1; j--) {
                            for (Integer i = 0; i < 4; i++) {
                                btnLager[i][j][1].setEnabled(true);
                            }
                        }

                        for (Integer j = 2; j > -1; j--) {
                            for (Integer i = 0; i < 4; i++) {
                                btnLager[i][j][0].setEnabled(true);
                            }
                        }
                        btnBearbeitenIsPressed = true;

                        btnFachA.setEnabled(false);
                        btnFachB.setEnabled(false);
                        btnFachC.setEnabled(false);

                    }
                } else if (Objects.equals(l.get(i).getAuftragsart(), "Auslagerung")) {
                    if (aw.auslagerungErfolgreich()) {
                        if (Objects.equals(l.get(i).getName(), "Holz")) {
                            btnLager[x][y][1].setText(" ");
                            btnLager[x][y][0].setText(" ");
                        } else btnLager[x][y][z].setText(" ");

                        for (Integer j = 2; j > -1; j--) {
                            for (Integer i = 0; i < 4; i++) {
                                btnLager[i][j][1].setEnabled(false);
                            }
                        }

                        for (Integer j = 2; j > -1; j--) {
                            for (Integer i = 0; i < 4; i++) {
                                btnLager[i][j][0].setEnabled(false);
                            }
                        }
                        btnFachA.setEnabled(true);
                        btnFachB.setEnabled(true);
                        btnFachC.setEnabled(true);

                        btnFachA.setBorderPainted(true);
                        btnFachC.setBorderPainted(true);
                        btnFachB.setBorderPainted(true);

                        btnNaechsterEintrag.setEnabled(false);
                        btnNaechsterEintrag.setBorderPainted(true);

                        btnAuftragBearbeiten.setEnabled(false);
                        btnAuftragBearbeiten.setBorderPainted(true);

                        btnAuftragAblehnen.setEnabled(false);
                        btnAuftragAblehnen.setBorderPainted(true);
                        i++;
                    } else {
                        for (Integer j = 2; j > -1; j--) {
                            for (Integer i = 0; i < 4; i++) {
                                btnLager[i][j][1].setEnabled(true);
                            }
                        }

                        for (Integer j = 2; j > -1; j--) {
                            for (Integer i = 0; i < 4; i++) {
                                btnLager[i][j][0].setEnabled(true);
                            }
                        }

                        btnBearbeitenIsPressed = true;

                        btnFachA.setEnabled(false);
                        btnFachB.setEnabled(false);
                        btnFachC.setEnabled(false);

                    }
                }

                btnFlaecheIsPressed = false;
                btnBearbeitenIsPressed = false;
        }else if (btnVerschrottenIsPressed&&btnFlaecheIsPressed){
            lblBelohnung.setText("Belohnung: " + (aw.getBelohnung()-300));
            aw.verschrotten(i,x,y,z);
            if(l.get(i).getName()=="Holz"){
                btnLager[x][y][0].setText(" ");
                btnLager[x][y][1].setText(" ");
                btnVerschrottenIsPressed=false;
                btnFlaecheIsPressed=false;
            }else{
                btnLager[x][y][z].setText(" ");
                btnVerschrottenIsPressed=false;
                btnFlaecheIsPressed=false;
            }


        }else if(btnUmlagernIsPressed){

            btnUmlagernIsPressed=false;
            btnFlaecheIsPressed=false;
        }





        }


}




