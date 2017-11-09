package DataAccess.Implementations;

import DataAccess.ConnectionFactory;
import Elements.ShowActor;

import java.sql.*;

public class ShowActorDao extends BaseDao<ShowActor> {

    @Override
    public ShowActor extractFromResultSet(ResultSet rs) throws SQLException {
        ShowActor showActor = new ShowActor();
        showActor.setShowId(rs.getInt("show"));
        showActor.setActorId(rs.getInt("actor"));

        return showActor;
    }

    public ShowActor insertShowActor(int showId, int actorId) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;

        String query = "INSERT INTO actor_list (show, actor) VALUES (?, ?)";
        statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        statement.setInt(1, showId);
        statement.setInt(2, actorId);

        int rowsAffected = statement.executeUpdate();
        if(rowsAffected == 0){
            throw new SQLException("No rows affected");
        }

        try(ResultSet generatedKeys = statement.getGeneratedKeys()){
            if (generatedKeys.next()){
                ShowActor id = new ShowActor();
                id.setShowId(generatedKeys.getInt("show"));
                id.setActorId(generatedKeys.getInt("actor"));

                return id;
            } else {
                throw new SQLException("No id obtained");
            }
        }
    }
}
