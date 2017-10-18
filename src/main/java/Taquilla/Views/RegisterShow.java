package Taquilla.Views;
import Taquilla.View.Helpers.PanelFactory;
import javafx.scene.control.DatePicker;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Date;

public class RegisterShow {

    private JTextField responsibleName;
    private JTextField responsibleTelephone;
    private JTextField responsibleTelephoneAlt;
    private JTextField responsibleEmail;
    private JTextField playName;
    private JTextArea playDescription;
    private JTextArea playActors;
    private JTextArea playComments;
    private JTextField scheduleDate;
    private JButton scheduleButton;

    JButton savePlay;
    private JPanel gui;
    private JFrame window;

    public RegisterShow(){
        init();
    }

    private void init(){
        this.responsibleName = new JTextField();
        this.responsibleTelephone = new JTextField();
        this.responsibleTelephoneAlt = new JTextField();
        this.responsibleEmail = new JTextField();
        this.playName = new JTextField();
        this.playDescription = new JTextArea();
        this.playActors = new JTextArea();
        this.playComments = new JTextArea();
        this.scheduleDate = new JTextField();
        this.scheduleButton = new JButton();

        this.savePlay = new JButton();
        this.gui = generateGUI();

        this.window = new JFrame();
        this.window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.window.setTitle("Register Show - Taco Taquilla (TM)");
        this.window.setContentPane(gui);
        this.window.pack();
        this.window.setLocationRelativeTo(null);
        this.window.setMinimumSize(window.getSize());
        this.window.setVisible(true);
    }

    private JPanel generateResponsibleData() {
        JPanel responsible = new JPanel();
        PanelFactory panelFactory = new PanelFactory();

        responsibleName.setColumns(20);
        responsibleTelephone.setColumns(15);
        responsibleTelephoneAlt.setColumns(15);
        responsibleEmail.setColumns(20);

        responsible.setLayout(new FlowLayout(FlowLayout.LEFT));
        responsible.setBorder(new TitledBorder("Datos del responsable: "));
        responsible.setPreferredSize(new Dimension(300, 10));
        responsible.add(panelFactory.labeledField("Nombre: ", responsibleName));
        responsible.add(panelFactory.labeledField("Telefono: ", responsibleTelephone));
        responsible.add(panelFactory.labeledField("Telefono Alt: ", responsibleTelephoneAlt));
        responsible.add(panelFactory.labeledField("E-Mail: ", responsibleEmail));

        return responsible;
    }

    private JPanel generatePlayData() {
        JPanel play = new JPanel();
        PanelFactory panelFactory = new PanelFactory();

        playName.setColumns(20);
        playDescription.setColumns(20);
        playDescription.setRows(3);
        playActors.setColumns(20);
        playActors.setRows(3);
        playComments.setColumns(20);
        playComments.setRows(3);

        play.setLayout(new FlowLayout(FlowLayout.LEFT));
        play.setBorder(new TitledBorder("Datos de la obra: "));
        play.setPreferredSize(new Dimension(350, 10));
        play.add(panelFactory.labeledField("Nombre: ", playName));
        play.add(panelFactory.labeledField("Descripcion: ", playDescription));
        play.add(panelFactory.labeledField("Actores: ", playActors));
        play.add(panelFactory.labeledField("Comentarios: ", playComments));

        return play;
    }

    private JPanel generateScheduleData() {
        JPanel schedule = new JPanel();
        PanelFactory panelFactory = new PanelFactory();

        scheduleDate.setColumns(20);
        scheduleButton.setText("Calendario...");

        schedule.setLayout(new FlowLayout(FlowLayout.LEFT));
        schedule.setBorder(new TitledBorder("Horarios y fechas: "));
        schedule.setPreferredSize(new Dimension(350, 10));
        schedule.add(panelFactory.labeledField("Fecha: " , scheduleDate));
        schedule.add(scheduleButton);

        return schedule;
    }

    private JPanel generateGUI() {
        JPanel gui = new JPanel(new BorderLayout());

        savePlay.setText("Guardar Obra");

        gui.setBorder(new TitledBorder("Registrar Show"));
        gui.setPreferredSize(new Dimension(1024, 720));
        gui.add(generateResponsibleData(), BorderLayout.WEST);
        gui.add(generatePlayData(), BorderLayout.CENTER);
        gui.add(generateScheduleData(), BorderLayout.EAST);

        gui.add(savePlay, BorderLayout.SOUTH);

        return gui;
    }

    public JTextField getResponsibleName() {
        return responsibleName;
    }

    public JTextField getResponsibleTelephone() {
        return responsibleTelephone;
    }

    public JTextField getResponsibleTelephoneAlt() {
        return responsibleTelephoneAlt;
    }

    public JTextField getResponsibleEmail() {
        return responsibleEmail;
    }

    public JTextField getPlayName() {
        return playName;
    }

    public JTextArea getPlayDescription() {
        return playDescription;
    }

    public JTextArea getPlayActors() {
        return playActors;
    }

    public JTextArea getPlayComments() {
        return playComments;
    }

    public JTextField getScheduleDate() {
        return scheduleDate;
    }

    public JButton getScheduleButton() {
        return scheduleButton;
    }

    public JButton getSavePlayButton() {
        return savePlay;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RegisterShow::new);
    }

}
