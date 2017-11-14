package Taquilla.Model;

import DataAccess.Implementations.PlayDao;
import DataAccess.Implementations.SeatDao;
import DataAccess.Implementations.SeatingDao;
import DataAccess.Implementations.ShowDao;
import Elements.Play;
import Elements.Show;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SaleWindowModel {
    private PlayDao playDao;
    private ShowDao showDao;
    private SeatDao seatDao;

    public SaleWindowModel() {
        playDao = new PlayDao();
        showDao = new ShowDao();
        seatDao = new SeatDao();
    }

    public List<Play> getPlays(){
        ArrayList<Play> shows= new ArrayList<>(playDao.findByDate(LocalDate.now()));
        ArrayList<Play> plays = new ArrayList<>();
        for (int i = 0; i < shows.size(); i++){
            boolean repeat = false;
            for (int j = 0; j < plays.size(); j++){
                if (plays.get(j).getId() == shows.get(i).getId()) repeat = true;
            }
            if (!repeat) plays.add(shows.get(i));
        }
        return plays;
    }

    public List<Show> getShowsFromPlay(Play play){
        return showDao.findByPlay(play);
    }
}
