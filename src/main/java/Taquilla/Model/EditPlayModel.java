package Taquilla.Model;

import DataAccess.Implementations.PlayDao;
import DataAccess.Implementations.ShowDao;
import Elements.Play;
import Elements.Show;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EditPlayModel {

    private Play play;
    private List<Show> shows;

    public EditPlayModel() {
        this.play = null;
        this.shows = null;
    }

    public void setPlay(String playName) {
        this.play = new PlayDao().findPlayByName(playName);
    }

    public Play getPlay() {
        return play;
    }

    public void setShows(Play play) {
        this.shows = new ShowDao().findByPlay(play);
    }

    public List<Show> getShows() {
        return shows;
    }

    public List<Show> getActiveShows() {
        List<Show> activeShows = new ArrayList<Show>();

        for (Show show : shows) {
            if (show.isCancelled() == false) {
                activeShows.add(show);
            }
        }

        return activeShows;
    }

    public void cancelPlay() {
        for (Show show : shows) {
            show.setCancelled(true);
            new ShowDao().updateShow(show);
        }

        play.setCancelled(true);
        new PlayDao().updatePlay(play);
        play = null;
        shows = null;
    }

    public void cancelShow(LocalDate date, LocalTime time) {
        for (int i = 0; i < shows.size(); i++) {
            if (shows.get(i).getDate().equals(date) && shows.get(i).getTime().equals(time)) {
                shows.get(i).setCancelled(true);
                new ShowDao().updateShow(shows.get(i));
                break;
            }
        }
    }

    public boolean checkDisponibility(LocalDate date, LocalTime time) {
        ShowDao dao = new ShowDao();
        List<Show> showsToCheck = dao.findByDate(date);

        for (Show show : showsToCheck) {
            if (show.getTime().equals(time) && show.isCancelled() == false) {
                return false;
            }
        }

        return true;
    }

}
