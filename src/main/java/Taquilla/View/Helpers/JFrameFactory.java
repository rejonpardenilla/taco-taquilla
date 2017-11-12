package Taquilla.View.Helpers;

import javax.swing.*;
import java.awt.*;

public class JFrameFactory {
    public static JFrame createFrame() {
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(700, 450));
        frame.setLocation(new Point(400, 200));
        return frame;
    }
}
