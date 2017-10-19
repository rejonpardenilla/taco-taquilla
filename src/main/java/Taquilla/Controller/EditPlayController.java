package Taquilla.Controller;

import Taquilla.Views.EditPlayView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditPlayController implements ActionListener {
    private final EditPlayView view;

    public EditPlayController(EditPlayView view) {
        this.view = view;
        this.view.getFindPlayButton().addActionListener(this);
        this.view.getSaveButton().addActionListener(this);
        this.view.getCancelButton().addActionListener(this);
        this.view.getCancelledPlaysButton().addActionListener(this);
        this.view.getCancelledShowsButton().addActionListener(this);
        this.view.getDeleteShowButton().addActionListener(this);
        this.view.getRescheduleShowButton().addActionListener(this);
        this.view.getDeletePlayButton().addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent event) {
        Object selectedButton = event.getSource();

        if (selectedButton == view.getFindPlayButton()) {

        } else if (selectedButton == view.getDeletePlayButton()) {

        } else if (selectedButton == view.getCancelledPlaysButton()) {

        } else if (selectedButton == view.getCancelledShowsButton()) {

        } else if (selectedButton == view.getDeleteShowButton()) {

        } else if (selectedButton == view.getRescheduleShowButton()) {

        } else if (selectedButton == view.getSaveButton()) {

        } else if (selectedButton == view.getCancelButton()) {

        }
    }
}
