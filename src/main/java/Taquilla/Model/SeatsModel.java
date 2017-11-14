package Taquilla.Model;

import DataAccess.Implementations.SeatDao;
import DataAccess.Implementations.SeatingDao;
import DataAccess.Implementations.ShowDao;
import DataAccess.Implementations.ZoneDao;
import Elements.Seat;
import Elements.Seating;
import Elements.Show;
import Elements.Zone;
import Taquilla.Auxiliary.SeatState;

import java.util.*;

public class SeatsModel {
    SeatDao seatdao = new SeatDao();
    SeatingDao seatingDao = new SeatingDao();
    String[] rowOrder = {"A", "B", "C", "D", "E", "F", "G", "H"};
    SeatState[][] grid;
    public SeatState[][] generateGrid(Show show){
        Collections.reverse(Arrays.asList(rowOrder));
        grid = new SeatState[8][20];
        ArrayList<Seat> seats = new ArrayList<>(seatdao.findAll());
        ArrayList<Seating> seatings = new ArrayList<Seating>(seatingDao.findByShow(show));
        for (Seat seat : seats){
            boolean taken = false;
            for (Seating seating : seatings){
                if (seating.getSeat().getId() == seat.getId()){
                    addToGrid(seat, seating);
                    taken = true;
                }
            }
            if (!taken){
                addToGrid(seat);
            }
        }
        return grid;
    }

    private void addToGrid(Seat seat, Seating seating){
        for (int i = 0; i < rowOrder.length; i++){
            if (Objects.equals(seat.getRow(), rowOrder[i])){
                grid[i][seat.getNumber()-1] = new SeatState(seat, seating);
            }
        }
    }

    private void addToGrid(Seat seat){
        for (int i = 0; i < rowOrder.length; i++){
            if (Objects.equals(seat.getRow(), rowOrder[i])){
                grid[i][seat.getNumber()-1] = new SeatState(seat);
            }
        }
    }

    public List<Zone> getAllZones(){
        ZoneDao zoneDao = new ZoneDao();
        return zoneDao.findAll();
    }

}
