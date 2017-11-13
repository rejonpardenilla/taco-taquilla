package Taquilla.View.Helpers;

import com.sun.istack.internal.Nullable;

import javax.swing.*;
import java.awt.*;

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

    public static void showFrameAndGui(JFrame frame, GUI gui) {
        frame.setContentPane(gui);
        frame.setVisible(true);
    }
}
