package Taquilla.Model;

import DataAccess.Implementations.ShowDao;
import Elements.Person;
import Elements.Seat;
import Elements.Show;

import java.util.ArrayList;

public class PurchaseModel {
    ArrayList<Seat> availableSeats, selectedSeats;
    Person client;

    ShowDao showDao;
    ArrayList<Show> shows;

    public PurchaseModel(){
        showDao = new ShowDao();
    }

    public ArrayList<Show> loadShows(){
        shows = new ArrayList<>(showDao.findAll());
        return shows;
    }


}
