package Taquilla.Controller;

import Elements.Show;
import Taquilla.Model.EditPlayModel;
import Taquilla.Views.EditPlayView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class EditPlayController implements ActionListener {
    private final EditPlayView view;
    private final EditPlayModel model;

    public EditPlayController(EditPlayView view, EditPlayModel model) {
        this.view = view;
        this.view.getFindPlayButton().addActionListener(this);
        this.view.getCancelButton().addActionListener(this);
        this.view.getCancelledPlaysButton().addActionListener(this);
        this.view.getCancelledShowsButton().addActionListener(this);
        this.view.getDeleteShowButton().addActionListener(this);
        this.view.getRescheduleShowButton().addActionListener(this);
        this.view.getDeletePlayButton().addActionListener(this);
        this.model = model;
    }

    private void findPlay() {
        String playName = view.getFindShowText().getText();

        cleanShowsTable();

        if (playName.equals("")) {
            JOptionPane.showMessageDialog(
                    null,
                    "Introduzca el nombre de una obra para buscar primero!",
                    "Error al buscar obra",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            model.setPlay(playName);

            if (model.getPlay() == null) {
                JOptionPane.showMessageDialog(null, "Obra no encontrada");
            } else {
                model.setShows(model.getPlay());
                fillShowsTable(model.getActiveShows());
                showPlayStatus();
            }
        }
    }

    private void showPlayStatus() {
        boolean playIsCancelled = model.getPlay().isCancelled();
        String playStatus = "";

        if (playIsCancelled == true) {
            playStatus = "Cancelada";
        } else {
            playStatus = "Activa";
        }

        view.getPlayStatusLabel().setText(playStatus);
    }

    private void cleanShowsTable() {
        int rows = view.getTableModel().getRowCount();
        if (rows > 0) {
            for (int i = rows-1; i >= 0; i--) {
                view.getTableModel().removeRow(i);
            }
        }
    }

    private void fillShowsTable(List<Show> shows) {
        for (Show show : shows) {
            Object showData[] = {show.getDate(), show.getTime()};

            view.getTableModel().addRow(showData);
        }
    }

    private void cancelShow() {
        final int NO_ROW_SELECTED = -1;

        //Si no hay una fila seleccionada, getSelectedRow() retorna -1.+
        int selectedRow = view.getShowsTable().getSelectedRow();

        if (selectedRow == NO_ROW_SELECTED) {
            JOptionPane.showMessageDialog(
                    null,
                    "Debe seleccionar una funcion primero!",
                    "Error al cancelar funcion",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            final int YES = 0;

            //confirmDelete será 0 si el usuario selecciona si, 1 si selecciona no.
            int confirmCancel = JOptionPane.showConfirmDialog(
                    null,
                    "Esta seguro de cancelar el show?",
                    "Cancelar show?",
                    JOptionPane.YES_NO_OPTION);

            if (confirmCancel == YES) {
                final int DATE_COLUMN = 0;
                final int TIME_COLUMN = 1;
                final int HOURS = 0;
                final int MINUTES = 1;

                String tableDate = view.getTableModel().getValueAt(selectedRow, DATE_COLUMN).toString();
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String tableTime[] = view.getTableModel().getValueAt(selectedRow, TIME_COLUMN).toString().split(":");

                LocalDate date = LocalDate.parse(tableDate, dateFormat);
                LocalTime time = LocalTime.of(Integer.parseInt(tableTime[HOURS]), Integer.parseInt(tableTime[MINUTES]));

                model.cancelShow(date, time);
                view.getTableModel().removeRow(selectedRow);
            }
        }
    }

    private void rescheduleShow() {
        final int NO_ROW_SELECTED = -1;

        //Si no hay una fila seleccionada, getSelectedRow() retorna -1.
        int selectedRow = view.getShowsTable().getSelectedRow();

        if (selectedRow == NO_ROW_SELECTED) {
            JOptionPane.showMessageDialog(
                    null,
                    "Debe seleccionar una funcion primero!",
                    "Error al recalendarizar funcion",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            final int DATE_COLUMN = 0;
            final int TIME_COLUMN = 1;
            final int HOURS = 0;
            final int MINUTES = 1;
            String newDateString = "";
            String newTimeString = "";

            try {
                newDateString = JOptionPane.showInputDialog("Fecha (dd-MM-yyyy):");
                newTimeString = JOptionPane.showInputDialog("Hora (mm:HH)");

                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String newTimeSplit[] = newTimeString.split(":");

                LocalDate newDate = LocalDate.parse(newDateString, dateFormat);
                LocalTime newTime = LocalTime.of(Integer.parseInt(newTimeSplit[HOURS]), Integer.parseInt(newTimeSplit[MINUTES]));


                if (model.checkDisponibility(newDate, newTime)) {
                    String oldDateString = view.getTableModel().getValueAt(selectedRow, DATE_COLUMN).toString();
                    DateTimeFormatter tableDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String oldTimeSplit[] = view.getTableModel().getValueAt(selectedRow, TIME_COLUMN).toString().split(":");

                    LocalDate oldDate = LocalDate.parse(oldDateString, tableDateFormat);
                    LocalTime oldTime = LocalTime.of(Integer.parseInt(oldTimeSplit[HOURS]), Integer.parseInt(oldTimeSplit[MINUTES]));

                    if (model.rescheduleShow(oldDate, oldTime, newDate, newTime)) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Disponible!\nRecalendarización exitosa!",
                                "Recalendarizacion exitosa",
                                JOptionPane.WARNING_MESSAGE);

                        cleanShowsTable();
                        fillShowsTable(model.getActiveShows());
                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "Error al recalendarizar la obra",
                                "Error",
                                JOptionPane.WARNING_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Horario no disponible!",
                            "Recalendarización fallida.",
                            JOptionPane.WARNING_MESSAGE);
                }
            } catch (DateTimeParseException e1) {
                JOptionPane.showMessageDialog(
                        null,
                        "La fecha u hora no son correctas!",
                        "Error en el formato de la fecha/hora",
                        JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException e2) {
                JOptionPane.showMessageDialog(
                        null,
                        "La hora no se escribio correctamente!",
                        "Error en el formato de la hora",
                        JOptionPane.ERROR_MESSAGE);
            } catch (NullPointerException e3) {
                JOptionPane.showMessageDialog(
                        null,
                        "Los campos de fecha y/u hora estan vacios",
                        "Error en el formato de la fecha/hora",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void cancelPlay() {
        if (model.getPlay() == null) {
            JOptionPane.showMessageDialog(
                    null,
                    "Debe buscar una obra primero!",
                    "Error al buscar obra",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            final int YES = 0;

            //confirmDelete será 0 si el usuario selecciona si, 1 si selecciona no.
            int confirmCancel = JOptionPane.showConfirmDialog(
                    null,
                    "Esta seguro de eliminar la obra?\nTodas las funciones se cancelaran.",
                    "Eliminar obra?",
                    JOptionPane.YES_NO_OPTION);

            if (confirmCancel == YES) {
                model.cancelPlay();
                JOptionPane.showMessageDialog(null, "Cancelacion de obra exitosa!");
                cleanShowsTable();
                view.getFindShowText().setText("");
            }
        }
    }

    private void cancelButton() {
        view.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object selectedButton = event.getSource();

        if (selectedButton == view.getFindPlayButton()) {
            findPlay();
        } else if (selectedButton == view.getDeletePlayButton()) {
            cancelPlay();
        } else if (selectedButton == view.getCancelledPlaysButton()) {
            //Registro de obras canceladas.
        } else if (selectedButton == view.getCancelledShowsButton()) {
            //Registro de shows cancelados.
        } else if (selectedButton == view.getDeleteShowButton()) {
            cancelShow();
        } else if (selectedButton == view.getRescheduleShowButton()) {
            rescheduleShow();
        } else if (selectedButton == view.getCancelButton()) {
            cancelButton();
        }
    }
}
