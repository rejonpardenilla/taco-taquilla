package Taquilla.Model;

import Elements.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseModel {
    private Person client;
    private Purchase purchase;
    private ArrayList<Ticket> tickets;

    private void processPurchase(Show show, List<Seat> seats, Person client){

        purchase = new Purchase();

        try {
            //client = client.save?
            client.save();

            purchase.setClient(client);
            purchase.setTotal(BigDecimal.valueOf(0));

            this.tickets = new ArrayList<>();

            for (Seat seat : seats) {
                Seating seating = generateSeating(show, seat);
                //seating = seating.save?
                seating.save();
                Ticket ticket = generateTicket(seating);
                //new total = total + ticket price
                purchase.setTotal(purchase.getTotal().add(ticket.getPrice()));
                this.tickets.add(ticket);
            }
            //purchase = purchase.save?
            purchase.save();
            for (Ticket ticket : tickets){
                ticket.setPurchase(purchase);
                //ticket = ticket.save?
                ticket.save();
            }

        } catch (SQLException e){
            e.printStackTrace();
        }


    }

    private Seating generateSeating(Show show, Seat seat){
        Seating seating = new Seating();
        seating.setShow(show);
        seating.setSeat(seat);
        seating.setState("TAKEN");
        return seating;
    }

    private Ticket generateTicket(Seating seating){
        Ticket ticket = new Ticket();
        ticket.setSeating(seating);
        ticket.setReturned(false);
        BigDecimal zoneDiscount = BigDecimal.valueOf(seating.getSeat().getZone().getDiscountPercent()).movePointLeft(2);
        ticket.setPrice(seating.getShow().getPrice().multiply(zoneDiscount));
        return ticket;
    }

    public PurchaseModel(Show show, List<Seat> seats){
        this.client = new Person();
        this.client.setType("anonymous");
        processPurchase(show, seats,client);
    }

    public PurchaseModel(Show show, List<Seat> seats, Person client){
        processPurchase(show, seats, client);
    }

}
