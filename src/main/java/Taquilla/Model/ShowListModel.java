package Taquilla.Model;

import DataAccess.Implementations.ShowDao;
import Elements.Show;

import java.time.LocalDate;
import java.util.List;

public class ShowListModel {

    private List<Show> getDayShows(){
        return getDayShows(0);
    }

    private List<Show> getDayShows(int daysFromToday){
        if(daysFromToday == 0){
            return showDao.findByDate(today);
        }
        else{
            return showDao.findByDate(today.plusDays(daysFromToday));
        }

    }

    private LocalDate today = LocalDate.now();
    private ShowDao showDao = new ShowDao();

}
