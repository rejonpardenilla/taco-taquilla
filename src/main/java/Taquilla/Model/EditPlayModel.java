package Taquilla.Model;

import DataAccess.Implementations.*;
import Elements.*;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EditPlayModel {

    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String SHOW_SEPARATOR = "~";
    private static final String FILE_HEADER = "play, date, time, row, number, price, client_name, client_last_name, play_total";

    private Play play;
    private List<Show> shows;

    public EditPlayModel() {
        this.play = null;
        this.shows = null;
    }

    private Seating getSeating(int seatingId) {
        SeatingDao dao = new SeatingDao();
        Seating seating = new Seating();

        seating = dao.findById(seatingId);
        seating.setSeat(getSeat(seating.getSeat().getId()));
        seating.setShow(getShow(seating.getShow().getId()));

        return seating;
    }

    private Show getShow(int showId) {
        ShowDao dao = new ShowDao();
        Show show = new Show();

        show = dao.findById(showId);
        show.setPlay(play);

        return show;
    }

    private Seat getSeat(int seatId) {
        return new SeatDao().findById(seatId);
    }

    private Purchase getPurchase(int purchaseId) {
        PurchaseDao dao = new PurchaseDao();
        Purchase purchase = new Purchase();
        Person client = new Person();

        purchase = dao.findById(purchaseId);
        client = getClient(purchase.getClient().getId());
        purchase.setClient(client);

        return purchase;
    }

    private Person getClient(int clientId) {
        return new PersonDao().findById(clientId);
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
        List<Show> showsToCheck = new ShowDao().findByDate(date);

        for (Show show : showsToCheck) {
            if (show.getTime().equals(time) && show.isCancelled() == false) {
                return false;
            }
        }

        return true;
    }

    public boolean rescheduleShow(LocalDate oldDate, LocalTime oldTime, LocalDate newDate, LocalTime newTime) {
        for (int i = 0; i < shows.size(); i++) {
            if (shows.get(i).getDate().equals(oldDate) && shows.get(i).getTime().equals(oldTime)) {

                shows.get(i).setDate(newDate);
                shows.get(i).setTime(newTime);
                new ShowDao().updateShow(shows.get(i));

                return true;
            }
        }

        return false;
    }

    public boolean generateReport() {
        if (play == null) {
            return false;
        }

        List<Ticket> tickets = new TicketDao().findByPlay(play);
        String playName = play.getName();
        String showDate = "";
        String showTime = "";
        String seatRow = "";
        String seatNumber = "";
        String seatPrice = "";
        String clientName = "";
        String clientLastName = "";
        BigDecimal playTotal = new BigDecimal(0);
        FileWriter fileWriter = null;

        for (int i = 0; i < tickets.size(); i++) {
            int seatingId = tickets.get(i).getSeating().getId();
            int purchaseId = tickets.get(i).getPurchase().getId();
            tickets.get(i).setSeating(getSeating(seatingId));
            tickets.get(i).setPurchase(getPurchase(purchaseId));
        }

        try {
            fileWriter = new FileWriter("reportes/reporte - " + playName + ".csv");
            fileWriter.append(FILE_HEADER);
            fileWriter.append(NEW_LINE_SEPARATOR);


            for (Ticket ticket : tickets) {
                boolean cancelledShow = ticket.getSeating().getShow().isCancelled();

                if (cancelledShow) {
                    showDate = ticket.getSeating().getShow().getDate().toString();
                    showTime = ticket.getSeating().getShow().getTime().toString();
                    seatRow = ticket.getSeating().getSeat().getRow();
                    seatNumber = String.valueOf(ticket.getSeating().getSeat().getNumber());
                    seatPrice = String.valueOf(ticket.getPrice());
                    clientName = ticket.getPurchase().getClient().getName();
                    clientLastName = ticket.getPurchase().getClient().getLastName();
                    playTotal = playTotal.add(ticket.getPrice());

                    fileWriter.append(playName);
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(showDate);
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(showTime);
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(seatRow);
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(seatNumber);
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(seatPrice);
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(clientName);
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(clientLastName);
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(SHOW_SEPARATOR);
                    fileWriter.append(NEW_LINE_SEPARATOR);
                }
            }

            String showEnd = "~, ~, ~, ~, ~, ~, ~, ~, " + playTotal;
            fileWriter.append(showEnd);
            fileWriter.append(NEW_LINE_SEPARATOR);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }
        }

        return false;
    }

}
