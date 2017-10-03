package Taquilla.View;

import java.awt.*;
import javax.swing.*;

public class ZoneView {
    private static final int ROWS = 8;
    private static final int COLUMNS = 20;
    private static final char[] COLUMN_LETTERS = {'H', 'G', 'F', 'E', 'D', 'C', 'B', 'A'};
    private static final String WINDOW_DISPLAY_NAME = "Choose seats";
    private static final Dimension WINDOW_SIZE = new Dimension(1200, 800);
    private static final int WINDOW_LOCATION_X = 150;
    private static final int WINDOW_LOCATION_Y = 50;
    private Icon res = (UIManager.getIcon("OptionPane.errorIcon"));

    public ZoneView() {
        JPanel panel = new JPanel(new GridLayout(ROWS, COLUMNS));
        for (int row = 1; row <= ROWS; row++) {
            for (int column = 1; column <= COLUMNS; column++) {
                final JToggleButton button = new JToggleButton(COLUMN_LETTERS[row - 1] + "" + column);
                performButtonAction(panel, button);
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
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                ZoneView theaterJToggle = new ZoneView();
            }
        });
    }

    private void performButtonAction(JPanel panel, JToggleButton button) {
        button.addActionListener(actionEvent -> {
            AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
            boolean selected = abstractButton.getModel().isSelected();
            if (selected) {
                button.setIcon(res);
            } else {
                button.setIcon(null);
            }
        });
        panel.add(button);
    }
}
