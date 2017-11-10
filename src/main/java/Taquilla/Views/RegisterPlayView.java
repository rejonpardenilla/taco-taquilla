package Taquilla.Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RegisterPlayView extends javax.swing.JFrame {

    private javax.swing.JLabel scheduleDateLabel;
    private javax.swing.JLabel scheduleHourLabel;
    private javax.swing.JSpinner scheduleHours;
    private javax.swing.JSpinner scheduleMinutes;
    private javax.swing.JLabel scheduleSelectedLabel;
    private javax.swing.JTable showsTable;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton disponibilityButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel panelPlay;
    private javax.swing.JPanel panelResponsible;
    private javax.swing.JPanel panelSchedule;
    private javax.swing.JTextArea playActors;
    private javax.swing.JLabel playActorsLabel;
    private javax.swing.JTextArea playComments;
    private javax.swing.JLabel playCommentsLabel;
    private javax.swing.JTextArea playDescription;
    private javax.swing.JLabel playDescriptionLabel;
    private javax.swing.JTextField playName;
    private javax.swing.JLabel playNameLabel;
    private javax.swing.JTextField responsibleEmail;
    private javax.swing.JLabel responsibleEmailLabel;
    private javax.swing.JTextField responsibleLastName;
    private javax.swing.JLabel responsibleLastNameLabel;
    private javax.swing.JTextField responsibleName;
    private javax.swing.JLabel responsibleNameLabel;
    private javax.swing.JTextField responsibleTelephone;
    private javax.swing.JTextField responsibleTelephoneAlt;
    private javax.swing.JLabel responsibleTelephoneAltLabel;
    private javax.swing.JLabel responsibleTelephoneLabel;
    private javax.swing.JButton saveButton;
    private javax.swing.JTextField scheduleDate;
    private DefaultTableModel showsTableModel;

    public RegisterPlayView() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        panelResponsible = new javax.swing.JPanel();
        responsibleNameLabel = new javax.swing.JLabel();
        responsibleName = new javax.swing.JTextField();
        responsibleLastNameLabel = new javax.swing.JLabel();
        responsibleLastName = new javax.swing.JTextField();
        responsibleTelephoneLabel = new javax.swing.JLabel();
        responsibleTelephone = new javax.swing.JTextField();
        responsibleTelephoneAltLabel = new javax.swing.JLabel();
        responsibleTelephoneAlt = new javax.swing.JTextField();
        responsibleEmailLabel = new javax.swing.JLabel();
        responsibleEmail = new javax.swing.JTextField();
        panelPlay = new javax.swing.JPanel();
        playNameLabel = new javax.swing.JLabel();
        playName = new javax.swing.JTextField();
        playDescriptionLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        playDescription = new javax.swing.JTextArea();
        playActorsLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        playActors = new javax.swing.JTextArea();
        playCommentsLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        playComments = new javax.swing.JTextArea();
        panelSchedule = new javax.swing.JPanel();
        scheduleSelectedLabel = new javax.swing.JLabel();
        scheduleHourLabel = new javax.swing.JLabel();
        scheduleHours = new javax.swing.JSpinner();
        scheduleMinutes = new javax.swing.JSpinner();
        jScrollPane4 = new javax.swing.JScrollPane();
        showsTable = new javax.swing.JTable();
        scheduleDateLabel = new javax.swing.JLabel();
        scheduleDate = new javax.swing.JTextField();
        disponibilityButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registrar obra - Taco Taquilla (TM)");
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        panelResponsible.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del responsable:"));

        responsibleNameLabel.setText("Nombre:");

        responsibleLastNameLabel.setText("Apellido:");

        responsibleTelephoneLabel.setText("Número de teléfono:");

        responsibleTelephoneAltLabel.setText("Número de teléfono alternativo:");

        responsibleEmailLabel.setText("E-mail:");

        javax.swing.GroupLayout panelResponsibleLayout = new javax.swing.GroupLayout(panelResponsible);
        panelResponsible.setLayout(panelResponsibleLayout);
        panelResponsibleLayout.setHorizontalGroup(
                panelResponsibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelResponsibleLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelResponsibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(responsibleNameLabel)
                                        .addComponent(responsibleName, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(responsibleLastNameLabel)
                                        .addComponent(responsibleLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(responsibleTelephoneLabel)
                                        .addComponent(responsibleTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(responsibleTelephoneAltLabel)
                                        .addComponent(responsibleTelephoneAlt, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(responsibleEmailLabel)
                                        .addComponent(responsibleEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(20, Short.MAX_VALUE))
        );
        panelResponsibleLayout.setVerticalGroup(
                panelResponsibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelResponsibleLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(responsibleNameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(responsibleName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(responsibleLastNameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(responsibleLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(responsibleTelephoneLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(responsibleTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(responsibleTelephoneAltLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(responsibleTelephoneAlt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(responsibleEmailLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(responsibleEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelPlay.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de la obra:"));

        playNameLabel.setText("Nombre:");

        playDescriptionLabel.setText("Descripción:");

        playDescription.setColumns(20);
        playDescription.setRows(5);
        jScrollPane1.setViewportView(playDescription);

        playActorsLabel.setText("Actores principales (uno por línea):");

        playActors.setColumns(20);
        playActors.setRows(5);
        jScrollPane2.setViewportView(playActors);

        playCommentsLabel.setText("Otros datos relevantes:");

        playComments.setColumns(20);
        playComments.setRows(5);
        jScrollPane3.setViewportView(playComments);

        javax.swing.GroupLayout panelPlayLayout = new javax.swing.GroupLayout(panelPlay);
        panelPlay.setLayout(panelPlayLayout);
        panelPlayLayout.setHorizontalGroup(
                panelPlayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelPlayLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelPlayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                                        .addGroup(panelPlayLayout.createSequentialGroup()
                                                .addGroup(panelPlayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(playNameLabel)
                                                        .addComponent(playDescriptionLabel)
                                                        .addComponent(playActorsLabel)
                                                        .addComponent(playCommentsLabel))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(jScrollPane2)
                                        .addComponent(jScrollPane3)
                                        .addComponent(playName))
                                .addContainerGap())
        );
        panelPlayLayout.setVerticalGroup(
                panelPlayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelPlayLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(playNameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(playName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(playDescriptionLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(playActorsLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(playCommentsLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelSchedule.setBorder(javax.swing.BorderFactory.createTitledBorder("Fechas y horarios:"));

        scheduleSelectedLabel.setText("Fechas seleccionadas disponibles:");

        scheduleHourLabel.setText("Horario (24 horas):");

        scheduleHours.setModel(new javax.swing.SpinnerNumberModel(23, 0, 23, 1));

        scheduleMinutes.setModel(new javax.swing.SpinnerNumberModel(59, 0, 59, 1));

        String header[] = {"Fecha", "Hora"};
        String data[][] = {};

        showsTableModel = new DefaultTableModel(data, header);

        showsTable.setModel(showsTableModel);

        jScrollPane4.setViewportView(showsTable);

        scheduleDateLabel.setText("Selecciones fecha (dd-MM-yyyy):");

        disponibilityButton.setText("Comprobar disponibilidad...");

        javax.swing.GroupLayout panelScheduleLayout = new javax.swing.GroupLayout(panelSchedule);
        panelSchedule.setLayout(panelScheduleLayout);
        panelScheduleLayout.setHorizontalGroup(
                panelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelScheduleLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(scheduleSelectedLabel)
                                                .addComponent(scheduleHourLabel)
                                                .addGroup(panelScheduleLayout.createSequentialGroup()
                                                        .addComponent(scheduleHours, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(scheduleMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                                                .addComponent(scheduleDateLabel)
                                                .addComponent(scheduleDate))
                                        .addComponent(disponibilityButton))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelScheduleLayout.setVerticalGroup(
                panelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelScheduleLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scheduleDateLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scheduleDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(scheduleHourLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(scheduleHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(scheduleMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(disponibilityButton)
                                .addGap(18, 18, 18)
                                .addComponent(scheduleSelectedLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        saveButton.setText("Guardar");

        cancelButton.setText("Cancelar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panelResponsible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(panelPlay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(panelSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(80, 80, 80)
                                                .addComponent(saveButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cancelButton)
                                                .addGap(93, 93, 93))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(panelResponsible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(panelPlay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(panelSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(cancelButton)
                                                        .addComponent(saveButton))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegisterPlayView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegisterPlayView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegisterPlayView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisterPlayView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegisterPlayView().setVisible(true);
            }
        });
    }

    public JSpinner getScheduleHours() {
        return scheduleHours;
    }

    public JSpinner getScheduleMinutes() {
        return scheduleMinutes;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JButton getDisponibilityButton() {
        return disponibilityButton;
    }

    public JTextArea getPlayActors() {
        return playActors;
    }

    public JTextArea getPlayComments() {
        return playComments;
    }

    public JTextArea getPlayDescription() {
        return playDescription;
    }

    public JTextField getPlayName() {
        return playName;
    }

    public JTextField getResponsibleEmail() {
        return responsibleEmail;
    }

    public JTextField getResponsibleLastName() {
        return responsibleLastName;
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

    public JButton getSaveButton() {
        return saveButton;
    }

    public JTextField getScheduleDate() {
        return scheduleDate;
    }

    public DefaultTableModel getTableModel() {
        return showsTableModel;
    }

}
