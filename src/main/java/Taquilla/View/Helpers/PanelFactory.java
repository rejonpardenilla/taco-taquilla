package Taquilla.View.Helpers;

import javax.swing.*;
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
}
