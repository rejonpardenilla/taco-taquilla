package Elements;

import DataAccess.Implementations.ShowActorDao;
import Elements.Base.DataAccessMethods;
import Elements.Base.SerializedObject;

import java.sql.SQLException;

public class ShowActor extends SerializedObject implements DataAccessMethods {

    private int showId;
    private int actorId;

    public int getShowId() {
        return showId;
    }

    public int getActorId() {
        return actorId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    @Override
    public SerializedObject save() throws SQLException {
        return null;
    }
}
