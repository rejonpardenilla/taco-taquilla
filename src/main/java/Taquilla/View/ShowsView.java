package Taquilla.View;

import DataAccess.Implementations.ShowDao;
import Elements.Show;
import Taquilla.View.Helpers.GUI;
import Taquilla.View.Helpers.JFrameHelper;

import javax.swing.*;
import java.util.ArrayList;

public class ShowsView {
    public GUI gui;

    public ShowsView() {
        JFrame frame = JFrameHelper.createFrame();
        gui = JFrameHelper.createCenteredGUI("Shows");
        JComboBox<String> startDateCombo = new JComboBox<>();
        addDatesToComboBox(startDateCombo);
        gui.addLabeledField("startDateCombo", "Start date: ", startDateCombo);

        JComboBox<String> endDateCombo = new JComboBox<>();
        addDatesToComboBox(endDateCombo);
        gui.addLabeledField("endDateCombo", "End date: ", endDateCombo);

        JFrameHelper.addSeparator(gui, 200);

        addShowsDetails(gui);

        JFrameHelper.showFrameAndGui(frame, gui);
    }

    public static void main(String[] args) {
        new ShowsView();
    }

    private static void addDatesToComboBox(JComboBox<String> comboBox) {
        ShowDao showDao = new ShowDao();
        ArrayList<Show> shows = new ArrayList<>(showDao.findAll());
        ArrayList<String> dates = new ArrayList<>();
        for (Show show : shows) {
            String dateText = show.getDate().toString();
            if (dates.isEmpty()) {
                comboBox.addItem(dateText);
                dates.add(dateText);
            } else {
                Boolean hasDate = false;
                for (String date : dates) {
                    if (dateText.equals(date)) {
                        hasDate = true;
                        break;
                    }
                }
                if (!hasDate) {
                    comboBox.addItem(dateText);
                    dates.add(dateText);
                }
            }
        }
    }

    private static void addShowsDetails(GUI gui) {
        ShowDao showDao = new ShowDao();
        ArrayList<Show> shows = new ArrayList<>(showDao.findAll());
        for (Show show : shows) {
            gui.add(new JTextArea(show.getPlay().getName()));
            JFrameHelper.addSeparator(gui, 5);
            gui.add(new JTextArea(show.getDate().toString()));
            JFrameHelper.addSeparator(gui, 5);
            gui.add(new JTextArea(show.getTime().toString()));
            JFrameHelper.addSeparator(gui, 200);
        }
    }
}
