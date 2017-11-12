package Taquilla.View;

import DataAccess.Implementations.SeatDao;
import DataAccess.Implementations.SeatingDao;
import DataAccess.Implementations.ShowDao;
import DataAccess.Implementations.ZoneDao;
import Elements.Seat;
import Elements.Seating;
import Elements.Show;
import Elements.Zone;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.*;

public class ZoneView {
    ZoneDao zoneDao = new ZoneDao();
    SeatDao seatDao = new SeatDao();
    SeatingDao seatingDao = new SeatingDao();
    Zone zone = zoneDao.findById(1);
    List<Seat> seats = seatDao.findAll();

    private ArrayList<String> rowLetters = getRowLetters(seats);
    private int rows = rowLetters.size();
    private int columns = countColumns(seats);
    private static final String WINDOW_DISPLAY_NAME = "Choose seats";
    private static final Dimension WINDOW_SIZE = new Dimension(1200, 800);
    private static final int WINDOW_LOCATION_X = 150;
    private static final int WINDOW_LOCATION_Y = 50;
    private Icon seatOccupiedIcon = (UIManager.getIcon("OptionPane.errorIcon"));
    private ArrayList<String> seatNames = new ArrayList<>();

    // ZoneView(Show show)
    public ZoneView(Show show) {
        ArrayList<String> seatingNames = getSeatingNames(show);
        JPanel panel = new JPanel(new GridLayout(rows, columns));
        for (int row = 1; row <= rows; row++) {
            for (int column = 1; column <= columns; column++) {
                String seatName = rowLetters.get(row - 1) + column;
                seatNames.add(seatName);
                final JToggleButton button = new JToggleButton(seatName);
                button.setFont(new Font("Source Sans Pro", Font.BOLD, 24));
                performButtonAction(panel, button);
                if (seatingNames != null) {
                    for (String seatingName : seatingNames) {
                        if (seatingName.equals(seatName)) {
                            selectButton(button);
                        }
                    }
                }
            }
        }
        final JFrame frame = new JFrame(WINDOW_DISPLAY_NAME);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setSize(WINDOW_SIZE);
        frame.setLocation(WINDOW_LOCATION_X, WINDOW_LOCATION_Y);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        ShowDao showDao = new ShowDao();
        Show show = showDao.findById(1);
        EventQueue.invokeLater(() -> {
            ZoneView theaterJToggle = new ZoneView(show);
        });
    }

    private void performButtonAction(JPanel panel, JToggleButton button) {
        String buttonText = button.getText();
        button.addActionListener(actionEvent -> {
            AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
            boolean selected = abstractButton.getModel().isSelected();
            if (selected) {
                selectButton(button);
            } else {
                button.setIcon(null);
                button.setText(buttonText);
            }
        });
        panel.add(button);
    }

    private static ArrayList<String> getRowLetters(List<Seat> seats) {
        ArrayList<String> rowLetters = new ArrayList<>();
        seats.forEach(seat -> {
            if (rowLetters.isEmpty()) {
                rowLetters.add(seat.getRow());
            } else {
                if (!rowLetters.get(rowLetters.size() - 1).equals(seat.getRow())) {
                    rowLetters.add(seat.getRow());
                }
            }
        });
        return rowLetters;
    }

    private static int countColumns(List<Seat> seats) {
        ArrayList<Integer> columns = new ArrayList<>();
        seats.forEach(seat -> columns.add(seat.getNumber()));
        Collections.sort(columns);
        return columns.get(columns.size() - 1);
    }

    private void selectButton(JToggleButton button) {
        button.setSelected(true);
        button.setIcon(seatOccupiedIcon);
        button.setText(null);
    }

    private ArrayList<String> getSeatingNames(Show show) {
        if (show != null) {
            ArrayList<Seating> seatings = new ArrayList<>(seatingDao.findByShow(show));
            ArrayList<String> seatingNames = new ArrayList<>();
            for (Seating seating : seatings) {
                Seat seat = seating.getSeat();
                String seatName = seat.getRow() + Integer.toString(seat.getNumber());
                if (seating.getState().equals("TAKEN")) seatingNames.add(seatName);
            }
            return seatingNames;
        } else {
            return null;
        }
    }
}