package Taquilla.View.Helpers;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class PanelFactory {
    public JPanel labeledField(String label, Component field){
        JPanel panel = new JPanel(new BorderLayout());
        JLabel jlabel = new JLabel(label);
        jlabel.setLabelFor(field);
        panel.add(jlabel, BorderLayout.WEST);
        panel.add(field, BorderLayout.EAST);
        return panel;
    }
    public Component labeledFieldC(String label, Component field){
        JPanel panel = new JPanel(new BorderLayout());
        JLabel jlabel = new JLabel(label);
        jlabel.setLabelFor(field);
        panel.add(jlabel, BorderLayout.WEST);
        panel.add(field, BorderLayout.EAST);
        Component comp = panel.getComponent(0);
        comp = panel.getComponent(1);
        return field;
    }

    public JPanel titledPanel(String title){
        return titledPanel(title, null);
    }

    public JPanel titledPanel(String title, LayoutManager layoutManager){
        JPanel panel = new JPanel(layoutManager);
        panel.setBorder(new TitledBorder(title));
        return panel;
    }
}
