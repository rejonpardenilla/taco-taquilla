package Taquilla.Controller;

import DataAccess.Implementations.ShowDao;
import Elements.Show;

import java.time.LocalDate;
import java.util.List;

public class ShowListController {
    LocalDate today = LocalDate.now();
    ShowDao showDao = new ShowDao();

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


}
