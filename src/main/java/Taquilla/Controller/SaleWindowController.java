package Taquilla.Controller;

import Elements.Base.SerializedObject;
import Elements.Play;
import Elements.Show;
import Taquilla.Model.SaleWindowModel;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

public class SaleWindowController {
    private ArrayList<SerializedObject> plays;
    private ArrayList<? extends SerializedObject> shows;
    private SaleWindowModel saleWindowModel;

    public SaleWindowController() {
        saleWindowModel = new SaleWindowModel();
    }

    public List<Play> getPlays(){
        return saleWindowModel.getPlays();
    }

    public TableModel getShows(Play play){
        String[] header = {"id", "Play", "Time"};
        Show[] shows = saleWindowModel.getShowsFromPlay(play).toArray(new Show[0]);
        String[][] data = new String[shows.length][3];

        for(int i = 0; i<shows.length; i++){
            data[i][0] = Integer.toString(shows[i].getId());
            data[i][1] = shows[i].getPlay().getName();
            data[i][2] = shows[i].getTime().toString();
        }

        return new DefaultTableModel(data, header);
    }

}
