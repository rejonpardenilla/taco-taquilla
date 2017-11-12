package Taquilla.View.Helpers;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class GUI extends JPanel{
    private PanelFactory panelFactory = new PanelFactory();
    private final DOM DOM = new DOM();

    /**
     * Constructors
     */
    private GUI(String title){
        this.setBorder(new TitledBorder(title));
    }

    public GUI(LayoutManager layout) {
        super(layout);
    }

    public GUI(LayoutManager layout, String title){
        super(layout);
        this.setBorder(new TitledBorder(title));
    }

    /**
     * DOM Manipulation
     * INPUT
     */
    @Override
    public Component add(String id, Component component){
        super.add(component);
        DOM.add(id, component);
        return component;
    }

    public Component add(String id, Component component, Object constraints){
        super.add(component, constraints);
        DOM.add(id, component);
        return component;
    }

    /**
     * DOM Manipulation
     * OUTPUT
     */
    //For all your JQuery needs
    public Component $(String id){
        return getById(id);
    }

    private Component getById(String id){
        return DOM.findById(id);
    }

    /**
     * Custom field creation
     */
    private void addLabeledField(String id, String label, Component field){
        JPanel panel = new JPanel(new BorderLayout());
        JLabel jlabel = new JLabel(label);
        jlabel.setLabelFor(field);
        panel.add(jlabel, BorderLayout.WEST);
        panel.add(field, BorderLayout.EAST);
        DOM.add(id, field);
        DOM.add(id+"Label", field);
        this.add(panel);
    }

    public static void main(String[] args) {
        JFrame window = new JFrame();
        GUI gui = new GUI("hello");
        PanelFactory pf = new PanelFactory();
        gui.addLabeledField("hello", "moto", new JTextField("hi"));
        System.out.println(((JTextField)gui.getById("hello")).getText());
        ((JTextField)gui.getById("hello")).setText("jjj");
        Component c = gui.getById("hello");
//        gui.add("h", pf.labeledFieldC("ho", new TextField()));
        gui.setVisible(true);
        window.setContentPane(gui);
        window.pack();
        window.setVisible(true);
    }


}


