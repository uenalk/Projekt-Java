package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class GUI extends Component {
    private final JButton[][] btnLager0 ;
    private final JButton[][] btnLager1;

    private final JButton btnNaechsterEintrag;
    private final JButton btnUmlagern;
    private final JButton btnVerschrotten;
    private final JButton btnBilanz;

    private final Label lblBelohnung;
    private final Label lblProduktname;
    private final Label lblAttribut1;
    private final Label lblAttribut2;
    private final Label lblAuftragsart;

    int i = 0;
    CSVListe p = new CSVListe();
    List<Produkte> l;
    Abwicklung aw = new Abwicklung();
    Lager lager;


    public GUI()
    {

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

        lblBelohnung = new Label("Belohnung");
        lblProduktname = new Label("Produktart: ");
        lblAttribut1 = new Label("1.Eigenschaft:");
        lblAttribut2 = new Label("2.Eigenschaft: ");
        lblAuftragsart = new Label("Auftragsart: ");

        btnLager1 = new JButton[4][3];
        btnLager0 = new JButton[4][3];
        lager = new Lager();


        //Panels Außen
        JPanel belohnung = new JPanel();
        JPanel infos = new JPanel();
        JPanel buttonsAktion = new JPanel();
        JPanel panel5 = new JPanel();


        //Hinzufügen der Elemente

        belohnung.setLayout(new GridLayout(1,0));
        infos.setLayout(new GridLayout(5,2));
        buttonsAktion.setLayout(new GridLayout(0,4));

        belohnung.add(lblBelohnung);
        infos.add(lblProduktname);
        infos.add(lblAuftragsart);
        infos.add(lblAttribut1);
        infos.add(lblAttribut2);

        buttonsAktion.add(btnNaechsterEintrag);
        buttonsAktion.add(btnUmlagern);
        buttonsAktion.add(btnVerschrotten);
        buttonsAktion.add(btnBilanz);


        //Eigenschaften der Elemente
        belohnung.setBackground(Color.gray);
        panel5.setBackground(Color.blue);

        panel5.setLayout(new BorderLayout());

        belohnung.setPreferredSize(new Dimension(100,50));
        infos.setPreferredSize(new Dimension(200,100));
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
                btnLager1[i][j]=new JButton(x1+y1+1);
                btnLager1[i][j].addActionListener(this::ButtonPressed);
                btnLager1[i][j].setName(x1+y1+1);
                mainInhalt.add(btnLager1[i][j]);
            }
        }

        String x0,y0,z0;
        for(Integer j=2; j>-1;j--){
            for(Integer i=0;i<4;i++){
                x0=i.toString();
                y0=j.toString();
                btnLager0[i][j]=new JButton(x0+y0+0);
                btnLager0[i][j].addActionListener(this::ButtonPressed);
                btnLager0[i][j].setName(x0+y0+0);
                mainInhalt.add(btnLager0[i][j]);
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

        //Button action
        btnNaechsterEintrag.addActionListener(this::nextEintrag);


    }


    public void nextEintrag(ActionEvent e) {
        l=p.getList();
        if(l!=null) {
            lblProduktname.setText("Produktart: " + l.get(i).getName());
            lblAuftragsart.setText("Auftragsart: " + l.get(i).getAuftragsart());
            lblAttribut1.setText("1.Eigenschaft: " + l.get(i).getAttribute1());
            lblAttribut2.setText("2.Eigenschaft: " + l.get(i).getAttribute2());
        }


    }

    public void ButtonPressed(ActionEvent e) {
        nextEintrag(e);
        var name = ((JButton) e.getSource()).getName();
        int index = Integer.parseInt(name);
        int x = index / 100;
        int y = (index / 10) - (x * 10);
        int z;
        if (x != 0) {
            z = (index % (100 * x)) - (y * 10);
        } else z = index - (y * 10);


    }
}


