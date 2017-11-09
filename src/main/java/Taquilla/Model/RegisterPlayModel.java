package Taquilla.Model;

import DataAccess.Implementations.PersonDao;
import DataAccess.Implementations.PlayDao;
import DataAccess.Implementations.ShowDao;
import Elements.Person;
import Elements.Play;
import Elements.Show;

import java.sql.SQLException;
import java.util.ArrayList;

public class RegisterPlayModel {

    private void saveActors(Play play) throws SQLException {

        //Registrando los actores en la tabla actor_list

        int numberOfActors = play.getActors().size();
        for (int i = 0; i < numberOfActors; i++) {
            Person actor = new Person();
            PersonDao daoActor = new PersonDao();
            int idActor = 0;

            actor.setName(play.getActors().get(i).getName());
            actor.setLastName(play.getActors().get(i).getLastName());
            actor.setPhone("");
            actor.setPhoneAlt("");
            actor.setEmail("");
            actor.setType("Actor");

            if (daoActor.isRegistered(actor.getName(), actor.getLastName())) {
                idActor = daoActor.findByName(actor.getName(), actor.getLastName()).getId();
            } else {
                idActor = daoActor.insertPerson(actor);
            }
        }
    }

    public boolean checkDisponibility(Show show) {
        ArrayList<Show> dateShows = new ArrayList<Show>();
        ShowDao query = new ShowDao();

        dateShows = (ArrayList<Show>) query.findByDate(show.getDate());

        for (Show checkedShow : dateShows) {
            if (checkedShow.getDate().equals(show.getDate())) {
                if (checkedShow.getTime().equals(show.getTime())) {
                    if (!checkedShow.isCancelled())
                        return false;
                }
            }
        }

        return true;
    }

    public void registerShow(Show show) {
        try {
            int playId = registerPlay(show.getPlay());
            ShowDao dao = new ShowDao();
            dao.insertShow(show, playId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int registerPlay(Play play) throws SQLException {
        int playId = 0;
        int responsibleId = registerResponsible(play.getResponsible());
        PlayDao dao = new PlayDao();

        //Registrando la obra en la tabla play

        if (dao.isRegistered(play.getName())){
            playId = dao.findPlayByName(play.getName()).getId();
        } else {
            playId = dao.insertPlay(play, responsibleId);
        }

        return playId;
    }

    public int registerResponsible(Person responsible) throws SQLException {
        int responsibleId = 0;
        PersonDao dao = new PersonDao();

        if (dao.isRegistered(responsible.getName(), responsible.getLastName())) {
            responsibleId = dao.findByName(responsible.getName(), responsible.getLastName()).getId();
        } else {
            responsibleId = dao.insertPerson(responsible);
        }

        return responsibleId;
    }
}
