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

            show.setId(dao.insertShow(show, playId));
            registerActors((ArrayList<Person>) show.getPlay().getActors());
            registerShowActor(show);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int registerPlay(Play play) throws SQLException {
        int playId = 0;
        int responsibleId = registerResponsible(play.getResponsible());
        PlayDao dao = new PlayDao();

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

    public void registerActors(ArrayList<Person> actors) throws SQLException {
        PersonDao personDao = new PersonDao();

        for (int i = 0; i < actors.size(); i++) {
            String name = actors.get(i).getName();
            String lastName = actors.get(i).getLastName();
            int actorId = 0;

            if (personDao.isRegistered(name, lastName)) {
                actorId = personDao.findByName(name, lastName).getId();
            } else {
                actorId = personDao.insertPerson(actors.get(i));
            }

            actors.get(i).setId(actorId);
        }
    }

    public void registerShowActor(Show show) throws SQLException {
        for(Person actor : show.getPlay().getActors()) {
            new ShowDao().insertActor(show.getId(), actor.getId());
        }
    }
}
