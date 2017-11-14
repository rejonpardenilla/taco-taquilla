package Taquilla.Model;

import DataAccess.Implementations.PlayDao;
import DataAccess.Implementations.SeatDao;
import DataAccess.Implementations.SeatingDao;
import DataAccess.Implementations.ShowDao;
import Elements.Play;
import Elements.Show;

import java.time.LocalDate;
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
        return playDao.findByDate(LocalDate.now());
    }

    public List<Show> getShowsFromPlay(Play play){
        return showDao.findByPlay(play);
    }
}
