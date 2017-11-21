package Taquilla.Controller;

import Elements.Person;
import Elements.Play;
import Elements.Show;
import Taquilla.Model.RegisterPlayModel;
import Taquilla.Views.RegisterPlayView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RegisterPlayController implements ActionListener {
    private final RegisterPlayView view;
    private final RegisterPlayModel model;
    private final DateTimeFormatter dateFormat;

    public RegisterPlayController(RegisterPlayView view, RegisterPlayModel model) {
        this.view = view;
        this.view.getDisponibilityButton().addActionListener(this);
        this.view.getSaveButton().addActionListener(this);
        this.view.getCancelButton().addActionListener(this);
        this.model = model;
        this.dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    private Person setResponsible() {
        Person responsible = new Person();

        responsible.setName(view.getResponsibleName().getText());
        responsible.setLastName(view.getResponsibleLastName().getText());
        responsible.setPhone(view.getResponsibleTelephone().getText());
        responsible.setPhoneAlt(view.getResponsibleTelephoneAlt().getText());
        responsible.setEmail(view.getResponsibleEmail().getText());
        responsible.setType("Responsable");

        return responsible;
    }

    private Play setPlay(Person responsible) {
        Play play = new Play();

        play.setName(view.getPlayName().getText());
        play.setResponsible(responsible);
        play.setDescription(view.getPlayDescription().getText());
        play.setActors(setActors(view.getPlayActors().getText()));
        play.setCancelled(false);
        play.setComments(view.getPlayComments().getText());

        return play;
    }

    //Separa el String que representa a los actores en actores individuales tipo Person.

    private List<Person> setActors(String actorsTextArea) {
        String[] splitedActors = actorsTextArea.split("\n");
        List<Person> actors = new ArrayList<Person>();

        for (int i = 0; i < splitedActors.length; i++) {
            actors.add(setActor(splitedActors[i]));
        }

        return actors;
    }

    //Separa el String que representa el nombre completo del actor en tipo Person.

    private Person setActor(String actorFullName) {
        String[] actorName = actorFullName.split(" ");
        Person actor = new Person();

        actor.setName(actorName[0]);
        actor.setLastName(actorName[1]);
        actor.setType("Actor");

        return actor;
    }

    private List<Show> setShows(Play play) {
        List<Show> shows = new ArrayList<Show>();
        int numberOfShows = view.getTableModel().getRowCount();
        final int DATE_COLUMN = 0;
        final int TIME_COLUMN = 1;

        for (int rows = 0; rows < numberOfShows; rows++) {
            Show show = new Show();

            String tableDate = view.getTableModel().getValueAt(rows, DATE_COLUMN).toString();
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String tableTime[] = view.getTableModel().getValueAt(rows, TIME_COLUMN).toString().split(":");

            LocalDate date = LocalDate.parse(tableDate, dateFormat);
            LocalTime time = LocalTime.of(Integer.parseInt(tableTime[0]), Integer.parseInt(tableTime[1]));

            show.setDate(date);
            show.setTime(time);
            show.setPlay(play);
            show.setPrice(BigDecimal.valueOf(100.00));
            show.setCancelled(false);
            shows.add(show);
        }

        return shows;
    }

    //Recupera la información de la GUI para poder guardarla posteriormente.

    private void savePlay() {
        List<Show> totalShows = new ArrayList<Show>();
        Person responsible = new Person();
        Play play = new Play();

        responsible = setResponsible();
        play = setPlay(responsible);
        totalShows = setShows(play);

        for (Show show : totalShows) {
            RegisterPlayModel registerModel = new RegisterPlayModel();
            registerModel.registerShow(show);
        }

        if (totalShows.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se registraron funciones!");
        } else {
            JOptionPane.showMessageDialog(null, "Obra guardada con exito!");
            view.dispose();
        }
    }

    private void checkDisponibility() {
        int hours = (int) view.getScheduleHours().getModel().getValue();
        int minutes = (int) view.getScheduleMinutes().getModel().getValue();
        LocalTime time = LocalTime.of(hours, minutes);
        LocalDate date = LocalDate.parse(view.getScheduleDate().getText(), dateFormat);
        Show show = new Show();

        show.setDate(date);
        show.setTime(time);

        if ( (!showInTable(show)) && (model.checkDisponibility(show)) ) {
            JOptionPane.showMessageDialog(null, "Disponible!");
            fillShowsTable(show);
        } else {
            JOptionPane.showMessageDialog(null, "No disponible!");
        }
    }

    private boolean showInTable(Show show) {
        int numberOfShows = view.getTableModel().getRowCount();
        final int DATE_COLUMN = 0;
        final int TIME_COLUMN = 1;
        final int HOURS = 0;
        final int MINUTES = 1;

        for (int rows = 0; rows < numberOfShows; rows++) {
            String tableDate = view.getTableModel().getValueAt(rows, DATE_COLUMN).toString();
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String tableTime[] = view.getTableModel().getValueAt(rows, TIME_COLUMN).toString().split(":");

            LocalDate date = LocalDate.parse(tableDate, dateFormat);
            LocalTime time = LocalTime.of(Integer.parseInt(tableTime[HOURS]), Integer.parseInt(tableTime[MINUTES]));

            if (show.getDate().equals(date)) {
                if (show.getTime().equals(time))
                    return true;
            }
        }

        return false;
    }

    private void fillShowsTable(Show show) {
        Object showData[] = {show.getDate(), show.getTime()};

        view.getTableModel().addRow(showData);
    }

    private void cancelButton() {
        view.dispose();
    }

    /**
     * Recibe un evento que corresponde a un botón presionado. De acuerdo al botón se evalua la acción a tomar.
     * @param event: Corresponde al botón presionado.
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        Object selectedButtton = event.getSource();

        if (selectedButtton == view.getDisponibilityButton()) {
            checkDisponibility();
        } else if (selectedButtton == view.getSaveButton()) {
            savePlay();
        } else if (selectedButtton == view.getCancelButton()) {
            cancelButton();
        }
    }

}
