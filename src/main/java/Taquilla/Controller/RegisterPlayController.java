package Taquilla.Controller;

import Elements.Person;
import Elements.Play;
import Elements.Show;
import Taquilla.Model.RegisterPlayModel;
import Taquilla.Views.RegisterPlay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RegisterPlayController implements ActionListener {
    private final RegisterPlay view;
    private final RegisterPlayModel model;
    private final DateTimeFormatter dateFormat;
    private final DateTimeFormatter timeFormat;

    public RegisterPlayController(RegisterPlay view) {
        this.view = view;
        this.view.getDisponibilityButton().addActionListener(this);
        this.view.getSaveButton().addActionListener(this);
        this.view.getCancelButton().addActionListener(this);
        this.model = new RegisterPlayModel();
        this.dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.timeFormat = DateTimeFormatter.ofPattern("HH:mm");
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
        play.setDescription(view.getPlayDescription().getText());
        play.setActors(setActors(view.getPlayActors().getText()));
        play.setComments(view.getPlayComments().getText());
        play.setResponsible(responsible);

        return play;
    }

    //Separa el String que representa a los actores en actores individuales tipo Person.

    private List<Person> setActors(String actorsTextArea) {
        String[] splitedActors = actorsTextArea.split("\n");
        List<Person> actors = new ArrayList<Person>();

        for (int i = 0; i < splitedActors.length; i++) {
            actors.add(setActor(splitedActors[i]));
            System.out.println(splitedActors[i]);
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

        return shows;
    }

    private Show setShow(String date, String time) {
        Show show = new Show();

        return show;
    }

    //Recupera la información de la GUI para poder guardarla posteriormente.

    private void savePlay() {
        List<Show> totalShows = new ArrayList<Show>();
        Person responsible = new Person();
        Play play = new Play();

        responsible = setResponsible();
        play = setPlay(responsible);
        totalShows = setShows(play);
    }

    private void checkDisponibility() {
        int hours = (int) view.getScheduleHours().getModel().getValue();
        int minutes = (int) view.getScheduleMinutes().getModel().getValue();
        LocalDate time = (LocalDate) timeFormat.parse(hours + ":" + minutes);
        LocalDate date = (LocalDate) dateFormat.parse(view.getScheduleDate().getText());

    }

    private void cancelButton() {
        System.exit(0);
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
