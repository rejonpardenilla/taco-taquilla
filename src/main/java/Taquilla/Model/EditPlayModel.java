package Taquilla.Model;

import Elements.Play;
import Elements.Show;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class EditPlayModel {

    public Play findPlayByName(String playName) {
        Play play = new Play();

        return play;
    }

    public void cancelPlay(Play play) {

    }

    public ArrayList<Show> findShowsByPlay(Play play) {
        ArrayList<Show> shows = null;

        return shows;
    }

    public void cancelShow(LocalDate date, LocalTime time, Play play) {

    }

}
