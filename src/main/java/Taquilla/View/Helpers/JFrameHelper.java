package Taquilla.View.Helpers;

import com.sun.istack.internal.Nullable;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class JFrameHelper {
    public static JFrame createFrame() {
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(700, 450));
        frame.setLocation(new Point(400, 200));
        return frame;
    }

    public static void showMessageAndClose(JFrame frame, Component component, String message) {
        JOptionPane.showMessageDialog(component, message);
        frame.dispose();
    }

    public static void showMessageAndClose(JFrame frame, String message) {
        showMessageAndClose(frame, null, message);
    }

    public static GUI createCenteredGUI(String title) {
        return new GUI(new FlowLayout(FlowLayout.CENTER), title);
    }

    public static GUI createLeftAlignedGUI(String title) { return new GUI(new FlowLayout(FlowLayout.LEFT), title); }

    public static void showFrameAndGui(JFrame frame, GUI gui) {
        frame.setContentPane(gui);
        frame.setVisible(true);
    }

    public static void addSeparator(GUI gui, int size) {
        String separator = "";
        for (int i = 0; i < size; i++) {
            separator = separator.concat(" ");
        }
        gui.add(new JLabel(separator));
    }

    public static String formatDate(Date date) {
        DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
        return df.format(date);
    }
}
