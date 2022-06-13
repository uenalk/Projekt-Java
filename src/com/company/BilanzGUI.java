package com.company;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.List;

public class BilanzGUI {
    private JPanel content;
    private JTable bilanzTabelle;
    private JLabel lblKosten;
    private JLabel lblUmsatz;
    private JLabel lblEinzelbuchungen;

    public JPanel getContent() {
        return content;
    }

    private List<Kontoeintrag> eintraege;

    public BilanzGUI(List<Kontoeintrag> eintraege) {
        this.eintraege = eintraege;
    }

    public void inhalt() {
        Integer umsatz = 0, kosten = 0;

        DefaultTableModel tabellenModell = (DefaultTableModel) bilanzTabelle.getModel();
        tabellenModell.addColumn("Differenz");
        tabellenModell.addColumn("Nachricht");


        for (Kontoeintrag element : eintraege) {
            tabellenModell.addRow(new String[]{Integer.toString(element.Differenz()), element.Nachricht()});
            if (element.Differenz() > 0) {
                umsatz += element.Differenz();
            } else {
                kosten -= element.Differenz();
            }
        }

        TableColumnModel spaltenModell = bilanzTabelle.getColumnModel();
        spaltenModell.getColumn(0).setMaxWidth(60);

        lblKosten.setText("Kosten gesammt : " + kosten);
        lblUmsatz.setText("Umsatz gesammt: " + umsatz);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        content = new JPanel();
        content.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JScrollPane scrollPane1 = new JScrollPane();
        content.add(scrollPane1, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        bilanzTabelle = new JTable();
        scrollPane1.setViewportView(bilanzTabelle);
        lblUmsatz = new JLabel();
        lblUmsatz.setText("Umsatz gesammt:");
        content.add(lblUmsatz, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblKosten = new JLabel();
        lblKosten.setText("Kosten gesammt:");
        content.add(lblKosten, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblEinzelbuchungen = new JLabel();
        lblEinzelbuchungen.setText("Einzelbuchungen:");
        content.add(lblEinzelbuchungen, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return content;
    }
}