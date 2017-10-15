package Taquilla.Controller;

import DataAccess.Implementations.ShowDao;
import Elements.Person;
import Elements.Seat;
import Elements.Show;

import java.util.ArrayList;

public class PurchaseController {
    private ArrayList<Seat> availableSeats, selectedSeats;
    private Person client;
    private ShowDao showDao;
    private ArrayList<Show> shows;

    public PurchaseController(){
        showDao = new ShowDao();
    }

    public ArrayList<Show> loadShows(){
        shows = new ArrayList<>(showDao.findAll());
        return shows;
    }
}
