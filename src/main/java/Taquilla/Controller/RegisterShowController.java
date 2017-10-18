package Taquilla.Controller;

import Taquilla.Views.RegisterShow;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador para la vista de registro de obras. Permite manipular los elementos dentro de la vista. Aquí se pueden
 * realizar todas las acciones correspondientes a obtener datos de la gui, procesarlos con el modelo y realizar acciones
 * adecuadas. Por ejemplo, podemos obtener los datos de la obra, y podemos crear las funciones para enviar y guardar
 * esos datos a la base de datos, usando el objeto adecuado para la obra, y el dao para acceso al a base de datos.
 */

public class RegisterShowController implements ActionListener {
    private final RegisterShow view;

    public RegisterShowController(RegisterShow view) {
        this.view = view;
        this.view.getScheduleButton().addActionListener(this);
        this.view.getSavePlayButton().addActionListener(this);
    }

    /**
     * Recibe un evento que corresponde a un botón presionado. De acuerdo al botón se evalua la acción a tomar.
     * @param event
     */

    @Override
    public void actionPerformed(ActionEvent event) {
        Object selectedButtton = event.getSource();

        if (selectedButtton == view.getScheduleButton()) {
            //Botón para seleccionar la fecha de la obra.

            final JFrame dateFrame = new JFrame();
            view.getScheduleDate().setText("daet");
        } else if (selectedButtton == view.getSavePlayButton()) {
            //Botón para guardar los datos de la obra.

            JOptionPane.showMessageDialog(null, "Obra guardada!");
        }
    }
}
